package com.revature.util;

import java.util.List;

import com.revature.pojo.Reimbursement.EventType;

public class ReimbursementCalculator {
	public double calculateAwardedAmount(List<Double> reimbursements, EventType
			newEventType, double cost) {
		double sum = 0.0;
		double rate = 0.0;
		
		for (double r : reimbursements) {
			sum += r;
		}
		
		switch(newEventType) {
			case UNIVERSITY_COURSE:
				rate = 0.8;
				break;
			case SEMINAR:
				rate = 0.6;
				break;
			case CERTIFICATION_PREP_CLASS:
				rate = 0.75;
				break;
			case CERTIFICATION:
				rate = 1.0;
				break;
			case TECHNICAL_TRAINING:
				rate = 0.9;
				break;
			default:
				rate = 0.3;
				break;
		}
		
		if ((1000.00 - sum) > (cost * rate)) {
			return Math.floor(cost * rate*100)/100;
		}
		else {
			return Math.floor((1000.00 - sum)*100)/100;
		}
	}
}
