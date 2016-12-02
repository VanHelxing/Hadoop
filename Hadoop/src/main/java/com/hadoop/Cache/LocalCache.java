package com.hadoop.Cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.hadoop.core.AppContext;
import com.hadoop.core.LoginInfo;

public class LocalCache implements Cache {
	
	private final static Logger log = Logger.getLogger(LocalCache.class);
	private final static Map<String, LoginInfo> properties = new HashMap<String, LoginInfo>();
	
	public LocalCache() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				log.debug("========> check session <========");
				removeSession();
			}
		}, 100, 1*60*1000);
		log.info("start app context thread");
	}
	
	@Override
	public LoginInfo getLoginInfo(String key) {
		return properties.get(key);
	}
	
	@Override
	public void put(String key, LoginInfo info) {
		properties.put(key, info);
		updateExpireTime(key);
	}
	
	@Override
	public void updateExpireTime(String key) {
		LoginInfo info = properties.get(key);
		info.setExpireTime(AppContext.getExpireTime());
	}
	
	@Override
	public Map<String, LoginInfo> getAllLoginInfo() {
		return properties;
	}
	
	@Override
	public void removeLogin(String key) {
		properties.remove(key);
	}
	
	private void removeSession() {
		Iterator<Entry<String, LoginInfo>> ite = properties.entrySet().iterator();
		while (ite.hasNext()) {
			Entry<String, LoginInfo> entry = ite.next();
			LoginInfo info = entry.getValue();
			if (info.isExpire()) {
				log.debug(info.getUserName() + "用户已超时...");
				ite.remove();
			}
			log.debug("========> remove session <========");
		}
	}
	

}
