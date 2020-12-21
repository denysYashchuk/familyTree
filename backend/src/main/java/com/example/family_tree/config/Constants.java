package com.example.family_tree.config;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public final class Constants {

    public static final int PAGE_SIZE = 15;
    public static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    public static final String FILE_URL = "/api/files/";
    public static final int MAX_IMAGES = 10;
    public static final List<String> IMAGE_FORMATS = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/svg");

    private Constants() {
    }

}
