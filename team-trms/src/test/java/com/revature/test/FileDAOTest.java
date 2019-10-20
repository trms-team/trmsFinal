package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

import com.revature.dao.FileDAO;
import com.revature.dao.FileDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class FileDAOTest {
	
	File myTxt;
	File myDocx;
	File myPDF;

	@Mock
	private Connection conn;
	
	@Mock
	Reimbursement r;

	@Spy
	FileDAOImpl fileDAO = new FileDAOImpl();
	
	String sql = "insert into file_table (reimbursement_id, filename, file) values (?, ?, ?)";
	String sql2 = "select * from file_table where file_id = ?";
	
	@Spy
	PreparedStatement stmtInsert = ConnectionFactory.getConnection().prepareStatement(sql);
	
	@Spy
	PreparedStatement stmtSelect = ConnectionFactory.getConnection().prepareStatement(sql2);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		myTxt = new File(
				"D:\\Revature\\project1\\trms\\team-trms\\src\\test\\resources\\testFile.txt");
		
		myDocx = new File(
				"D:\\Revature\\project1\\trms\\team-trms\\src\\test\\resources\\testFile.docx");
		
		myPDF = new File(
				"D:\\Revature\\project1\\trms\\team-trms\\src\\test\\resources\\testFile.pdf");
		
		fileDAO.setConn(conn);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUploadFileTxtFile() {
		
		String sql = "insert into file_table (reimbursement_id, filename, file) values (?, ?, ?)";
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmtInsert);
			when(r.getReimbursement_id()).thenReturn(1);
			assertEquals(true, fileDAO.uploadFile(myTxt, r));
			Mockito.verify(stmtInsert).executeUpdate();
		} catch (SQLException e) {
			fail("there was an sql exception");
		}
	}
	
	@Test
	public void testUploadFileDocxFile() {
		String sql = "insert into file_table (reimbursement_id, filename, file) values (?, ?, ?)";
		try {
			fileDAO.setConn(conn);
			when(conn.prepareStatement(sql)).thenReturn(stmtInsert);
			when(r.getReimbursement_id()).thenReturn(1);
			assertEquals(true, fileDAO.uploadFile(myDocx, r));
			Mockito.verify(stmtInsert).executeUpdate();
		} catch (SQLException e) {
			fail("there was an sql exception");
		}
	}
	
	@Test
	public void testUploadFilePDFFile() {
		String sql = "insert into file_table (reimbursement_id, filename, file) values (?, ?, ?)";
		try {
			fileDAO.setConn(conn);
			when(conn.prepareStatement(sql)).thenReturn(stmtInsert);
			when(r.getReimbursement_id()).thenReturn(1);
			assertEquals(true, fileDAO.uploadFile(myPDF, r));
			Mockito.verify(stmtInsert).executeUpdate();
		} catch (SQLException e) {
			fail("there was an sql exception");
		}
	}
	
	@Test
	public void testGetFile() {
		String sql = "select * from file_table where file_id = ?";
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmtSelect);
		} catch (SQLException e) {
			fail("sql exception encounterd");
			e.printStackTrace();
		}
		File dbFile =  fileDAO.getFile(1);
		File myTxt = new File(
				"D:\\Revature\\project1\\trms\\team-trms\\src\\test\\resources\\testFile.txt");		
		assertEquals(myTxt, dbFile);
		
	}

	public FileDAOTest() throws SQLException {
		super();
	}

}
