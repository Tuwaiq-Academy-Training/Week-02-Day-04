package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LoanService {


    private ArrayList<Loan> loans=new ArrayList();
    private final BookService bookService;
    private final UserService userService;
    private final LogService logService;

    public ArrayList<Loan> getLoans(){
        return  loans;
    }

    public boolean addLoan(Loan loan) {
        User currentUser=userService.getUser(loan.getUserid());
        Book currentBook=bookService.getBook(loan.getBookid());
        if(currentUser==null|| currentBook==null){
            return false;
        }

        logService.addLog("New loan added by" +
                " "+currentUser.getId()+" To a book "+ currentBook.getId());

        return loans.add(loan);
    }
}
