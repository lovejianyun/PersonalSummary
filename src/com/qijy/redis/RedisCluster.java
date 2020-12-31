package com.qijy.redis;

import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterCommand;
import redis.clients.jedis.JedisClusterConnectionHandler;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisSlotBasedConnectionHandler;

/**
 * 重写redis集群客户端类，
 * 继承JedisCluster，并且新增订阅和发布方法，
 * 用于实现redis集群环境下的订阅发布功能
 * @author Jiangcy
 *
 */
public class RedisCluster extends JedisCluster{

	private int maxRedirections = 0;
	private JedisClusterConnectionHandler connectionHandler;
	
	private int connectionTimeout=10000;
	
	public RedisCluster(Set<HostAndPort> jedisClusterNode, int timeout,
			int maxRedirections, GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, timeout, maxRedirections, poolConfig);
		this.maxRedirections = maxRedirections;
		this.connectionHandler = new JedisSlotBasedConnectionHandler(jedisClusterNode, poolConfig, connectionTimeout, timeout);
	}

	/**
	 * 消息发布
	 * @param channel 发布消息的频道
	 * @param message 消息内容
	 * @return
	 */
   public Long publish(final String channel,final String message)
   {
     return (Long)new JedisClusterCommand<Long>(this.connectionHandler, this.maxRedirections)
     {
       public Long execute(Jedis connection)
       {
         return connection.publish(channel, message);
       }
     }.run(channel);
   }
   
   /**
    * 消息订阅
    * @param jedisPubSub 接收订阅消息监听器
    * @param channels 订阅频道，可填写多个频道
    * @return
    */
//   public boolean subscribe(final JedisPubSub jedisPubSub,final String...channels)
//   {
//	   return (Boolean)new JedisClusterCommand<Boolean>(this.connectionHandler, this.maxRedirections)
//	   {
//
//			@Override
//			public Boolean execute(Jedis jedis) {
//				jedis.subscribe(jedisPubSub, channels);
//				return true;
//			}
//	   }.run("");
//   }
   
}
