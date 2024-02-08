'use client';
// UserProvider.tsx
import React, { createContext, useState, useContext } from 'react';

// Define the shape of the context
interface UserContextType {
    user: number | null; // or the specific type you expect for userId
    setUser: (userId: number | null) => void; // Function to update the userId
}

// Create the context with the correct type
const UserContext = createContext<UserContextType>({
    user: null,
    setUser: () => {}, // Provide a default empty function
});

// Provider component that allows consuming components to subscribe to context changes
export const UserProvider: React.FC = ({ children }:any) => {
    const [user, setUser] = useState<number | null>(null);

    // Provide the context value with userId and setUserId
    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
};

// Custom hook for accessing the context
export const useUserContext = () => useContext(UserContext);
