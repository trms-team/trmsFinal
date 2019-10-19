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
	PreparedStatement getPendingByEmployeeStmt = ConnectionFactory.
		getConnection().prepareStatement("select * from reimbursement_test inner join status_test on reimbursement_test.status_id = status_test.status_id"
				+ " where (status_test.direct_sup_status = 'PENDING' or status_test.dep_head_status = 'PENDING' or status_test.ben_co_status = 'PENDING')"
				+ " and reimbursement_test.employee_username = ? order by reimbursement_test.submission_time desc");
	
	@Spy
	PreparedStatement getAcceptedByEmployeeStmt = ConnectionFactory.
		getConnection().prepareStatement("select * from reimbursement_test inner join status_test on reimbursement_test.status_id = status_test.status_id"
				+ " where status_test.direct_sup_status = 'ACCEPTED' and status_test.dep_head_status = 'ACCEPTED' and status_test.ben_co_status = 'ACCEPTED'"
				+ " and reimbursement_test.employee_username = ? order by reimbursement_test.submission_time desc");
	
	@Spy
	PreparedStatement getRejectedByEmployeeStmt = ConnectionFactory.
		getConnection().prepareStatement("select * from reimbursement_test inner join status_test on reimbursement_test.status_id = status_test.status_id"
				+ " where (status_test.direct_sup_status = 'REJECTED' or status_test.dep_head_status = 'REJECTED' or status_test.ben_co_status = 'REJECTED')"
				+ " and reimbursement_test.employee_username = ? order by reimbursement_test.submission_time desc");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		LocalDateTime eventLdt = LocalDateTime.of(2019, Month.OCTOBER, 18, 13, 20, 0);
		LocalDateTime submitLdt = LocalDateTime.of(2019, Month.OCTOBER, 18, 13, 0, 0);
		username = "nick";
		reimbursement = new Reimbursement(3, username, "nick@gmail.com", "4081111111", eventLdt,
				"Tampa", "Fun stuff", EventType.OTHER, "For relaxation", 90.00, GradeFormat.LETTER, "To help my work", 10.00,
				80.00, 3, submitLdt);
		pendingReimbursements = new LinkedList<>();
		pendingReimbursements.add(reimbursement);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPendingByEmployee() {
		sql = "select * from reimbursement_test inner join status_test on reimbursement_test.status_id = status_test.status_id"
				+ " where (status_test.direct_sup_status = 'PENDING' or status_test.dep_head_status = 'PENDING' or status_test.ben_co_status = 'PENDING')"
				+ " and reimbursement_test.employee_username = ? order by reimbursement_test.submission_time desc";
		
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
		sql = "select * from reimbursement_test inner join status_test on reimbursement_test.status_id = status_test.status_id"
				+ " where status_test.direct_sup_status = 'ACCEPTED' and status_test.dep_head_status = 'ACCEPTED' and status_test.ben_co_status = 'ACCEPTED'"
				+ " and reimbursement_test.employee_username = ? order by reimbursement_test.submission_time desc";
		
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
		sql = "select * from reimbursement_test inner join status_test on reimbursement_test.status_id = status_test.status_id"
				+ " where (status_test.direct_sup_status = 'REJECTED' or status_test.dep_head_status = 'REJECTED' or status_test.ben_co_status = 'REJECTED')"
				+ " and reimbursement_test.employee_username = ? order by reimbursement_test.submission_time desc";
		
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
