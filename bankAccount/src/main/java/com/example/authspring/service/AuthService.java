package com.example.authspring.service;


import com.example.authspring.model.ApiResponse;
import com.example.authspring.model.LoginForm;
import com.example.authspring.model.MyUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    private ArrayList<MyUser> usersList=new ArrayList<>();


    public ArrayList<MyUser> getUsers(){
        return usersList;
    }

    public void addUser(MyUser myUser){
        usersList.add(myUser);
    }

    public void updateUser(int index, MyUser myUser) {
        usersList.set(index,myUser);
    }

    public void deleteUser(int index) {
        usersList.remove(index);
    }

    public boolean login(LoginForm loginForm) {
        for (int i = 0; i < usersList.size(); i++) {
            MyUser myUser=usersList.get(i);
            if(myUser.getUsername().equals(loginForm.getUsername())&&
                    myUser.getPassword().equals(loginForm.getPassword())){
                return true;
            }
        }
        return false;
    }
}
