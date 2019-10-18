class Reimbursement {
    constructor(reimbursement_id, employeeUsername, email, phone, eventTime, location, eventName, 
            eventType, description, cost, gradingFormat, workRelatedJustification, workHoursMissed,
            awardedAmount, statusId, submissionTime) {
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
}

function displayReimbursements(status, reimbursements) {
    let currTbody = document.getElementById(status + "-table").getElementsByTagName("tbody")[0];
    currTbody.innerHTML = '';

    for (r of reimbursements) {
        let newRow = currTbody.insertRow();

        let cell1 = newRow.insertCell(0);
        let a = document.createElement('a');
        let linkText = document.createTextNode(r.reimbursement_id);
        a.appendChild(linkText);
        a.href=`reimbursement/${r.reimbursement_id}.html`;
        cell1.appendChild(a);

        let cell2 = newRow.insertCell(1);
        cell2.appendChild(document.createTextNode(r.eventName));

        let cell3 = newRow.insertCell(2);
        let text = "";
        if (r.eventType === 'UNIVERSITY_COURSE') {
            text = "University Course";
        }
        else if (r.eventType === 'SEMINAR') {
            text = "Seminar";
        }
        else if (r.eventType === 'CERTIFICATION_PREP_CLASS') {
            text = "Certification Preparation Class";
        }
        else if (r.eventType === 'CERTIFICATION') {
            text = "Certification";
        }
        else if (r.eventType === 'TECHNICAL_TRAINING') {
            text = "Technical Training";
        }
        else {
            text = "Other";
        }
        cell3.appendChild(document.createTextNode(text));

        let cell4 = newRow.insertCell(3);
        cell4.appendChild(document.createTextNode("$" + r.awardedAmount));

        let cell5 = newRow.insertCell(4);
        cell5.appendChild(document.createTextNode(r.submissionTime.monthValue + "/" 
            + r.submissionTime.dayOfMonth + "/" + r.submissionTime.year));
    }
}


function getPendingReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayReimbursements("pending", JSON.parse(xhr.responseText));
            }
            else {
                console.log("failed to retrieve reimbursements");
            }
        }
        else {
            console.log("fetching request");
        }
    }
    xhr.open("GET", "employee-home/pending", true);
    xhr.send();
}

function getAcceptedReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayReimbursements("accepted", JSON.parse(xhr.responseText));
            }
            else {
                console.log("failed to retrieve reimbursements");
            }
        }
        else {
            console.log("fetching request");
        }
    }
    xhr.open("GET", "employee-home/accepted", true);
    xhr.send();
}

function getRejectedReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayReimbursements("rejected", JSON.parse(xhr.responseText));
            }
            else {
                console.log("failed to retrieve reimbursements");
            }
        }
        else {
            console.log("fetching request");
        }
    }
    xhr.open("GET", "employee-home/rejected", true);
    xhr.send();
}


window.onload = function() {
    this.getPendingReimbursements();
    this.document.getElementById("pending-tab").addEventListener("click", getPendingReimbursements, false);
    this.document.getElementById("accepted-tab").addEventListener("click", getAcceptedReimbursements, false);
    this.document.getElementById("rejected-tab").addEventListener("click", getRejectedReimbursements, false);
}