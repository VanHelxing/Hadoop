package com.hadoop.service;

public interface UserService {
	
	/*
	 * 验证登录信息
	 * @param userName
	 * @param password
	 * @return blooean
	 */
	public boolean exist(String userName, String password);
}
