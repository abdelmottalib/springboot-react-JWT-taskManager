import  { useState, useEffect } from 'react';
import axios from 'axios';

interface todo {
    id: number;
    title: string;
    description: string;
    done: boolean;
}
const TodoApp = () => {
    const [todos, setTodos] = useState<todo[]>([]);
    const [newDescription, setNewDescription] = useState('');
    const [newTitle, setNewTitle] = useState('');

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/todos');
            const sortedTodos = response.data.sort((a:todo, b:todo) => a.id - b.id);
            setTodos(sortedTodos);
        } catch (error) {
            console.error('Error fetching todos:', error);
        }
    };
    useEffect(() => {
        console.log('todos:', todos);
    }, [todos]);
    const addTodo = async () => {
        if (newDescription.trim() !== '') {
            try {
                await axios.post('http://localhost:8080/api/v1/todos', {title: newTitle, description: newDescription, done: false});
                fetchTodos();
                setNewDescription('');
                setNewTitle('');
            } catch (error) {
                console.error('Error adding todo:', error);
            }
        }
    };
    const fetchTodo =  (id:number) => {
        try {
            const response =  axios.get(`http://localhost:8080/api/v1/todos/${id}`);
            console.log('response:', response);
        } catch (error) {
            console.error('Error fetching todo:', error);
        }
    }
    const toggleTodo = async (id:number) => {
        try {
            // const response = await fetchTodo(id);
            const response = await axios.get(`http://localhost:8080/api/v1/todos/${id}`);
            console.log('response:', response);
            await axios.put(`http://localhost:8080/api/v1/todos/${id}`,  {title:response.data.title,
                description: response.data.description, done: !response.data.done});
            fetchTodos();
        } catch (error) {
            console.error('Error toggling todo:', error);
        }
    };

    const removeTodo = async (id:number) => {
        try {
            await axios.delete(`http://localhost:8080/api/v1/todos/${id}`);
            fetchTodos();
        } catch (error) {
            console.error('Error removing todo:', error);
        }
    };

    return (
        <div>
            <h1>Todo App</h1>

            <div>
                <input
                    type="text"
                    placeholder="New title"
                    value={newTitle}
                    onChange={(e) => setNewTitle(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="New description"
                    value={newDescription}
                    onChange={(e) => setNewDescription(e.target.value)}
                />
                <button onClick={addTodo}>Add Todo</button>
            </div>

            <ul>
                {todos.map(todo => (
                    <li key={todo.id}>
                        <input
                            type="checkbox"
                            checked={todo.done}
                            onChange={() => toggleTodo(todo.id)}
                        />
                        <span style={{ textDecoration: todo.done ? 'line-through' : 'none' }}>{todo.description}</span>
                        <button onClick={() => removeTodo(todo.id)}>Remove</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TodoApp;
