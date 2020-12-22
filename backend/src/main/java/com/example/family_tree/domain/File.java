package com.example.family_tree.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Entity
@Table(name = "files")
@Getter
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Size(max = 10)
    @Column(name = "type", length = 10, nullable = false)
    private FileType type;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    @Column(name = "member_id", nullable = false)
    private int memberId;

    public File(@Size(max = 100) String name, @Size(max = 10) FileType type, byte[] data, int memberId) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.memberId = memberId;
    }
}
