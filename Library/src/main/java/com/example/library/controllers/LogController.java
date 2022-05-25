package com.example.library.controllers;

import com.example.library.model.Log;
import com.example.library.model.User;
import com.example.library.service.LogService;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    @GetMapping
    public ResponseEntity<ArrayList<Log>> getLogs(){
        return ResponseEntity.status(200).body(logService.getLogs());
    }
}
