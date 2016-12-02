package com.hadoop.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hadoop.common.Constants;
import com.hadoop.dao.UserIDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserIDao userDao;
	
	
	@Override
	public boolean exist(String userName, String password) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constants.USER_NAME, userName);
		params.put(Constants.PASSWORD, password);
		
		int count = userDao.exist(params);
		if (count == 0) {
			return false;
		}
		
		return true;
	}

}
