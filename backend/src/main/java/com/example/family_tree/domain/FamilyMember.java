package com.example.family_tree.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Entity
@Table(name = "family_member")
@Getter
@Setter
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Size(max = 6)
    @Column(name = "sex", length = 6, nullable = false)
    private String sex;

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    @ManyToMany
    @JoinTable(name = "parents",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<FamilyMember> parents;

    @ManyToMany
    @JoinTable(name = "parents",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<FamilyMember> children;

    @OneToMany
    @JoinColumn(name = "member_id")
    private Set<File> files;

}
