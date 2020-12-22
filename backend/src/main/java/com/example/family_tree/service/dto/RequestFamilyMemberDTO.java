package com.example.family_tree.service.dto;

import com.example.family_tree.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@NoArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestFamilyMemberDTO {

    @NotEmpty(message = "Name must not be empty")
    private String name;

    private Sex sex;

    @NotNull(message = "Birth year must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy", timezone = "UTC")
    @JsonProperty("birth_year")
    private Date birthYear;

    @Size(max = 2, message = "Max parents size is 2")
    List<ResponseFamilyMemberDTO.FamilyMemberDTO> parents;
    List<ResponseFamilyMemberDTO.FamilyMemberDTO> children;

}
