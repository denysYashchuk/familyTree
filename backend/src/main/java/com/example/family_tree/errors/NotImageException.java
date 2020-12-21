package com.example.family_tree.errors;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public class NotImageException extends RuntimeException {

    public NotImageException() {
        super("Not image format");
    }
}
