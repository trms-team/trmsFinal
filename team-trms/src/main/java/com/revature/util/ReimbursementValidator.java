package com.revature.util;

import static com.revature.util.LoggerUtil.warn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.Reimbursement.EventType;
import com.revature.pojo.Reimbursement.GradeFormat;
import com.revature.pojo.Reimbursement.Status;
import com.revature.service.ReimbursementServiceImpl;

public class ReimbursementValidator {
	private static ReimbursementServiceImpl reimbursementService = new ReimbursementServiceImpl();
	
	private static ReimbursementCalculator reimburseCalculator = new ReimbursementCalculator();
	
	
	public Reimbursement validReimbursement(String username, String email, String phone, String eventName, String eventType, 
			String eventTimeStr, String location, String description, String cost,
			String gradingFormat, String workRelatedJustification, String workHoursMissed) {
		
		if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			warn(email + " is an invalid email on server-side");
			return null;
		}
		
		if (!phone.matches("\\d{10}")) {
			warn(phone + " is an invalid phone number on server-side");
			return null;
		}
		
		if ("".equals(eventName) || eventName == null) {
			warn("Event name cannot be empty on server-side");
			return null;
		}
		
		boolean checkIfEventType = false;
		for (EventType e : EventType.values()) {
			if (eventType.equals(e.name())) {
				checkIfEventType = true;
				break;
			}
		}
		if (!checkIfEventType) {
			warn(eventType + " is an invalid event type on server-side");
			return null;
		}
		
		LocalDateTime parsedEventTime;
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			eventTimeStr = eventTimeStr.replace('T', ' ');
			parsedEventTime = LocalDateTime.parse(eventTimeStr, formatter);
		} catch(Exception e) {
			warn(eventTimeStr + " is an invalid date and time on server-side");
			return null;
		}
		
		if ("".equals(location) || location == null) {
			warn("Location cannot be empty on server-side");
			return null;
		}
		
		if ("".equals(description) || description == null) {
			warn("Description cannot be empty on server-side");
			return null;
		}
		
		double parsedCost;
		try {
			parsedCost = Double.parseDouble(cost);
		} catch(NumberFormatException e) {
			warn(cost + " is an invalid cost on server-side");
			return null;
		}
		
		boolean checkIfGradingFormat = false;
		for (GradeFormat g : GradeFormat.values()) {
			if (gradingFormat.equals(g.name())) {
				checkIfGradingFormat = true;
				break;
			}
		}
		if (!checkIfGradingFormat) {
			warn(gradingFormat + " is an invalid grading format on server-side");
			return null;
		}
		
		if ("".equals(workRelatedJustification) || workRelatedJustification == null) {
			warn("Work related justification cannot be empty on server-side");
			return null;
		}
		
		double parsedWorkHoursMissed;
		try {
			parsedWorkHoursMissed = Double.parseDouble(workHoursMissed);
		} catch(NumberFormatException e) {
			warn(workHoursMissed + " is an invalid hour on server-side");
			return null;
		}
		
		double awardedAmount = reimburseCalculator.calculateAwardedAmount(reimbursementService.getPendingAndAwardedAmounts(username), 
				EventType.valueOf(eventType), parsedCost);
		
		
		LocalDateTime submissionTime = LocalDateTime.now();
		
		Reimbursement validReimbursement = new Reimbursement(username, email, phone, parsedEventTime, location,
				eventName, EventType.valueOf(eventType), description, parsedCost, GradeFormat.valueOf(gradingFormat),
				workRelatedJustification, parsedWorkHoursMissed, awardedAmount, submissionTime, Status.PENDING, 
				Status.PENDING, Status.PENDING, null, null, null, null);
		
		return validReimbursement;
	}
}
