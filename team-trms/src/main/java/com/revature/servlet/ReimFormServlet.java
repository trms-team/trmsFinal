package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.Reimbursement.EventType;
import com.revature.pojo.Reimbursement.GradeFormat;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;
import com.revature.util.ReimbursementCalculator;

public class ReimFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static ReimbursementService reimbursementService = new ReimbursementServiceImpl();
	
	private static UserService userService = new UserServiceImpl();
	
	private static ReimbursementCalculator reimburseCalculator = new ReimbursementCalculator();
	
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
<<<<<<< HEAD
		User user = userService.getCurrentUser();
		
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
		
		double awardedAmount = reimburseCalculator.calculateAwardedAmount(reimbursementService.getPendingAndAwardedAmounts(user.getUsername()), 
				EventType.valueOf(eventType), Double.parseDouble(cost));
		
		
		LocalDateTime submissionTime = LocalDateTime.now();
		
		LocalDateTime 
		
		Reimbursement newReimbursement = new Reimbursement(1, user.getUsername(),
				email, phone, _____, location, eventName, EventType.valueOf(eventType), 
				description, Double.parseDouble(cost), GradeFormat.valueOf(gradingFormat),
				workRelatedJustification, Double.parseDouble(workHoursMissed), awardedAmount,
				
		)
				
=======
		
>>>>>>> 8f1062ccbfd952fc23148acc8f02fe06f5003e35
	
	}
}