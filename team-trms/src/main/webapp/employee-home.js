class Reimbursement {
    constructor(reimbursementId, employeeUsername, email, phone, eventTime, location, eventName, 
            eventType, description, cost, gradingFormat, workRelatedJustification, workHoursMissed,
            awardedAmount, submissionTime, rejectedReason) {
        this.reimbursementId = reimbursementId;
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

function displayReimbursements(status, reimbursements) { 
    currentReims = [];
    let currTbody = document.getElementById(status + "-table").getElementsByTagName("tbody")[0];
    currTbody.innerHTML = '';

    for (r of reimbursements) {
        currentReims.push(r);

        let newRow = currTbody.insertRow();

        let cell1 = newRow.insertCell(0);
        let a = document.createElement('a');
        let linkText = document.createTextNode(r.reimbursementId);
        a.appendChild(linkText);
        a.href="#";
        a.setAttribute("id", `reim-${r.reimbursementId}`);
        a.setAttribute("data-toggle", "modal");
        a.setAttribute("data-target", "#info-modal");
        cell1.appendChild(a);

        let cell2 = newRow.insertCell(1);
        cell2.appendChild(document.createTextNode(r.eventName));

        let cell3 = newRow.insertCell(2);
        let text = formatEventType(r.eventType);
        cell3.appendChild(document.createTextNode(text));

        let cell4 = newRow.insertCell(3);
        cell4.appendChild(document.createTextNode("$" + r.awardedAmount.toFixed(2)));

        let cell5 = newRow.insertCell(4);
        cell5.appendChild(document.createTextNode(r.submissionTime[1] + "/" 
            + r.submissionTime[2] + "/" + r.submissionTime[0]));
    }
}

function showSingleRow(table, key, value) {
    let row = table.insertRow();
    row.insertCell().innerHTML = `<b>${key}</b>`;
    row.insertCell().innerHTML = value;
}

function displaySingleReimbursement(id) {
    for (c of currentReims) {
        if (c.reimbursementId == id) {
            let reimTitle = document.createElement("h5");
            reimTitle.innerHTML = `Reimbursement ID#${c.reimbursementId}`;

            // To make sure it doesn't keep on appending more
            let modalHeader = document.getElementById("modal-header");
            while (modalHeader.firstChild) {
                modalHeader.removeChild(modalHeader.firstChild);
            }

            modalHeader.appendChild(reimTitle);

            let modalTable = document.getElementById("modalTable");
            
            while (modalTable.firstChild) {
                modalTable.removeChild(modalTable.firstChild);
            }
            
            if (c.rejectedReason !== null) {
                showSingleRow(modalTable, "Reason Rejected", c.rejectedReason);
            }
            showSingleRow(modalTable, "Username", c.employeeUsername);
            showSingleRow(modalTable, "Email", c.email);
            showSingleRow(modalTable, "Phone", c.phone);
            showSingleRow(modalTable, "Event Name", c.eventName);
            let fixedEventType = formatEventType(c.eventType);
            showSingleRow(modalTable, "Event Type", fixedEventType);
            let fixedTime = formatTime(r.eventTime[3], r.eventTime[4]);
            showSingleRow(modalTable, "Event Time", 
                `${c.eventTime[1]}/${c.eventTime[2]}/${c.eventTime[0]} - ${fixedTime}`);
            showSingleRow(modalTable, "Location", c.location);
            showSingleRow(modalTable, "Description", c.description);
            showSingleRow(modalTable, "Cost", `$${c.cost.toFixed(2)}`);
            let fixedGradingFormat = formatGradingFormat(c.gradingFormat);
            showSingleRow(modalTable, "Grade Format", fixedGradingFormat);
            showSingleRow(modalTable, "Work Related Justification", c.workRelatedJustification);
            showSingleRow(modalTable, "Work Hours Missed", c.workHoursMissed.toFixed(2));
            showSingleRow(modalTable, "Awarded Amount", `$${c.awardedAmount.toFixed(2)}`);
            showSingleRow(modalTable, "Date Submitted",
                `${c.submissionTime[1]}/${c.submissionTime[2]}/${c.submissionTime[0]}`);
            break;
        }
    }
}

function getPendingReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // In case a non-employee tries to access
                if (xhr.responseText === "") {
                	window.location.href = "unauthorized.html";
                }
                else {
                	document.getElementById("hide").style.visibility = "visible";
                    displayReimbursements("pending", JSON.parse(xhr.responseText));
                }
                
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

function formatEventType(eventType) {	
    if (eventType === "UNIVERSITY_COURSE") {	
        return "University Course";	
    }	
    else if (eventType === "SEMINAR") {	
        return "Seminar";	
    }	
    else if (eventType === "CERTIFICATION_PREP_CLASS") {	
        return "Certification Preparation Class";	
    }	
    else if (eventType === "CERTIFICATION") {	
        return "Certification";	
    }	
    else if (eventType === "TECHNICAL_TRAINING") {	
        return "Technical Training";	
    }	
    else {	
        return "Other";	
    }	
}	

function formatTime(hour, minute) {	
    let ampm;	
    let newHour = hour;	

    if (hour == 0) {	
        newHour = 12;	
        ampm = "AM";	
    }	
    else if (hour > 0 && hour < 12) {	
        ampm = "AM";	
    }	
    else if (hour == 12) {	
        ampm = "PM";	
    }	
    else {	
        newHour = hour - 12;	
        ampm = "PM";	
    }	
    let newMinute = (minute < 10) ? `${minute}0` : minute;	
    return `${newHour}:${newMinute} ${ampm}`;	
}	

function formatGradingFormat(gradingFormat) {	
    if (gradingFormat === "LETTER") {	
        return "A - F";	
    }	
    else if (gradingFormat === "PERCENT") {	
        return "0 - 100";	
    }	
    else {	
        return "Presentation";	
    }	
}


function getAcceptedReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // In case a non-employee tries to access
                if (xhr.responseText === "") {
                	window.location.href = "unauthorized.html";
                }
                else {
                	document.getElementById("hide").style.visibility = "visible";
                    displayReimbursements("accepted", JSON.parse(xhr.responseText));
                }
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
                // In case a non-employee tries to access
                if (xhr.responseText === "") {
                	window.location.href = "unauthorized.html";
                	
                }
                else {
                	document.getElementById("hide").style.visibility = "visible";
                    displayReimbursements("rejected", JSON.parse(xhr.responseText));
                }
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

document.addEventListener("click", function(e) {
    if (e.target && e.target.id.includes("reim")) {
        displaySingleReimbursement(e.target.id.substring(5));
    }
});

window.onload = function() {
    this.getPendingReimbursements();
    this.document.getElementById("pending-tab").addEventListener("click", getPendingReimbursements, false);
    this.document.getElementById("accepted-tab").addEventListener("click", getAcceptedReimbursements, false);
    this.document.getElementById("rejected-tab").addEventListener("click", getRejectedReimbursements, false);
}