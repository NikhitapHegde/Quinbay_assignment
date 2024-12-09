document.getElementById('add-btn').addEventListener('click', addTodo);
document.getElementById('clear-completed').addEventListener('click', clearCompleted);
document.getElementById('toggle-theme').addEventListener('click', toggleDarkMode);

let completedCount = 0;
let totalCount = 0;
let isDarkMode = false;

function addTodo() {
    const todoInput = document.getElementById('todo-input');
    const todoText = todoInput.value.trim();

    if (todoText === "") return;  //For empty task

    //Create a new todo item
    const todoItem = document.createElement('div');
    todoItem.classList.add('todo-item');

    //Add the task text
    const todoTextElement = document.createElement('span');
    todoTextElement.textContent = todoText;
    todoItem.appendChild(todoTextElement);

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

    //Clear the input field
    todoInput.value = "";
}

function markComplete(event) {
    const todoItem = event.target.parentElement;
    todoItem.classList.toggle('completed');

    //Update completed count
    updateTaskCounts();
}

function deleteTodo(event) {
    const todoItem = event.target.parentElement;
    todoItem.style.opacity = 0;
    setTimeout(() => todoItem.remove(), 300); //Remove after animation

    //Decrement total task count
    totalCount--;

    //Update completed count
    updateTaskCounts();
}

function updateTaskCounts() {
    //Count completed tasks
    completedCount = document.querySelectorAll('.todo-item.completed').length;

    //Update the displayed counts for completed and total tasks
    document.getElementById('completed-count').textContent = completedCount;
    document.getElementById('total-count').textContent = totalCount;

    //Update progress
    let progress = (totalCount === 0) ? 0 : Math.round((completedCount / totalCount) * 100);
    document.getElementById('progress').textContent = progress + '%';
}

function clearCompleted() {
    const completedItems = document.querySelectorAll('.todo-item.completed');
    completedItems.forEach(item => item.remove());
    totalCount -= completedItems.length;
    completedCount = 0;
    updateTaskCounts();
}

function toggleDarkMode() {
    isDarkMode = !isDarkMode;
    document.body.classList.toggle('dark-mode', isDarkMode);
}
