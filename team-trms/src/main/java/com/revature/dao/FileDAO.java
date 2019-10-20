package com.revature.dao;

import java.io.File;
import java.sql.Connection;

import com.revature.pojo.Reimbursement;

public interface FileDAO {

	public boolean uploadFile(File file, Reimbursement r);
	
	public File getFile(int fileId);
	
	public void setConn(Connection conn);
	
}
