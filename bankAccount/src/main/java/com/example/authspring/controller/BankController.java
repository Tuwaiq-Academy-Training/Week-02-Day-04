package com.example.authspring.controller;

import com.example.authspring.model.ApiResponse;
import com.example.authspring.model.BankAccount;
import com.example.authspring.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService){
        this.bankService=bankService;
    }

    @GetMapping("/account")
    public ResponseEntity getBankUsers(){
       ArrayList<BankAccount> accounts = bankService.getAccounts();
       return ResponseEntity.status(200).body(accounts);
    }

    @PostMapping("/account")
    public ResponseEntity addBankUser(@RequestBody @Valid BankAccount bankAccount, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        boolean isValidId=bankService.addAccount(bankAccount);
        if (isValidId){
            return ResponseEntity.status(200).body(new ApiResponse("New account opened !",200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid user id",400));
    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam int accountId,@RequestParam int balance){

        boolean isValidId=bankService.deposit(accountId,balance);
        if (isValidId){
            return ResponseEntity.status(200).body(new ApiResponse("Deposit completed",200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid account id",400));
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestParam int accountId,@RequestParam int balance){

        int isValid=bankService.withdraw(accountId,balance);
        if (isValid==0){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid account id",400));
        }
        if(isValid==-1){
            return ResponseEntity.status(400).body(new ApiResponse("You don't have the balance",400));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Withdraw completed",200));
    }
}
