package com.QuickStartFrame.management.util;

import java.util.Date;

import org.apache.log4j.Logger;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * memcached的缓存实用类.
 */
public class MemcacheUtil {
	private static final Logger logger = Logger.getLogger(MemcacheUtil.class);
	
	// 创建全局的唯一实例
	protected MemCachedClient mcc;

	public MemcacheUtil(String[] servers, Integer[] weights, String name) {
		// 获取socke连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance(name);

		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);

		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// 设置主线程的睡眠时间
		pool.setMaintSleep(30);

		// 设置TCP的参数，连接超时等
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		pool.setAliveCheck(true);
		// 初始化连接池
		pool.initialize();
		
		mcc = new MemCachedClient(name);
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		if (mcc.set(key, value)) {
			return true;
		} else {
			logger.error("[ERROR.Memcache.set]" + key + "=" + value);
			return false;
		}
	}

	public boolean add(String key, Object value, Integer hash) {
		if (mcc.set(key, value, hash)) {
			return true;
		} else {
			logger.error("[ERROR.Memcache.add]" + key + "=" + value);
			return false;
		}
	}

	/**
	 * 添加一个指定的值到缓存中
	 * 
	 * @param key
	 * @param value
	 * @param expiry
	 *            超时时间
	 * @return
	 */
	public boolean add(String key, Object value, Date expiry) {
		if (mcc.add(key, value, expiry)) {
			return true;
		} else {
			if (mcc.replace(key, value, expiry)) {
				return true;
			} else {
				logger.error("[ERROR.Memcache.set(Date)]" + key + "=" + value);
				return false;
			}

		}
	}

	public boolean replace(String key, Object value) {
		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return mcc.get(key);
	}

	public boolean remove(String key) {
		return mcc.delete(key);
	}
}