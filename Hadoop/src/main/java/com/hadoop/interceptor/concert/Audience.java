package com.hadoop.interceptor.concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {
	
	
	@Pointcut("execution(** com.hadoop.interceptor.concert.Performance.perform(..))")
	public void performances() {
		
	}
	
	@Before("performances() && args(name)")
	public void clapping(String name) {
		System.out.println("观众们都站起来为" + name + "鼓掌~");
	}
	
}
