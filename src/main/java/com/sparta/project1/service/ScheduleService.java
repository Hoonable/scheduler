package com.sparta.project1.service;

import com.sparta.project1.dto.ScheduleRequestDto;
import com.sparta.project1.dto.ScheduleResponseDto;
import com.sparta.project1.entity.Schedule;
import com.sparta.project1.repository.ScheduleRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;

    }
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;

//        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) +1 : 1;
//        schedule.setId(maxId);
//
//        scheduleList.put(schedule.getId(), schedule);
//
//        ScheduleResponseDto scheduleResponseDto =  new ScheduleResponseDto(schedule);
//        return scheduleResponseDto;
    }


    public List<ScheduleResponseDto> getSchedule() {

        //DB 조회
        return scheduleRepository.findAll();




//        List<ScheduleResponseDto> responseList = scheduleList.values().stream().map(ScheduleResponseDto::new).toList();
//        return responseList;
    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 스케줄이 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);

        if (schedule != null) {
            if (schedule.getPassword().equals(requestDto.getPassword())){
            scheduleRepository.update(id, requestDto);
             return id;}
            else{
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }





    public Long deleteSchedule(Long id, ScheduleRequestDto requestDto) {


        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);

        if (schedule != null) {
            if (schedule.getPassword().equals(requestDto.getPassword())){
            scheduleRepository.delete(id);
            return id;}
            else{
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }
}
