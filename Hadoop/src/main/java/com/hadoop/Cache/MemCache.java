package com.hadoop.Cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hadoop.core.LoginInfo;
import com.hadoop.util.MemcachedUtils;

public class MemCache implements Cache {

	/**
	 * 获得登录信息
	 */
	@Override
	public LoginInfo getLoginInfo(String key) {
		return (LoginInfo)MemcachedUtils.get(LOGIN_KEY + key);
	}

	/**
	 * 放入登录信息
	 */
	@Override
	public void put(String key, LoginInfo info) {
		long ms = getExpireTime();
		info.setExpireTime(new Date().getTime() + ms);
		MemcachedUtils.set(LOGIN_KEY + key, info, new Date(ms));
	}

	/**
	 * 更新超时时间
	 */
	@Override
	public void updateExpireTime(String key) {
		LoginInfo info = getLoginInfo(LOGIN_KEY + key);
		long ms = getExpireTime();
		info.setExpireTime(new Date().getTime() + ms);
		MemcachedUtils.replace(LOGIN_KEY + key, info, new Date(ms));
	}

	/**
	 * 获取所有登录信息
	 */
	@Override
	public Map<String, LoginInfo> getAllLoginInfo() {
		Map<String, LoginInfo> map = new HashMap<String, LoginInfo>();
		List<String> list = MemcachedUtils.getAllLoginKey();
		for(String key : list) {
			LoginInfo info = (LoginInfo)MemcachedUtils.get(key);
			if (info != null) {
				map.put(info.getSessionId(), info);
			}
		}
		return map;
	}

	@Override
	public void removeLogin(String key) {
		MemcachedUtils.delete(LOGIN_KEY + key);
	}
	
	public long getExpireTime() {
		long ms = 30 * 60 * 1000;
		return ms;
	}

}
