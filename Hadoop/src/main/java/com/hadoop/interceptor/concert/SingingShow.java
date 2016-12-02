package com.hadoop.interceptor.concert;

import org.springframework.stereotype.Service;

@Service("singingShow")
public class SingingShow implements Performance {

	
	@Override
	public void perform(String name) {
		System.out.println(name + "入场了~");
	}

}
