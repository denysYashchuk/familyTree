package com.example.family_tree.service.impl;

import com.example.family_tree.domain.FamilyMember;
import com.example.family_tree.errors.ManyParentsException;
import com.example.family_tree.repository.FamilyMemberRepository;
import com.example.family_tree.service.FamilyMemberService;
import com.example.family_tree.service.dto.PageDTO;
import com.example.family_tree.service.dto.RequestFamilyMemberDTO;
import com.example.family_tree.service.dto.ResponseFamilyMemberDTO;
import com.example.family_tree.service.mapper.FamilyMemberMapper;
import com.example.family_tree.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.family_tree.util.Constants.PAGE_SIZE;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyMemberMapper familyMemberMapper;

    @Override
    @Transactional(readOnly = true)
    public PageDTO<ResponseFamilyMemberDTO> getFamilyMembers(Pageable pageable, int startAge, int endAge) {
        log.debug("Request to get FamilyMember {} - {} years old, page: {}", startAge, endAge, pageable.getPageNumber());

        Date endYear;
        if (startAge == -1) {
            endYear = DateUtil.getZeroYear();
        } else {
            endYear = DateUtil.getStartOfYearAgo(startAge);
        }

        Date startYear;
        if (endAge == -1) {
            startYear = DateUtil.getCurrentYearStart();
        } else {
            startYear = DateUtil.getStartOfYearAgo(endAge);
        }

        Page<FamilyMember> page = familyMemberRepository
                .findAllByBirthYearIsBetween(startYear, endYear, pageable);


        List<ResponseFamilyMemberDTO> familyMemberDTOs = page.get()
                .map(familyMemberMapper::familyMemberToResponseFamilyMemberDTO)
                .collect(Collectors.toList());

        return new PageDTO<>(familyMemberDTOs, pageable.getPageNumber(), page.getTotalPages());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseFamilyMemberDTO getFamilyMember(Integer id) {
        log.debug("Request to get FamilyMember : {}", id);
        return familyMemberRepository.findById(id).map(familyMemberMapper::familyMemberToResponseFamilyMemberDTO).orElse(null);
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteFamilyMember(Integer id) {
        log.debug("Request to delete FamilyMember : {}", id);
        familyMemberRepository.deleteById(id);
    }

    @Override
    @Transactional
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
    @Transactional
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

    @Transactional(readOnly = true)
    private List<FamilyMember> getFamilyMemberList(List<Integer> ids) {
        return familyMemberRepository.findByIdIn(ids);
    }

}
