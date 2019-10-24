package com.revature.service;

import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User.Role;

public interface ReimbursementService {
	public void addReimbursement(Reimbursement reimbursement);
	
	public List<Reimbursement> showEmployeePending(String username);
	
	public List<Reimbursement> showEmployeeAccepted(String username);
	
	public List<Reimbursement> showEmployeeRejected(String username);
	
	public List<Reimbursement> showSupervisorPending(String username);
	
	public List<Reimbursement> showSupervisorInProgress(String username);
	
	public List<Reimbursement> showSupervisorAccepted(String username);
	
	public List<Reimbursement> showSupervisorRejected(String username);
	
	public List<Reimbursement> showDepartmentHeadPending(String username);
	
	public List<Reimbursement> showDepartmentHeadInProgress(String username);
	
	public List<Reimbursement> showDepartmentHeadAccepted(String username);
	
	public List<Reimbursement> showDepartmentHeadRejected(String username);
	
	public List<Reimbursement> showBenCoPending(String username);
	
	public List<Reimbursement> showBenCoAccepted(String username);
	
	public List<Reimbursement> showBenCoRejected(String username);
	
	public void submitReimbursement(Reimbursement newReimbursement);
	
	public void acceptReimbursement(Reimbursement reimbursement, List<Role> accepterRoles);
	
	public void rejectReimbursement(Reimbursement reimbursement, List<Role> rejecterRoles, String reasonRejected);
	
	public void updateReimbursementAmount(Reimbursement reimbursement);
	
	public List<Double> getPendingAndAwardedAmounts(String username);
}
