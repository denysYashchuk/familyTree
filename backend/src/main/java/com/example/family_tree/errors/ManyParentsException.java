package com.example.family_tree.errors;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public class ManyParentsException extends RuntimeException {

    public ManyParentsException() {
        super("Too many parents for 1 member");
    }

}
