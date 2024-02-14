package com.example.demo.user.auth;


import com.example.demo.exceptions.EmailAlreadyExistsException;
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
    private final UserJPAService userService;

    public AuthenticationResponse register(registerRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            System.out.println("Email already exists");
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
                .token(token).id(user.getId()).build();
        System.out.println("the token sent by register is:"+token);
        return build;
    }
    public  AuthenticationResponse signin(signinRequest request) {
        System.out.println("from signin");
        System.out.println("the email is:"+request.getEmail());
        System.out.println("the password is:"+request.getPassword());
        authenticationManager.authenticate(//if the username or the password are not correct it will throw an exception
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
         var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token).id(user.getId()).build();
    }
}
