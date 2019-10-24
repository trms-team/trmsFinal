package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.pojo.User.Role;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

public class BenCoHomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8590133292252582859L;
	
	private static ReimbursementService reimbursementService = new ReimbursementServiceImpl();

	/**
     * @see HttpServlet#HttpServlet()
     */
    public BenCoHomeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) (request.getSession().getAttribute("user"));
		
		if (user != null && user.getRoles().contains(Role.BENCO)) {
			ObjectMapper om = new ObjectMapper();
			String name = request.getPathInfo();
			
			if (name.substring(1).equals("pending")) {
				List<Reimbursement> pending = reimbursementService.showBenCoPending(user.getUsername());
				response.getWriter().write(om.writeValueAsString(pending));
			}
			else if (name.substring(1).equals("accepted")) {
				List<Reimbursement> accepted = reimbursementService.showBenCoAccepted(user.getUsername());
				response.getWriter().write(om.writeValueAsString(accepted));
			}
			else if (name.substring(1).equals("rejected")) {
				List<Reimbursement> rejected = reimbursementService.showBenCoRejected(user.getUsername());
				response.getWriter().write(om.writeValueAsString(rejected));
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) (request.getSession().getAttribute("user"));
		
		if (user != null && user.getRoles().contains(Role.BENCO)) {
			ObjectMapper om = new ObjectMapper();
			om.registerModule(new JavaTimeModule());
			
			String body = request.getReader().readLine();
			
			String name = request.getPathInfo();
			
			Reimbursement reimbursement = om.readValue(body, Reimbursement.class);
			
			if (name.substring(1).equals("accept")) {
				System.out.println(reimbursement.toString());
				reimbursementService.acceptReimbursement(reimbursement, user.getRoles());
			}
			else if (name.substring(1).equals("reject")) {
				reimbursementService.rejectReimbursement(reimbursement, user.getRoles(), reimbursement.getRejectedReason());
			}
			else if (name.substring(1).equals("updateAmount")) {
				reimbursementService.updateReimbursementAmount(reimbursement);
			}
		}
		
	}
}
