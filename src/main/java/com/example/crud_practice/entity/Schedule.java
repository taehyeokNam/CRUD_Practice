package com.example.crud_practice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String scheduleName;
    String managerName;
    String password;

    public Schedule(String scheduleName, String managerName, String password) {
        this.scheduleName = scheduleName;
        this.managerName = managerName;
        this.password = password;
    }
}
