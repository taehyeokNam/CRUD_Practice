package com.example.crud_practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Manager extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String managerName;
    private String email;

    @OneToMany(mappedBy = "manager")
    private List<Schedule> schedules;

    public Manager(String managerName, String email) {
        this.managerName = managerName;
        this.email = email;
    }
}
