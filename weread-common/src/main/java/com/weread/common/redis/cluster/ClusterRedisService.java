package com.weread.common.redis.cluster;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.weread.common.redis.IRedisService;

import redis.clients.jedis.JedisCluster;
@Configuration
@ConditionalOnProperty(name="redis.mode",havingValue="cluster")
public class ClusterRedisService implements IRedisService {
	@Autowired
	private JedisCluster jedis;

	@Override
	public void set(String key, String value) {
		jedis.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedis.get(key);
	}

	@Override
	public void del(String key) {
		jedis.del(key);
	}

	@Override
	public boolean expire(String key, long expire, TimeUnit tu) {
		if(null == tu) tu = TimeUnit.SECONDS;
		if (TimeUnit.MILLISECONDS == tu) {
			return jedis.pexpire(key, expire) > 0;
		} else {
			return jedis.expire(key, (int)tu.toSeconds(expire)) > 0;
		}
	}
	

	@Override
	public <T> void setList(String key, List<T> list) {
		 String value = JSON.toJSONString(list);  
	     set(key,value);
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clz) {
		String json = get(key);  
        if(json!=null){  
            List<T> list = JSON.parseArray(json, clz);  
            return list;  
        }  
        return null;
	}

	@Override
	public long lpush(String key, Object obj) {
		return jedis.lpush(key, JSON.toJSONString(obj));
	}

	@Override
	public long rpush(String key, Object obj) {
		return jedis.rpush(key, JSON.toJSONString(obj));
	}

	@Override
	public String lpop(String key) {
		return jedis.lpop(key);
	}

	@Override
	public long incr(String key) {
		return jedis.incr(key);
	}

	@Override
	public boolean setNx(String key, String value) {
		return jedis.setnx(key, value)>0;
	}

	@Override
	public long incr(String key, int value) {
		return jedis.incrBy(key,value);
	}

	@Override
	public Long ttl(String key) {
		return jedis.ttl(key);
	}

	@Override
	public void sadd(String key, String value) {
		jedis.sadd(key, value);
	}

	@Override
	public Set<String> smembers(String key) {
		return jedis.smembers(key);
	}

	@Override
	public boolean exists(String key) {
		return jedis.exists(key.getBytes());
	}

	@Override
	public boolean addZset(String key, String value, double score) {
		return jedis.zadd(key, score, value)>0;
	}

	@Override
	public Set<String> getZset(String key, int page, int size) {
		long start = (page-1)*size;
		return jedis.zrange(key, start, start+size);
	}

	@Override
	public long zCard(String key) {
		return jedis.zcard(key);
	}

	@Override
	public Long zRem(String key, String... values) {
		return jedis.zrem(key, values);
	}

	@Override
	public Set<String> keys(String key) {
		return jedis.hkeys(key);
	}

	@Override
	public void del(Collection<String> keys) {
		jedis.del(StringUtils.toStringArray(keys));
	}

	@Override
	public List<String> multiGet(Collection<String> keys) {
		return jedis.mget(StringUtils.toStringArray(keys));
	}

	@Override
	public void set(String key, String value, long timeout, TimeUnit tu) {
		jedis.set(key, value);
		expire(key, timeout, tu);
	}

	@Override
	public String getSet(String key, String value) {
		return jedis.getSet(key, value);
	}

	@Override
	public boolean geoadd(String key, double longitude, double latitude, String member) {
		return jedis.geoadd(key, longitude, latitude, member) > 0;
	}

}
