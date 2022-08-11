package com.example.authspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@AllArgsConstructor @Data
public class BankAccount {

    @NotNull(message = "Id can't be null")
    private int userId;
    @Range(min = 100,message = "balance can't be less than 100 SAR")
    @NotNull(message = "balance can't be null")
    private int balance;
}
