package com.revature.service;

import com.revature.dao.UserDAO;
import static com.revature.util.LoggerUtil.info;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.User;

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
