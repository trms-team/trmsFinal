package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.User;

public class UserServiceImpl implements UserService{

	private static UserDAO userDAO = new UserDAOImpl();
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public User login(String username, String password) {

		User user = userDAO.getUser(username);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	

}
