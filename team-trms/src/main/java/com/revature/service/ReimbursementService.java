package com.revature.service;

import java.util.List;

import com.revature.pojo.Reimbursement;

public interface ReimbursementService {
	public List<Reimbursement> showEmployeePending(String username);
}
