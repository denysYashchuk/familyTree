package com.example.family_tree.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@AllArgsConstructor
public class PageDTO {

    @JsonProperty("members")
    private List<ResponseFamilyMemberDTO> members;

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("total_pages")
    private int totalPages;

}
