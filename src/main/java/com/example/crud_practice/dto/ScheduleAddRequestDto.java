package com.example.crud_practice.dto;

import lombok.Getter;

@Getter
public class ScheduleAddRequestDto {

    private String scheduleName;
    private String password;
    private long managerId;
}
