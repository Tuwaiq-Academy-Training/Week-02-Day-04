package com.example.authspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class MyUser {

    @NotNull(message = "Id can't be null")
    private int id;
    @NotEmpty(message = "username can't be empty")
    @Size(min = 3,max = 10,message = "username must be more than 3 char and less than 10 char")
    private String username;
    @NotEmpty(message = "password can't be empty")
    @Size(min = 6,max = 15,message = "password must be more than 6 char and less than 15 char")
   // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "please enter strong password")
    private String password;
    @NotEmpty(message = "email can't be empty")
    @Email(message = "Please enter a valid email")
    private String email;
    @NotEmpty(message = "role can't be empty")
    @Pattern(regexp = "(admin|user)",message = "Role must be in admin or user only")
    private String role;
}
