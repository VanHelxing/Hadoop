package test;

import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

public class TestXmemcached {
	
	@Test
	public void test() throws TimeoutException, InterruptedException, MemcachedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("memcache/spring-xmemcached.xml");
		XMemcachedClient client = (XMemcachedClient) context.getBean("xmemcachedClient");
		client.add("name", 100, "张四");
		String str = client.get("name");
		
		System.out.println("----------------" + str + "-----------------");
	}

}
