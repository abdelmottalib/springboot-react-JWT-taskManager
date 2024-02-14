'use client';
import {useState, useEffect, useContext} from 'react';
import axios from 'axios';
import {useRouter} from "next/navigation";
// import {UserContext, useUserContext} from "@/app/UserProvider";
import {useAtom} from "jotai";
import './globals.css';
import {userAtom} from "@/app/atoms";
import {useUserContext} from "@/app/UserProvider";
interface todo {
    id: number;
    title: string;
    description: string;
    done: boolean;
}

const getAuthToken = () => {
    return localStorage.getItem('token');
};

const axiosConfig = !getAuthToken() ? {} : {
    headers: {
        Authorization: `Bearer ${getAuthToken()}`
    },
};

const TodoApp = () => {
    const router = useRouter();
    const [todos, setTodos] = useState<todo[]>([]);
    const [newDescription, setNewDescription] = useState('');
    const [newTitle, setNewTitle] = useState('');
    const [idd, setIdd] = useAtom(userAtom);
    const {user, setUser} = useUserContext();
    const fetchTodos = async () => {
        try {
            console.log('fetching todos1');
            console.log("the token when fetching is " + axiosConfig)
            console.log('user in home page:', user);
            if (!getAuthToken()) {
                router.push('/register');
            }
            console.log("the token axiosConfig: " + getAuthToken())
            const response = await axios.get(`http://localhost:8080/api/todos`, axiosConfig);
            console.log('fetching todos2');

            // Ensure response.data is an array before sorting
            if (Array.isArray(response.data)) {
                const sortedTodos = response.data.sort((a: todo, b: todo) => a.id - b.id);
                console.log('fetching todos3');
                setTodos(sortedTodos);

            } else {
                console.log('empty');
            }
        } catch (error) {
            router.push('/register');
        }
    };

    useEffect(() => {
        console.log('User:', user);
    }, [user]);
    useEffect(() => {
            fetchTodos();
            console.log(getAuthToken());
    }, []);
    const addTodo = async () => {
        if (newDescription.trim() !== '') {
            try {
                console.log("clicked");
                await axios.post(`http://localhost:8080/api/todos`, {
                    title: newTitle,
                    description: newDescription,
                    done: false
                }, axiosConfig);
                fetchTodos();
                setNewDescription('');
                setNewTitle('');
            } catch (error) {
                console.error('Error adding todo:', error);
            }
        }
    };
    const fetchTodo = (id: number) => {
        try {
            const response = axios.get(`http://localhost:8080/api/v1/todos/${id}`);
            console.log('response:', response);
        } catch (error) {
            console.error('Error fetching todo:', error);
        }
    }
    const toggleTodo = async (id: number) => {
        try {
            // const response = await fetchTodo(id);
            const response = await axios.get(`http://localhost:8080/users/todos/${id}`, axiosConfig);
            console.log('response:', response);
            await axios.put(`http://localhost:8080/api/v1/todos/${id}`, {
                title: response.data.title,
                description: response.data.description, done: !response.data.done
            });
            fetchTodos();
        } catch (error) {
            console.error('Error toggling todo:', error);
        }
    };

    const removeTodo = async (id: number) => {
        try {
            console.log("token in delete" + getAuthToken())
            await axios.delete(`http://localhost:8080/api/todos/${id}`, axiosConfig);
            console.log("deleted")
            fetchTodos();
        } catch (error) {
            console.error('Error removing todo:', error);
        }
    };
    useEffect(() => {
        console.log('todos:', todos);
    }, [todos]);

    return (
        <div className="max-w-xl mx-auto p-4">
            <h1 className="text-2xl font-bold mb-4">Todo App</h1>

            <div className="mb-4">
                <input
                    type="text"
                    placeholder="New title"
                    value={newTitle}
                    onChange={(e) => setNewTitle(e.target.value)}
                    className="border rounded p-2 mr-2 outline-none"
                />
                <input
                    type="text"
                    placeholder="New description"
                    value={newDescription}
                    onChange={(e) => setNewDescription(e.target.value)}
                    className="border rounded p-2 mr-2 outline-none"
                />
                <button onClick={addTodo} className="bg-blue-500 text-white py-2 px-4 rounded">
                    Add Todo
                </button>
            </div>

            <ul>
                {todos.map((todo) => (
                    <li key={todo.id} className="flex items-center mb-2">
                        <input
                            type="checkbox"
                            checked={todo.done}
                            onChange={() => toggleTodo(todo.id)}
                            className="mr-2"
                        />
                        <span
                            className={`flex-1 ${todo.done ? 'line-through' : ''}`}
                        >
                            {todo.description}
                        </span>
                        <button
                            onClick={() => removeTodo(todo.id)}
                            className="bg-red-500 text-white py-1 px-2 rounded"
                        >
                            Remove
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TodoApp;