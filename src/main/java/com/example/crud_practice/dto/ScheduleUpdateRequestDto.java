package com.example.crud_practice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    @NotBlank
    @Size(min = 1, max = 200)
    private String scheduleName;
    @NotBlank(message = "담당자 이름을 입력하세요")
    private String managerName;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

}
