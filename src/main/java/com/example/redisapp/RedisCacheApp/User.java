package com.example.redisapp.RedisCacheApp;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	
	@Id
	private int id;
	
	private String userName;
	
	public User() {
		
	}

	public User(int id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + "]";
	}
	
	

}
