package com.example.crud_practice.controller;

import com.example.crud_practice.dto.*;
import com.example.crud_practice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<ScheduleGetResponseDto>> getAllSchedules(@RequestParam String date, @RequestParam String managerName,
                                                                        @RequestParam(defaultValue = "1", required = false) int page,
                                                                        @RequestParam(defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(scheduleService.getAllSchedules(date, managerName, page, size));
    }

    @PatchMapping("/schedules/{scheduleid}")
    public ResponseEntity<ScheduleGetResponseDto> updateSchedule(@PathVariable long scheduleid, @RequestBody ScheduleUpdateRequestDto requestDto) {
        scheduleService.updateSchedule(scheduleid, requestDto);
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleid));
    }

    @DeleteMapping("/schedules/{scheduleid}")
    public void deleteSchedule(@PathVariable long scheduleid, @RequestBody ScheduleDeleteRequestDto requestDto) {
        scheduleService.deleteSchedule(scheduleid, requestDto);
    }
}
