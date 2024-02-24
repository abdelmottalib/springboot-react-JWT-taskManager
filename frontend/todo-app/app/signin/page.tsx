'use client'
import React, {FormEvent, useEffect, useState} from 'react';
import axios from "axios";
import {useRouter} from "next/navigation";
import '../globals.css';
import Cookies from "js-cookie";


const page = () => {
    const [passwordError, setPasswordError] = useState(false)
    const [emailError, setEmailError] = useState(false)
    const getAuthToken = () => {
        return Cookies.get('jwt');
    };

    const axiosConfig = !getAuthToken() ? {} : {
        headers: {
            Authorization: `Bearer ${getAuthToken()}`
        },
    };
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
                if (getAuthToken() !== null) {
                    
                    const response = await axios.get('http://localhost:8080/api/todos', axiosConfig);
                    router.push('/');
                }
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
            
            
            // setToken(token);
            Cookies.set('jwt', token, {
                expires: 7, // Optional: Set expiration in days
                // httpOnly: true, // Important for security
                secure: true,  // Use in production (with HTTPS)
                sameSite: 'strict' // Can help mitigate CSRF
            });
            router.push('/');
        } catch (error:any) {
            if (error.response && error.response.status === 404) {
                // Display user-friendly error message about email being in use
                setEmailError(true)
                
            } else if (error.response && error.response.status === 401) {
                setPasswordError(true)
                // Handle other kinds of errors (network issues, etc.)
                
            }
        }
    }

    async function handleGithubSignIn () {
        // const response = await axios.get('/auth/google');
        router.push("/oauth2/authorization/github");
    }

    return (
        <div className="max-w-md mx-auto p-4 bg-white shadow-md rounded-md">
            <h2 className="text-2xl font-bold mb-4">Sign In</h2>
            <form onSubmit={handleSignIn} className="space-y-4">
                <div>
                    <label className="block text-sm font-medium text-gray-600">
                        Email:
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            className="mt-1 p-2 w-full border rounded-md"
                        />
                    </label>
                </div>
                {emailError && <p className="text-red-500 text-sm">Email does not exist</p>}

                <div>
                    <label className="block text-sm font-medium text-gray-600">
                        Password:
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            className="mt-1 p-2 w-full border rounded-md"
                        />
                    </label>
                </div>
                {passwordError && <p className="text-red-500 text-sm">Password is incorrect</p>}
                <button
                    type="submit"
                    onClick={handleSubmit}
                    className="bg-blue-500 text-white py-2 px-4 rounded-md"
                >
                    Sign In
                </button>
                {/*<button*/}
                {/*    onClick={handleGithubSignIn}*/}
                {/*    className="ml-2 bg-red-500 text-white py-2 px-4 rounded-md mt-4"*/}
                {/*>*/}
                {/*    Sign In with Github*/}
                {/*</button>*/}
            </form>
        </div>

    );
};

export default page;
