package com.revature.service;

import static com.revature.util.LoggerUtil.info;

import java.util.LinkedList;
import java.util.List;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.User;
import com.revature.pojo.User.Role;

public class UserServiceImpl implements UserService{

	private static UserDAO userDAO = new UserDAOImpl();
	
	public User login(String username, String password) {

		User user = userDAO.getUser(username);
		if(user != null && user.getPassword().equals(password)) {
			info("User " + user.getUsername() + " is registered in the database and their password matches.");
			return user;
		}
		return null;
	}
	

}
