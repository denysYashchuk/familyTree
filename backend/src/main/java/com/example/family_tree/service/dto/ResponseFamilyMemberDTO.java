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

    public ResponseFamilyMemberDTO(FamilyMember familyMember) {
        this.id = familyMember.getId();
        this.name = familyMember.getName();
        this.sex = familyMember.getSex();
        this.birthYear = familyMember.getBirthYear();

        List<FamilyMember> parents = familyMember.getParents();

        if (parents != null && !parents.isEmpty()) {
            this.parents = parents.stream().map(FamilyMemberDTO::new).collect(Collectors.toList());
        }

        List<FamilyMember> children = familyMember.getChildren();

        if (children != null && !children.isEmpty()) {
            this.children = children.stream().map(FamilyMemberDTO::new).collect(Collectors.toList());
        }

        Set<File> files = familyMember.getFiles();

        if (files != null && !files.isEmpty()) {
            this.files = files.stream().map(ResponseFileDTO::new).collect(Collectors.toList());
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FamilyMemberDTO {

        private Integer id;
        private String name;

    }

}
