const taskInput = document.getElementById('taskInput');
const addTaskButton = document.getElementById('addTaskButton');
const taskList = document.getElementById('taskList');

addTaskButton.addEventListener('click', () => {
  const taskText = taskInput.value.trim();
  if (taskText !== '') {
    const li = document.createElement('li');
    li.innerHTML = `
      <span>${taskText}</span>
      <button class="delete-button">Delete</button>
    `;
    taskList.appendChild(li);
    taskInput.value = '';
  }
});

taskList.addEventListener('click', (event) => {
  if (event.target.classList.contains('delete-button')) {
    const li = event.target.parentElement;
    taskList.removeChild(li);
  }
});