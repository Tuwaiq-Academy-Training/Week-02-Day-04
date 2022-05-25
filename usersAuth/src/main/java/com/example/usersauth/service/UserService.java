package com.example.usersauth.service;

import com.example.usersauth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthService authService;
    public ArrayList<User> getUsers(){
        return authService.getUsers();
    }
}
