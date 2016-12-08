package com.hadoop.memcache;

import org.springframework.stereotype.Component;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Component
public class TestDao {
	
	@ReadThroughSingleCache(namespace = "test", expiration = 300)
	public long getUserById(@ParameterValueKeyProvider long id) throws Exception {
		System.out.println("没有缓存名字~");
		return id;
	}
}
