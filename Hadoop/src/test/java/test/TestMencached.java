package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danga.MemCached.MemCachedClient;

public class TestMencached {
	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
		MemCachedClient memCachedClient = (MemCachedClient) context.getBean("memcachedClient");
		//boolean flag = memCachedClient.set("hello", "Taylor Swift");
		//System.out.println(flag);
		//memCachedClient.get("hello");
		System.out.println(memCachedClient.get("hello"));
	}
}
