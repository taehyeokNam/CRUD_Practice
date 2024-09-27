package com.example.crud_practice.service;

import com.example.crud_practice.dto.ScheduleAddRequestDto;
import com.example.crud_practice.dto.ScheduleAddResponseDto;
import com.example.crud_practice.dto.ScheduleGetResponseDto;
import com.example.crud_practice.dto.ScheduleUpdateRequestDto;
import com.example.crud_practice.entity.Schedule;
import com.example.crud_practice.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<ScheduleGetResponseDto> getAllSchedules(String date, String managerName) {

        List<Schedule> scheduleList = new ArrayList<>();
        List<ScheduleGetResponseDto> dtoList = new ArrayList<>();

        if (!date.isEmpty()){
            LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
            LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

            if (managerName.isEmpty()){
                scheduleList = scheduleRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(startDateTime, endDateTime);
            }else{
                scheduleList = scheduleRepository.findAllByManagerNameAndModifiedAtBetweenOrderByModifiedAtDesc(managerName, startDateTime, endDateTime);
            }
        }
        if (date.isEmpty() && !managerName.isEmpty()){
            scheduleList = scheduleRepository.findByManagerNameOrderByModifiedAtDesc(managerName);
        }

        for (Schedule schedule : scheduleList){
            dtoList.add(new ScheduleGetResponseDto(
                    schedule.getId(),
                    schedule.getScheduleName(),
                    schedule.getManagerName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
             ));
        }

        return dtoList;
    }

    @Transactional
    public void updateSchedule(long scheduleid, ScheduleUpdateRequestDto requestDto) {

        Schedule schedule = scheduleRepository.findById(scheduleid).orElseThrow(()-> new NullPointerException("존재하지 않는 일정입니다"));

        if (requestDto.getPassword().isEmpty()){
            throw new NullPointerException("비밀번호를 입력하세요");
        }

        if (!requestDto.getPassword().equals(schedule.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.updateSchedule(requestDto.getScheduleName(), requestDto.getManagerName());
    }
}
