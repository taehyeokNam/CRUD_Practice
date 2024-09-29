package com.example.crud_practice.service;

import com.example.crud_practice.dto.ManagerAddRequestDto;
import com.example.crud_practice.dto.ManagerAddResponseDto;
import com.example.crud_practice.entity.Manager;
import com.example.crud_practice.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Transactional
    public ManagerAddResponseDto addManager(ManagerAddRequestDto requestDto) {

        Manager newManager = new Manager(requestDto.getManagerName(), requestDto.getEmail());
        Manager saveedManager = managerRepository.save(newManager);

        return new ManagerAddResponseDto(saveedManager);
    }
}
