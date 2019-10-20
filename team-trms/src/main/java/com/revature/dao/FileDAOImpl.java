package com.revature.dao;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.trace;
import static com.revature.util.LoggerUtil.warn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		int reimbursementId = r.getReimbursement_id();
		String filename = file.getPath();
		String sql = "insert into file_table (reimbursement_id, filename, file) values (?, ?, ?)";
		
		try(FileInputStream fis = new FileInputStream(file)) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reimbursementId);
			stmt.setString(2, filename);
			stmt.setBinaryStream(3, fis);
			stmt.executeUpdate();
		} catch (SQLException e) {
			trace("sql exception in upload file");
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			warn("File not found exception in upload file");
			return false;
		} catch (IOException e1) {
			warn("IO exception in upload file");
			e1.printStackTrace();
			return false;
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public File getFile(int fileId) {

		byte[] data = new byte[1_000_000];
		String filename = "";
		String sql = "select * from file_table where file_id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fileId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			filename = rs.getString(3);
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			InputStream is = rs.getBinaryStream(4);
			int length;
			while((length = is.read(data)) > 0) {
				fos.write(data, 0, length);
			}
			fos.close();
			return file;
		} catch (FileNotFoundException e) {
			error("file not found exception in get file");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			error("IO exceptionn in get file");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			trace("there was a sql exception in get file");
			e.printStackTrace();
			return null;
		}
	}
}
