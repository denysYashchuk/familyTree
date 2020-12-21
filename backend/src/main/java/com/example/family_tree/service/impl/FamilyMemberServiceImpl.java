package com.example.family_tree.service.impl;

import com.example.family_tree.domain.FamilyMember;
import com.example.family_tree.errors.ManyParentsException;
import com.example.family_tree.repository.FamilyMemberRepository;
import com.example.family_tree.service.FamilyMemberService;
import com.example.family_tree.service.dto.PageDTO;
import com.example.family_tree.service.dto.RequestFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFamilyMemberDTO;
import com.example.family_tree.service.mapper.FamilyMemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.family_tree.config.Constants.CURRENT_YEAR;
import static com.example.family_tree.config.Constants.PAGE_SIZE;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final Logger log = LoggerFactory.getLogger(FamilyMemberServiceImpl.class);

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyMemberMapper familyMemberMapper;

    public FamilyMemberServiceImpl(FamilyMemberRepository familyMemberRepository, FamilyMemberMapper familyMemberMapper) {
        this.familyMemberRepository = familyMemberRepository;
        this.familyMemberMapper = familyMemberMapper;
    }

    @Override
    public PageDTO getFamilyMembers(int startAge, int endAge, int pageNum, String sort) {
        log.debug("Request to get FamilyMember {} - {} years old, page: {}", startAge, endAge, pageNum);

        if (startAge == -1) {
            startAge = 0;
        }
        if (endAge == -1) {
            endAge = CURRENT_YEAR;
        }

        Page<FamilyMember> page;

        switch (sort) {
            case "age":
                page = familyMemberRepository
                        .findAllByBirthYearIsBetween(CURRENT_YEAR - endAge, CURRENT_YEAR - startAge,
                                PageRequest.of(pageNum - 1, PAGE_SIZE, Sort.by("birthYear").descending()));
                break;
            case "sex":
                page = familyMemberRepository
                        .findAllByBirthYearIsBetween(CURRENT_YEAR - endAge, CURRENT_YEAR - startAge,
                                PageRequest.of(pageNum - 1, PAGE_SIZE, Sort.by("sex").ascending()));
                break;
            default:
                page = familyMemberRepository
                        .findAllByBirthYearIsBetween(CURRENT_YEAR - endAge, CURRENT_YEAR - startAge,
                                PageRequest.of(pageNum - 1, PAGE_SIZE));
                break;
        }

        List<ResponseFamilyMemberDTO> familyMemberDTOs = page.get()
                .map(familyMemberMapper::familyMemberToResponseFamilyMemberDTO)
                .collect(Collectors.toList());

        return new PageDTO(familyMemberDTOs, pageNum, page.getTotalPages());
    }

    @Override
    public ResponseFamilyMemberDTO getFamilyMember(Integer id) {
        log.debug("Request to get FamilyMember : {}", id);
        return familyMemberRepository.findById(id).map(familyMemberMapper::familyMemberToResponseFamilyMemberDTO).orElse(null);
    }

    @Override
    public ResponseFamilyMemberDTO saveFamilyMember(Integer id, RequestFamilyMemberDTO requestFamilyMemberDTO) {
        log.debug("Request to create/update new FamilyMember: {}", requestFamilyMemberDTO);
        FamilyMember familyMember = familyMemberMapper.requestFamilyMemberDTOtoFamilyMember(requestFamilyMemberDTO);


        if (familyMember == null) {
            return null;

        } else {

            if (id != null && familyMemberRepository.findById(id).isPresent()) {
                log.debug("Updating FamilyMember {}", id);
                familyMember.setId(id);
            } else if (id != null) {
                throw new EntityNotFoundException("There is no FamilyMember with ID " + id);
            } else {
                log.debug("Creating FamilyMember");
            }

            List<Integer> parentIds;

            if (requestFamilyMemberDTO.getParents() != null) {
                parentIds = requestFamilyMemberDTO.getParents().stream()
                        .map(ResponseFamilyMemberDTO.FamilyMemberDTO::getId)
                        .collect(Collectors.toList());
            } else {
                parentIds = Collections.emptyList();
            }

            if (parentIds.size() > 2) {
                throw new ManyParentsException();
            }

            List<Integer> childrenIds;
            if (requestFamilyMemberDTO.getChildren() != null) {
                childrenIds = requestFamilyMemberDTO.getChildren().stream()
                        .map(ResponseFamilyMemberDTO.FamilyMemberDTO::getId)
                        .collect(Collectors.toList());
            } else {
                childrenIds = Collections.emptyList();
            }

            addParentsAndChildren(parentIds, childrenIds, familyMember);

            return familyMemberMapper.familyMemberToResponseFamilyMemberDTO(familyMemberRepository.save(familyMember));
        }
    }

    @Override
    public void deleteFamilyMember(Integer id) {
        log.debug("Request to delete FamilyMember : {}", id);
        familyMemberRepository.deleteById(id);
    }

    @Override
    public ResponseFamilyMemberDTO addParent(Integer memberId, Integer parentId) {
        FamilyMember familyMember = familyMemberRepository.findById(memberId).orElse(null);

        if (familyMember == null) {
            throw new EntityNotFoundException("There is no FamilyMember with ID " + memberId);
        }

        List<FamilyMember> parents = familyMember.getParents();

        if (parents.size() > 2) {
            throw new ManyParentsException();

        } else {
            FamilyMember parent = familyMemberRepository.findById(parentId)
                    .orElseThrow(() -> new EntityNotFoundException("There is no FamilyMember with ID " + parentId));
            parents.add(parent);

            return familyMemberMapper.familyMemberToResponseFamilyMemberDTO(familyMemberRepository.save(familyMember));
        }
    }

    @Override
    public ResponseFamilyMemberDTO removeParent(Integer memberId, Integer parentId) {
        FamilyMember familyMember = familyMemberRepository.findById(memberId).orElse(null);

        if (familyMember == null) {
            throw new EntityNotFoundException("There is no FamilyMember with ID " + memberId);
        }

        familyMember.getParents().removeIf(parent -> parent.getId().equals(parentId));

        return familyMemberMapper.familyMemberToResponseFamilyMemberDTO(familyMemberRepository.save(familyMember));
    }

    private void addParentsAndChildren(List<Integer> parentIds, List<Integer> childIds, FamilyMember familyMember) {

        if (parentIds != null && !parentIds.isEmpty()) {
            familyMember.setParents(getFamilyMemberList(parentIds));
        }

        if (childIds != null && !childIds.isEmpty()) {
            familyMember.setChildren(getFamilyMemberList(childIds));
        }

    }

    private List<FamilyMember> getFamilyMemberList(List<Integer> ids) {
        return familyMemberRepository.findByIdIn(ids);
    }

}
