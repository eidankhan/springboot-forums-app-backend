package com.sprinboot.jwt.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploads")
@CrossOrigin
public class FileController {

    @GetMapping
    public void getByName(String name){

    }
}
