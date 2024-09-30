package com.example.crud_practice.service;

import com.example.crud_practice.dto.*;
import com.example.crud_practice.entity.Manager;
import com.example.crud_practice.entity.Schedule;
import com.example.crud_practice.repository.ManagerRepository;
import com.example.crud_practice.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ManagerRepository managerRepository;

    @Transactional
    public ScheduleAddResponseDto addSchedule(ScheduleAddRequestDto requestDto) {

        Manager manager = managerRepository.findById(requestDto.getManagerId()).orElseThrow(()-> new NullPointerException("존재하지 않는 담당자입니다"));

        Schedule newSchedule = new Schedule(
                requestDto.getScheduleName(),
                requestDto.getPassword(),
                manager
        );

        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return new ScheduleAddResponseDto(savedSchedule);
    }

    public ScheduleGetResponseDto getSchedule(long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new NullPointerException("존재하지 않는 일정입니다."));

        return new ScheduleGetResponseDto(schedule, schedule.getManager().getManagerName());
    }

    public Page<ScheduleGetResponseDto> getAllSchedules(String date, String managerName, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Schedule> schedules;

        if (!date.isEmpty() && !managerName.isEmpty()) {
            LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
            LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

            Manager manager = managerRepository.findByManagerName(managerName).orElseThrow(()-> new NullPointerException("존재하지 않는 담당자입니다."));
            schedules = scheduleRepository.findAllByManagerIdAndModifiedAtBetweenOrderByModifiedAtDesc(manager.getId(), startDateTime, endDateTime, pageable);

        }
        else if (date.isEmpty() && !managerName.isEmpty()){
            Manager manager = managerRepository.findByManagerName(managerName).orElseThrow(()-> new NullPointerException("존재하지 않는 담당자입니다."));
            schedules = scheduleRepository.findAllByManagerIdOrderByModifiedAtDesc(manager.getId(), pageable);

        }
        else if (!date.isEmpty()){
            LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
            LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

            schedules = scheduleRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(startDateTime, endDateTime, pageable);

        }
        else{
            throw new IllegalArgumentException("담당자 이름 또는 날짜를 입력해주세요");
        }

        return schedules.map(schedule -> new ScheduleGetResponseDto(schedule, schedule.getManager().getManagerName()));
    }


    @Transactional
    public void updateSchedule(long scheduleId, ScheduleUpdateRequestDto requestDto) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new NullPointerException("존재하지 않는 일정입니다"));
        Manager manager = managerRepository.findByManagerName(requestDto.getManagerName()).orElseThrow(()-> new NullPointerException("존재하지 않는 담당자입니다."));

        if (requestDto.getPassword().isEmpty()){
            throw new NullPointerException("비밀번호를 입력하세요");
        }

        if (!requestDto.getPassword().equals(schedule.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.updateSchedule(requestDto.getScheduleName(), manager);
    }

    @Transactional
    public void deleteSchedule(long scheduleId, ScheduleDeleteRequestDto requestDto) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new NullPointerException("존재하지 않는 일정입니다."));

        if (!schedule.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        scheduleRepository.deleteById(scheduleId);
    }
}
