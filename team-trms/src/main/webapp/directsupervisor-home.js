class Reimbursement {
    constructor(reimbursement_id, employeeUsername, email, phone, eventTime, location, eventName, 
            eventType, description, cost, gradingFormat, workRelatedJustification, workHoursMissed,
            awardedAmount, statusId, submissionTime, rejectedReason) {
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
        this.rejectedReason = rejectedReason;
    }
}

let currentReims = [];

