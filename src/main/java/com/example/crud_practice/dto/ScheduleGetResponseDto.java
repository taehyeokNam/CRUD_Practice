package com.example.crud_practice.dto;

import com.example.crud_practice.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter

public class ScheduleGetResponseDto {

    private final long scheduleId;
    private final String scheduleName;
    private final String managerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleGetResponseDto(Schedule schedule, String managerName) {
        this.scheduleId = schedule.getId();
        this.scheduleName = schedule.getScheduleName();
        this.managerName = managerName;
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
