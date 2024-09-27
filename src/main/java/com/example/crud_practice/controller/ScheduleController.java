package com.example.crud_practice.controller;

import com.example.crud_practice.dto.ScheduleAddRequestDto;
import com.example.crud_practice.dto.ScheduleAddResponseDto;
import com.example.crud_practice.dto.ScheduleGetResponseDto;
import com.example.crud_practice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleAddResponseDto> addSchedule(@RequestBody ScheduleAddRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.addSchedule(requestDto));
    }

    @GetMapping("/schedules/{scheduleid}")
    public ResponseEntity<ScheduleGetResponseDto> getSchedule(@PathVariable long scheduleid) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleid));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponseDto>> getAllSchedules(@RequestParam String date, @RequestParam String managerName) {
        return ResponseEntity.ok(scheduleService.getAllSchedules(date, managerName));
    }
}
