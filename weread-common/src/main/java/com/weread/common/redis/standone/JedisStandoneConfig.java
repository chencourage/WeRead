package com.weread.common.redis.standone;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;


@Configuration
@ConditionalOnProperty(name="redis.mode",havingValue="standone")
public class JedisStandoneConfig {
	
	@Value("${spring.redis.host:}")
	private String host;
	@Value("${spring.redis.port:}")
	private int port;
	@Value("${spring.redis.pool.max-active:100}")
	private int maxTotal;
	@Value("${spring.redis.pool.max-idle:50}")
	private int maxIdle;
	@Value("${spring.redis.pool.min-idle:8}")
	private int minIdle;
	@Value("${spring.redis.pool.max-wait:1000}")
	private long maxWait;
	@Value("${spring.redis.pool.testOnBorrow:true}")
	private boolean testOnBorrow;
	@Value("${spring.redis.pool.testOnReturn:true}")
	private boolean testOnReturn;
	
	private JedisPoolConfig initJedisConfig() {
		JedisPoolConfig jedisconfig = new JedisPoolConfig();
	    jedisconfig.setMaxTotal(maxTotal);
	    jedisconfig.setMaxIdle(maxIdle);
	    jedisconfig.setMinIdle(minIdle);
	    jedisconfig.setMaxWaitMillis(maxWait);
	    jedisconfig.setTestOnBorrow(testOnBorrow);
	    jedisconfig.setTestOnReturn(testOnReturn);
		return jedisconfig;
	}
	
	public RedisConnectionFactory jedisConnectionFactory() {
		JedisPoolConfig jedisconfig = initJedisConfig();
	    JedisConnectionFactory factory = null;

		factory = new JedisConnectionFactory();
		factory.setPoolConfig(jedisconfig);
		factory.setHostName(host);
		factory.setPort(port);
		factory.setUsePool(true);
		factory.afterPropertiesSet();

	    return factory;
	}
	
	@Primary
	@Bean(name="defaultRedisTemplate",autowire=Autowire.BY_TYPE)
	public RedisTemplate<String, String> redisTemplate(){
		RedisTemplate<String, String> rt = new RedisTemplate<>();
		rt.setConnectionFactory(jedisConnectionFactory());
		RedisSerializer<String> serializer = new StringRedisSerializer();
		rt.setKeySerializer(serializer);
		rt.setValueSerializer(serializer);
		rt.setHashKeySerializer(serializer);
		rt.setHashValueSerializer(serializer);
		return rt;
	}

}
