package com.revature.service;

import java.util.List;

import com.revature.pojo.Reimbursement;

public interface ReimbursementService {
	public void addReimbursement(Reimbursement reimbursement);
	
	public List<Reimbursement> showEmployeePending(String username);
	
	public List<Reimbursement> showEmployeeAccepted(String username);
	
	public List<Reimbursement> showEmployeeRejected(String username);
	
	public void submitReimbursement(Reimbursement newReimbursement);
	
	public List<Double> getPendingAndAwardedAmounts(String username);
}
