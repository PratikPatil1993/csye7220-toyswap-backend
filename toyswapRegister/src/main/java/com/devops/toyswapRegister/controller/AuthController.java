package com.devops.toyswapRegister.controller;

import com.devops.toyswapRegister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.devops.toyswapRegister.model.User;




@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User u = userService.register(user);


        if (u != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{

            return new ResponseEntity<>((HttpStatus.BAD_GATEWAY));


        }

    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
//        try {
//            String token = authService.authenticate(authRequest.getEmail(), authRequest.getPassword());
//            AuthResponse authResponse = new AuthResponse(token);
//
//            return new ResponseEntity<>(authResponse, HttpStatus.OK);
//        } catch (AuthenticationException authenticationException) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }

}