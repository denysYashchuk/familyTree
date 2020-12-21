package com.example.family_tree.service.impl;

import com.example.family_tree.domain.File;
import com.example.family_tree.errors.ManyImagesException;
import com.example.family_tree.errors.NotImageException;
import com.example.family_tree.repository.FileRepository;
import com.example.family_tree.service.FileService;
import com.example.family_tree.service.dto.ResponseFileDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

import static com.example.family_tree.config.Constants.IMAGE_FORMATS;
import static com.example.family_tree.config.Constants.MAX_IMAGES;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public ResponseFileDTO storeImage(MultipartFile multipartFile, int memberId) throws IOException {
        if (!IMAGE_FORMATS.contains(multipartFile.getContentType().toLowerCase())) {
            throw new NotImageException();
        }

        Long numOfImages = fileRepository.countByMemberId(memberId);

        if (numOfImages != null && numOfImages == MAX_IMAGES) {
            throw new ManyImagesException();
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        File file = new File(fileName, multipartFile.getContentType(), multipartFile.getBytes(), memberId);

        File savedFile = fileRepository.save(file);

        return new ResponseFileDTO(savedFile);
    }

    @Override
    public File getFile(int id) {
        return fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no file with id: " + id));
    }
}
