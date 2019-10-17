package com.revature.dao;

import static com.revature.util.LoggerUtil.warn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.Reimbursement.EventType;
import com.revature.util.ConnectionFactory;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void createReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub

	}

	@Override
	public Reimbursement getReimbursement(int reimbursementId) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<Reimbursement> getPendingReimbursementsByEmployee(String username) {
		String sql = "select * from reimbursement_test inner join status_test on reimbursement_test.status_id = status_test.status_id"
				+ " where (status_test.direct_sup_status = 'PENDING' or status_test.dep_head_status = 'PENDING' or status_test.ben_co_status = 'PENDING')"
				+ " and reimbursement_test.employee_username = ?";

		List<Reimbursement> pendingReimbursements = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				pendingReimbursements.add(new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getTimestamp(5).toLocalDateTime(), rs.getString(6), rs.getString(7), EventType.valueOf(rs.getString(8)), rs.getString(9),
						rs.getDouble(10), rs.getInt(11), rs.getString(12), rs.getDouble(13),
						rs.getDouble(14), rs.getInt(15), rs.getTimestamp(16).toLocalDateTime()));
			}
		} catch (SQLException e) {
			warn(e.getMessage());
		}
		
		return pendingReimbursements;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		String sql = "select * from reimbursement_test";
		
		List<Reimbursement> reimbursements = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getTimestamp(5).toLocalDateTime(), rs.getString(6), rs.getString(7), EventType.valueOf(rs.getString(8)), rs.getString(9),
					rs.getDouble(10), rs.getInt(11), rs.getString(12), rs.getDouble(13),
					rs.getDouble(14), rs.getInt(15), rs.getTimestamp(16).toLocalDateTime()));
			}
			
		} catch (SQLException e) {
			warn(e.getMessage());
		}
		
		return reimbursements;
	}

}
