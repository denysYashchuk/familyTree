package com.example.family_tree.service;

import com.example.family_tree.domain.File;
import com.example.family_tree.service.dto.ResponseFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public interface FileService {

    ResponseFileDTO storeImage(MultipartFile file, int memberId) throws IOException;

    File getFile(int id);

}
