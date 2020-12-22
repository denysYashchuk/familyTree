package com.example.family_tree.service.dto;

import com.example.family_tree.domain.FamilyMember;
import com.example.family_tree.domain.File;
import com.example.family_tree.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Builder
@Getter
public class ResponseFamilyMemberDTO {

    private Integer id;
    private String name;
    private Sex sex;

    @JsonProperty("birth_year")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy", timezone = "UTC")
    private Date birthYear;

    private List<FamilyMemberDTO> parents;
    private List<FamilyMemberDTO> children;
    private List<ResponseFileDTO> files;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FamilyMemberDTO {

        private Integer id;
        private String name;

    }

}
