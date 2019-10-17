package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {
	private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
	
	@Override
	public List<Reimbursement> showEmployeePending(String username) {
		return reimbursementDAO.getPendingReimbursementsByEmployee(username);
	}

}
