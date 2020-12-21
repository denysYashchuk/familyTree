package com.example.family_tree.service;

import com.example.family_tree.service.dto.PageDTO;
import com.example.family_tree.service.dto.RequestFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFamilyMemberDTO;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public interface FamilyMemberService {

    PageDTO getFamilyMembers(int startAge, int endAge, int page, String sort);

    ResponseFamilyMemberDTO getFamilyMember(Integer id);

    ResponseFamilyMemberDTO saveFamilyMember(Integer id, RequestFamilyMemberDTO requestFamilyMemberDTO);

    void deleteFamilyMember(Integer id);

    ResponseFamilyMemberDTO addParent(Integer memberId, Integer parentId);

    ResponseFamilyMemberDTO removeParent(Integer memberId, Integer parentId);

}
