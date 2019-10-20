function calculateReim() {
    let e = document.getElementById("input-event-type");
    let eventType = e.options[e.selectedIndex].value;
    let cost = document.getElementById("input-cost").value;

    let rate = 0.0;

    if (eventType === 'UNIVERSITY_COURSE') {
        rate = 0.8;
    }
    else if (eventType === 'SEMINAR') {
        rate = 0.6;
    }
    else if (eventType === 'CERTIFICATION_PREP_CLASS') {
        rate = 0.75;
    }
    else if (eventType === 'CERTIFICATION') {
        rate = 1.0;
    }
    else if (eventType === 'TECHNICAL_TRAINING') {
        rate = 0.9;
    }
    else {
        rate = 0.3;
    }

    // For truncating after two decimal points
    let reimAmount = cost * rate;
    let re = new RegExp('^-?\\d+(?:\.\\d{0,' + (2 || -1) + '})?');
    
    reimAmount = `$${reimAmount.toString().match(re)[0]}`;

    let indexDecimalPoint = reimAmount.indexOf(".");
    
    if (indexDecimalPoint === -1) {
    	reimAmount += ".00";
    }
    else if (reimAmount.length - 2 === indexDecimalPoint) {
    	reimAmount += "0";
    }
    
    document.getElementById("read-reimburse").value = reimAmount;
}


window.onload = function() {
    this.document.getElementById("input-event-type").addEventListener("change", calculateReim, false);
    this.document.getElementById("input-cost").addEventListener("change", calculateReim, false);
};