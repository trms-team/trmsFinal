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
	private static final long serialVersionUID = -5701824943208981255L;
	UserService userService = new UserServiceImpl();

	public LogoutServlet() {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession(false);
		
		info("Logging out");
		
		if (session != null) {
			session.invalidate();
		}
		
		response.sendRedirect("login.html");
	}

}
