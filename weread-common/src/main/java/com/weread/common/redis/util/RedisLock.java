package com.weread.common.redis.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weread.common.base.SystemConfig;
import com.weread.common.redis.IRedisService;
import com.weread.common.utils.DateUtil;
import com.weread.common.utils.SpringContextUtil;

/**
 * redis锁
 * @author lisheng
 *
 */
public class RedisLock {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

	private IRedisService redisService = SpringContextUtil.getBean(IRedisService.class);

    private String lockKey;

    /**
     * 锁超时时间
     */
    private long expireTime = 5 * 60 * 1000; //默认锁5分钟

    private volatile boolean locked = false;

    public RedisLock(String lockKey) {
        this.lockKey = SystemConfig.SYSTEM_REDIS_PREFIX + lockKey + "_lock";
    }

    public RedisLock(String lockKey, int expireTime, TimeUnit timeUnit) {
        this(lockKey);
        this.expireTime = timeUnit.toMillis(expireTime);
    }

    public synchronized boolean lock() {
        long expires = System.currentTimeMillis() + expireTime + 100;
        String expiresStr = String.valueOf(expires); // 锁到期时间
        if (redisService.setNx(lockKey, expiresStr)) {
        	redisService.expire(lockKey, expireTime, TimeUnit.MILLISECONDS);
            locked = true;
            return true;
        }

        String currentValueStr = redisService.get(lockKey); // redis里的时间
        if (null != currentValueStr && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
        	LOGGER.info("redis锁过期，没有删除，key={}，过期时间：{}", lockKey, DateUtil.format(new Date(Long.parseLong(currentValueStr)), DateUtil.SENCOND_PATTERN));
            String oldValueStr = redisService.getSet(lockKey, expiresStr);
            if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
            	redisService.expire(lockKey, expireTime, TimeUnit.MILLISECONDS);
                locked = true;
                return true;
            }
        }
        return false;
    }
    
    public String getLockKey() {
        return lockKey;
    }

    public synchronized void unlock() {
        if (locked) {
            redisService.del(lockKey);
            locked = false;
        }
    }
}