package com.example.family_tree.service;

import com.example.family_tree.domain.FamilyMember;
import com.example.family_tree.service.dto.PageDTO;
import com.example.family_tree.service.dto.RequestFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFamilyMemberDTO;
import org.springframework.data.domain.Pageable;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
/**
 * Service Interface for managing {@link FamilyMember}.
 */
public interface FamilyMemberService {

    /**
     * Get the family members filtered by age
     *
     * @param pageable the pagination information.
     * @param startAge the min family member age
     * @param endAge the max family member age
     * @return the page of persisted entities
     */
    PageDTO getFamilyMembers(Pageable pageable, int startAge, int endAge);

    /**
     * Get the "id" familyMember.
     *
     * @param id the id of the entity.
     * @return the persisted entity.
     */
    ResponseFamilyMemberDTO getFamilyMember(Integer id);

    /**
     * Save a familyMember.
     *
     * @param requestFamilyMemberDTO the entity to save or update.
     * @param id the id of entity to update
     * @return the persisted entity.
     */
    ResponseFamilyMemberDTO saveFamilyMember(Integer id, RequestFamilyMemberDTO requestFamilyMemberDTO);

    /**
     * Delete the "id" familyMember.
     *
     * @param id the id of the entity.
     */
    void deleteFamilyMember(Integer id);

    /**
     * Add parent to member
     *
     * @param memberId id of the member
     * @param parentId id of the parent
     * @return the updated entity
     */
    ResponseFamilyMemberDTO addParent(Integer memberId, Integer parentId);

    /**
     * Remove parent from member
     *
     * @param memberId id of the member
     * @param parentId id of the parent
     * @return the updated entity
     */
    ResponseFamilyMemberDTO removeParent(Integer memberId, Integer parentId);

}
