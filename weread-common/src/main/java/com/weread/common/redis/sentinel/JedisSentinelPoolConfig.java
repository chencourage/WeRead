package com.weread.common.redis.sentinel;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisSentinelPool;

@Configuration
@ConditionalOnProperty(name = "redis.mode", havingValue = "sentinel")
public class JedisSentinelPoolConfig {
	@Value("${spring.redis.sentinel.nodes}")
	private String nodes;
	@Value("${spring.redis.sentinel.master:mymaster}")
	private String masterName;
	
	@Bean
	public JedisSentinelPool getJedisSentinelPool() {
		Set<String> sentinels = new HashSet<String>();
		String[] serverArray = nodes.split(",");
		for (String ipPort : serverArray) {
			ipPort = ipPort.trim();
			if(ipPort.length() == 0) continue;
			sentinels.add(ipPort.trim());
		}
		JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels);
		return jedisSentinelPool;
	}
}
