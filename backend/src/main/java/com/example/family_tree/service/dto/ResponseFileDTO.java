package com.example.family_tree.service.dto;

import com.example.family_tree.domain.File;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.example.family_tree.config.Constants.FILE_URL;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public class ResponseFileDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("type")
    private String type;

    @JsonProperty("size")
    private long size;

    public ResponseFileDTO(File file) {
        this.name = file.getName();
        this.url = FILE_URL + file.getId();
        this.type = file.getType();
        this.size = file.getData().length;
    }
}
