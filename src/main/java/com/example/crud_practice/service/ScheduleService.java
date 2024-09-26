package com.example.crud_practice.service;

import com.example.crud_practice.dto.ScheduleAddRequestDto;
import com.example.crud_practice.dto.ScheduleAddResponseDto;
import com.example.crud_practice.dto.ScheduleGetResponseDto;
import com.example.crud_practice.entity.Schedule;
import com.example.crud_practice.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleAddResponseDto addSchedule(ScheduleAddRequestDto requestDto) {

        Schedule newSchedule = new Schedule(
                requestDto.getScheduleName(),
                requestDto.getManagerName(),
                requestDto.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return new ScheduleAddResponseDto(
                savedSchedule.getId(),
                savedSchedule.getScheduleName(),
                savedSchedule.getManagerName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    public ScheduleGetResponseDto getSchedule(long scheduleid) {

        Schedule schedule = scheduleRepository.findById(scheduleid).orElseThrow(()-> new NullPointerException("존재하지 않는 일정입니다."));

        return new ScheduleGetResponseDto(
                schedule.getId(),
                schedule.getScheduleName(),
                schedule.getManagerName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
