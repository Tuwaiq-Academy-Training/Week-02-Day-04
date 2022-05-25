package com.example.usersauth.service;

import com.example.usersauth.model.Api;
import com.example.usersauth.model.LoginForm;
import com.example.usersauth.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    private ArrayList<User> users=new ArrayList();


    public ArrayList<User> getUsers(){
        return users;
    }

    public boolean register(User user){
        for (int i = 0; i < users.size(); i++) {
            User currentUser=users.get(i);
            if(currentUser.getUsername().equals(user.getUsername())||
                    currentUser.getEmail().equals(user.getEmail())){
                return false;
            }
        }

        users.add(user);
        return true;
    }

//    public boolean login (LoginForm loginForm){
//        for (int i = 0; i < users.size(); i++) {
//            User currentUser=users.get(i);
//            if(currentUser.getUsername().equals(loginForm.getUsername())){
//                if(currentUser.getPassword().equals(loginForm.getPassword())){
//                    return true;
//                }else {
//                    return false;
//                }
//            }
//        }
//        return false;
//    }

    public Integer login (LoginForm loginForm){
        for (int i = 0; i < users.size(); i++) {
            User currentUser=users.get(i);
            if(currentUser.getUsername().equals(loginForm.getUsername())){
                if(currentUser.getPassword().equals(loginForm.getPassword())){
                    return 1;
                }else {
                    return 0;
                }
            }
        }
        return -1;
    }
}
