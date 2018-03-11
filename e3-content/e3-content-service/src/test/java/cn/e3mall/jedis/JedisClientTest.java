package cn.e3mall.jedis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;

public class JedisClientTest {

	@SuppressWarnings("resource")
	@Test
	public void testJedisClient() throws Exception {
		//初始化Spring容器
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		//从容器中获取jedisClient对象
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("test", "mytest");
		System.out.println(jedisClient.get("test"));
	}
}
