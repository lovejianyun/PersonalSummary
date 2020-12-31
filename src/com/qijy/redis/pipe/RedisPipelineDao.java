package com.qijy.redis.pipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.JedisClusterCRC16;



public class RedisPipelineDao {

	private RedisClusterWithPipeline redisCluster = RedisClusterWithPipeline.Cluster;
	private JedisWithSlotCache jedisWithSlotCache = redisCluster.newJedisWithSlotCache();
	
	
	
//	public void muiltSet(Map<String, String> dataMap) throws Exception{
//		RedisSetPipeline<String> redisPipe = new RedisSetPipeline<String>(){
//			@Override
//			public void execute(Pipeline pipe, String key, String value) {
//				pipe.set(key, value);
//			}
//		};
//		redisPipe.muiltRun(dataMap);
//	}
	
	/**
	 * string类型写redis管道实现
	 * @return
	 */
	public RedisSetPipeline<String> newStringSetPipe(){
		return new RedisSetPipeline<String>(){
			@Override
			public void execute(Pipeline pipe, String key,String value) {
				pipe.set(key, value);
			}
		};
	}
	/**
	 * hash类型写redis管道实现
	 * @return
	 */
	public RedisSetPipeline<Map<String, String>> newHashSetPipe(){
		return new RedisSetPipeline<Map<String,String>>(){
			@Override
			public void execute(Pipeline pipe, String key,Map<String, String> value) {
				pipe.hmset(key, value);
			}
		};
	}
	/**
	 * list类型写redis管道实现
	 * @return
	 */
	public RedisSetPipeline<List<String>> newRpushPipe(){
		return new RedisSetPipeline<List<String>>(){
			@Override
			public void execute(Pipeline pipe, String key,List<String> list) {
				if(list != null && list.size()>0)
					pipe.rpush(key,list.toArray(new String[list.size()]));
			}
		};
	}
	
	
	
	
	public Map<String, Map<String, String>>  muiltHgetall(Collection<String> keys) throws Exception{
		RedisGetPipeline<Map<String,String>> redisPipe = new RedisGetPipeline<Map<String,String>>(){
			@Override
			public Response<Map<String, String>> execute(Pipeline pipe, String key) {
				return pipe.hgetAll(key);
			}
		};
		return redisPipe.muiltRun(keys);	
	}
	
	public Map<String, String>  muiltGet(Collection<String> keys) throws Exception{
		RedisGetPipeline<String> redisPipe = new RedisGetPipeline<String>(){
			@Override
			public Response<String> execute(Pipeline pipe, String key) {
				return pipe.get(key);
			}
		};
		return redisPipe.muiltRun(keys);	
	}
	
	

	/**
	 * 设置值操作
	 * @author Administrator
	 *
	 * @param <V> 值的类型
	 */
	public abstract class RedisSetPipeline<V> extends PipelineOps{
		protected abstract void execute(Pipeline pipe,String key,V value);
		public void muiltRun(Map<String,V> dataMap) throws Exception{
			for(Entry<String,V> entry:dataMap.entrySet()){
				String key = entry.getKey();
				V value = entry.getValue();
				try {
					Pipeline pipe = getPipeline(key,false);
					execute(pipe,key,value);
				}catch(JedisConnectionException jce1) {
					try {
						Pipeline pipe = getPipeline(key,false);
						execute(pipe,key,value);
					}catch(Exception jce2) {
						Pipeline pipe = getPipeline(key,false);
						execute(pipe,key,value);
					}
				}
			}
			clearAll();
		}
	}
	/**
	 * 获取值操作
	 * @author Administrator
	 *
	 * @param <R> 获取返回值类型
	 */
	private abstract class RedisGetPipeline<R> extends PipelineOps{
		public abstract Response<R> execute(Pipeline pipe,String key);		
		public <V> Map<String,R> muiltRun(Collection<String> keys) throws Exception{
			Map<String,Response<R>> respMap = new HashMap<>();
			for(String key:keys){
				try {
					Pipeline pipe = getPipeline(key,false);
					respMap.put(key, execute(pipe,key));
				}catch(JedisConnectionException jce1) {
					try {
						Pipeline pipe = getPipeline(key,false);
						respMap.put(key, execute(pipe,key));
					}catch(Exception jce2) {
						Pipeline pipe = getPipeline(key,false);
						respMap.put(key, execute(pipe,key));
					}
				}
			}
			//关闭pipe，获取结果
			close();
			Map<String,R> result = new HashMap<>();
			for(Entry<String,Response<R>> entry:respMap.entrySet()){
				Response<R> resp = entry.getValue();
				result.put(entry.getKey(), resp.get());
			}
			return result;
		}		
	}
	private class PipelineOps{
		private HashMap<Jedis,Pipeline> pipeMap = new HashMap<>();
		protected Pipeline getPipeline(String key,boolean isNewPipe) throws Exception{
			int slot = JedisClusterCRC16.getSlot(key);
			Jedis jedis = jedisWithSlotCache.getJedisFromSlot(slot,isNewPipe);
			if(isNewPipe){
				close();
			}
			if(!pipeMap.containsKey(jedis)){
				pipeMap.put(jedis, jedis.pipelined());
			}
			return pipeMap.get(jedis);
		}
		
		public void close() throws IOException{
			try {
				for(Pipeline pipe:pipeMap.values()){
					pipe.close();
				}
				pipeMap.clear();
			} catch (IOException e) {
				throw new IOException("关闭pipeline异常",e);
			}
		}
		
		public void clearAll() throws IOException{
			for(Pipeline pipe:pipeMap.values()){
				if(pipe != null)
					pipe.clear();
			}
		}
	}
	
//	public void pipeHincrBy(String key, String field, long value) {
//		int slot = JedisClusterCRC16.getSlot(key);
//		Jedis jedis = jedisWithSlotCache.getJedisFromSlot(slot);
//		try {
//			jedis.pipelined().hincrBy(key, field, 1);
//		}catch(JedisConnectionException jce1) {
//			try {
//				jedis = jedisWithSlotCache.getJedisFromSlot(slot);
//				jedis.pipelined().hincrBy(key, field, 1);
//			}catch(Exception jce2) {
//				jedis = jedisWithSlotCache.getJedisFromSlot(slot,true);
//				jedis.pipelined().hincrBy(key, field, 1);
//			}
//		}
//	}

	
//	public static void main(String[] args) throws Exception {
//		RedisPipelineDao commonDao = new RedisPipelineDao();
//		CommonCacheDao cacheDao = new CommonCacheDao();
//		
////		String cahceKey = "TEST1";
////		Map<String,String> dataMap =new HashMap<>();
////		for(int i=0;i<100000;i++){
////			String key = cahceKey+":CACHE_"+i;
////			dataMap.put(key,key+".........");
////		}
////		
////		long t1 = System.currentTimeMillis();
////		commonDao.muiltSet(dataMap);
////		long t2 = System.currentTimeMillis();
////		System.out.println(t2-t1);
//		
//		
//		
//		Set<String> keys = cacheDao.getKeys1("USER_CYCLE_INFO|*");
//		long t3 = System.currentTimeMillis();
//		Map<String, Map<String, String>>  res = commonDao.muiltHgetall(keys);
////		Map<String,String> res = commonDao.muiltGet(dataMap.keySet());
//		long t4 = System.currentTimeMillis();
//		
//		System.out.println(res.size()+"   "+(t4-t3));
//		
//	}
	
	
	public static void main(String[] args) throws Exception {
		RedisSetPipeline<List<String>> newRpushpipe = new RedisPipelineDao().newRpushPipe();
		List<String> list = new ArrayList<>();
		list.add("aaaaaaaaaaaaaaaa");
		list.add("bbbbbbbbbbbbbbbb");
		list.add("cccccccccccccccc");
		Map<String,List<String>> dataMap = new HashMap<>();
		dataMap.put("TEST:list", list);
		newRpushpipe.muiltRun(dataMap);
		newRpushpipe.close();
		
		newRpushpipe.muiltRun(dataMap);
		newRpushpipe.close();
	}
	
	
//	public static void main(String[] args)  throws Exception {
//		RedisPipelineDao commonDao = new RedisPipelineDao();
//		CommonCacheDao cacheDao = new CommonCacheDao();
//		
//		String cahceKey = "TEST";
//		List<String> keys =new ArrayList<>();
//		long t1 = System.currentTimeMillis();
//		for(int i=0;i<100000;i++){
//			String key = cahceKey+":CACHE_"+i;
//			commonDao.pipeSet(key, key+"............");
//			keys.add(key);
//		}
//		long t2 = System.currentTimeMillis();
//		
//		List<String> res = commonDao.pipeMuiltGet(keys);
//		long t3 = System.currentTimeMillis();
//		
////		long t1 = System.currentTimeMillis();
////		Set<String> keys = cacheDao.getKeys1("USER_TEMP|*");
////		long t2 = System.currentTimeMillis();
////		List<Map<String,String>> res = commonDao.pipeMuiltHgetAll(keys); 
////		long t3 = System.currentTimeMillis();
//		
//		System.out.println(res.size()+"   "+(t2-t1)+"   "+(t3-t2));
//	}
	
	
	
	
	
	
//	private RedisClusterUtil utils = RedisClusterUtil.getInstance();
//	private CommonCacheDao redisDao =new CommonCacheDao();
//	
//	
//	public void hmset(Map<String,Map<String,String>> hashData) throws Exception{
//		
//		com.mon3x.analysis.utils.RedisClusterPipeline jedisPipeline = null;
//		Map<String,Object> batchObject = null;
//		try{
//			JedisCluster jedis = utils.getJedisClusterClient();
//			jedisPipeline = com.mon3x.analysis.utils.RedisClusterPipeline.pipelined(jedis);
////			jedisPipeline.refreshCluster();
//			for(Entry<String,Map<String,String>> entry:hashData.entrySet()){
//				String key = entry.getKey();
//				Map<String,String> hash = entry.getValue();
//				jedisPipeline.hmset(key, hash);
//			}
//			batchObject = jedisPipeline.syncAndReturnAll();
//		}catch(Exception e){
//			throw new Exception("Redis pipeline 批量查询出现异常",e);
//		} finally{
//			if(jedisPipeline != null){
//				jedisPipeline.close();
//			}
//		}
//	}
	
	

}


