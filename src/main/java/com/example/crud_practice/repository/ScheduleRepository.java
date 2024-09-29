package com.example.crud_practice.repository;

import com.example.crud_practice.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<Schedule> findAllByManagerIdOrderByModifiedAtDesc(Long id, Pageable pageable);
    Page<Schedule> findAllByManagerIdAndModifiedAtBetweenOrderByModifiedAtDesc(Long id, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
