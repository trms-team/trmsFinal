package com.revature.test;

import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.UserDAOImpl;
import com.revature.pojo.User;
import com.revature.pojo.User.Role;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {
	private UserDAOImpl userDAO = new UserDAOImpl();
	private User user;
	private String sql;
	
	@Mock
	private Connection conn;
	
	@Spy
	PreparedStatement getUserStmt = ConnectionFactory.getConnection().prepareStatement("select * from user_test where username = ?");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		List<Role> roles = new LinkedList<>();
		roles.add(User.Role.EMPLOYEE);
		user = new User("employee", "password", "Bob", "Builder", roles,
				"jane");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUser() {
		sql = "select * from user_test where username = ?";
		
		try {
			when(conn.prepareStatement(sql)).thenReturn(getUserStmt);
			userDAO.setConn(conn);
			userDAO.getUser(user.getUsername());
			Mockito.verify(getUserStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserDAOTest() throws SQLException {
		super();
	}
}
