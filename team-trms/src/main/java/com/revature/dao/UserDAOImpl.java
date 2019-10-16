package com.revature.dao;

import java.sql.Connection;
import static com.revature.util.LoggerUtil.warn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.User;
import com.revature.pojo.User.Role;
import com.revature.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO {
	private Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public User getUser(String username) {
		String sql = "select * from user_test where username = ?";
		
		User user = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), parseRoles((String[]) rs.getArray(5).getArray()), rs.getString(6));
			}
			
		} catch (SQLException e) {
			warn(e.getMessage());
		}
		
		return user;
	}

	public void createUser(User user) {
		// TODO Auto-generated method stub

	}
	
	// This converts array of String roles to list of enum roles
	private List<Role> parseRoles(String[] roles) {
		List<Role> properRoles = new ArrayList<>(); 
		
		for (String r : roles) {
			properRoles.add(User.Role.valueOf(r));
		}
		
		return properRoles;
	}

}
