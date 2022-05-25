package com.example.library.controllers;

import com.example.library.model.Api;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ArrayList<Book>> getBooks(){
    return ResponseEntity.status(200).body(bookService.getBooks());
    }

    @PostMapping
    public ResponseEntity<Api> addBook(@RequestBody @Valid Book book, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isBookAdded=bookService.addBook(book);
        if(!isBookAdded){
            return ResponseEntity.status(500).body(new Api("Error adding a book",500));

        }
        return ResponseEntity.status(200).body(new Api("New book added",200));
    }

    @PutMapping("/buy")
    public ResponseEntity<Api> buyBook(@RequestParam String userid,
                                       @RequestParam String bookid,
                                       @RequestParam Integer price){
        Integer buyStatus=bookService.buyBook(userid,bookid,price);

        switch (buyStatus){
            case -1:
                return ResponseEntity.status(400).body(new Api("Userid is not valid",400));
            case 0:
                return ResponseEntity.status(400).body(new Api("Bookid is not valid",400));
            case 1:
                return ResponseEntity.status(400).body(new Api("Price is not enough",400));
            case 2:
                return ResponseEntity.status(200).body(new Api("Book purchased !",200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error",500));
        }

    }




}
