package com.example.redisapp.BeanConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@Configuration
@EnableRedisRepositories
@PropertySource(name = "application", value = "classpath:application.properties")
public class BeanConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName("127.0.0.1");
		jedisConFactory.setPort(6379);
		jedisConFactory.setTimeout(10000);
		RedisConnection con= jedisConFactory.getConnection();
		return jedisConFactory;
	}
	@Primary
	@Bean
	@Qualifier("myRedisBean")
	public RedisTemplate<String,Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);

		return redisTemplate;
	}
	
	@Bean
	RedisCacheWriter redisCacheWriter() {
		return RedisCacheWriter.lockingRedisCacheWriter(jedisConnectionFactory());
	}
	
	@Bean
	RedisCacheConfiguration defaultRedisCacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20));
	}
	
	
	@Bean
	CacheManager timeOutCacheManager() {
		Map<String,RedisCacheConfiguration> config=new HashMap<String,RedisCacheConfiguration>();
		config.put("users",RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10)));
		return new RedisCacheManager(redisCacheWriter(),defaultRedisCacheConfiguration(),config);
	}
}
