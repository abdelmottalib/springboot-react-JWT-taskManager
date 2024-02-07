package com.example.demo.user;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    final private UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("{email}")
    public User findById(@PathVariable("email") String email) {
        System.out.println("email: " + email);
        return this.userService.findById(email);
    }
}
