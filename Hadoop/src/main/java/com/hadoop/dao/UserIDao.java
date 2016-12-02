package com.hadoop.dao;

import java.util.Map;

import org.cx.common.BaseCrud;
import org.springframework.stereotype.Repository;

import com.hadoop.po.User;

@Repository
public interface UserIDao extends BaseCrud<User> {
	
	/*
	 * 验证用户登录
	 * @param map
	 * @return int
	 */
	public int exist(Map<String, Object> params);
}
