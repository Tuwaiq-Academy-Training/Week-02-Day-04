package com.example.usersauth.controllers;

import com.example.usersauth.model.Api;
import com.example.usersauth.model.LoginForm;
import com.example.usersauth.model.User;
import com.example.usersauth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<Api> register(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isRegistered=authService.register(user);
        if(!isRegistered){
            return ResponseEntity.status(400).body(new Api("Your registered before !",400));
        }
        return ResponseEntity.status(201).body(new Api("Your register is completed",201));
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Api> login(@RequestBody @Valid LoginForm loginForm,Errors errors){
//        if(errors.hasErrors()){
//            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
//        }
//        boolean isLogin= authService.login(loginForm);
//        if(!isLogin){
//            return ResponseEntity.status(400).body(new Api("Invalid username or password",400));
//        }
//        return ResponseEntity.status(200).body(new Api("Welcome back",200));
//    }

    @PostMapping("/login")
    public ResponseEntity<Api> login(@RequestBody @Valid LoginForm loginForm,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        Integer loginCase= authService.login(loginForm);

        switch (loginCase){
            case -1:
                return ResponseEntity.status(400).body(new Api("Invalid username",400));
            case 0:
                return ResponseEntity.status(400).body(new Api("Invalid password",400));
            case 1:
                return ResponseEntity.status(200).body(new Api("Welcome back",200));
            default:
                return ResponseEntity.status(400).body(new Api("Invalid username or password",400));
        }

    }
}
