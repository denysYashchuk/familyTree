package com.example.family_tree.service.mapper;

import com.example.family_tree.domain.FamilyMember;
import com.example.family_tree.service.dto.RequestFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFamilyMemberDTO;
import org.springframework.stereotype.Service;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Service
public class FamilyMemberMapper {

    public ResponseFamilyMemberDTO familyMemberToResponseFamilyMemberDTO(FamilyMember familyMember) {
        return new ResponseFamilyMemberDTO(familyMember);
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
