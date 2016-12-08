package com.hadoop.memcache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TestDao2 {

	@CachePut(value = "defaultCache", key = "#id")
	public long getUserById(long id) throws Exception {
		System.out.println("没有缓存命中~");
		return id;
	}
	
	@Cacheable(value = "defaultCache")
	public String getList(String author) throws Exception {
		System.out.println("没有缓存命中~");
		return author;
	}
	
}
