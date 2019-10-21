package com.revature.servlet;

import static com.revature.util.LoggerUtil.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

public class LogoutServlet extends HttpServlet {
	
	UserService userService = new UserServiceImpl();

	public LogoutServlet() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		info(userService.getCurrentUser().getUsername() + " has logged out");
		userService.setCurrentUser(null);
		HttpSession session = request.getSession();
		session.invalidate();  
		response.sendRedirect("login");
	}

}
