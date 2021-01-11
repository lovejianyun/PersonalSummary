package com.qijy.redis.pipe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterInfoCache;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSlotBasedConnectionHandler;

/**
 * 项目名称：monitor2in1-cluster    
 * 类名称：RedisClusterUtil    
 * 类描述： redis集群链接工具类  
 * 创建人：个人姓名   
 * 创建时间：2016-8-29 上午10:44:25    
 * 修改人：hl  
 * 修改时间：2016-8-29 上午10:44:25    
 * 修改备注：    
 * @version     
 *
 */
public enum RedisClusterWithPipeline {
	Cluster;
	
//	private static final Logger LOG = Logger.getLogger(RedisClusterWithPipeline.class);

	private static int MAX_ACTIVE = 10000;
    private static int MAX_IDLE = 100;
    private static int MIN_IDLE = 10;
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 1000*10;
    private static int MAXREDIRECTIONS = 100;
    private static boolean TEST_ON_BORROW = true;
    
	//配置文件
//	private MonConfig conf = MonConfig.getInstance();
	
    private  JedisPoolConfig config = null;
    //Redis集群的节点集合
 	private Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
 	//redis 集群客户端
 	private JedisCluster jedisCluster = null;
    
 	
 	public void init() {
 		initRedisConfig();
 		initConnectionHandler();
 	}
 	
 	//集群主节点和从节点连接map
 	private Map<JedisPool, List<JedisPool>> masterAndSlaveMap = new HashMap<>();
 	

 	public void initRedisConfig(){
 		try {
 			config = new JedisPoolConfig();
        	//初始化redis集群配置
            config.setMaxIdle(MAX_IDLE);
            config.setMinIdle(MIN_IDLE);
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            
            // Redis集群的节点集合
            String redisStr = "192.169.2.24:2000|192.169.2.24:2001|192.169.2.24:2002|192.169.2.24:2003|192.169.2.24:2004|192.169.2.24:2005";
//            if(StringUtils.isNullOrEmpty(redisStr)){
//            	LOG.error("redis 配置错误！INFO： "+redisStr);
//            }
            
            String[] redisInfo = redisStr.trim().split("\\|");
            for(String ipAndPortStr:redisInfo){
            	String[] ipAndPort = ipAndPortStr.split(":");
            	try {
            		jedisClusterNodes.add(new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
				} catch (Exception e) {
            		e.printStackTrace();
//	            	LOG.error("redis 配置错误！INFO： "+ipAndPortStr);
				}
            }
        } catch (Exception e) {
 			e.printStackTrace();
//        	LOG.error("设置redis配置出错！",e);
        }   	
 	}


	/**
     * 方法名称:main
     * 方法描述: 获取集群客户端  
     * @param
     * @return void
     * 创建人： hl
     * 创建时间：2016-8-29 上午10:51:23
     */
     public JedisCluster getJedisClusterClient(){
    	if(null != jedisCluster){
    	   return jedisCluster;
    	}
    	//获取redis客户端
    	try {
    		if(config == null) {
    			init();
    		}
    		//初始化redis配置失败，重新初始化
    		if(jedisClusterNodes == null || jedisClusterNodes.isEmpty()){
    			initRedisConfig();
    		}
    		
        	jedisCluster = new JedisCluster(jedisClusterNodes, TIMEOUT, MAXREDIRECTIONS, config);

        	
		} catch (Exception e) {
			//关闭异常链接
			if(null != jedisCluster){

				try {
					jedisCluster.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
			jedisCluster = null;
			e.printStackTrace();
		}
    	 
    	return jedisCluster;
     }
     
     /**
      * 获取主节点和从节点map
      * @param: @return
      * @param: @throws Exception      
      * @return: Map<JedisPool,List<JedisPool>>      
      * @throws
      */
     public Map<JedisPool,List<JedisPool>> masterAndSlave() throws Exception{
 		JedisCluster edis = null;
 		try{
 			edis = getJedisClusterClient(); 
 			if(null == edis){
 				throw new SQLException("获取Redis连接失败");
 			}
 			Map<String, JedisPool> map =  edis.getClusterNodes();
 			Map<JedisPool, List<JedisPool>> masterAndSlaveMap = new HashMap<>();
 			for(Entry<String,JedisPool> entry:map.entrySet()) {
 				JedisPool pool = entry.getValue();
 				Jedis jedis = pool.getResource();
 				String replicationInfo = jedis.info("Replication");
 				Map<String,String> infoMap = StringUtils.toMap(replicationInfo, "\r\n", ":");
 				
 				if(infoMap.get("role").equals("master")) {
 					int slaves =StringUtils.checkInteger(infoMap.get("connected_slaves"));
 					List<JedisPool> slavesList = new ArrayList<>();
 					for(int i=0;i<slaves;i++) {
 						String slaveInfo = infoMap.get("slave"+i);
 						Map<String,String> slaveInfoMap = StringUtils.toMap(slaveInfo, ",", "=");
 						String poolKey =slaveInfoMap.get("ip")+":"+slaveInfoMap.get("port");
 						slavesList.add(map.get(poolKey));
 					}
 					masterAndSlaveMap.put(pool, slavesList);
 				}
 			}
 			return masterAndSlaveMap;
 		}catch(Exception e){
 			throw e;
 		}
     }
     
     
     /**
      * 方法名称:resetClient
      * 方法描述: 重置客户端链接  
      * @return void
      * 创建人： hl
      * 创建时间：2016-8-30 上午8:58:07
      * 
      * TODO 集群链接关闭
      */
     public void resetClient(){
    	 try {
    		 if(null != jedisCluster){
        		 jedisCluster.close();
        		 jedisCluster = null;
        	 }
		} catch (Exception e) {
			 jedisCluster = null;
		}
     }
     
     /**
      * 方法名称:getMasterNodes
      * 方法描述: 获取集群中的master 节点  
      * @return HashMap<String,JedisPool>
      * 创建人： hl
      * 创建时间：2016-8-30 上午9:15:46
      */
     public Map<String,JedisPool> getMasterNodes(){
    	if(null == jedisCluster){
    		//为空初始化客户端
    		getJedisClusterClient();
    	}
    	
    	Map<String,JedisPool>  maserNodes = new HashMap<String,JedisPool>();
    	try {
			//获取集群中的所有节点
			Map<String,JedisPool>  nodes = jedisCluster.getClusterNodes();
			//遍历所有节点
			for(Entry<String, JedisPool> en :  nodes.entrySet()){
				if(isMasterNode(en.getValue())){
					maserNodes.put(en.getKey(), en.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maserNodes;
     } 
     
     
    /**
     * 方法名称:getKeys
     * 方法描述: 判断当前的节点是否是master节点  
     * @param jedisPool
     * @param
     * @return Set<String>
     * 创建人： hl
     * 创建时间：2016-8-30 上午9:20:17
     */
 	public boolean isMasterNode(JedisPool jedisPool) {
		String clientInfo = "";
		Jedis edis = null;
		try {
			edis = jedisPool.getResource();
			if(null == edis){
				return false;
			}
			//获取节点中单个所有对应的key
		    clientInfo = edis.info("Replication");
		    String[] data = clientInfo.split("\r\n");
	        for(String info : data){
	        	if(info.contains(":")){
	        		 String[] pair = info.split(":");
	        		 if("role".equals(pair[0]) && "master".equals(pair[1])){
	        			 return true;
	        		 }
	        	}
	        }
		} catch (Exception e) {
//			LOG.error("判断是否为主节点失败！"+edis.getClient().getHost()+":"+edis.getClient().getPort(), e);
		}finally{
			edis.close();
		}
		
        return false;
    }
     
 	
 	
 	

 	
 	/**
 	 * 方法名称:testConn
 	 * 方法描述: 测试redis链接  
 	 * @return boolean
 	 * 创建人： hl
 	 * 创建时间：2016-8-31 下午2:49:11
 	 */
 	public boolean testConn(){
 		boolean result = true;
 		try {
 			jedisCluster = getJedisClusterClient();
 			if(null != jedisCluster){
 				Jedis edis = null;
 	 	 		Map<String, JedisPool> jedisPool = jedisCluster.getClusterNodes();
 	 	 		for(Entry<String, JedisPool> en : jedisPool.entrySet()){
 	 	 			try { 
 	 	 				edis = en.getValue().getResource();
 	 	 				result = edis.isConnected();
 	 	 				if(!result){
// 							LOG.error("Redis 集群节点："+en.getKey() +"连接异常！");
 	 	 				}
					} catch (Exception e) {
//						LOG.error("Redis 集群节点："+en.getKey() +" 异常！", e);
						result =false;
					}finally{
						if(null != edis){
							edis.close();
						}
					}
 	 	 			
 	 	 			if(!result){
 	 	 				break;
 	 	 			}
 	 	 		}
 			}
		} catch (Exception e) {
//			LOG.error("测试redis链接失败！ ADRR："+conf.getRedisIP(), e);
			result = false;
		}
 		
 		return result;
 	}

	
 	
    
    private JedisSlotBasedConnectionHandler connectionHandler;
    private JedisClusterInfoCache cache;
    private Map<String, JedisPool> nodes;
    private Map<Integer, JedisPool> slots;
    
    
    @SuppressWarnings("unchecked")
	public void initConnectionHandler() {
    	if(null == jedisCluster) {
    		getJedisClusterClient();
    	}
    	if(null == this.connectionHandler ) {
    		try {
	        	Field connField = JedisCluster.class.getDeclaredField("connectionHandler");
	        	connField.setAccessible(true);
	        	this.connectionHandler = (JedisSlotBasedConnectionHandler) connField.get(jedisCluster);
	        	connField.setAccessible(false);
	        	
	        	
	        	Field cacheField = JedisSlotBasedConnectionHandler.class.getSuperclass().getDeclaredField("cache");
	        	cacheField.setAccessible(true);
	        	this.cache = (JedisClusterInfoCache) cacheField.get(connectionHandler);
	        	cacheField.setAccessible(false);
	        	
	        	Field nodesField = JedisClusterInfoCache.class.getDeclaredField("nodes");
	        	Field slotField = JedisClusterInfoCache.class.getDeclaredField("slots");
	        	nodesField.setAccessible(true);
	        	this.nodes =  (HashMap<String, JedisPool>) nodesField.get(this.cache);
	        	nodesField.setAccessible(false);
	        	slotField.setAccessible(true);
	        	this.slots =  (HashMap<Integer, JedisPool>) slotField.get(this.cache);
	        	slotField.setAccessible(false);

    		}catch(Exception e) {
//    			LOG.error("获取redis slot缓存信息失败", e);
    		}
    	}
	}


	public JedisWithSlotCache newJedisWithSlotCache() {
		if(config == null) {
	 		init();
		}
		return new JedisWithSlotCache(cache, connectionHandler);
	}}
