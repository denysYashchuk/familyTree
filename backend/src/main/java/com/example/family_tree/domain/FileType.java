package com.example.family_tree.domain;

import lombok.Getter;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public enum FileType {
    JPG("image/jpg"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    SVG("image/svg");

    private String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static FileType getByType(String type) {

        for (FileType fileType : values()) {

            if (fileType.type.equals(type)) {
                return fileType;
            }
        }

        return null;
    }
}
