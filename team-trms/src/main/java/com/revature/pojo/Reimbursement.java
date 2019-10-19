package com.revature.pojo;

import java.time.LocalDateTime;

public class Reimbursement {
	public enum EventType {
		UNIVERSITY_COURSE, SEMINAR, CERTIFICATION_PREP_CLASS, CERTIFICATION,
		TECHNICAL_TRAINING, OTHER;
	}
	
	public enum GradeFormat {
		LETTER, PERCENT, PRESENTATION;
	}
	
	public enum Status {
		PENDING, REJECTED, ACCEPTED;
	}
	
	private int reimbursement_id;
	
	private String employeeUsername;
	
	private String email;
	
	private String phone;
	
	private LocalDateTime eventTime;
	
	private String location;
	
	private String eventName;
	
	private EventType eventType;
	
	private String description;
	
	private double cost;
	
	private GradeFormat gradingFormat;
	
	private String workRelatedJustification;
	
	private double workHoursMissed;
	
	private double awardedAmount;
	
	private LocalDateTime submissionTime;
	
	private Status directSupervisorStatus;
	
	private Status departmentHeadStatus;
	
	private Status bencoStatus;
	
	private String rejectedReason;
	
	private LocalDateTime directSupervisorTime;
	
	private LocalDateTime departmentHeadTime;
	
	private LocalDateTime bencoTime;
	
	public Reimbursement() {
		super();
	}

	
}