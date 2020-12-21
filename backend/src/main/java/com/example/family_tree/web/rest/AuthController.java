package com.example.family_tree.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping
    public ResponseEntity auth() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
