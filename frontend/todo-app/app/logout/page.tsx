'use client'
import React, { useEffect } from 'react';

import {useRouter} from 'next/navigation';

const page = () => {
    const router = useRouter();

    useEffect(() => {
        // Remove the token from local storage
        localStorage.removeItem('token');

        // Redirect to the home page
        router.push('/');
    }, [history]);

    return (
        <div className={'h-screen w-screen flex items-center justify-center'}>
            <p>Logging out...</p>
        </div>
    );
};

export default page;
