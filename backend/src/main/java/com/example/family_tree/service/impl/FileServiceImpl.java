package com.example.family_tree.service.impl;

import com.example.family_tree.domain.File;
import com.example.family_tree.domain.FileType;
import com.example.family_tree.errors.ManyImagesException;
import com.example.family_tree.errors.NotImageException;
import com.example.family_tree.repository.FileRepository;
import com.example.family_tree.service.FileService;
import com.example.family_tree.service.dto.ResponseFileDTO;
import com.example.family_tree.service.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

import static com.example.family_tree.util.Constants.MAX_IMAGES;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Override
    @Transactional
    public ResponseFileDTO storeImage(MultipartFile multipartFile, int memberId) throws IOException {
        FileType fileType = FileType.getByType(multipartFile.getContentType());

        if (fileType == null) {
            throw new NotImageException();
        }

        Long numOfImages = fileRepository.countByMemberId(memberId);

        if (numOfImages != null && numOfImages == MAX_IMAGES) {
            throw new ManyImagesException();
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        File file = new File(fileName, fileType, multipartFile.getBytes(), memberId);

        File savedFile = fileRepository.save(file);

        return fileMapper.fileToResponseFileDTO(savedFile);
    }

    @Override
    @Transactional(readOnly = true)
    public File getFile(int id) {
        return fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no file with id: " + id));
    }
}
