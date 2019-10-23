package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface UserDAO {

	public User getUser(String username);
	
	public List<User> getAllReportsToUser(String username);
	
}
