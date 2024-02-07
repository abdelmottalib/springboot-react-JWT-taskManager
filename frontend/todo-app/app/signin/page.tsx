'use client'
import React, {FormEvent, useEffect, useState} from 'react';
import axios from "axios";
import {useRouter} from "next/navigation";

const getAuthToken = () => {
    return localStorage.getItem('token');
};

const axiosConfig = {
    headers: {
        Authorization: `Bearer ${getAuthToken()}`
    },
};
const page = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const router = useRouter();
    const handleSignIn = (e:any) => {
        e.preventDefault();
        setEmail('');
        setPassword('');
    };

    useEffect(() => {
        const check = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/v1/todos', axiosConfig);
                router.push('/');
            } catch (error) {
            }
        }
        check();
    }, []);
    const handleSubmit = async (e:any) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/v1/auth/signin', {
                email,
                password,
            });
            const { token } = response.data;
            localStorage.setItem('token', token);
            router.push('/');
        } catch (error) {
            console.error('Sign In failed:', error);
        }
    }
    return (
        <div>
            <h2>Sign In</h2>
            <form onSubmit={handleSignIn}>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" onClick={handleSubmit}>Sign In</button>
            </form>
        </div>
    );
};

export default page;
