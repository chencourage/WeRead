package com.weread.common.redis.standone;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.weread.common.redis.IRedisService;

@Configuration
@ConditionalOnProperty(name="redis.mode",havingValue="standone")
public class StandoneRedisService implements IRedisService{  
  
    @Autowired
    private RedisTemplate<String, String> redisTemplate;  
      
    @Override  
    public void set(final String key, final String value) {  
    	redisTemplate.opsForValue().set(key, value);
    }
    @Override  
    public void set(final String key, final String value,long timeout,TimeUnit tu) {  
    	redisTemplate.opsForValue().set(key, value,timeout,tu);
    }
    @Override
    public String get(final String key){
        return redisTemplate.opsForValue().get(key);  
    }
    @Override
    public List<String> multiGet(final Collection<String> keys){
    	return redisTemplate.opsForValue().multiGet(keys);
    }
  
    @Override  
    public boolean expire(final String key, long expire,TimeUnit tu) {
        return redisTemplate.expire(key, expire, tu);  
    }  
  
    @Override  
    public <T> void setList(String key, List<T> list) {  
        String value = JSON.toJSONString(list);  
        set(key,value);
    }  
  
    @Override  
    public <T> List<T> getList(String key,Class<T> clz) {  
        String json = get(key);  
        if(json!=null){  
            List<T> list = JSON.parseArray(json, clz);  
            return list;  
        }  
        return null;
    }  
  
    @Override  
    public long lpush(final String key, Object obj) {  
        final String value = JSON.toJSONString(obj);  
        return redisTemplate.opsForList().leftPushAll(key,value);
    }  
  
    @Override  
    public long rpush(final String key, Object obj) {  
        final String value = JSON.toJSONString(obj);  
        return redisTemplate.opsForList().rightPushAll(key,value);  
    }  
  
    @Override  
    public String lpop(final String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  
    
    @Override
    public long incr(String key) {
		return redisTemplate.opsForValue().increment(key, 1);
	}
    
    @Override
	public boolean setNx(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key,value);
	}

	@Override
	public void del(String key) {
		redisTemplate.delete(key);
	}
	
	@Override
	public void del(Collection<String> keys) {
		redisTemplate.delete(keys);
	}

	@Override
	public long incr(String key, int value) {
		return redisTemplate.opsForValue().increment(key, value);
	}

	@Override
	public Long ttl(String key) {
		return redisTemplate.getExpire(key);
	}

	@Override
	public void sadd(String key, String value) {
		redisTemplate.opsForSet().add(key, value);
	}

	@Override
	public Set<String> smembers(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	@Override
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	@Override
	public boolean addZset(String key,String value,double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}
	
	@Override
	public Set<String> getZset(String key,int page,int size){
		long start = (page-1)*size;
		return redisTemplate.opsForZSet().range(key, start, start+size);
	}
	
	@Override
	public long zCard(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}
	@Override
	public Long zRem(String key,String... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}
	@Override
	public Set<String> keys(String key){
		return redisTemplate.keys(key);
	}
	@Override
	public String getSet(String key, String value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}
	@Override
	public boolean geoadd(String key, double longitude, double latitude, String member) {
		return redisTemplate.opsForGeo().geoAdd(key, new Point(longitude, latitude), member) > 0;
	}
}  