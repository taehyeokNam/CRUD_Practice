package com.example.crud_practice.repository;

import com.example.crud_practice.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime startDate, LocalDateTime endDate);
    List<Schedule> findByManagerNameOrderByModifiedAtDesc(String managerName);
    List<Schedule> findAllByManagerNameAndModifiedAtBetweenOrderByModifiedAtDesc(String managerName, LocalDateTime startDate, LocalDateTime endDate);

}
