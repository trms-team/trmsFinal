class Reimbursement {
    constructor(reimbursement_id, employeeUsername, email, phone, eventTime, location, eventName, 
            eventType, description, cost, gradingFormat, workRelatedJustification, workHoursMissed,
            awardedAmount, submissionTime, rejectedReason) {
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
        this.submissionTime = submissionTime;
        this.rejectedReason = rejectedReason;
    }
}

let currentReims = [];

function displayReimbursements(status, reimbursements){
	currentReims = [];
	let currTbody = document.getElementById(status + "-table").getElementsByTagName("tbody")[0];
	currTbody.innerHTML = '';
	
	for(r of reimbursements){
		currentReims.push(r);

        let newRow = currTbody.insertRow();

        let cell1 = newRow.insertCell(0);
        let a = document.createElement('a');
        let linkText = document.createTextNode(r.reimbursement_id);
        a.appendChild(linkText);
        a.href="#";
        a.setAttribute("id", `reim-${r.reimbursement_id}`);
        a.setAttribute("data-toggle", "modal");
        a.setAttribute("data-target", "#info-modal");
        cell1.appendChild(a);
        
        let cell2 = newRow.insertCell(1);
        cell2.appendChild(document.createTextNode(r.employeeUsername)));
        
        let cell3 = newRow.insertCell(2);
        cell3.appendChild(document.createTextNode(r.eventName));
        
        let cell4 = newRow.insertCell(3);
        cell4.appendChild(document.createTextNode(r.awardedAmount));
        
        let cell5 = 
	}
}

