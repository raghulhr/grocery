package com.grocery.authservice.controller;

import com.grocery.authservice.dto.AuthRequest;
import com.grocery.authservice.dto.LoginResponse;
import com.grocery.authservice.entity.User;
import com.grocery.authservice.repository.UserRepository;
import com.grocery.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/welcome")
    public String AuthHello() {
        return "Welcome to Auth controller";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            Optional<User> optionalUser = userRepository.findUserByEmail(authRequest.getUsername());
            User user = optionalUser.orElse(new User());
            return service.generateToken(user);
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody AuthRequest authRequest) {
        return userRepository.findUserByEmail(authRequest.getUsername())
                .map(user -> authenticateAndGenerateResponse(user, authRequest))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<LoginResponse> authenticateAndGenerateResponse(User user, AuthRequest authRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), authRequest.getPassword())
            );
            if (authenticate.isAuthenticated()) {
                String jwtToken = service.generateToken(user);
                LoginResponse response = buildLoginResponse(user, authRequest.getUsername(), jwtToken);
                return ResponseEntity.ok(response);
            }
        } catch (AuthenticationException e) {
            // Log exception here if necessary
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private LoginResponse buildLoginResponse(User user, String username, String jwtToken) {
        return LoginResponse.builder()
                .userId(user.getId())
                .userName(user.getUsername())
                .emailId(username)
                .token(jwtToken)
                .role(user.getUserType().getUserType())
                .build();
    }


   /* @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody AuthRequest authRequest) {
        Optional<User> userDetails = userRepository.findUserByEmail(authRequest.getUsername());
        if(userDetails.isPresent()) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.get().getUsername(), authRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                String jwtToke = service.generateToken(authRequest.getUsername());
                LoginResponse response = new LoginResponse();
                response.setUserId(userDetails.get().getId());
                response.setUserName(userDetails.get().getUsername());
                response.setEmailId(authRequest.getUsername());
                response.setToken(jwtToke);
                response.setRole(userDetails.get().getUserType().getUserType());
                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            throw new RuntimeException("user not found with email :" + authRequest.getUsername());
        }
    }*/
}
