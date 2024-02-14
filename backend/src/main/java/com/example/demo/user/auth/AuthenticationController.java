package com.example.demo.user.auth;


import com.example.demo.exceptions.EmailAlreadyExistsException;
import com.example.demo.exceptions.PasswordDoesntMatchError;
import com.example.demo.exceptions.EmailDoesntExistError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
        try {
            return ResponseEntity.ok(authenticationService.signin(request));
        }
        catch(PasswordDoesntMatchError e){
            return ResponseEntity.status(401).build();
        }
        catch(EmailDoesntExistError e){
            return ResponseEntity.status(404).build();
        }
        //
    }
    @DeleteMapping("/signout")
    public void logout(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("**********************************************");
        System.out.println("the user is: " + userDetails.getUsername());
        authenticationService.logout(userDetails.getUsername());
    }
}
