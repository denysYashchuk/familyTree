package com.example.family_tree.service.dto;

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
import java.util.List;

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

    @Pattern(regexp = "male|female", message = "Sex must be \"male\" or \"female\"")
    private String sex;

    @NotNull(message = "Birth year must not be null")
    @Min(value = 0, message = "Birth year must be >= 0")
    @Max(value = 2020, message = "Birth year must be <= 2020")
    @JsonProperty("birth_year")
    private Integer birthYear;

    @Size(max = 2, message = "Max parents size is 2")
    List<ResponseFamilyMemberDTO.FamilyMemberDTO> parents;
    List<ResponseFamilyMemberDTO.FamilyMemberDTO> children;

}
