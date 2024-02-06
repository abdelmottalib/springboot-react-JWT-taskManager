package com.example.demo.user.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody registerRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody signinRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
        //
    }
}
