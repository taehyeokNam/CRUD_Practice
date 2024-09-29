package com.example.crud_practice.dto;

import com.example.crud_practice.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleAddResponseDto {

    private final long scheduleId;
    private final String scheduleName;
    private final String managerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleAddResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.scheduleName = schedule.getScheduleName();
        this.managerName = schedule.getManager().getManagerName();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
