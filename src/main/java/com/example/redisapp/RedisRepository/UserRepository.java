package com.example.redisapp.RedisRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.redisapp.RedisCacheApp.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
