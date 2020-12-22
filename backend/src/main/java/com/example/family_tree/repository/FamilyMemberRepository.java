package com.example.family_tree.repository;

import com.example.family_tree.domain.FamilyMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Integer> {

    List<FamilyMember> findByIdIn(List<Integer> ids);

    Page<FamilyMember> findAllByBirthYearIsBetween(Date startYear, Date endYear, Pageable pageable);

}
