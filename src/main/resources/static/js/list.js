function filterTable() {
    const searchInputValue = document.querySelector('#searchInput').value.toLowerCase();

    const lectRows = document.querySelectorAll('#taskTable tbody tr');

    lectRows.forEach(function (row) {
        const taskId = row.cells[0].textContent.toLowerCase();
        const taskInput = row.querySelector('.task-name'); // Get the input element with class 'task-name'
        const taskName = taskInput.value.toLowerCase();   // Extract the value of that input

        if (taskId.includes(searchInputValue) || taskName.includes(searchInputValue)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}


function confirmDelete() {
    return confirm('Are you sure you want to delete this task?');
}
$(document).ready(function () {
    $('#taskTable').DataTable({
        searching: false
    });
});

document.addEventListener('DOMContentLoaded', function() {

    // Add click event to edit button
    document.querySelectorAll('.edit-button').forEach(function(button) {
        button.addEventListener('click', function(event) {
            event.preventDefault();

            let row = event.target.closest('tr');
            let input = row.querySelector('.task-name');
            let saveButton = row.querySelector('.save-button');

            input.readOnly = false;
            input.focus();
            saveButton.style.display = 'block';
        });
    });

    // Add click event to save button
    document.querySelectorAll('.save-button').forEach(function(button) {
        button.addEventListener('click', function(event) {
            let row = event.target.closest('tr');
            let input = row.querySelector('.task-name');
            let taskId = row.querySelector('td:first-child').textContent.trim();

            saveTaskName(taskId, input.value);

            input.readOnly = true;
            event.target.style.display = 'none';
        });
    });
});

function saveTaskName(id, name) {
    fetch(`/admin/task/update/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Task updated successfully!");
        } else {
            alert(data.message);  // Show the error message from the server
        }
    });
}

// create
$(document).ready(function(){
    $(".create").click(function(e){
        e.preventDefault();

        const newRow = `
            <tr id="newTaskRow">
                <td>New</td>
                <td style="display: flex; justify-content: center; align-items: center; width: 100%;">
                    <input type="text" class="task-name" value="Enter the task name here"/>
                    <a href="javascript:void(0);" class="save-button new-save-button">
                        <img src="/image/save.svg" alt="Save">
                    </a>
                </td>
                <td></td>
            </tr>`;
        $("#taskTable tbody").append(newRow);
    });

    // Logic to save the new task when the save button is clicked
    $(document).on('click', '.new-save-button', function(){
        const taskName = $(this).siblings(".task-name").val();
        if(taskName) {
            // Send the data to the server using AJAX
            $.ajax({
                type: "POST",
                url: "/admin/task/create",
                contentType: "application/json",
                data: JSON.stringify({ name: taskName }),
                success: function(response) {
                    if(response.success) {
                        alert(response.message);  // Display the success message
                        location.reload();  // Refresh the page to see the new task
                    } else {
                        alert(response.message);  // Display the error message
                    }
                },
                error: function() {
                    alert('Error communicating with the server.');
                }
            });
        } else {
            alert('Please enter a task name.');
        }
    });
});

