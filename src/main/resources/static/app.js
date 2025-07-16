const form = document.getElementById('todo-form');
const input = document.getElementById('todo-input');
const list = document.getElementById('todo-list');

// 📥 Загрузка задач при старте
window.addEventListener('DOMContentLoaded', () => {
    fetch('/api/todos')
        .then(res => res.json())
        .then(data => data.forEach(renderTodo));
});

// 🧠 Функция отображения одной задачи
function renderTodo(todo) {
    const li = document.createElement('li');
    li.className = 'todo-item';

    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = todo.done;
    checkbox.addEventListener('change', () => {
        todo.done = checkbox.checked;
        fetch(`/api/todos/${todo.id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(todo)
        });
    });

    const span = document.createElement('span');
    span.textContent = todo.title;
    if (todo.done) span.style.textDecoration = 'line-through';

    const deleteBtn = document.createElement('button');
    deleteBtn.textContent = '❌';
    deleteBtn.addEventListener('click', () => {
        fetch(`/api/todos/${todo.id}`, { method: 'DELETE' })
            .then(() => li.remove());
    });

    li.appendChild(checkbox);
    li.appendChild(span);
    li.appendChild(deleteBtn);
    list.appendChild(li);
}

// 📨 Создание новой задачи
form.addEventListener('submit', (e) => {
    e.preventDefault();
    const text = input.value.trim();
    if (text !== '') {
        fetch('/api/todos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: text, done: false })
        })
            .then(res => res.json())
            .then(todo => {
                renderTodo(todo);
                input.value = '';
            });
    }
});
