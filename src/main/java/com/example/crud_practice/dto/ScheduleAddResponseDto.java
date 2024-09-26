package com.example.crud_practice.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleAddResponseDto {

    private final long scheduleId;
    private final String scheduleName;
    private final String managerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleAddResponseDto(long scheduleId, String scheduleName, String managerName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.managerName = managerName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
