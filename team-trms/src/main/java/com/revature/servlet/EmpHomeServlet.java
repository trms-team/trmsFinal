package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.pojo.User.Role;
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) (request.getSession().getAttribute("user"));
		
		if (user != null && user.getRoles().contains(Role.EMPLOYEE)) {
			ObjectMapper om = new ObjectMapper();
			String name = request.getPathInfo();
			
			if (name.substring(1).equals("pending")) {
				List<Reimbursement> pending = reimbursementService.showEmployeePending(user.getUsername());
				response.getWriter().write(om.writeValueAsString(pending));
			}
			else if (name.substring(1).equals("accepted")) {
				List<Reimbursement> accepted = reimbursementService.showEmployeeAccepted(user.getUsername());
				response.getWriter().write(om.writeValueAsString(accepted));
			}
			else if (name.substring(1).equals("rejected")) {
				List<Reimbursement> rejected = reimbursementService.showEmployeeRejected(user.getUsername());
				response.getWriter().write(om.writeValueAsString(rejected));
			}	
		}
	
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
