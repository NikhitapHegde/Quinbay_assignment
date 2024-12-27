const apiUrl = "http://localhost:8080/api/todos";

// Function to add a new todo item
function addTodo() {
    const todoInput = document.getElementById('todo-input');
    const todoText = todoInput.value.trim();

    if (todoText === "") return;  // Do not add empty tasks

    // Create a new todo object
    const todo = {
        task: todoText,
        completed: false
    };

    // Send a POST request to the backend to save the new todo
    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todo)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to add todo');
        }
        return response.json();
    })
    .then(data => {
        console.log('Todo added:', data); // Debugging line
        // Add the task to the list
        createTodoItem(data);
        updateTaskCounts();  // Update task counts after adding a new todo
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to add todo. Please try again.');
    });

    // Clear the input fields
    todoInput.value = "";
}

// Function to create the todo item element
function createTodoItem(todo) {
    const todoList = document.getElementById('todo-list');
    
    const todoItem = document.createElement('div');
    todoItem.classList.add('todo-item');
    todoItem.setAttribute('data-id', todo.id);  // Store the todo id for future updates/deletions

    const todoTextElement = document.createElement('span');
    todoTextElement.textContent = todo.task;
    todoItem.appendChild(todoTextElement);

    // Add the complete button
    const completeBtn = document.createElement('button');
    completeBtn.classList.add('complete-btn');
    completeBtn.textContent = '✔';
    completeBtn.addEventListener('click', () => markComplete(todo.id));
    todoItem.appendChild(completeBtn);

    // Add the delete button
    const deleteBtn = document.createElement('button');
    deleteBtn.classList.add('delete-btn');
    deleteBtn.textContent = '❌';
    deleteBtn.addEventListener('click', () => deleteTodo(todo.id));
    todoItem.appendChild(deleteBtn);

    // Add the todo item to the list
    todoList.appendChild(todoItem);
}

// Function to mark a task as completed
function markComplete(id) {
    const todoItem = document.querySelector(`[data-id="${id}"]`);

    // Send a PUT request to the backend to update the completed status
    fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ completed: true })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Todo marked complete:', data); // Debugging line
        todoItem.classList.add('completed');
        updateTaskCounts(); // Update task counts after marking as complete
    })
    .catch(error => console.error('Error:', error));
}

// Function to delete a todo item
function deleteTodo(id) {
    const todoItem = document.querySelector(`[data-id="${id}"]`);

    // Send a DELETE request to the backend to remove the todo
    fetch(`${apiUrl}/${id}`, {
        method: 'DELETE'
    })
    .then(() => {
        todoItem.remove();
        updateTaskCounts(); // Update task counts after deleting a todo
    })
    .catch(error => console.error('Error:', error));
}

// Listen for the Enter key press to add a todo
document.getElementById('todo-input').addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        addTodo();
    }
});

// Add event listener to the + button (assuming its ID is 'add-btn')
document.getElementById('add-btn').addEventListener('click', () => {
    console.log('Add button clicked');
    addTodo();
});

// Function to update the task counts (completed and total)
function updateTaskCounts() {
    const completedCount = document.querySelectorAll('.todo-item.completed').length;
    const totalCount = document.querySelectorAll('.todo-item').length;
    const progress = totalCount === 0 ? 0 : Math.round((completedCount / totalCount) * 100);

    document.getElementById('completed-count').textContent = completedCount;
    document.getElementById('total-count').textContent = totalCount;
    document.getElementById('progress').textContent = progress + '%';
}

// Fetch and display existing todos on page load
window.onload = () => {
    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch todos');
            }
            return response.json();
        })
        .then(data => {
            data.forEach(todo => createTodoItem(todo));
            updateTaskCounts();  // Update task counts after loading existing todos
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to fetch todos. Please check your backend server.');
        });
};
