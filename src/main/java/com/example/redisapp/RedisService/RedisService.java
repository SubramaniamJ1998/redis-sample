package com.example.redisapp.RedisService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redisapp.RedisCacheApp.User;
import com.example.redisapp.RedisRepository.RedisUserRepository;
import com.example.redisapp.RedisRepository.UserRepository;

@Service
public class RedisService {

	@Autowired
	private UserRepository userRepository;

	public User post(User user) {
		userRepository.save(user);
		return user;
	}

	public List<User> get() {
		return userRepository.findAll();
	}

	public Object getById(int id) {
		return userRepository.findById(id);
	}

	public void update(User user) {
		userRepository.save(user);

	}

	public int delete(int id) {
		userRepository.deleteById(id);
		return id;
	}

}
