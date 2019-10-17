package com.revature.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class EmpHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ReimbursementService reimbursementService = new ReimbursementServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpHomeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("employee-home.html");
		view.forward(request, response);
		
		User user = (User) (request.getSession().getAttribute("user"));
		
		List<Reimbursement> pending = reimbursementService.showEmployeePending(user.getUsername());
		
		for (Reimbursement r : pending) {
			System.out.println(r.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
