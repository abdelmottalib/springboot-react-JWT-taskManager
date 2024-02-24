package com.example.demo.user.auth;


import com.example.demo.exceptions.EmailAlreadyExistsException;
import com.example.demo.exceptions.PasswordDoesntMatchError;
import com.example.demo.exceptions.EmailDoesntExistError;
import jakarta.servlet.http.HttpServletRequest;
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
    private final BlackListTokens blackListTokens;
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
    public void signout(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);

        blackListTokens.addToken(jwt);
    }
}
