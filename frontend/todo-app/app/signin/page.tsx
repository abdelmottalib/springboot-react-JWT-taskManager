'use client'
import React, {FormEvent, useEffect, useState} from 'react';
import axios from "axios";
import {useRouter} from "next/navigation";
import {useUserContext} from "@/app/UserProvider";
import '../globals.css';

const getAuthToken = () => {
    console.log('getAuthToken', localStorage.getItem('token'));
    return localStorage.getItem('token');
};

const axiosConfig = !getAuthToken() ? {} : {
    headers: {
        Authorization: `Bearer ${getAuthToken()}`
    },
};
const page = () => {
    const {user, setUser} = useUserContext();
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
                const response = await axios.get('http://localhost:8080/users/1', axiosConfig);
                router.push('/');
            } catch (error) {
            }
        }
        console.log(getAuthToken());
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
            console.log('token', token);
            console.log('response.data.id', response.data.id);
            localStorage.setItem('token', token);
            setUser(response.data.id);
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
