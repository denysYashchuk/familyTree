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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Entity
@Getter
@Setter
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "sex", length = 6, nullable = false)
    private Sex sex;

    @Column(name = "birth_year", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthYear;

    @ManyToMany
    @JoinTable(name = "parents",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<FamilyMember> parents = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "parents",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<FamilyMember> children = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "member_id")
    private Set<File> files = new HashSet<>();

}
