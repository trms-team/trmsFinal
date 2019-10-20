package com.revature.service;

import static com.revature.util.LoggerUtil.info;

import java.util.LinkedList;
import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {
	private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
	
	@Override
	public void addReimbursement(Reimbursement reimbursement) {
		reimbursementDAO.createReimbursement(reimbursement);
		info("added reimbursement by employee " + reimbursement.getEmployeeUsername());
	}
	
	@Override
	public List<Reimbursement> showEmployeePending(String username) {
		info("showing pending requests by employee " + username);
		return reimbursementDAO.getPendingReimbursementsByEmployee(username);
	}

	@Override
	public List<Reimbursement> showEmployeeAccepted(String username) {
		info("showing accepted requests by employee " + username);
		return reimbursementDAO.getAcceptedReimbursementsByEmployee(username);
	}

	@Override
	public List<Reimbursement> showEmployeeRejected(String username) {
		info("showing rejected requests by employee " + username);
		return reimbursementDAO.getRejectedReimbursementsByEmployee(username);
	}

	@Override
	public void submitReimbursement(Reimbursement newReimbursement) {
		info("submitting reimbursement id#" + newReimbursement.getReimbursement_id());
		reimbursementDAO.createReimbursement(newReimbursement);
	}
	
	@Override
	public List<Double> getPendingAndAwardedAmounts(String username) {
		List<Reimbursement> reimbursements = showEmployeePending(username);
		reimbursements.addAll(showEmployeeAccepted(username));
		
		List<Double> amounts = new LinkedList<>();
		
		for (Reimbursement r : reimbursements) {
			amounts.add(r.getAwardedAmount());
		}
		
		return amounts;
	}
	
}
