package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {


    private ArrayList<User> users=new ArrayList();

    public ArrayList<User> getUsers(){
        return  users;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public User getUser(String userid) {
        for (User user:users) {
            if(user.getId().equals(userid)){
                return user;
            }
        }
        return null;
    }
}
