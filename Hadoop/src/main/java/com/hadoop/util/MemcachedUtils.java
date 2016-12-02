package com.hadoop.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;

public class MemcachedUtils {
	
	private static final Logger log = Logger.getLogger(MemcachedUtils.class);
	private static MemCachedClient cachedClient = new MemCachedClient("memcachedPool");

	
	// 避免被外部实例化
	private MemcachedUtils() {}
	
	
	/**
	 * @return
	 */
	public MemCachedClient getInstance() {
		return cachedClient;
	}
	
	/*
	 * 向缓存添加新的键值对，如果键已经存在，则之前的值将被替换。
	 * 
	 * @param key    键
	 * @param value  值
	 * 
	 * @return
	 */
	public static boolean set(String key, Object value) {
		System.out.println("尝试放入key : " + key);
		return setExp(key, value, null);
	}
	
	
	/*
	 * 向缓存添加新的键值对，如果键已经存在，则之前的值将被替换。
	 * 
	 * @param key    键
	 * @param value  值
	 * @param expire 过期时间   New Date(1000*10)：十秒后过期
	 * @return
	 */
	public static boolean set(String key, Object value, Date expire) {
		return setExp(key, value, expire);
	}
	
	
	/*
	 * 向缓存添加新的键值对，如果键已经存在，则之前的值将被替换。
	 * 
	 * @param key    键
	 * @param value  值
	 * @param expire 过期时间   New Date(1000*10)：十秒后过期
	 * @return
	 */
	private static boolean setExp(String key, Object value, Date expire) {
		boolean flag = false;
		try 
		{
			flag = cachedClient.set(key, value, expire);
		} 
		catch (Exception e) 
		{
			log.error("Memcached set方法报错，key值： " + key);
		}
		return flag;
	}
	
	
	/*
	 * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。
	 * 
	 * @param key	键
	 * @param value 值
	 * 
	 * @return
	 */
	public static boolean add(String key, Object value) {
		return addExp(key, value, null);
	}
	
	
	/*
	 * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。
	 * 
	 * @param key	 	键
	 * @param value  	值
	 * @param expire 	过期时间   New Date(1000*10)：十秒后过期
	 * 
	 * @return
	 */
	public static boolean add(String key, Object value, Date expire) {
		return addExp(key, value, expire);
	}
	
	
	/*
	 * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。
	 * 
	 * @param key	 	键
	 * @param value  	值
	 * @param expire 	过期时间   New Date(1000*10)：十秒后过期
	 * 
	 * @return
	 */
	private static boolean addExp(String key, Object value, Date expire) {
		boolean flag = false;
		try 
		{
			flag = cachedClient.add(key, value, expire);
		} 
		catch (Exception e) {
			log.error("Memcached add方法报错， key值： " + key);
		}
		return flag;
	}
	
	
	/*
	 * 仅当键已经存在时，replace 命令才会替换缓存中的键。
	 * 
	 * @param key		键
	 * @param value		值
	 * 
	 * @return
	 */
	public static boolean replace(String key, Object value) {
		return replaceExp(key, value, null);
	}
	
	/*
	 * 仅当键已经存在时，replace 命令才会替换缓存中的键。
	 * 
	 * @param key		键
	 * @param value		值
	 * @param expire	过期时间    New Date(1000*10)：十秒后过期
	 * 
	 * @return
	 */
	public static boolean replace(String key, Object value, Date expire) {
		return replaceExp(key, value, expire);
	}
	
	/*
	 * 仅当键已经存在时，replace 命令才会替换缓存中的键。
	 * 
	 * @param key		键
	 * @param value		值
	 * @param expire	过期时间    New Date(1000*10)：十秒后过期
	 * 
	 * @return
	 */
	private static boolean replaceExp(String key, Object value, Date expire) {
		boolean flag = false;
		try 
		{
			flag = cachedClient.replace(key, value, expire);
		} 
		catch (Exception e) 
		{
			log.error("Memcached replace方法报错， key值： " + key);
		}
		return flag;
	}
	
	/**
	 * get 命令用于检索与之前添加的键值对相关的值
	 * 
	 * @param key	键
	 * 
	 * @return
	 */
	public static Object get(String key){
		Object obj = null;
		try {
			obj = cachedClient.get(key);
		} catch (Exception e) {
			log.error("Memcached get方法报错， key值： " + key);
		}
		return obj;
	}
	
	/**
	 * 删除 memcached 中的任何现有值。
	 * 
	 * @param key	值
	 * 
	 * @return
	 */
	public static boolean delete(String key) {
		return deleteExp(key, null);
	}
	
	
	/**
	 * 删除 memcached 中的任何现有值。
	 * 
	 * @param key		值
	 * @param expire 	过期时间   New Date(1000*10)：十秒后过期
	 * 
	 * @return
	 */
	public static boolean delete(String key, Date expire) {
		return deleteExp(key, expire);
	}
	
	
	/**
	 * 删除 memcached 中的任何现有值。
	 * 
	 * @param key		值
	 * @param expire 	过期时间   New Date(1000*10)：十秒后过期
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static boolean deleteExp(String key, Date expire) {
		boolean flag = false;
		try {
			flag = cachedClient.delete(key, expire);
		} catch (Exception e) {
			log.error("Memcached delete方法报错， key值： " + key);
		}
		return flag;
	}
	
	
	/**
	 * 获取所有登录的key
	 */
	public static List<String> getAllLoginKey() {
		// (通过stats item 和 stats cachedump)
		List<String> keyList = new ArrayList<String>();
		Map<String, Map<String, String>> map = cachedClient.statsItems();
		for(Map.Entry<String, Map<String, String>> entry : map.entrySet()){
			Map<String, String> m = entry.getValue();
			for(Map.Entry<String, String> innerEntry : m.entrySet()){
				String key = innerEntry.getKey();
				String value = innerEntry.getValue();
				if(key.endsWith("number")){
					String[] arr = key.split(":");
					int slabNumber = Integer.valueOf(arr[1].trim());
					int limit = Integer.valueOf(value.trim());
					Map<String, Map<String, String>> dump = cachedClient.statsCacheDump(slabNumber, limit);
					for(Iterator<String> dumpIt = dump.keySet().iterator(); dumpIt.hasNext();){
						String dumpKey = dumpIt.next();
						Map<String, String> allMap = dump.get(dumpKey);
						for(Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();){
							String allKey = allIt.next();
							if(allKey.startsWith("LOGIN-"))
								keyList.add(allKey);
						}
					}
					
				}
			}
		}
				
		return keyList;
	}
	
	
	/**
	 * 清理缓存中的所有键/值对
	 * 
	 * @return
	 */
	public static boolean flashAll() {
		boolean flag = false;
		try {
			flag = cachedClient.flushAll();
		} catch (Exception e) {
			log.error("Memcached flashAll方法报错");
		}
		return flag;
	}
	
}
