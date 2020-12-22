package com.example.family_tree.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@AllArgsConstructor
@Getter
public class PageDTO<T> {

    private List<T> members;

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("total_pages")
    private int totalPages;

}
