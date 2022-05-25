package com.example.usersauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class User {
    @NotEmpty(message = "ID is required !")
    @Size(min = 3)
    private String id;
    @NotEmpty(message = "Username is required !")
    @Size(min = 4)
    private String username;
    @NotEmpty(message = "Password is required !")
    @Size(min = 8,max = 20,message = "Your password must be more than 8 char")
    @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
            ,message = "You need strong password")
    private String password;
    @NotEmpty(message = "Role is required !")
    @Pattern(regexp = "(admin|superuser|user)",
            message = "You role must be in (admin,superuser,user)")
    private String role;
    @NotEmpty(message = "Email is required !")
    @Email(message = "You email is invalid")
    private String email;
}
