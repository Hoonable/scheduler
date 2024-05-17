package com.sparta.project1.controller;

import com.sparta.project1.dto.ScheduleRequestDto;
import com.sparta.project1.dto.ScheduleResponseDto;
import com.sparta.project1.entity.Schedule;
import com.sparta.project1.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {


    //Schedule schedule = new Schedule();
    private final ScheduleService service; //JdbcTemplate jdbcTemplate;

    public Controller(ScheduleService scheduleService) {
        this.service = scheduleService;}


    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return service.createSchedule(requestDto);


    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules() {

        return service.getSchedule();



    }


    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        return service.updateSchedule(id, requestDto);



    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        return service.deleteSchedule(id, requestDto);

    }



}