package com.revature.servlet;

import static com.revature.util.LoggerUtil.warn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.pojo.User.Role;
import com.revature.service.ReimbursementServiceImpl;
import com.revature.util.ReimbursementValidator;

public class ReimFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static ReimbursementServiceImpl reimbursementService = new ReimbursementServiceImpl();
	
	private static ReimbursementValidator reimbursementValidator = new ReimbursementValidator();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ReimFormServlet() {
        super();
    }
	
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) (request.getSession().getAttribute("user"));
		
		if (user != null && user.getRoles().contains(Role.EMPLOYEE)) {
			String email = request.getParameter("input-email");
			String phone = request.getParameter("input-phone");
			String eventName = request.getParameter("input-event-name");
			String eventType = request.getParameter("input-event-type");
			String eventTimeStr = request.getParameter("input-event-date-time");
			String location = request.getParameter("input-location");
			String description = request.getParameter("input-description");
			String cost = request.getParameter("input-cost");
			String gradingFormat = request.getParameter("input-grading-format");
			String workRelatedJustification = request.getParameter("input-work-rel-justification");
			String workHoursMissed = request.getParameter("input-work-hours-missed");
			
			Reimbursement checkedReimbursement = reimbursementValidator.validReimbursement(user.getUsername(), email,
					phone, eventName, eventType, eventTimeStr, location, description, cost, 
					gradingFormat, workRelatedJustification, workHoursMissed);
			
			if (checkedReimbursement != null) {
				reimbursementService.addReimbursement(checkedReimbursement);
			}
			else {
				warn("Reimbursement could not be saved");
			}
			
			response.sendRedirect("employee-home.html");
		}
		
	}
}