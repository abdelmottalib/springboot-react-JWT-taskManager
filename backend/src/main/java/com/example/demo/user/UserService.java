package com.example.demo.user;


import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;
    UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public User findById(String email) {
        System.out.println("service email: " + email);
        return this.userDao.findById(email);
    }
}
