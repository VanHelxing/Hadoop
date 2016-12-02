package com.hadoop.core;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hadoop.Cache.Cache;
import com.hadoop.Cache.LocalCache;
import com.hadoop.Cache.MemCache;

public class AppContext {
	
	public final static int TIMEOUT = 2;  //单位:分钟
	
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
	
	private final static Cache cache;
	
	private final static AppContext instance = new AppContext();
	
	private static Logger log = Logger.getLogger(AppContext.class);
	
	static {
		//cache = new LocalCache();
		cache = new MemCache();
	}
	
	private AppContext() {}
	
	public static AppContext getInstance() {
		return instance;
	}
	
	public LoginInfo getLoginInfo(String key) {
		return cache.getLoginInfo(key);
	}
	
	public void put(String key, LoginInfo info) {
		log.debug("key : " + key + " info " + info.getUserName());
		cache.put(key, info);
	}
	
	public void remove(String key) {
		cache.removeLogin(key);
	}
	
	public void updateExpireTime(String key) {
		log.debug(key);
		cache.updateExpireTime(key);
	}
	
	public static Map<String, LoginInfo> getAllLoginInfo() {
		return cache.getAllLoginInfo();
	}
	
	public void cleanContext() {
		threadLocal.remove();
	}
	
	public void setContext(Object o) {
		threadLocal.set(o);
	}
	
	public LoginInfo getLoginInfo() {
		return (LoginInfo)threadLocal.get();
	}
	
	public static long getExpireTime() {
		long ms = AppContext.TIMEOUT * 60 * 1000;
		ms = ms + new Date().getTime();
		return ms;
	}
	
	
}
