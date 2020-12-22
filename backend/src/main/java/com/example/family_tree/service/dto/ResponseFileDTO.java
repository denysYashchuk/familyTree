package com.example.family_tree.service.dto;

import com.example.family_tree.domain.File;
import com.example.family_tree.domain.FileType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import static com.example.family_tree.util.Constants.FILE_URL;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Getter
@Builder
public class ResponseFileDTO {

    private Integer id;
    private String name;
    private FileType type;
    private long size;

}
