package com.revature.test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.pojo.User;
import com.revature.pojo.User.Role;
import com.revature.service.UserService;
import com.revature.servlet.LoginServlet;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {
	private LoginServlet loginServlet;
	private User user;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private UserService userService;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		loginServlet = new LoginServlet();
		
		List<Role> roles = new LinkedList<>();
		roles.add(User.Role.DEPARTMENT_HEAD);
		user = new User("jboni", "password", "Jacob", "Boni", roles, "nickrulez");
		
		when(userService.login(user.getUsername(), user.getPassword())).thenReturn(user);
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPostCorrectUser() {
		when(request.getParameter("username")).thenReturn(user.getUsername());
		when(request.getParameter("password")).thenReturn(user.getPassword());
		when(request.getSession()).thenReturn(session);
		try {
			loginServlet.doPost(request, response);
			Mockito.verify(session).setAttribute("user", user);
			Mockito.verify(response).sendRedirect("departmenthead-home.html");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
