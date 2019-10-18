package com.revature.service;

import static com.revature.util.LoggerUtil.info;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {
	private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
	
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

	
	
}
