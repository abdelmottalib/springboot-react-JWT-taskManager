'use client';
import React, { createContext, useState } from "react";

type UserContextType = {
    user: any; // Replace 'any' with the actual type of your user object
    setUser: React.Dispatch<any>; // Replace 'any' with the actual type of the setUser function
};

const UserContext = createContext<UserContextType>({
    user: null,
    setUser: () => {},
});

function UserProvider({ children }: { children: React.ReactNode }) {
    const [user, setUser] = useState<any>(null); // Replace 'any' with the actual type of your user object

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
}

export { UserContext, UserProvider };