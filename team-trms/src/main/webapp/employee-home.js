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

function displayReimbursements(status, reimbursements) { 
    currentReims = [];
    let currTbody = document.getElementById(status + "-table").getElementsByTagName("tbody")[0];
    currTbody.innerHTML = '';

    for (r of reimbursements) {
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
        cell2.appendChild(document.createTextNode(r.eventName));

        let cell3 = newRow.insertCell(2);
        let text = formatEventType(r.eventType);
        cell3.appendChild(document.createTextNode(text));

        let cell4 = newRow.insertCell(3);
        cell4.appendChild(document.createTextNode("$" + r.awardedAmount.toFixed(2)));

        let cell5 = newRow.insertCell(4);
        cell5.appendChild(document.createTextNode(r.submissionTime.monthValue + "/" 
            + r.submissionTime.dayOfMonth + "/" + r.submissionTime.year));
    }
}

function showSingleRow(table, key, value) {
    let row = table.insertRow();
    row.insertCell().innerHTML = `<b>${key}</b>`;
    row.insertCell().innerHTML = value;
}

function displaySingleReimbursement(id) {
    for (c of currentReims) {
        if (c.reimbursement_id == id) {
            let reimTitle = document.createElement("h5");
            reimTitle.innerHTML = `Reimbursement ID#${c.reimbursement_id}`;

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
            let fixedTime = formatTime(r.eventTime.hour, r.eventTime.minute);
            showSingleRow(modalTable, "Event Time", 
                `${r.eventTime.monthValue}/${r.eventTime.dayOfMonth}/${r.eventTime.year} - ${fixedTime}`);
            showSingleRow(modalTable, "Location", c.location);
            showSingleRow(modalTable, "Description", c.description);
            showSingleRow(modalTable, "Cost", `$${c.cost.toFixed(2)}`);
            let fixedGradingFormat = formatGradingFormat(c.gradingFormat);
            showSingleRow(modalTable, "Grade Format", fixedGradingFormat);
            showSingleRow(modalTable, "Work Related Justification", c.workRelatedJustification);
            showSingleRow(modalTable, "Work Hours Missed", c.workHoursMissed.toFixed(2));
            showSingleRow(modalTable, "Awarded Amount", `$${c.awardedAmount.toFixed(2)}`);
            showSingleRow(modalTable, "Date Submitted",
                `${r.submissionTime.monthValue}/${r.submissionTime.dayOfMonth}/${r.submissionTime.year}`);
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