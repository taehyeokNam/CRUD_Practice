package com.example.crud_practice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ManagerAddRequestDto {

    @NotBlank(message = "담당자 이름을 입력하세요")
    private String managerName;
    @Email(message = "알맞은 이메일 형식을 입력하세요")
    @NotBlank
    private String email;
}
