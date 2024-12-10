document.getElementById('add-btn').addEventListener('click', addTodo);
document.getElementById('clear-completed').addEventListener('click', clearCompleted);
document.getElementById('search-input').addEventListener('input', searchTasks); 

let completedCount = 0;
let totalCount = 0;

//Function to add a new todo item
function addTodo() {
    const todoInput = document.getElementById('todo-input');
    const todoText = todoInput.value.trim();
    const dueDateInput = document.getElementById('due-date-input');
    const dueDate = dueDateInput.value;

    if (todoText === "") return;  // Do not add empty tasks

    //Create a new todo item
    const todoItem = document.createElement('div');
    todoItem.classList.add('todo-item');

    //Add the task text
    const todoTextElement = document.createElement('span');
    todoTextElement.textContent = todoText;
    todoItem.appendChild(todoTextElement);

    //Display the due date if provided
    if (dueDate) {
        const dueDateElement = document.createElement('span');
        dueDateElement.classList.add('due-date');
        dueDateElement.textContent = `Due: ${new Date(dueDate).toLocaleDateString()}`;
        todoItem.appendChild(dueDateElement);

        //Check if the task is overdue and highlight it
        const currentDate = new Date().toISOString().split('T')[0];
        if (dueDate < currentDate) {
            dueDateElement.classList.add('overdue');
        }
    }

    //Add the complete button
    const completeBtn = document.createElement('button');
    completeBtn.classList.add('complete-btn');
    completeBtn.textContent = '✔';
    completeBtn.addEventListener('click', markComplete);
    todoItem.appendChild(completeBtn);

    //Add the delete button
    const deleteBtn = document.createElement('button');
    deleteBtn.classList.add('delete-btn');
    deleteBtn.textContent = '❌';
    deleteBtn.addEventListener('click', deleteTodo);
    todoItem.appendChild(deleteBtn);

    //Add the todo item to the list with animation
    document.getElementById('todo-list').appendChild(todoItem);
    todoItem.style.opacity = 0;
    setTimeout(() => {
        todoItem.style.transition = 'opacity 0.5s';
        todoItem.style.opacity = 1;
    }, 10);

    //Increment total tasks count
    totalCount++;

    //Update the displayed task counts
    updateTaskCounts();

    //Clear the input fields
    todoInput.value = "";
    dueDateInput.value = "";
}

//Function to mark a task as completed
function markComplete(event) {
    const todoItem = event.target.parentElement;
    todoItem.classList.toggle('completed');
    updateTaskCounts();
}

//Function to delete a task
function deleteTodo(event) {
    const todoItem = event.target.parentElement;
    todoItem.style.opacity = 0;
    setTimeout(() => todoItem.remove(), 300); 

    //Decrement total task count
    totalCount--;

    //Update completed count
    updateTaskCounts();
}

//Function to update the task counts (completed and total)
function updateTaskCounts() {
    // Count completed tasks
    completedCount = document.querySelectorAll('.todo-item.completed').length;

    //Update the displayed counts for completed and total tasks
    document.getElementById('completed-count').textContent = completedCount;
    document.getElementById('total-count').textContent = totalCount;

    //Update progress percentage
    let progress = (totalCount === 0) ? 0 : Math.round((completedCount / totalCount) * 100);
    document.getElementById('progress').textContent = progress + '%';
}

//Function to clear completed tasks
function clearCompleted() {
    const completedItems = document.querySelectorAll('.todo-item.completed');
    completedItems.forEach(item => item.remove());
    totalCount -= completedItems.length;
    completedCount = 0;
    updateTaskCounts();
}

//Function to filter tasks based on the search query
function searchTasks() {
    const searchQuery = document.getElementById('search-input').value.toLowerCase();
    const todoItems = document.querySelectorAll('.todo-item');

    todoItems.forEach(item => {
        const taskText = item.querySelector('span').textContent.toLowerCase();

        if (taskText.includes(searchQuery)) {
            item.style.display = 'flex'; 
        } else {
            item.style.display = 'none'; 
        }
    });
}
