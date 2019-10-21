package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -5701824943208981255L;
	UserService userService = new UserServiceImpl();

	public LogoutServlet() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		System.out.println("Logging out");
		
		request.getSession(false).removeAttribute("user");
		
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

}
