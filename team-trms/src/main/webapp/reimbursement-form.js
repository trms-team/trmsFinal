class Reimbursement {
    constructor(email, phone, eventName, eventType, eventTime, location, 
            description, cost, gradingFormat, workRelatedJustification, workHoursMissed,
            awardedAmount, files, submissionTime) {
        this.email = email;
        this.phone = phone;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventTime = eventTime;
        this.location = location;
        this.description = description;
        this.cost = cost;
        this.gradingFormat = gradingFormat;
        this.workRelatedJustification = workRelatedJustification;
        this.workHoursMissed = workHoursMissed;
        this.awardedAmount = awardedAmount;
        this.files = files;
        this.submissionTime = submissionTime;
    }
}