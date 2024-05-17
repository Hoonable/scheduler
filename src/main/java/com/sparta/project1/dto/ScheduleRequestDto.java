package com.sparta.project1.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private String title;
    private String content;
    private String manager;
    private String password;
    private String date;
}
