package com.sparta.project1.dto;

import com.sparta.project1.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String manager;
    private String password;
    private String date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(Long id, String title, String content, String manager, String password, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.manager = manager;
        this.password = password;
        this.date = date;

    }
}
