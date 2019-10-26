package com.revature.pojo;

import java.io.File;
import java.util.List;

public class Form {
	
	private String email;
	private String phone;
	private String eventName;
	private String eventType;
	private String eventTime;
	private String location;
	private String description;
	private String cost;
	private String gradingFormat;
	private String workRelJust;
	private String workHoursMissed;
	private MyFile[] files;

	public Form() {
		super();
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getWorkRelJust() {
		return workRelJust;
	}

	public void setWorkRelJust(String workRelJust) {
		this.workRelJust = workRelJust;
	}

	public String getWorkHoursMissed() {
		return workHoursMissed;
	}

	public void setWorkHoursMissed(String workHoursMissed) {
		this.workHoursMissed = workHoursMissed;
	}

	public MyFile[] getFiles() {
		return files;
	}

	public void setFiles(MyFile[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Form [email=" + email + ", phone=" + phone + ", eventName=" + eventName + ", eventType=" + eventType
				+ ", location=" + location + ", description=" + description + ", cost=" + cost + ", gradingFormat="
				+ gradingFormat + ", workRelJust=" + workRelJust + ", workHoursMissed=" + workHoursMissed + ", files="
				+ files + "]";
	}

	public Form(String email, String phone, String eventName, String eventType, String location, String description,
			String cost, String gradingFormat, String workRelJust, String workHoursMissed, MyFile[] files) {
		super();
		this.email = email;
		this.phone = phone;
		this.eventName = eventName;
		this.eventType = eventType;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.workRelJust = workRelJust;
		this.workHoursMissed = workHoursMissed;
		this.files = files;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((files == null) ? 0 : files.hashCode());
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((workHoursMissed == null) ? 0 : workHoursMissed.hashCode());
		result = prime * result + ((workRelJust == null) ? 0 : workRelJust.hashCode());
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
		Form other = (Form) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
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
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (files == null) {
			if (other.files != null)
				return false;
		} else if (!files.equals(other.files))
			return false;
		if (gradingFormat == null) {
			if (other.gradingFormat != null)
				return false;
		} else if (!gradingFormat.equals(other.gradingFormat))
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
		if (workHoursMissed == null) {
			if (other.workHoursMissed != null)
				return false;
		} else if (!workHoursMissed.equals(other.workHoursMissed))
			return false;
		if (workRelJust == null) {
			if (other.workRelJust != null)
				return false;
		} else if (!workRelJust.equals(other.workRelJust))
			return false;
		return true;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
}
