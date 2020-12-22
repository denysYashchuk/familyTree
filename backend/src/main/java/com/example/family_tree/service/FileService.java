package com.example.family_tree.service;

import com.example.family_tree.domain.File;
import com.example.family_tree.service.dto.ResponseFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */

/**
 * Service Interface for managing {@link File}.
 */
public interface FileService {

    /**
     * Store new image
     *
     * @param file     image to save
     * @param memberId id of the image owner
     * @return response file entity
     * @throws IOException
     */
    ResponseFileDTO storeImage(MultipartFile file, int memberId) throws IOException;

    /**
     * Get the "id" file.
     *
     * @param id the id of the entity.
     * @return the persisted entity.
     */
    File getFile(int id);

}
