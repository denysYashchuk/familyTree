package com.example.family_tree.web.rest;

import com.example.family_tree.service.FamilyMemberService;
import com.example.family_tree.service.dto.PageDTO;
import com.example.family_tree.service.dto.RequestFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFamilyMemberDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@RestController
@RequestMapping(value = "/api/family-members",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @GetMapping
    public ResponseEntity<PageDTO> getFamilyMembers(@RequestParam(name = "start", defaultValue = "-1") int startAge,
                                                    @RequestParam(name = "end", defaultValue = "-1") int endAge,
                                                    Pageable pageable) {
        PageDTO page = familyMemberService.getFamilyMembers(pageable, startAge, endAge);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFamilyMemberDTO> getFamilyMember(@PathVariable Integer id) {
        ResponseFamilyMemberDTO dto = familyMemberService.getFamilyMember(id);

        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseFamilyMemberDTO> addFamilyMember(@Valid @RequestBody RequestFamilyMemberDTO requestFamilyMemberDTO) {
        ResponseFamilyMemberDTO dto = familyMemberService.saveFamilyMember(null, requestFamilyMemberDTO);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFamilyMemberDTO> updateFamilyMember(@PathVariable Integer id, @Valid @RequestBody RequestFamilyMemberDTO requestFamilyMemberDTO) {
        ResponseFamilyMemberDTO dto = familyMemberService.saveFamilyMember(id, requestFamilyMemberDTO);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamilyMember(@PathVariable Integer id) {
        familyMemberService.deleteFamilyMember(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{memberId}/parents/{parentId}")
    public ResponseEntity<ResponseFamilyMemberDTO> addParent(@PathVariable Integer memberId, @PathVariable Integer parentId) {
        ResponseFamilyMemberDTO dto = familyMemberService.addParent(memberId, parentId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}/parents/{parentId}")
    public ResponseEntity<ResponseFamilyMemberDTO> deleteParent(@PathVariable Integer memberId, @PathVariable Integer parentId) {
        ResponseFamilyMemberDTO dto = familyMemberService.removeParent(memberId, parentId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
