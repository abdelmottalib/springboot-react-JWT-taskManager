package com.example.demo.user;


import com.example.demo.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("users")

public class UserController {
    private final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("{userId}")
    public User getUser(@PathVariable ("userId") Integer userId) {
        return userService.getUser(userId);
    }
}
