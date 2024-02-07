'use client';
import React, {useContext, useEffect, useState} from 'react';
import axios from 'axios';
import {UserContext} from "@/app/UserProvider";

const page = () => {
    const {user, setUser} = useContext(UserContext);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);

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
            setUser(response.data.id);
            // Assuming your backend returns a JWT token upon successful registration
            const { token } = response.data;
            localStorage.setItem('token', token);
            // Handle the token (e.g., store it in local storage)
            // ...

            // Notify parent component about successful registration
        } catch (error) {
            console.error('Registration failed:', error);
        } finally {
            setLoading(false);
            setEmail("");
            setPassword("");
            setFirstName("");
            setLastName("");
        }
    };
    useEffect(() => {
        console.log('User:', user);
    }, [user]);
    return (
        <div>
            <h2>Register</h2>
            <div>
                <label>
                    First Name:
                    <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                </label>
            </div>
            <div>
                <label>
                    Last Name:
                    <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                </label>
            </div>
            <div>
                <label>
                    Email:
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                </label>
            </div>
            <div>
                <label>
                    Password:
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </label>
            </div>
            <div>
                <button onClick={handleRegister} disabled={loading}>
                    {loading ? 'Registering...' : 'Register'}
                </button>
            </div>
        </div>
    );
};
export default page;