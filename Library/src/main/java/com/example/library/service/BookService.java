package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BookService {


    private ArrayList<Book> books=new ArrayList();
    private final UserService userService;
    private final LogService logService;

    public ArrayList<Book> getBooks(){
        return  books;
    }

    public boolean addBook(Book book) {
       return books.add(book);
    }

    public Integer buyBook(String userid, String bookid, Integer price) {
        User currentUser=userService.getUser(userid);
        Book currentBook=getBook(bookid);
        if(currentUser==null){
            return -1;
        }
        if(currentBook==null){
            return 0;
        }

        if(!(price >= currentBook.getPrice())){
            return 1;
        }

        books.remove(currentBook);

        logService.addLog("The user : " +
                " "+currentUser.getId()+" Bought the book : "+ currentBook.getId());

        return 2;

    }

    public Book getBook(String bookid){
        for (Book book:books) {
            if(book.getId().equals(bookid)){
                return book;
            }
        }
        return null;
    }
}
