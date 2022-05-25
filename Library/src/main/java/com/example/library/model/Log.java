package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class Log {
    private String message;
    private LocalDate date;

    public Log(String message){
        this.message=message;
        date=LocalDate.now();
    }
}
