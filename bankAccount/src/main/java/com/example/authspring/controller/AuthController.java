package com.example.authspring.controller;


import com.example.authspring.model.ApiResponse;
import com.example.authspring.model.LoginForm;
import com.example.authspring.model.MyUser;
import com.example.authspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @GetMapping("/users")
    public ResponseEntity getUsers(){
        ArrayList<MyUser> users=authService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/register")
    public ResponseEntity addUser(@RequestBody @Valid MyUser myUser, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        authService.addUser(myUser);
        return ResponseEntity.status(201).body( new ApiResponse("New user added !",201));
    }
//
    @PutMapping("/users/{index}")
    public ResponseEntity updateUser(@RequestBody @Valid MyUser myUser
            ,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        authService.updateUser(index,myUser);
        return ResponseEntity.status(201).body( new ApiResponse("User updated !",201));
    }

    @DeleteMapping("/users/{index}")
    public ResponseEntity deleteUser(@PathVariable int index){
        authService.deleteUser(index);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted !",200));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginForm loginForm,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
      boolean isValid=authService.login(loginForm);
        if(isValid){
            return ResponseEntity.status(200).body(new ApiResponse("Welcome back",200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Wrong username or password",400));
    }



}
