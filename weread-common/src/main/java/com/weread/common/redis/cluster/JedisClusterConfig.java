package com.weread.common.redis.cluster;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
@Configuration
@ConditionalOnProperty(name="redis.mode",havingValue="cluster")
public class JedisClusterConfig {
	@Value("${redis.nodes}")
	private String nodes;
	@Value("${redis.timeout:10000}")
	private int timeout;
	@Value("${redis.maxAttempts:5}")
	private int maxAttempts;
	
	@Bean
	public JedisCluster getJedisCluster() {
		String[] serverArray = nodes.split(",");
		Set<HostAndPort> nodes = new HashSet<>();
		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
		}
		return new JedisCluster(nodes, timeout, maxAttempts);
	}

}
