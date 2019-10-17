package com.revature.dao;

import java.util.List;

import com.revature.pojo.Reimbursement;

public interface ReimbursementDAO {
	public void createReimbursement(Reimbursement reimbursement);
	
	public Reimbursement getReimbursement(int reimbursementId);
	
	public List<Reimbursement> getPendingReimbursementsByEmployee(String username);
	
	public List<Reimbursement> getAllReimbursements();
}
