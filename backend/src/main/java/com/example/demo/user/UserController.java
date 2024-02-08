package com.example.demo.user;



import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")

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
