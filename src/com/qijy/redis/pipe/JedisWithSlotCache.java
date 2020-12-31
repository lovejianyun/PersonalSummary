package com.qijy.redis.pipe;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClusterInfoCache;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSlotBasedConnectionHandler;

public class JedisWithSlotCache {
	
	private JedisClusterInfoCache cache;
	private JedisSlotBasedConnectionHandler connectionHandler;
	
//	private Map<Integer,JedisPool> slotsMap = new HashMap<>();
	private Map<JedisPool,Jedis> jedisMap = new HashMap<>();
	
	public JedisWithSlotCache(JedisClusterInfoCache cache,JedisSlotBasedConnectionHandler connectionHandler) {
		this.cache = cache;
		this.connectionHandler = connectionHandler;
	}
	
	
	/**
	 * 通过slot获取jedis连接，
	 * 
	 * @param: @param slot
	 * @param: @param isNew	 是否重新从jedisPool获取jedis连接
	 * @param: @return      
	 * @return: Jedis      
	 * @throws
	 */
	public Jedis getJedisFromSlot(int slot) {
		return getJedisFromSlot(slot,false);
	}
	public Jedis getJedisFromSlot(int slot, boolean isNew) {
		JedisPool connectionPool = cache.getSlotPool(slot);
		if (connectionPool != null && !isNew) {
			return fromJedisMap(connectionPool, isNew);
		} else {
			connectionHandler.renewSlotCache();
			connectionPool = cache.getSlotPool(slot);
			if (connectionPool != null) {
				return fromJedisMap(connectionPool, isNew);
			} else {
				return connectionHandler.getConnection();
			}
		}
	}

	private Jedis fromJedisMap(JedisPool connectionPool, boolean isNew) {
		Jedis jedis = jedisMap.get(connectionPool);
		if (jedis == null || isNew) {
			jedis = connectionPool.getResource();
			jedisMap.put(connectionPool, jedis);
		}
		
		return jedis;
	}
	
	
	/**
	 * 关闭此缓存内的jedis连接，回收到连接池
	 */
	public void closeAllJedis(){
		for(Jedis jedis:jedisMap.values()){
			jedis.close();
		}
	}
	
	public void renewSlotCache(){
		connectionHandler.renewSlotCache();
	}
	
}
