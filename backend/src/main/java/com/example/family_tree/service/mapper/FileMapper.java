package com.example.family_tree.service.mapper;

import com.example.family_tree.domain.File;
import com.example.family_tree.service.dto.ResponseFileDTO;
import org.springframework.stereotype.Component;

import static com.example.family_tree.util.Constants.FILE_URL;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Component
public class FileMapper {

    public ResponseFileDTO fileToResponseFileDTO(File file) {
        return ResponseFileDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .type(file.getType())
                .size(file.getData().length)
                .build();
    }

}
