package com.revature.test;

import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
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

import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.Reimbursement.EventType;
import com.revature.pojo.Reimbursement.GradeFormat;
import com.revature.pojo.Reimbursement.Status;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class ReimbursementDAOTest {
	private ReimbursementDAOImpl reimbursementDAO = new ReimbursementDAOImpl();
	private Reimbursement reimbursement;
	private List<Reimbursement> pendingReimbursements;
	private String username;
	private String sql;
	
	@Mock
	private Connection conn;
	
	@Spy
	PreparedStatement createReimbursementStmt = ConnectionFactory.
		getConnection().prepareStatement("insert into reimbursement_test (employee_username, email, phone, event_time, location, event_name, "
				+ "event_type, description, cost, format_id, work_related_just, work_hours_missed, awarded_amount, "
				+ "submission_time, direct_sup_status, dep_head_status, ben_co_status, rejected_reason, "
				+ "direct_sup_time, dep_head_time, ben_co_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
	@Spy
	PreparedStatement getPendingByEmployeeStmt = ConnectionFactory.
		getConnection().prepareStatement("select * from reimbursement_test where (direct_sup_status = 'PENDING' or dep_head_status = 'PENDING' or ben_co_status = 'PENDING')"
				+ " and employee_username = ? order by submission_time desc");
	
	@Spy
	PreparedStatement getAcceptedByEmployeeStmt = ConnectionFactory.
		getConnection().prepareStatement("select * from reimbursement_test where direct_sup_status = 'ACCEPTED' and dep_head_status = 'ACCEPTED' and ben_co_status = 'ACCEPTED'"
				+ " and employee_username = ? order by submission_time desc");
	
	@Spy
	PreparedStatement getRejectedByEmployeeStmt = ConnectionFactory.
		getConnection().prepareStatement("select * from reimbursement_test where (direct_sup_status = 'REJECTED' or dep_head_status = 'REJECTED' or ben_co_status = 'REJECTED')"
				+ " and employee_username = ? order by submission_time desc");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		LocalDateTime eventLdt = LocalDateTime.of(2019, Month.OCTOBER, 10, 12, 0, 0);
		LocalDateTime submitLdt = LocalDateTime.of(2019, Month.OCTOBER, 12, 0, 0, 0);
		LocalDateTime dirSupLdt = LocalDateTime.of(2019, Month.OCTOBER, 15, 16, 24, 0);
		LocalDateTime depHeadLdt = LocalDateTime.of(2019, Month.OCTOBER, 17, 12, 1, 0);
		LocalDateTime bencoLdt = LocalDateTime.of(2019, Month.OCTOBER, 17, 16, 30, 0);
		
		username = "brian";
		reimbursement = new Reimbursement(2, username, "brian@gmail.com", "5555555555", eventLdt,
				"Tampa", "OCA", EventType.CERTIFICATION, "I really really like Java", 245.00, GradeFormat.PERCENT, 
				"I need a job", 2.00, 245.00, submitLdt, Status.ACCEPTED, Status.ACCEPTED, Status.ACCEPTED,
				null, dirSupLdt, depHeadLdt, bencoLdt);
		pendingReimbursements = new LinkedList<>();
		pendingReimbursements.add(reimbursement);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreateReimbursement() {
		sql = "insert into reimbursement_test (employee_username, email, phone, event_time, location, event_name, "
				+ "event_type, description, cost, format_id, work_related_just, work_hours_missed, awarded_amount, "
				+ "submission_time, direct_sup_status, dep_head_status, ben_co_status, rejected_reason, "
				+ "direct_sup_time, dep_head_time, ben_co_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			when(conn.prepareStatement(sql)).thenReturn(createReimbursementStmt);
			reimbursementDAO.setConn(conn);
			reimbursementDAO.createReimbursement(reimbursement);
			Mockito.verify(createReimbursementStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPendingByEmployee() {
		sql = "select * from reimbursement_test where (direct_sup_status = 'PENDING' or dep_head_status = 'PENDING' or ben_co_status = 'PENDING')"
				+ " and employee_username = ? order by submission_time desc";
		
		try {
			when(conn.prepareStatement(sql)).thenReturn(getPendingByEmployeeStmt);
			reimbursementDAO.setConn(conn);
			reimbursementDAO.getPendingReimbursementsByEmployee(username);
			Mockito.verify(getPendingByEmployeeStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void testGetAcceptedByEmployee() {
		sql = "select * from reimbursement_test where direct_sup_status = 'ACCEPTED' and dep_head_status = 'ACCEPTED' and ben_co_status = 'ACCEPTED'"
				+ " and employee_username = ? order by submission_time desc";
		
		try {
			when(conn.prepareStatement(sql)).thenReturn(getAcceptedByEmployeeStmt);
			reimbursementDAO.setConn(conn);
			reimbursementDAO.getAcceptedReimbursementsByEmployee(username);
			Mockito.verify(getAcceptedByEmployeeStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void testGetRejectedByEmployee() {
		sql = "select * from reimbursement_test where (direct_sup_status = 'REJECTED' or dep_head_status = 'REJECTED' or ben_co_status = 'REJECTED')"
				+ " and employee_username = ? order by submission_time desc";
		
		try {
			when(conn.prepareStatement(sql)).thenReturn(getRejectedByEmployeeStmt);
			reimbursementDAO.setConn(conn);
			reimbursementDAO.getRejectedReimbursementsByEmployee(username);
			Mockito.verify(getRejectedByEmployeeStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ReimbursementDAOTest() throws SQLException {
		super();
	}
}
