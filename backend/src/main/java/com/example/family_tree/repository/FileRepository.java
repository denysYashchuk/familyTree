package com.example.family_tree.repository;

import com.example.family_tree.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public interface FileRepository extends JpaRepository<File, Integer> {

    Long countByMemberId(int memberId);

}
