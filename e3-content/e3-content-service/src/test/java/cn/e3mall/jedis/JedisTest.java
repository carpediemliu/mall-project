package cn.e3mall.jedis;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedis() throws Exception {
		//创建一个jedis对象
		Jedis jedis = new Jedis("192.168.25.133",6379);
		//直接使用jedis操作redis
		jedis.set("test123", "test key");
		System.out.println(jedis.get("test123"));
		//关闭连接
		jedis.close();
		
	}
	
	@Test
	public void testJedisPool() throws Exception {
		//创建连接池
		JedisPool jedisPool = new JedisPool("192.168.25.133", 6379);
		//获取连接
		Jedis jedis = jedisPool.getResource();
		//操作redis
		String value = jedis.get("test123");
		System.out.println(value);
		//关闭连接
		jedis.close();
		//关闭连接池
		jedisPool.close();
		
	}
	
	@Test
	public void testJedisCluster() throws Exception {
		//创建一个操控redis集群的对象，有一个nodes是一个set类型。set中包含若干个HostAndPort对象。
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.133", 7001));
		nodes.add(new HostAndPort("192.168.25.133", 7002));
		nodes.add(new HostAndPort("192.168.25.133", 7003));
		nodes.add(new HostAndPort("192.168.25.133", 7004));
		nodes.add(new HostAndPort("192.168.25.133", 7005));
		nodes.add(new HostAndPort("192.168.25.133", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		//直接操作redis
		String string = jedisCluster.set("test", "123");
		System.out.println(string);
		//关闭jedisCluster对象
		jedisCluster.close();
	}
}
