package com.revature.pojo;

import java.time.LocalDateTime;

public class Reimbursement {
	public enum EventType {
		UNIVERSITY_COURSE, SEMINAR, CERTIFICATION_PREP_CLASS, CERTIFICATION,
		TECHNICAL_TRAINING, OTHER;
	}
	
	public enum GradeFormat {
		LETTER, PERCENT;
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
	
	private int statusId;
	
	private LocalDateTime submissionTime;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursement_id, String employeeUsername, String email, String phone,
			LocalDateTime eventTime, String location, String eventName, EventType eventType, String description,
			double cost, GradeFormat gradingFormat, String workRelatedJustification, double workHoursMissed,
			double awardedAmount, int statusId, LocalDateTime submissionTime) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.employeeUsername = employeeUsername;
		this.email = email;
		this.phone = phone;
		this.eventTime = eventTime;
		this.location = location;
		this.eventName = eventName;
		this.eventType = eventType;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.workRelatedJustification = workRelatedJustification;
		this.workHoursMissed = workHoursMissed;
		this.awardedAmount = awardedAmount;
		this.statusId = statusId;
		this.submissionTime = submissionTime;
	}

	public int getReimbursement_id() {
		return reimbursement_id;
	}

	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}

	public String getEmployeeUsername() {
		return employeeUsername;
	}

	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public GradeFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradeFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getWorkRelatedJustification() {
		return workRelatedJustification;
	}

	public void setWorkRelatedJustification(String workRelatedJustification) {
		this.workRelatedJustification = workRelatedJustification;
	}

	public double getWorkHoursMissed() {
		return workHoursMissed;
	}

	public void setWorkHoursMissed(double workHoursMissed) {
		this.workHoursMissed = workHoursMissed;
	}

	public double getAwardedAmount() {
		return awardedAmount;
	}

	public void setAwardedAmount(double awardedAmount) {
		this.awardedAmount = awardedAmount;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(LocalDateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(awardedAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeUsername == null) ? 0 : employeeUsername.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((eventTime == null) ? 0 : eventTime.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + reimbursement_id;
		result = prime * result + statusId;
		result = prime * result + ((submissionTime == null) ? 0 : submissionTime.hashCode());
		temp = Double.doubleToLongBits(workHoursMissed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((workRelatedJustification == null) ? 0 : workRelatedJustification.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(awardedAmount) != Double.doubleToLongBits(other.awardedAmount))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeUsername == null) {
			if (other.employeeUsername != null)
				return false;
		} else if (!employeeUsername.equals(other.employeeUsername))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventTime == null) {
			if (other.eventTime != null)
				return false;
		} else if (!eventTime.equals(other.eventTime))
			return false;
		if (eventType != other.eventType)
			return false;
		if (gradingFormat != other.gradingFormat)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		if (statusId != other.statusId)
			return false;
		if (submissionTime == null) {
			if (other.submissionTime != null)
				return false;
		} else if (!submissionTime.equals(other.submissionTime))
			return false;
		if (Double.doubleToLongBits(workHoursMissed) != Double.doubleToLongBits(other.workHoursMissed))
			return false;
		if (workRelatedJustification == null) {
			if (other.workRelatedJustification != null)
				return false;
		} else if (!workRelatedJustification.equals(other.workRelatedJustification))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursement_id=" + reimbursement_id + ", employeeUsername=" + employeeUsername
				+ ", email=" + email + ", phone=" + phone + ", eventTime=" + eventTime + ", location=" + location
				+ ", eventName=" + eventName + ", eventType=" + eventType + ", description=" + description + ", cost="
				+ cost + ", gradingFormat=" + gradingFormat + ", workRelatedJustification=" + workRelatedJustification
				+ ", workHoursMissed=" + workHoursMissed + ", awardedAmount=" + awardedAmount + ", statusId=" + statusId
				+ ", submissionTime=" + submissionTime + "]";
	}

	
	
}