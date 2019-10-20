package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
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
		
		
		r = new Reimbursement(1, "brian", "brian @gmail.com", "5555555555", LocalDateTime.of(1,  2, 3, 4, 5),
				"Ohio", "history class", Reimbursement.EventType.UNIVERSITY_COURSE, "I really like history",
				543.21, Reimbursement.GradeFormat.LETTER, "history is really cool and i should be paid to learn about it",
				40.5, 434.56, LocalDateTime.now(), Reimbursement.Status.PENDING, Reimbursement.Status.PENDING, 
				Reimbursement.Status.PENDING, null, null, null, null);
		
		
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
			
		
		byte[] myBytes = new byte[1_000_000];
		try(FileOutputStream fos = new FileOutputStream(myTxt)) {
			fos.write(myBytes);
		} catch (IOException e) {
			System.out.println("issue with making a test file");
		}
		
		File uploadedFile = fileDAO.getFile(1);
		byte[] uploadedBytes = new byte[1_000_000];
		try(FileOutputStream fos = new FileOutputStream(uploadedFile)) {
			fos.write(uploadedBytes);
		} catch (IOException e) {
			fail("io exception thrown");
		}
		
		assertArrayEquals(myBytes, uploadedBytes);
		
		
		
	}
	

	public FileDAOTest() throws SQLException {
		super();
	}

}
