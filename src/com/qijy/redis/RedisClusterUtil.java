package com.qijy.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
public class RedisClusterUtil {
	


	
	private static RedisClusterUtil redisConnUtils;//单例模式
   
	private static int MAX_ACTIVE = 10000;
    
    private static int MAX_IDLE = 100;
    
    private static int MIN_IDLE = 10;
    
    private static int MAX_WAIT = 10000;
    
    private static int TIMEOUT = 1000*10;
    
    private static int MAXREDIRECTIONS = 100;
    
    private static boolean TEST_ON_BORROW = true;
    
    private  static JedisPoolConfig config = new JedisPoolConfig();

    //Redis集群的节点集合
 	private static Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
 	//redis 集群客户端
 	private JedisCluster jedisCluster = null;
 	
    //初始化redis配置
 	static{
 		initRedisConfig();
 	}
 	
 	
    /**
     * 初始化Redis连接池
     */
    private RedisClusterUtil(){}
    
    /**
     * 方法名称:getInstance
     * 方法描述: 创建单利  
     * @return RedisConnUtil
     * 创建人： hl
     * 创建时间：2016-8-29 上午11:08:19
     */
    public  static RedisClusterUtil getInstance(){
		if(null == redisConnUtils){
			synchronized (RedisClusterUtil.class) {
				if(null == redisConnUtils){
					redisConnUtils =  new RedisClusterUtil();
				}
			}
		}
		
		return redisConnUtils;
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
    		//初始化redis配置失败，重新初始化
    		if(jedisClusterNodes.isEmpty() || jedisClusterNodes.size() == 0){
    			initRedisConfig();
    		}
    		
        	jedisCluster = new RedisCluster(jedisClusterNodes, TIMEOUT, MAXREDIRECTIONS, config);
		} catch (Exception e) {
			//关闭异常链接
			e.printStackTrace();
			if(null != jedisCluster){
				try {
					jedisCluster.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			jedisCluster = null;
		}
    	 
    	return jedisCluster;
     }
     
     /**
      * 获取自定义redis集群客户端连接池
      * @return
      */
//     public RedisCluster getMyRedisCluster(){
//     	if(null != redisCluster){
//     	   return redisCluster;
//     	}
//     	//获取redis客户端
//     	try {
//     		//初始化redis配置失败，重新初始化
//     		if(jedisClusterNodes.isEmpty() || jedisClusterNodes.size() == 0){
//     			initRedisConfig();
//     		}
//     		
//     		redisCluster = new RedisCluster(jedisClusterNodes, TIMEOUT, MAXREDIRECTIONS, config);
// 		} catch (Exception e) {
// 			//关闭异常链接
// 			if(null != redisCluster){
// 				redisCluster.close();
// 			}
// 			redisCluster = null;
// 		}
//     	 
//     	return redisCluster;
//     }
     
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
			//LOG.error("判断是否为主节点失败！"+edis.getClient().getHost()+":"+edis.getClient().getPort(), e);
			e.printStackTrace();
		}finally{
			edis.close();
		}
		
        return false;
    }
     
 	/**
 	 * 方法名称:initRedisConfig
 	 * 方法描述:初始刷redis的配置文件   
 	 * @return void
 	 * 创建人： hl
 	 * 创建时间：2016-8-30 上午11:00:43
 	 */
 	public static void initRedisConfig(){
 		try {
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
            
//            String[] redisInfo = redisStr.trim().split("，");
            String[] redisInfo = redisStr.trim().split("\\|");
            for(String ipAndPortStr:redisInfo){
            	String[] ipAndPort = ipAndPortStr.split(":");
            	try {
            		jedisClusterNodes.add(new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
				} catch (Exception e) {
//	            	LOG.error("redis 配置错误！INFO： "+ipAndPortStr);
				}
            }
        } catch (Exception e) {
//        	LOG.error("设置redis配置出错！");
        }   	
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
						e.printStackTrace();
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
			e.printStackTrace();
			result = false;
		}
 		
 		return result;
 	}
 	
	public static void main(String[] args) {
		
	}
}
