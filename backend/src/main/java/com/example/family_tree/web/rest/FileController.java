package com.example.family_tree.web.rest;

import com.example.family_tree.domain.File;
import com.example.family_tree.service.FileService;
import com.example.family_tree.service.dto.ResponseFileDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@RestController
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/family-members/{memberId}/files")
    public ResponseEntity<ResponseFileDTO> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @PathVariable("memberId") int memberId) {
        try {
            ResponseFileDTO responseFileDTO = fileService.storeImage(file, memberId);

            return new ResponseEntity<>(responseFileDTO, HttpStatus.CREATED);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") int id) {
        File file = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }
}
