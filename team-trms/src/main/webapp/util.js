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
