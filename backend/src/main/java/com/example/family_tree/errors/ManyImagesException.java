package com.example.family_tree.errors;

import static com.example.family_tree.util.Constants.MAX_IMAGES;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public class ManyImagesException extends RuntimeException {

    public ManyImagesException() {
        super("Max number of images pr member is " + MAX_IMAGES);
    }
}
