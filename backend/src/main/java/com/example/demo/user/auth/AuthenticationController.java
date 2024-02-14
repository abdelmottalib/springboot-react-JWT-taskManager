package com.example.demo.user.auth;


import com.example.demo.exceptions.EmailAlreadyExistsException;
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
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(409).build();
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody signinRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
        //
    }
}
