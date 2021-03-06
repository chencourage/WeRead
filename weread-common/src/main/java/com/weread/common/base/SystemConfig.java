package com.weread.common.base;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 系统常量
 * @date 2020年8月28日
 */
public class SystemConfig {
	public static final String APP_VERSION = "00";
	public static final String APP_NAME = "read";
	public static final String DEF_ENCODE = "UTF-8";
	public static final String APP_PATH = System.getProperty("user.dir");
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static final String SYSTEM_REDIS_PREFIX = "red_"; //redis前缀
	
	public static final int LOGIN_SESSION_TIMEOUT_MINUTE = 2 * 60; //登录回话超时时间(分钟)
	public static final int LOGIN_SESSION_TIMEOUT_SECOND = LOGIN_SESSION_TIMEOUT_MINUTE * 60; //登录回话超时时间(秒)
	public static final int LOGIN_SESSION_REFRESH_MILLISECOND = (LOGIN_SESSION_TIMEOUT_MINUTE - 30) * 60 * 1000; //登录回话过期前30分钟刷新会话(毫秒)
	public static final int STEPOPT_SESSION_TIMEOUT_MINUTE = 30; //分步回话超时时间(分钟)
	public static final int STEPOPT_SESSION_TIMEOUT_SECOND = STEPOPT_SESSION_TIMEOUT_MINUTE * 60; //分步回话超时时间(秒)
	
	public static final String AGENT_URL_PREFIX = "/agent/";
	public static final String MERC_URL_PREFIX = "/merc/";
	
	public static final String LOCAL_IP;
	
	/**
     * 首页小说设置
     * */
	public static final String INDEX_BOOK_SETTINGS_KEY = "indexBookSettingsKey";

    /**
     * 首页新闻
     * */
	public static final String INDEX_NEWS_KEY = "indexNewsKey";

    /**
     * 首页点击榜单
     * */
	public static final String INDEX_CLICK_BANK_BOOK_KEY = "indexClickBankBookKey";

    /**
     * 首页友情链接
     * */
	public static final String INDEX_LINK_KEY = "indexLinkKey";

    /**
     * 首页新书榜单
     * */
	public static final String INDEX_NEW_BOOK_KEY = "indexNewBookKey";


    /**
     * 首页更新榜单
     * */
	public static final String INDEX_UPDATE_BOOK_KEY = "indexUpdateBookKey";

    /**
     * 模板目录保存key
     * */
	public static final String TEMPLATE_DIR_KEY =  "templateDirKey";;

    /**
     * 正在运行的爬虫线程存储KEY前缀
     * */
	public static final String RUNNING_CRAWL_THREAD_KEY_PREFIX = "runningCrawlTreadDataKeyPrefix";

    /**
     * 上一次搜索引擎更新的时间
     * */
	public static final String ES_LAST_UPDATE_TIME = "esLastUpdateTime";

    /**
     * 搜索引擎转换锁
     * */
	public static final String ES_TRANS_LOCK = "esTransLock";

    /**
     * 上一次搜索引擎是否更新过小说点击量
     * */
	public static final String ES_IS_UPDATE_VISIT = "esIsUpdateVisit";

    /**
     * 累积的小说点击量
     * */
	public static final String BOOK_ADD_VISIT_COUNT = "bookAddVisitCount";
	
	/**
     * 模板路径前缀保存key
     * */
    public static final String TEMPLATE_PATH_PREFIX_KEY = "templatePathPrefixKey";

    /**
     * 保存图片到自己的存储介质路径前缀
     * */
    public static final String LOCAL_PIC_PREFIX = "/localPic/";

    /**
     * 用户客户端标识保存key
     * */
    public static final String USER_CLIENT_MARK_KEY = "userClientMarkKey";

    /**
     * Object Json 缓存存在的最小长度
     * */
    public static final int OBJECT_JSON_CACHE_EXIST_LENGTH = 5;

    /**
     * 首页设置的小说数量
     * */
    public static final int INDEX_BOOK_SETTING_NUM = 32;

    /**
     * 累积的最大点击量
     * */
    public static final Integer ADD_MAX_VISIT_COUNT = 10;
	
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
