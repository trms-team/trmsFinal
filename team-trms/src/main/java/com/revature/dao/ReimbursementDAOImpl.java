package com.revature.dao;

import static com.revature.util.LoggerUtil.trace;
import static com.revature.util.LoggerUtil.warn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.Reimbursement.EventType;
import com.revature.pojo.Reimbursement.GradeFormat;
import com.revature.pojo.Reimbursement.Status;
import com.revature.util.ConnectionFactory;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void createReimbursement(Reimbursement reimbursement) {
		// 22 fields in database
		String sql = "insert into reimbursement_test (employee_username, email, phone, event_time, location, event_name, "
				+ "event_type, description, cost, format_id, work_related_just, work_hours_missed, awarded_amount, "
				+ "submission_time, direct_sup_status, dep_head_status, ben_co_status, rejected_reason, "
				+ "direct_sup_time, dep_head_time, ben_co_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, reimbursement.getEmployeeUsername());
			stmt.setString(2, reimbursement.getEmail());
			stmt.setString(3, reimbursement.getPhone());
			stmt.setTimestamp(4, timeConvert(reimbursement.getEventTime()));
			stmt.setString(5, reimbursement.getLocation());
			stmt.setString(6, reimbursement.getEventName());
			stmt.setString(7, reimbursement.getEventType().toString());
			stmt.setString(8, reimbursement.getDescription());
			stmt.setDouble(9, reimbursement.getCost());
			
			if (reimbursement.getGradingFormat() == GradeFormat.LETTER) {
				stmt.setInt(10, 1);
			}
			else if (reimbursement.getGradingFormat() == GradeFormat.PERCENT) {
				stmt.setInt(10, 2);
			}
			else if (reimbursement.getGradingFormat() == GradeFormat.PRESENTATION) {
				stmt.setInt(10, 3);
			}
			
			stmt.setString(11, reimbursement.getWorkRelatedJustification());
			stmt.setDouble(12, reimbursement.getWorkHoursMissed());
			stmt.setDouble(13, reimbursement.getAwardedAmount());
			stmt.setTimestamp(14, timeConvert(reimbursement.getSubmissionTime()));
			stmt.setString(15, reimbursement.getDirectSupervisorStatus().toString());
			stmt.setString(16, reimbursement.getDepartmentHeadStatus().toString());
			stmt.setString(17, reimbursement.getBencoStatus().toString());
			stmt.setString(18, reimbursement.getRejectedReason());
			stmt.setTimestamp(19, null);
			stmt.setTimestamp(20, null);
			stmt.setTimestamp(21, null);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			trace("SQL exception in createReimbursement");
		}
	}
	
	private static Timestamp timeConvert(LocalDateTime time) {
		return Timestamp.valueOf(time);
	}

	@Override
	public Reimbursement getReimbursement(int reimbursementId) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<Reimbursement> getPendingReimbursementsByEmployee(String username) {
		String sql = "select * from reimbursement_test where (direct_sup_status = 'PENDING' or dep_head_status = 'PENDING' or ben_co_status = 'PENDING')"
				+ " and (direct_sup_status != 'REJECTED' or dep_head_status = 'REJECTED' or ben_co_status = 'REJECTED')"
				+ " and employee_username = ? order by submission_time desc";

		List<Reimbursement> pendingReimbursements = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getTimestamp(5).toLocalDateTime(), rs.getString(6), rs.getString(7), EventType.valueOf(rs.getString(8)), rs.getString(9),
						rs.getDouble(10), null, rs.getString(12), rs.getDouble(13),
						rs.getDouble(14), rs.getTimestamp(15).toLocalDateTime(), Status.valueOf(rs.getString(16)), Status.valueOf(rs.getString(17)),
						Status.valueOf(rs.getString(18)), rs.getString(19), null, null, null);
				
				if (rs.getInt(11) == 1) {
					r.setGradingFormat(GradeFormat.LETTER);
				}
				else if (rs.getInt(11) == 2) {
					r.setGradingFormat(GradeFormat.PERCENT);
				}
				else if (rs.getInt(11) == 3) {
					r.setGradingFormat(GradeFormat.PRESENTATION);
				}
				
				if (rs.getTimestamp(20) != null) {
					r.setDirectSupervisorTime(rs.getTimestamp(20).toLocalDateTime());
				}
				
				if (rs.getTimestamp(21) != null) {
					r.setDepartmentHeadTime(rs.getTimestamp(21).toLocalDateTime());
				}
						
				if (rs.getTimestamp(22) != null) {
					r.setBencoTime(rs.getTimestamp(22).toLocalDateTime());
				}
				
				
				pendingReimbursements.add(r);
			}
		} catch (SQLException e) {
			warn(e.getMessage());
		}
		
		return pendingReimbursements;
	}
	
	@Override
	public List<Reimbursement> getAcceptedReimbursementsByEmployee(String username) {
		String sql = "select * from reimbursement_test where direct_sup_status = 'ACCEPTED' and dep_head_status = 'ACCEPTED' and ben_co_status = 'ACCEPTED'"
				+ " and employee_username = ? order by submission_time desc";

		List<Reimbursement> acceptedReimbursements = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getTimestamp(5).toLocalDateTime(), rs.getString(6), rs.getString(7), EventType.valueOf(rs.getString(8)), rs.getString(9),
						rs.getDouble(10), null, rs.getString(12), rs.getDouble(13),
						rs.getDouble(14), rs.getTimestamp(15).toLocalDateTime(), Status.valueOf(rs.getString(16)), Status.valueOf(rs.getString(17)),
						Status.valueOf(rs.getString(18)), rs.getString(19), null, null, null);
				
				if (rs.getInt(11) == 1) {
					r.setGradingFormat(GradeFormat.LETTER);
				}
				else if (rs.getInt(11) == 2) {
					r.setGradingFormat(GradeFormat.PERCENT);
				}
				else if (rs.getInt(11) == 3) {
					r.setGradingFormat(GradeFormat.PRESENTATION);
				}
				
				if (rs.getTimestamp(20) != null) {
					r.setDirectSupervisorTime(rs.getTimestamp(20).toLocalDateTime());
				}
				
				if (rs.getTimestamp(21) != null) {
					r.setDepartmentHeadTime(rs.getTimestamp(21).toLocalDateTime());
				}
						
				if (rs.getTimestamp(22) != null) {
					r.setBencoTime(rs.getTimestamp(22).toLocalDateTime());
				}
				
				acceptedReimbursements.add(r);
			}
		} catch (SQLException e) {
			warn(e.getMessage());
		}
		
		return acceptedReimbursements;
	}

	@Override
	public List<Reimbursement> getRejectedReimbursementsByEmployee(String username) {
		String sql = "select * from reimbursement_test where (direct_sup_status = 'REJECTED' or dep_head_status = 'REJECTED' or ben_co_status = 'REJECTED')"
				+ " and employee_username = ? order by submission_time desc";

		List<Reimbursement> rejectedReimbursements = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getTimestamp(5).toLocalDateTime(), rs.getString(6), rs.getString(7), EventType.valueOf(rs.getString(8)), rs.getString(9),
						rs.getDouble(10), null, rs.getString(12), rs.getDouble(13),
						rs.getDouble(14), rs.getTimestamp(15).toLocalDateTime(), Status.valueOf(rs.getString(16)), Status.valueOf(rs.getString(17)),
						Status.valueOf(rs.getString(18)), rs.getString(19), null, null, null);
				
				if (rs.getInt(11) == 1) {
					r.setGradingFormat(GradeFormat.LETTER);
				}
				else if (rs.getInt(11) == 2) {
					r.setGradingFormat(GradeFormat.PERCENT);
				}
				else if (rs.getInt(11) == 3) {
					r.setGradingFormat(GradeFormat.PRESENTATION);
				}
				
				if (rs.getTimestamp(20) != null) {
					r.setDirectSupervisorTime(rs.getTimestamp(20).toLocalDateTime());
				}
				
				if (rs.getTimestamp(21) != null) {
					r.setDepartmentHeadTime(rs.getTimestamp(21).toLocalDateTime());
				}
						
				if (rs.getTimestamp(22) != null) {
					r.setBencoTime(rs.getTimestamp(22).toLocalDateTime());
				}
				
				rejectedReimbursements.add(r);
			}
		} catch (SQLException e) {
			warn(e.getMessage());
		}
		
		return rejectedReimbursements;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		String sql = "select * from reimbursement_test";
		
		List<Reimbursement> reimbursements = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getTimestamp(5).toLocalDateTime(), rs.getString(6), rs.getString(7), EventType.valueOf(rs.getString(8)), rs.getString(9),
						rs.getDouble(10), null, rs.getString(12), rs.getDouble(13),
						rs.getDouble(14), rs.getTimestamp(15).toLocalDateTime(), Status.valueOf(rs.getString(16)), Status.valueOf(rs.getString(17)),
						Status.valueOf(rs.getString(18)), rs.getString(19), rs.getTimestamp(20).toLocalDateTime(), rs.getTimestamp(21).toLocalDateTime(),
						rs.getTimestamp(22).toLocalDateTime());
				
				if (rs.getInt(11) == 1) {
					r.setGradingFormat(GradeFormat.LETTER);
				}
				else if (rs.getInt(11) == 2) {
					r.setGradingFormat(GradeFormat.PERCENT);
				}
				else if (rs.getInt(11) == 3) {
					r.setGradingFormat(GradeFormat.PRESENTATION);
				}
				reimbursements.add(r);
			}
			
		} catch (SQLException e) {
			warn(e.getMessage());
		}
		
		return reimbursements;
	}

}
