package com.revature.dao;

import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User.Role;

public interface ReimbursementDAO {
	public void createReimbursement(Reimbursement reimbursement);
	
	public Reimbursement getReimbursement(int reimbursementId);
	
	public List<Reimbursement> getPendingReimbursementsByEmployee(String username);
	
	public List<Reimbursement> getAcceptedReimbursementsByEmployee(String username);
	
	public List<Reimbursement> getRejectedReimbursementsByEmployee(String username);
	
	public List<Reimbursement> getPendingReimbursementsBySupervisor(String username);
	
	public List<Reimbursement> getInProgressReimbursementsBySupervisor(String username);
	
	public List<Reimbursement> getAcceptedReimbursementsBySupervisor(String username);
	
	public List<Reimbursement> getRejectedReimbursementsBySupervisor(String username);
	
	public List<Reimbursement> getPendingReimbursementsByDepartmentHead(String username);
	
	public List<Reimbursement> getInProgressReimbursementsByDepartmentHead(String username);
	
	public List<Reimbursement> getAcceptedReimbursementsByDepartmentHead(String username);
	
	public List<Reimbursement> getRejectedReimbursementsByDepartmentHead(String username);
	
	public List<Reimbursement> getPendingReimbursementsByBenCo(String username);
	
	public List<Reimbursement> getAcceptedReimbursementsByBenCo(String username);
	
	public List<Reimbursement> getRejectedReimbursementsByBenCo(String username);
	
	public List<Reimbursement> getAllReimbursements();
	
	public void updateReimbursementToAccepted(Reimbursement reimbursement, List<Role> roles);
	
	public void updateReimbursementToRejected(Reimbursement reimbursement, List<Role> roles, String reasonRejected);

	public void updateReimbursementAmount(Reimbursement reimbursement);
}
