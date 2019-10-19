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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.FileDAO;
import com.revature.dao.FileDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class FileDAOTest {

	public static FileDAO fileDAO = new FileDAOImpl();
	
	File myTxt;
	File myDocx;
	File myPDF;
	Reimbursement r;

	@Mock
	Connection conn;

	@Spy
	PreparedStatement stmtInsert;

	{
		Connection myConn = ConnectionFactory.getConnection();
		stmtInsert = myConn.prepareStatement("insert into file_test values (?, ?, ?, ?)");
	}

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
		
		
		r = new Reimbursement(1, "jboni", "pisecojacob@gmail.com","300-352-4813", LocalDateTime.of(1,  2,  3 , 4, 5), 
				"Siena College", "HistoryClass", Reimbursement.EventType.UNIVERSITY_COURSE, "it was a class in history",
				543.21, Reimbursement.GradeFormat.LETTER, "I missed work because i love history sooooooo much", 55.55, 123.45, 1, LocalDateTime.now());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUploadFileTxtFile() {
		String sql = "insert into file_test values (?, ?, ?, ?)";
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmtInsert);
		} catch (SQLException e) {
			fail("there was an sql exception");
		}
		assertEquals(true, fileDAO.uploadFile(myTxt, r));
	}
	
	@Test
	public void testUploadFileDocxFile() {
		String sql = "insert into file_test values (?, ?, ?, ?)";
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmtInsert);
		} catch (SQLException e) {
			fail("there was an sql exception");
		}
		assertEquals(true, fileDAO.uploadFile(myDocx, r));
	}
	
	@Test
	public void testUploadFilePDFFile() {
		String sql = "insert into file_test values (?, ?, ?, ?)";
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmtInsert);
		} catch (SQLException e) {
			fail("there was an sql exception");
		}
		assertEquals(true, fileDAO.uploadFile(myPDF, r));
	}
	
	@Test
	public void testGetFile() {
		
		File myFile = new File("D:\\Revature\\project1\\trms\\team-trms\\src\\test\\resources\\testFile.txt");
		
		assertEquals(myFile, fileDAO.getFile(1));
	}

	@Test
	public void testUploadFileContents() {
		fail("Not yet implemented");
	}

	public FileDAOTest() throws SQLException {
		super();
	}

}
