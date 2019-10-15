package com.revature.dao;

import com.revature.pojo.User;

public interface UserDAO {

	public User getUser(String username);
	
	public void createUser(User user);
	
}
