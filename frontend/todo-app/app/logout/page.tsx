'use client'
import React, { useEffect } from 'react';

import {useRouter} from 'next/navigation';
import {useTokenContext} from "@/app/tokenProvider";

const page = () => {
    const router = useRouter();
    const {token, setToken} = useTokenContext();
    useEffect(() => {
        // Remove the token from local storage

        setToken(null);
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
