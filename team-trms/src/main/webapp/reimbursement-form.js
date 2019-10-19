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
        this.files = files;
        this.awardedAmount = awardedAmount;
        this.submissionTime = submissionTime;
    }
}



function submitForm(){
	let newReimbursement = new Reimbursement(
			this.document.getElementById("input-email").value,
			this.document.getElementById("input-phone").value,
			this.document.getElementById("input-event-name").value,
			this.document.getElementById("input-event-type").value,
			this.document.getElementById("input-event-date-time").value,
			this.document.getElementById("input-location").value,
			this.document.getElementById("input-description").value,
			this.document.getElementById("input-cost").value,
			this.document.getElementById("input-grading-format").value,
			this.document.getElementById("input-work-rel-justification").value,
			this.document.getElementById("input-work-hours-missed").value,
			this.document.getElementById("input-files").value,
			
			
	)
	
}

window.onload() = function(){
	this.document.getElementById("submit-btn").addEventListener("click", submitForm);
}