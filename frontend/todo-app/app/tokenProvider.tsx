'use client';
// TokenProvider.tsx
import React, { createContext, useState, useContext } from 'react';

// Define the shape of the context
interface UserContextType {
    token: number | null; // or the specific type you expect for userId
    setToken: (userId: number | null) => void; // Function to update the userId
}

// Create the context with the correct type
const UserContext = createContext<UserContextType>({
    token: null,
    setToken: () => {}, // Provide a default empty function
});

// Provider component that allows consuming components to subscribe to context changes
export const TokenProvider: React.FC = ({ children }:any) => {
    const [user, setUser] = useState<number | null>(null);

    // Provide the context value with userId and setUserId
    return (
        <UserContext.Provider value={{ token: user, setToken: setUser }}>
            {children}
        </UserContext.Provider>
    );
};

// Custom hook for accessing the context
export const useTokenContext = () => useContext(UserContext);
