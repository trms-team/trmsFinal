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
import com.revature.pojo.Reimbursement.Status;
import com.revature.pojo.User.Role;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;
import com.revature.util.ReimbursementCalculator;

public class ReimFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static ReimbursementServiceImpl reimbursementService = new ReimbursementServiceImpl();
	
	private static UserService userService = new UserServiceImpl();
	
	private static ReimbursementCalculator reimburseCalculator = new ReimbursementCalculator();
	
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
		User user = userService.getCurrentUser();
		
		if (user != null && user.getRoles().contains(Role.EMPLOYEE)) {
			String email = request.getParameter("input-email");
			String phone = request.getParameter("input-phone");
			System.out.println(phone);
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
			
			
			//LocalDateTime eventTime = ___;
			
			LocalDateTime submissionTime = LocalDateTime.now();
			
			/*Reimbursement newReimbursement = new Reimbursement(1, user.getUsername(),
					email, phone, eventTime, location, eventName, EventType.valueOf(eventType), 
					description, Double.parseDouble(cost), GradeFormat.valueOf(gradingFormat),
					workRelatedJustification, Double.parseDouble(workHoursMissed), awardedAmount,
					submissionTime, Status.PENDING, Status.PENDING, Status.PENDING, null,
					null, null, null);
			)*/
		}
		
	}
}