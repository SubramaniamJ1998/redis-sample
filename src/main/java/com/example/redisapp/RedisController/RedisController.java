package com.example.redisapp.RedisController;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redisapp.RedisCacheApp.User;
import com.example.redisapp.RedisService.RedisService;

@RestController
@RequestMapping("/users")
@CacheConfig(cacheNames="users",cacheManager="timeOutCacheManager")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@PostMapping
	public User save(@RequestBody User user) {
		return redisService.post(user);

	}

	@GetMapping("/get")
	@Cacheable(value="users")
	public List<User> list() {
		return redisService.get();
	}

	@GetMapping("/get/{id}")
	@Cacheable(value="users")
	public Object getUser(@PathVariable int id) {
		return  redisService.getById(id);
	}

	@PutMapping
	@CachePut(value="users")
	public void update(@RequestBody User user) {
		redisService.update(user);
	}

	@DeleteMapping("/delete/{id}")
	@CacheEvict(value="users")
	public int delete(@PathVariable int id) {
		return redisService.delete(id);

	}
	
	@DeleteMapping("/delete")
	@CacheEvict(value="users", allEntries=true)
	public void delete() {
		
	}
	
	
}
