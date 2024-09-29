package com.example.crud_practice.dto;

import com.example.crud_practice.entity.Manager;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ManagerAddResponseDto {

    private final long id;
    private final String managerName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ManagerAddResponseDto(Manager manager) {
        this.id = manager.getId();
        this.managerName = manager.getManagerName();
        this.email = manager.getEmail();
        this.createdAt = manager.getCreatedAt();
        this.modifiedAt = manager.getModifiedAt();
    }
}
