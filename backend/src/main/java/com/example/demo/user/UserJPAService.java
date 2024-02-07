package com.example.demo.user;


import org.springframework.stereotype.Repository;

@Repository
public class UserJPAService implements UserDao{
    private final UserRepository userRepository;
    UserJPAService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findById(String email) {
        System.out.println("servicejpa email: " + email);
        return this.userRepository.findByEmail(email).orElse(null);
    }
}
