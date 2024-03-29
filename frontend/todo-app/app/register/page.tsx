'use client';
import React, {useContext, useEffect, useState} from 'react';
import axios from 'axios';
import {useAtom} from "jotai";
import '../globals.css';
import Cookies from 'js-cookie';
import {useRouter} from "next/navigation";

const page = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [emailError, setEmailError] = useState(false);
    const router = useRouter();
    const handleRegister = async () => {
        try {
            setLoading(true);

            // Make a request to your backend registration endpoint
            const response = await axios.post('http://localhost:8080/api/v1/auth/register', {
                firstname: firstName,
                lastname: lastName,
                email,
                password,
            });
            // const userInfo = await axios.get(`http://localhost:8080/users/${email}`);
            // Assuming your backend returns a JWT token upon successful registration
            const { token } = response.data;
            Cookies.set('jwt', token, {
                expires: 7, // Optional: Set expiration in days
                // httpOnly: true, // Important for security
                secure: true,  // Use in production (with HTTPS)
                sameSite: 'strict' // Can help mitigate CSRF
            });
            
            // setToken(token);
            router.push('/');
            // Handle the token (e.g., store it in local storage)
            // ...

            // Notify parent component about successful registration
        } catch (error:any) {
            if (error.response && error.response.status === 409) {
                // Display user-friendly error message about email being in use
                setEmailError(true)
                
            } else {
                // Handle other kinds of errors (network issues, etc.)
                console.error('Registration failed:', error);
            }
        } finally {
            setLoading(false);
            // setEmail("");
            // setPassword("");
            // setFirstName("");
            // setLastName("");
        }
    };
    return (
        <div className="max-w-md mx-auto p-4 bg-white shadow-md rounded-md">
            <h2 className="text-2xl font-bold mb-4">Register</h2>

            <div className="mb-4">
                <label className="block text-sm font-medium text-gray-600">
                    First Name:
                    <input
                        type="text"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        className="mt-1 p-2 w-full border rounded-md"
                    />
                </label>
            </div>

            <div className="mb-4">
                <label className="block text-sm font-medium text-gray-600">
                    Last Name:
                    <input
                        type="text"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        className="mt-1 p-2 w-full border rounded-md"
                    />
                </label>
            </div>

            <div className="mb-4">
                <label className="block text-sm font-medium text-gray-600">
                    Email:
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="mt-1 p-2 w-full border rounded-md"
                    />
                </label>
                {emailError && <p className="text-red-500 text-xs mt-1">Email is already in use</p>}
            </div>

            <div className="mb-4">
                <label className="block text-sm font-medium text-gray-600">
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="mt-1 p-2 w-full border rounded-md"
                    />
                </label>
            </div>

            <div>
                <button
                    onClick={handleRegister}
                    disabled={loading}
                    className="bg-blue-500 text-white py-2 px-4 rounded-md"
                >
                    {loading ? 'Registering...' : 'Register'}
                </button>
            </div>
        </div>
    );
};
export default page;