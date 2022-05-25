package com.example.library.controllers;

import com.example.library.model.Api;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/")
    public ResponseEntity<ArrayList<User>> getUsers(){
    return ResponseEntity.status(200).body(userService.getUsers());

    }

    @PostMapping("/")
    public ResponseEntity<Api> addUser(@RequestBody @Valid User user, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isUserAdded=userService.addUser(user);

        if(!isUserAdded){
            return ResponseEntity.status(500).body(new Api("Error adding a user",500));

        }

        return ResponseEntity.status(200).body(new Api("New user added",200));
    }
}
