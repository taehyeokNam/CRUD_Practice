package com.example.crud_practice.controller;

import com.example.crud_practice.dto.ManagerAddRequestDto;
import com.example.crud_practice.dto.ManagerAddResponseDto;
import com.example.crud_practice.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerContorller {

    private final ManagerService managerService;

    @PostMapping("/managers")
    public ResponseEntity<ManagerAddResponseDto> addManager (@RequestBody @Valid ManagerAddRequestDto requestDto) {
        return ResponseEntity.ok(managerService.addManager(requestDto));
    }
}
