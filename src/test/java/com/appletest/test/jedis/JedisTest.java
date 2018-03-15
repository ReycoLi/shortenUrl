package com.appletest.test.jedis;


import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	/**
	 * test connection of redis and set, get, incr method 
	 * <p>Description: </p>
	 * <p>Company: www.appletest.com</p>
	 * @author zheli 
	 * @version 1.0
	 */
	@Test
	public void testJedis() throws Exception{

		Jedis jedis = new Jedis("192.168.25.130", 6379);
	
		jedis.set("test123", "my first jedis test");
		String string = jedis.get("test123");
		Long incrResult = jedis.incr("key1");
		
		System.out.println(incrResult);
		System.out.println(string);

		jedis.close();
	}
	
	 /**
	 * test connection of connection pool and set, get, incr method
	 * <p>Description: </p>
	 * <p>Company: www.appletest.com</p>
	 * @author zheli 
	 * @version 1.0
	 */
	@Test
	public void testJedisPool() throws Exception{

		JedisPool jedisPool = new JedisPool("192.168.25.130", 6379);

		Jedis jedis = jedisPool.getResource();

		System.out.println(jedis.get("test123"));

		jedis.close();

		jedisPool.close();
	}
	
}
