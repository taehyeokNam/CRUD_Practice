package com.example.crud_practice.controller;

import com.example.crud_practice.dto.ScheduleAddRequestDto;
import com.example.crud_practice.dto.ScheduleAddResponseDto;
import com.example.crud_practice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleAddResponseDto> addSchedule(ScheduleAddRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.addSchedule(requestDto));
    }
}
