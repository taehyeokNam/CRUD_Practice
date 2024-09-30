package com.example.crud_practice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleAddRequestDto {

    @NotBlank(message = "일정의 이름을 입력하세요")
    @Size(min = 1, max = 200)
    private String scheduleName;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @Positive
    private long managerId;
}
