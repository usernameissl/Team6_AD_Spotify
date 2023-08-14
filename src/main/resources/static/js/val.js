function checkTaskCompletion() {
    const taskDataString = sessionStorage.getItem('taskData');
    const foundDataJSON = sessionStorage.getItem('foundData');
    const userDataString = sessionStorage.getItem('userData');

    // Check if userData is not set in session storage
    if (!userDataString) {
        storeFormData();
    }

    if (taskDataString && foundDataJSON) {
        const taskData = JSON.parse(taskDataString);
        const foundData = JSON.parse(foundDataJSON);
        const selectedTaskId = document.getElementById('taskId').value;
        const selectedModelId = document.getElementById('modelId').value;

        const isTaskCompleted = foundData.some(entry => entry.taskId === selectedTaskId && entry.modelId === selectedModelId);

        if (isTaskCompleted) {
            window.alert("You have already completed this task for the selected model. Please choose another task.");
            return false;
        }
    }
    return true;
}


function storeFormData() {
    const name = document.getElementById('name').value;
    const age = document.getElementById('age').value;
    const gender = document.getElementById('gender').value;
    const modelId = document.getElementById('modelId').value;
    const taskId = document.getElementById('taskId').value;

    // Generate a 6 character GUID
    let guid = 'xxxxxx'.replace(/[x]/g, function() {
        return (Math.random() * 36 | 0).toString(36);
    });

    let uniqueName = name + '-' + guid;

    const userData = {
        name: name,
        uniqueName: uniqueName,
        age: age,
        gender: gender,
    };
    sessionStorage.setItem('userData', JSON.stringify(userData));

    const taskData = {
        modelId: modelId,
        taskId: taskId
    }
    sessionStorage.setItem('taskData', JSON.stringify(taskData));

    const selectedData = {
        modelId: modelId,
        taskId: taskId
    }
    sessionStorage.setItem('selectedData', JSON.stringify(selectedData));
}

function populateFields() {
    const userDataString = sessionStorage.getItem('userData');
    const taskDataString = sessionStorage.getItem('taskData');
    const foundDataJSON = sessionStorage.getItem('foundData');
    if (userDataString) {
        const userData = JSON.parse(userDataString);
        const nameInput = document.getElementById('name');
        const ageInput = document.getElementById('age');
        const genderSelect = document.getElementById('gender');

        nameInput.value = userData.name || '';
        ageInput.value = userData.age || '';
        genderSelect.value = userData.gender || '';

        nameInput.disabled = userData.name ? true : false;
        ageInput.disabled = userData.age ? true : false;
        genderSelect.disabled = userData.gender ? true : false;
    }

    if (foundDataJSON) {
        const foundData = JSON.parse(foundDataJSON);
        const modelId = document.getElementById('modelId').value;
        const taskDropdown = document.getElementById('taskId');

        // Disable completed tasks for the selected model
        foundData.forEach(entry => {
            const option = taskDropdown.querySelector(`option[value="${entry.taskId}"]`);
            
            if (entry.modelId === modelId) {
                if (option) {
                    option.disabled = true;
                }
            } else {
                if (option) {
                    option.disabled = false;
                }
            }
        });
    }
}
window.onload = function() {
    sessionStorage.removeItem('totalDuration');
    populateFields();
    checkTaskCompletion();
};

var modelSelect = document.getElementById("modelId");
var taskSelect = document.getElementById("taskId");
const foundDataJSON = sessionStorage.getItem('foundData');
modelSelect.addEventListener("change", function() {
    const foundData = JSON.parse(foundDataJSON);
    const modelId = document.getElementById('modelId').value;
    const taskDropdown = document.getElementById('taskId');

    // Disable completed tasks for the selected model
    foundData.forEach(entry => {
        const option = taskDropdown.querySelector(`option[value="${entry.taskId}"]`);
        
        if (entry.modelId === modelId) {
            if (option) {
                option.disabled = true;
            }
        } else {
            if (option) {
                option.disabled = false;
            }
        }
    });
});

//clicking that will refresh the form and clear sessionStorage
var completeButton = document.getElementById("completeButton");
const nameInput = document.getElementById("name");
const ageInput = document.getElementById("age");
const genderSelect = document.getElementById('gender');
completeButton.addEventListener("click", function() {
    sessionStorage.clear();
    nameInput.value = "";
    ageInput.value = "";
    nameInput.disabled = false;
    ageInput.disabled = false;
    genderSelect.disabled = false;
    const taskDropdown = document.getElementById('taskId');
    const allOptions = taskDropdown.querySelectorAll('option');
    allOptions.forEach(option => {
        option.disabled = false;
    });
});
