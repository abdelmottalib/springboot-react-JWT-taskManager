package com.example.demo.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserJPAService userJPAService;

    public UserService(UserJPAService userJPAService) {
        this.userJPAService =  userJPAService;
    }

    public User getUser(Integer userId) {
        return userJPAService.getUser(userId);
    }
}
