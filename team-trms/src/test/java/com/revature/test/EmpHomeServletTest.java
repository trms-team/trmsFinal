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
import com.revature.servlet.EmpHomeServlet;

@RunWith(MockitoJUnitRunner.class)
public class EmpHomeServletTest {
	private EmpHomeServlet empHomeServlet;
	private User user;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	HttpSession session;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		empHomeServlet = new EmpHomeServlet();
		List<Role> roles = new LinkedList<>();
		roles.add(User.Role.EMPLOYEE);
		user = new User("jeff", "password", "Jeff", "Franken", roles, "jane");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoGet() {
		try {
			when(session.getAttribute("user")).thenReturn(user);
			empHomeServlet.doGet(request, response);
			Mockito.verify(response).sendRedirect("employee-home.html");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
