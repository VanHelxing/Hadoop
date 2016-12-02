package com.hadoop.Cache;

import java.util.Map;

import com.hadoop.core.LoginInfo;

public interface Cache {

	public final static String LOGIN_KEY = "LOGIN-";
	
	/*
	 * 获得的登录信息
	 * @param key
	 * @return loginInfo
	 */
	public LoginInfo getLoginInfo(String key);
	
	/*
	 * 将登陆信息放入缓存
	 * @param key
	 * @param info 登录信息
	 */
	public void put(String key, LoginInfo info);
	
	/*
	 * 更新登陆超时时间
	 * @param key
	 */
	public void updateExpireTime(String key);
	
	
	/*
	 * 获得所有登录信息
	 */
	public Map<String, LoginInfo> getAllLoginInfo();
	
	/*
	 * 移除登录信息
	 * @param key
	 */
	public void removeLogin(String key);
	
}
