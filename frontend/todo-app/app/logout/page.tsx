'use client'
import React, { useEffect } from 'react';

import {useRouter} from 'next/navigation';
import {useTokenContext} from "@/app/tokenProvider";
import axios from "axios";
import Cookies from "js-cookie";

const page = () => {

    const {token, setToken} =useTokenContext();
    const getAuthToken = () => {
        return token;
    };

    const axiosConfig = !getAuthToken() ? {} : {
        headers: {
            Authorization: `Bearer ${getAuthToken()}`
        },
    };
    const router = useRouter();
    useEffect(() => {
        // Remove the token from local storage
        console.log('the token in signout is ' + getAuthToken())
        // if (getAuthToken() !== null) {
        //     axios.delete('http://localhost:8080/api/v1/auth/signout', axiosConfig)
        // }
        // setToken(null);
        Cookies.remove('jwt');
        // Redirect to the home page
        router.push('/register');
    }, []);


    return (
        <div className={'h-screen w-screen flex items-center justify-center'}>
            <p>Logging out...</p>
        </div>
    );
};

export default page;
