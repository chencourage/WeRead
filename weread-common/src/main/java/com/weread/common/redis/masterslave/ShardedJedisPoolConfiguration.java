package com.weread.common.redis.masterslave;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConditionalOnProperty(name = "redis.mode", havingValue = "masterslave")
public class ShardedJedisPoolConfiguration {
	@Value("${master.ip}")
	private String masterIp;
	@Value("${master.port}")
	private String masterPort;
	@Value("${slave.ip}")
	private String slaveIp;
	@Value("${slave.port}")
	private String slavePort;
	@Value("${jedis.max.active.size}")
	private int maxActiveSize;
	@Value("${jedis.max.idle}")
	private int maxIdle;
	@Value("${jedis.max.wait}")
	private long maxWait;

	@Bean("slaveJedis")
	public JedisPool getSlaverJedis() {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数（100个足够用了，没必要设置太大）
		config.setMaxTotal(maxActiveSize);
		// 最大空闲连接数
		config.setMaxIdle(maxIdle);
		// 获取Jedis连接的最大等待时间（30秒）
		config.setMaxWaitMillis(maxWait);
		// 在获取Jedis连接时，自动检验连接是否可用
		config.setTestOnBorrow(true);
		// 在将连接放回池中前，自动检验连接是否有效
		config.setTestOnReturn(true);
		// 自动测试池中的空闲连接是否都是可用连接
		config.setTestWhileIdle(true);
		return new JedisPool(config, slaveIp, Integer.parseInt(slavePort));
	}

	@Bean("masterJedis")
	public JedisPool getMasterJedis() {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数（100个足够用了，没必要设置太大）
		config.setMaxTotal(maxActiveSize);
		// 最大空闲连接数
		config.setMaxIdle(maxIdle);
		// 获取Jedis连接的最大等待时间（30秒）
		config.setMaxWaitMillis(maxWait);
		// 在获取Jedis连接时，自动检验连接是否可用
		config.setTestOnBorrow(true);
		// 在将连接放回池中前，自动检验连接是否有效
		config.setTestOnReturn(true);
		// 自动测试池中的空闲连接是否都是可用连接
		config.setTestWhileIdle(true);
		return new JedisPool(config, masterIp, Integer.parseInt(masterPort));
	}
}
