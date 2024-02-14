package com.example.demo.user;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserJPAService {
    private final UserRepository userRepository;

    public UserJPAService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void deleteUser(String email) {
        System.out.println("reached the serviceJPA");
        userRepository.deleteByEmail(email);
    }
}
