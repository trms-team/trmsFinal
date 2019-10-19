package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

public class ReimFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static ReimbursementService reimbursementService = new ReimbursementServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimFormServlet() {
        super();
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}
}