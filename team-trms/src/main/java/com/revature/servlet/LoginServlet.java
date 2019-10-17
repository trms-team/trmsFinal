package com.revature.servlet;

import static com.revature.util.LoggerUtil.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.login(username, password);
		
		if(user != null) {
			request.getSession().setAttribute("user", user);
			info("User " + user.getUsername() + " has logged in successfully.");
			if (user.getRoles().contains(User.Role.EMPLOYEE)) {
				response.sendRedirect("employee-home.html");
			}
			// This order is to give people with both dep head and dir sup
			// roles the dep head page
			else if (user.getRoles().contains(User.Role.DEPARTMENT_HEAD)) {
				response.sendRedirect("departmentheadhome");
			}
			else if (user.getRoles().contains(User.Role.DIRECT_SUPERVISOR)) {
				response.sendRedirect("directsupervisorhome");
			}
			else {
				response.sendRedirect("bencohome");
			}
		}
		else {
			// Maybe add in an alert using JS
			info("Invalid username / password");
			response.sendRedirect("login.html");
		}
		
	}

}
