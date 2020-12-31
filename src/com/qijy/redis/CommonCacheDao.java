package com.qijy.redis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.util.JedisClusterCRC16;

public class CommonCacheDao {
	private RedisClusterUtil utils = RedisClusterUtil.getInstance();
	public String get(String tName,String primaryKey,String field) throws Exception{

		String result = null;
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			result = edis.hget(tName+"|"+primaryKey, field);
		}catch(Exception e){
			throw e;
		}
		
		return result;
	}
	
	public String get(String primaryKey,String field) throws Exception{
		String result = null;
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			result = edis.hget(primaryKey, field);
		}catch(Exception e){
			throw e;
		}
		
		return result;
	}
	

	public void set(String tn, String primaryKey, String field,
			String value) throws Exception {

		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			edis.hset(tn+"|"+primaryKey, field, value);
		}catch(Exception e){
			throw e;
		}
		
	}
	
	
	public void setStr(String key,String value) throws Exception {
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			edis.set(key, value);
		}catch(Exception e){
			throw e;
		}
		
	}
	
	
	public void setex(String key,int seconds,String value) throws Exception{
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			edis.setex(key, seconds, value);
		}catch(Exception e){
			throw e;
		}
	}
	
	
	public String getStr(String key) throws Exception {
		
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			
			return edis.get(key);
		}catch(Exception e){
			throw e;
		}
	}
	
	public void hdel(String tn,String primaryKey,String ...fields) throws Exception{
	        if(null == fields ||fields.length == 0)
	            return;
	        JedisCluster edis = null;
	        try{
				edis = utils.getJedisClusterClient();
	            if(null == edis){
	                throw new SQLException("获取Redis数据库连接失败");
	            }
	            edis.hdel(tn+"|"+primaryKey, fields);
	        }catch(Exception e){
	            throw e;
	        }
	}
	
	public List<String> hget(String tn,String primaryKey,String ...fields) throws Exception{
		if(null == fields ||fields.length == 0)
			return null;
		
		List<String> result = new LinkedList<String>();
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			result = edis.hmget(tn+"|"+primaryKey, fields);
		}catch(Exception e){
			throw e;
		}
		return result.isEmpty()?null:result;
	}
	
	
	/**
	 * 把多个字段获取的结果转换为MAP
	 * 
	 * @Title hgetMAP 
	 * @param  
	 * @return Map<String,String> 
	 * @throws 
	 * @author:   Jiang Chunyu 
	 * @Date:     2016-4-11 下午5:28:45
	 */
	public Map<String,String> hgetMAP(String tn,String primaryKey,String...fields) throws Exception{
    	List<String> dataList= hget( tn, primaryKey,fields);
    	Map<String,String> data = new HashMap<String,String>();
    	for(int i=0;i<fields.length;i++){
    		String value = "";
    		if(null != (dataList.get(i))){
    			value = dataList.get(i);
    		}
    		data.put(fields[i], value);
        }
    	return data;
	}
	
	public Map<String,String> hgetMAP1(String tn,String primaryKey,String...fields) throws Exception{
    	List<String> dataList= hget( tn, primaryKey,fields);
    	Map<String,String> data = new HashMap<String,String>();
    	for(int i=0;i<fields.length;i++){
    		String value = "";
    		if(null != (dataList.get(i))){
    			value = dataList.get(i);
    		}else{
    			continue;
    		}
    		data.put(fields[i], value);
        }
    	return data;
	}
	
	public Map<String,String> hgetMAP(String primaryKey,String...fields) throws Exception{
    	List<String> dataList= hget(primaryKey,fields);
    	Map<String,String> data = new HashMap<String,String>();
    	for(int i=0;i<fields.length;i++){
    		String value = "";
    		if(null != (dataList.get(i))){
    			value = dataList.get(i);
    		}
    		data.put(fields[i], value);
        }
    	return data;
	}
	
	public Map<String,String> hgetLinkedMAP(String tn,String primaryKey,String...fields) throws Exception{
    	List<String> dataList= hget( tn, primaryKey,fields);
    	Map<String,String> data = new LinkedHashMap<String,String>();
    	for(int i=0;i<fields.length;i++){
    		String value = "";
    		if(null != (dataList.get(i))){
    			value = dataList.get(i);
    		}
    		data.put(fields[i], value);
        }
    	return data;
	}
	

	public List<String> hget(String primaryKey,String ...fields) throws Exception{
		if(null == fields || fields.length == 0)
			return null;
		
		List<String> result = new LinkedList<String>();
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			result = edis.hmget(primaryKey, fields);
		}catch(Exception e){
			throw e;
		}
		
		return result.isEmpty()?null:result;
	}
	
	
	public void hmset(String tn,String primaryKey,Map<String,String> data) throws Exception{
		if(null == data){
			return;
		}
		
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			edis.hmset(tn+"|"+primaryKey, data);
		}catch(Exception e){
			throw e;
		}
	}
	
	   public void hmset(String key,Map<String,String> data) throws Exception{
	        if(null == data){
	            return;
	        }
	        
	        JedisCluster edis = null;
	        try{
				edis = utils.getJedisClusterClient();
	            if(null == edis){
	                throw new SQLException("获取Redis数据库连接失败");
	            }
	            edis.hmset(key, data);
	        }catch(Exception e){
	            throw e;
	        }
	    }
	   
	   public List<String> hmget(String key,String field) throws Exception{

	        if(null == field){
	            return null;
	        }
	        
	        JedisCluster edis = null;
	        try{
				edis = utils.getJedisClusterClient();
	            if(null == edis){
	                throw new SQLException("获取Redis数据库连接失败");
	            }
	           return edis.hmget(key, field);
	        }catch(Exception e){
	            throw e;
	        }
	    }


    
    /**
     * 
     * 方法名称:exists
     * 方法描述:   
     * @param key
     * @return          
     * 创建人：   "jiangChunyu"
     * 创建时间：2015-9-24 下午02:52:57
     * @throws Exception 
     */
    public boolean exists(String key) throws Exception{
        JedisCluster edis = null;
        try{
			edis = utils.getJedisClusterClient();
            if(null == edis){
                throw new SQLException("获取Redis数据库连接失败");
            }
            return edis.exists(key);
        }catch(Exception e){
            throw e;
        }
    }
    
    
    /**
     * 判断hash中是否存在某字段的值
     * 方法名称:hexists
     * 方法描述:   
     * @param key
     * @param field
     * @return
     * @throws Exception          
     * 创建人：   intel
     * 创建时间：2015-10-9 下午9:02:00
     */
    public boolean hexists(String key,String field) throws Exception{

        JedisCluster edis = null;
        try{
			edis = utils.getJedisClusterClient();
            if(null == edis){
                throw new SQLException("获取Redis数据库连接失败");
            }
            return edis.hexists(key, field);
        }catch(Exception e){
            throw e;
        }
    }
    
    
    /**
     * 
     * 方法名称:getBaseData
     * 方法描述:   获取基本数据缓存
     * @param  tn    表名
     * @param  primaryKey  主键
     * @return          
     * 创建人：   "jiangChunyu"
     * 创建时间：2015-9-23 上午09:24:55
     * @throws Exception 
     */
    public Map<String,String> hgetAll(String tn,String primaryKey) throws Exception{

        
        Map<String,String> result = new HashMap<String,String>();
        JedisCluster edis = null;
        try{
			edis = utils.getJedisClusterClient();
            if(null == edis){
                throw new SQLException("获取Redis数据库连接失败");
            }
            String key = tn+"|"+primaryKey;
            result = edis.hgetAll(key);
            
        }catch(Exception e){
            throw e;
        }
        return result.isEmpty()?null:result;
    }
    
    
    public Map<String,String> hgetAll(String key) throws Exception{
        
        Map<String,String> result = new HashMap<String,String>();
        JedisCluster edis = null;
        try{
			edis = utils.getJedisClusterClient();
            if(null == edis){
                throw new SQLException("获取Redis数据库连接失败");
            }
            
            result = edis.hgetAll(key);
            
        }catch(Exception e){
            throw e;
        }
        return result.isEmpty()?null:result;
    }

	/**
	 * 从Redis的list中获取最大100个数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> lpop(String key) throws Exception{

		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			List<String> result = new LinkedList<String>();
			int count = 0;
			String line = null;
			do{
				line = edis.lpop(key);
				if(null != (line)){
					result.add(line);
				}
				count++;
			}while(null != line && count < 500);
			return result.isEmpty() ? null : result;
		}catch(Exception e){
			throw e;
		}
	}
	
	
	/**
	 * 从Redis的list中获取最大100个数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String lpopOne(String key) throws Exception{

		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			return  edis.lpop(key);
		}catch(Exception e){
			throw e;
		}
	}

	/**
	 * 从Redis的list中获取所有数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> lpopAll(String key) throws Exception{

		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			List<String> result = new LinkedList<String>();
			String line = null;
			do{
				line = edis.lpop(key);
				if(null!=(line)){
					result.add(line);
				}
			}while(null != line);
			return result.isEmpty() ? null : result;
		}catch(Exception e){
			throw e;
		}
	}
	
	public void rpush(String key, String sql_) throws Exception {

		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			edis.rpush(key, sql_);
		}catch(Exception e){
			throw e;
		}
	}

	public void rpush(String key,List<String> sqls) throws Exception{
		if(null == sqls || sqls.size()==0){
			return;
		}
		
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			String[] sqlArray =sqls.toArray(new String[sqls.size()]);
			edis.rpush(key, sqlArray);
		}catch(Exception e){
			throw e;
		}
	}
	
	/*获取所有key*/
	public Set<String> getKeys1(String patern) throws Exception{
	 
	 	Set<String> keys =  new HashSet<String>() ;
		
		try{
			JedisCluster edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			
			Map<String,JedisPool> map = edis.getClusterNodes();
			for(Entry<String,JedisPool> en : map.entrySet()){
				Set<String> set = en.getValue().getResource().keys(patern);
				if(null != set && !set.isEmpty()){
					keys.addAll(set);
				}
			}
		}catch(Exception e){
			throw e;
		}
		return keys;
	 }	
 
	/*删除对应key*/
    public void del(String key) throws Exception{

		
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			edis.del(key);
		}catch(Exception e){
			throw e;
		}
    }
	
	public String hget(String name,String key) throws Exception{

		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			return edis.hget(name, key);
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * 方法名称:getAllKeysInCluster
	 * 方法描述: 获取集群中所有的key   
	 * @param key
	 * @return
	 * @return Set<String>
	 * 创建人： hl
	 * 创建时间：2016-8-29 下午2:38:55
	 */
	public Set<String> getAllKeysInCluster1(String key) throws Exception {
		
		try{
			Map<String,JedisPool> masterNode = utils.getMasterNodes();
			if(null == masterNode){
				throw new SQLException("获取Redis数据库连接失败");
			}
			
			Set<String> result = new HashSet<String>();
			Jedis edis = null;
			//遍历所有节点
			for(Entry<String, JedisPool> en : masterNode.entrySet()){
				try {
					edis = en.getValue().getResource();
					if(null == edis){
						continue;
					}
					//获取单个节点中的keys
					Set<String> nodeKeys = edis.keys(key);
					if(null != nodeKeys && nodeKeys.size() > 0){
						result.addAll(nodeKeys);
					}
				} catch (Exception e) {
					throw new SQLException("Redis 获取集群Keys失败！",e);
				}finally{
					if(null != edis){
						edis.close();
					}
				}
			}
			return result;
		}catch(Exception e){
			throw e;
		}
	}
	
	public Set<String> getAllKeysInCluster(String key) throws Exception {
		Map<String, String> keyMap = this.getRedisKeyMap(key);
		Set<String> set = keyMap.keySet();
		return set;
	}
	
	

	/**
	 * 方法名称:getAllKeysInCluster
	 * 方法描述: 获取集群中所有的key   
	 * @param
	 * @return
	 * @return Set<String>
	 * 创建人： hl
	 * 创建时间：2016-8-29 下午2:38:55
	 */
	public void flushall() throws Exception {
		try{
			Map<String,JedisPool> masterNode = utils.getMasterNodes();
			Jedis edis = null;
			//遍历所有节点
			for(Entry<String, JedisPool> en : masterNode.entrySet()){
				try {
					edis = en.getValue().getResource();
					if(null == edis){
						continue;
					}
					//获取单个节点中的keys
					edis.flushAll();
				} catch (Exception e) {
					throw new SQLException("Redis 获取集群Keys失败！");
				}finally{
					if(null != edis){
						edis.close();
					}
				}
			}
		}catch(Exception e){
			throw e;
		}
	}
	
	
	public List<String> getRedisKeyScan(String redisKeyStartWith) {
		JedisCluster jedisCluster = null;
		List<String> mget = new ArrayList<String>();
        try {
			jedisCluster = utils.getJedisClusterClient();
			Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
			String cursor = ScanParams.SCAN_POINTER_START;
//			String key = redisKeyStartWith + "*";
		    ScanParams scanParams = new ScanParams();
		    scanParams.match(redisKeyStartWith);
		    scanParams.count(5000);
            for (Entry<String, JedisPool> entry : clusterNodes.entrySet()) {
            	//如果节点信息配置文件不包含，则跳过
				
            	String result = getValue(cursor,mget,entry,scanParams);
            	if("0".equals(result)){
            		continue;
            	}
            }
        } catch (Exception e){
        	
        }
        return mget;
    }
	
	
	public String getValue(String cursor, List<String> mget, Entry<String, JedisPool> entry, ScanParams scanParams) {
		Jedis jedis = null;
		try {
			jedis = entry.getValue().getResource();
            // 判断非从节点(因为若主从复制，从节点会跟随主节点的变化而变化)
            if (!jedis.info("replication").contains("role:slave")) {
            	while(true){
            		long t1 = System.currentTimeMillis();
            		ScanResult<String> keysScan = jedis.scan(cursor,scanParams);
            		long t2 = System.currentTimeMillis();
            		if(t2-t1 > 1000){
            			
            		}
            		cursor =  keysScan.getStringCursor();
            		List<String> result = keysScan.getResult();
            		if (result.size() > 0) {
            			Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
            			for (String resultkey : result) {
            				// cluster模式执行多key操作的时候，这些key必须在同一个slot上，不然会报:JedisDataException:
            				// CROSSSLOT Keys in request don't hash to the same slot
            				int slot = JedisClusterCRC16.getSlot(resultkey);
            				// 按slot将key分组，相同slot的key一起提交
            				if (map.containsKey(slot)) {
            					map.get(slot).add(resultkey);
            				} else {
            					List<String> list = new ArrayList<String>();
            					list.add(resultkey);
            					map.put(slot, list);
            				}
            			}
            			t1 = System.currentTimeMillis();
            			for (Entry<Integer, List<String>> integerListEntry : map.entrySet()) {
            				List<String> value = jedis.mget(integerListEntry.getValue().toArray(new String[integerListEntry.getValue().size()]));
            				mget.addAll(value);
            			}
            			t2 = System.currentTimeMillis();
            			
            		}
            		//游标为0说明已经查询完毕
            		if("0".equals(cursor)){
            			return "0";
            		}
            	}
            }
		} catch (Exception e) {
			throw e;
		}
		finally{
			if(jedis != null){
				jedis.close();
			}
			
		}
		return "0";
	}
	
	public Map<String, String> getRedisKeyMap(String redisKeyStartWith) {
		JedisCluster jedisCluster = null;
		Map<String, String> keyMap = new HashMap<String, String>();
	
		try {
			
			RedisClusterUtil utils = RedisClusterUtil.getInstance();
			jedisCluster = utils.getJedisClusterClient();
			Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
			String cursor = ScanParams.SCAN_POINTER_START;
//			String key = redisKeyStartWith + "*";
			ScanParams scanParams = new ScanParams();
			scanParams.match(redisKeyStartWith);
			scanParams.count(5000);
		
			for (Entry<String, JedisPool> entry : clusterNodes.entrySet()) {
				//如果节点信息配置文件不包含，则跳过
				String result = getKey(cursor, keyMap, entry, scanParams);
				if("0".equals(result)){
					continue;
				}
			}
		} catch (Exception e){
			throw e;
		}
		return keyMap;
	}
	
	public String getKey(String cursor, Map<String, String> keyMap, Entry<String, JedisPool> entry, ScanParams scanParams) {
		Jedis jedis =null;
		try {
			jedis = entry.getValue().getResource();
			// 判断非从节点(因为若主从复制，从节点会跟随主节点的变化而变化)
			if (!jedis.info("replication").contains("role:slave")) {
				while(true){
					long t1 = System.currentTimeMillis();
					ScanResult<String> keysScan = jedis.scan(cursor,scanParams);
					long t2 = System.currentTimeMillis();
					if(t2-t1 > 1000){
					}
					cursor =  keysScan.getStringCursor();
					List<String> result = keysScan.getResult();
					for (String resultKey : result) {
						keyMap.put(resultKey, entry.getKey());
					}
					
					//游标为0说明已经查询完毕
					if("0".equals(cursor)){
						return "0";
					}
				}
			}
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(jedis != null){
				jedis.close();
			}
			
		}
		
		return "0";
	}
	public String getType(String key) throws Exception{
		JedisCluster edis = null;
		try{
			edis = utils.getJedisClusterClient();
			if(null == edis){
				throw new SQLException("获取Redis数据库连接失败");
			}
			return edis.type(key);
		}catch(Exception e){
			throw e;
		}
	}
	
}
