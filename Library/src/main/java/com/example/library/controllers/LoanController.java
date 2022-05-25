package com.example.library.controllers;

import com.example.library.model.Api;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.service.BookService;
import com.example.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<ArrayList<Loan>> getLoans(){
    return ResponseEntity.status(200).body(loanService.getLoans());
    }

    @PostMapping
    public ResponseEntity<Api> addLoan(@RequestBody @Valid Loan loan, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isLoanAdded=loanService.addLoan(loan);
        if(!isLoanAdded){
            return ResponseEntity.status(400).body(new Api("Invalid book id or user id",400));
        }
        return ResponseEntity.status(201).body(new Api("New loan added",201));
    }
}
