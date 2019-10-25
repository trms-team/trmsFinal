package com.revature.test;

import static org.mockito.Mockito.when;

import java.io.IOException;

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

import com.revature.servlet.LogoutServlet;

@RunWith(MockitoJUnitRunner.class)
public class LogoutServletTest {
	private LogoutServlet logoutServlet;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		logoutServlet = new LogoutServlet();
		when(request.getSession(false)).thenReturn(session);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPost() {
		try {
			logoutServlet.doPost(request, response);
			Mockito.verify(session).invalidate();
			Mockito.verify(response).sendRedirect("login.html");
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
	}

}
