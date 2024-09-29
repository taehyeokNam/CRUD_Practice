package com.example.crud_practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scheduleName;
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Manager manager;

    public Schedule(String scheduleName, String password, Manager manager) {
        this.scheduleName = scheduleName;
        this.password = password;
        this.manager = manager;
    }

    public void updateSchedule(String scheduleName, Manager manager) {
        this.scheduleName = scheduleName;
        this.manager = manager;
    }
}
