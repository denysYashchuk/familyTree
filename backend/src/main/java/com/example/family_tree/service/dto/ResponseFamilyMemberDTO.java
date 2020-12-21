package com.example.family_tree.service.dto;

import com.example.family_tree.domain.FamilyMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public class ResponseFamilyMemberDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("birth_year")
    private Integer birthYear;

    @JsonProperty("parents")
    private List<FamilyMemberDTO> parents;

    @JsonProperty("children")
    private List<FamilyMemberDTO> children;

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

    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FamilyMemberDTO {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("name")
        private String name;

        public FamilyMemberDTO(FamilyMember familyMember) {
            this.id = familyMember.getId();
            this.name = familyMember.getName();
        }
    }

}
