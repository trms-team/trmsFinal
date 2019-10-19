package com.revature.dao;

import java.io.File;

import com.revature.pojo.Reimbursement;

public interface FileDAO {

	public boolean uploadFile(File file, Reimbursement r);
	
	public File getFile(int fileId);
	
}
