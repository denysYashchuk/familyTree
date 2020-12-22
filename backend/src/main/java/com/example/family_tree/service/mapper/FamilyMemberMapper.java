package com.example.family_tree.service.mapper;

import com.example.family_tree.domain.FamilyMember;
import com.example.family_tree.domain.File;
import com.example.family_tree.service.dto.RequestFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Component
@RequiredArgsConstructor
public class FamilyMemberMapper {

    private final FileMapper fileMapper;

    public ResponseFamilyMemberDTO familyMemberToResponseFamilyMemberDTO(FamilyMember familyMember) {
        ResponseFamilyMemberDTO.ResponseFamilyMemberDTOBuilder builder =  ResponseFamilyMemberDTO.builder()
                .id(familyMember.getId())
                .name(familyMember.getName())
                .sex(familyMember.getSex())
                .birthYear(familyMember.getBirthYear());

        List<FamilyMember> parents = familyMember.getParents();

        if (parents != null && !parents.isEmpty()) {
            builder.parents(parents.stream()
                    .map(fm -> new ResponseFamilyMemberDTO.FamilyMemberDTO(fm.getId(), fm.getName()))
                    .collect(Collectors.toList()));
        }

        List<FamilyMember> children = familyMember.getChildren();

        if (children != null && !children.isEmpty()) {
            builder.children(children.stream()
                    .map(fm -> new ResponseFamilyMemberDTO.FamilyMemberDTO(fm.getId(), fm.getName()))
                    .collect(Collectors.toList()));
        }

        Set<File> files = familyMember.getFiles();

        if (files != null && !files.isEmpty()) {
            builder.files(files.stream()
                    .map(fileMapper::fileToResponseFileDTO)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }

    public FamilyMember requestFamilyMemberDTOtoFamilyMember(RequestFamilyMemberDTO requestFamilyMemberDTO){
        if(requestFamilyMemberDTO == null){
            return null;

        }else {
            FamilyMember familyMember = new FamilyMember();

            familyMember.setName(requestFamilyMemberDTO.getName());
            familyMember.setSex(requestFamilyMemberDTO.getSex());
            familyMember.setBirthYear(requestFamilyMemberDTO.getBirthYear());

            return familyMember;
        }
    }

}
