package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.User;

public class UserDAOImpl implements UserDAO {
	
	List<User> userDatabase;
	{
	userDatabase = new ArrayList<User>();
	
	userDatabase.add(new User("Jboni", "password", "Jacob", "Boni"));
	userDatabase.add(new User("JShin", "password", "Jane", "Shin"));
	}
	
	public UserDAOImpl() {
		super();
	}

	public User getUser(String username) {
		
		User ret = null;
		
		for(User u : userDatabase) {
			if(u.getUsername().equals(username)) {
				ret = u;
			}
		}
		return ret;
	}

	public void createUser(User user) {
		// TODO Auto-generated method stub

	}

}
