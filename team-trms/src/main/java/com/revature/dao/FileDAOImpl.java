package com.revature.dao;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.trace;
import static com.revature.util.LoggerUtil.warn;
import static com.revature.util.LoggerUtil.fatal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pojo.Reimbursement;
import com.revature.util.ConnectionFactory;

public class FileDAOImpl implements FileDAO {

	private Connection conn = ConnectionFactory.getConnection();

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public FileDAOImpl() {
		super();
	}

	@Override
	public boolean uploadFile(File file, Reimbursement r) {
		String filename = file.getName();
		int reimbursementId = r.getReimbursement_id();		
		byte[] data = new byte[1_000_000]; //1 megabyte per file
		try (FileInputStream fis = new FileInputStream(file)) {
			fis.read(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			warn("File not found exception in upload file");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			error("IO exception in upload file");
			return false;
		}
		
		String sql = "insert into file_test values (?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setInt(2, reimbursementId);
			stmt.setString(3, filename);
			stmt.setBytes(4, data);
		} catch (SQLException e) {
			trace("sql exception in upload file");			
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public File getFile(int fileId) {
		String sql = "select * from file_table where file_id = ?";
		String filename = "";
		byte[] data = new byte[1_000_000];
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fileId);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				filename = rs.getString(3);
				data = rs.getBytes(4);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			trace("sql exception in getFile");
			return null;
		}
		if(filename.equals("")) {
			fatal("filename not initialized");
			return null;
		}
		File file = new File(filename);
		try(FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(data);
		} catch (FileNotFoundException e) {
			trace("file not found exception in get file");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			trace("IO exceptionn in get file");
			e.printStackTrace();
			return null;
		}
		return file;
	}
}
