function startTimer() {
    // Record the time when the page has fully loaded
    let loadTime = new Date().getTime();

    // Set to 0 if not present
    let totalDuration = parseInt(sessionStorage.getItem('totalDuration')) || 0;

    setInterval(function() {
        totalDuration += 1000;
        let minutes = Math.floor(totalDuration / 60000);
        let seconds = ((totalDuration % 60000) / 1000).toFixed(0);

        seconds = seconds < 10 ? '0' + seconds : seconds;
        document.getElementById('timerDisplay').textContent = `${minutes}:${seconds}`;
    }, 1000);
}

document.addEventListener("DOMContentLoaded", startTimer);