package com.example.demo.user.auth;


import com.example.demo.exceptions.EmailAlreadyExistsException;
import com.example.demo.exceptions.EmailDoesntExistError;
import com.example.demo.exceptions.PasswordDoesntMatchError;
import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserJPAService;
import com.example.demo.user.UserRepository;
import com.example.demo.user.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserJPAService userJPAService;

    public AuthenticationResponse register(registerRequest request) {
        if (userJPAService.existsByEmail(request.getEmail())) {
            
            throw new EmailAlreadyExistsException();
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var token = jwtService.generateToken(user);
        AuthenticationResponse build = AuthenticationResponse.builder()
                .token(token).build();
        
        return build;
    }
    public  AuthenticationResponse signin(signinRequest request) {
        
        
        
        if (!userJPAService.existsByEmail(request.getEmail())) {
            
            
            throw new EmailDoesntExistError();
        }
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            
            throw new PasswordDoesntMatchError();
        }
        authenticationManager.authenticate(//if the username or the password are not correct it will throw an exception
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
         var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token).build();
    }
    public void logout(String email) {
        
        this.userJPAService.deleteUser(email);
    }
}
