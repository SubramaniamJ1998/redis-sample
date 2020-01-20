package com.example.redisapp.RedisCacheApp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@ComponentScan(basePackages="com.example.redisapp")
@EnableAutoConfiguration
@EnableRedisRepositories
@SpringBootApplication
@EnableCaching
@EntityScan(basePackages="com.example.redisapp")
@EnableJpaRepositories(basePackages="com.example.redisapp")
public class RedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheApplication.class, args);
	}

}
