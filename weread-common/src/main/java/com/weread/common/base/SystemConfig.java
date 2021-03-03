package com.weread.common.base;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 系统常量
 * @date 2020年8月28日
 */
public class SystemConfig {
	public static final String APP_VERSION = "00";
	public static final String APP_NAME = "cdb-agent";
	public static final String DEF_ENCODE = "UTF-8";
	public static final String APP_PATH = System.getProperty("user.dir");
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static final String SYSTEM_REDIS_PREFIX = "cdb_"; //redis前缀
	
	public static final int LOGIN_SESSION_TIMEOUT_MINUTE = 2 * 60; //登录回话超时时间(分钟)
	public static final int LOGIN_SESSION_TIMEOUT_SECOND = LOGIN_SESSION_TIMEOUT_MINUTE * 60; //登录回话超时时间(秒)
	public static final int LOGIN_SESSION_REFRESH_MILLISECOND = (LOGIN_SESSION_TIMEOUT_MINUTE - 30) * 60 * 1000; //登录回话过期前30分钟刷新会话(毫秒)
	public static final int STEPOPT_SESSION_TIMEOUT_MINUTE = 30; //分步回话超时时间(分钟)
	public static final int STEPOPT_SESSION_TIMEOUT_SECOND = STEPOPT_SESSION_TIMEOUT_MINUTE * 60; //分步回话超时时间(秒)
	
	public static final String AGENT_URL_PREFIX = "/agent/";
	public static final String MERC_URL_PREFIX = "/merc/";
	
	public static final String LOCAL_IP;
	
	static {
		String localIP = "127.0.0.1";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			localIP = addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
		}
		LOCAL_IP = localIP;
	}
}
