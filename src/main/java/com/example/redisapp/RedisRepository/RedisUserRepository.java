package com.example.redisapp.RedisRepository;

import java.util.List;


import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.redisapp.RedisCacheApp.User;


public class RedisUserRepository {
	
	private HashOperations hashOperations;

	private RedisTemplate<String,Object> myRedisBean;

	 public RedisUserRepository(RedisTemplate<String,Object> myRedisBean){
	 this.myRedisBean = myRedisBean;
	 this.hashOperations = this.myRedisBean.opsForHash();
	 }

	public void save(User user) {
		hashOperations.put("USER", user.getId(), user);
	}

	public List findAll() {
		return hashOperations.values("USER");
	}

	public User findById(int id) {
		return (User) hashOperations.get("USER", id);
	}

	public void update(User user) {
		save(user);
	}

	public void delete(int id) {
		hashOperations.delete("USER", id);
	}
}
