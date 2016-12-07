package test;

import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;



public class TestXmemcached {

	@Test
	public void test() throws TimeoutException, InterruptedException, MemcachedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("memcache/spring-xmemcached.xml");
		MemcachedClient client = (MemcachedClient) context.getBean("xmemcachedClient");
		
		//System.out.println(client);

		client.add("name", 100, "张三");
		
	
	//	MemCachedClient client = (MemCachedClient) context.getBean("xmemcachedClient");
	//	System.out.println(client);
	//	client.add("name", 100,"张三");
	//	System.out.println(client.get("name"));
	}
}
