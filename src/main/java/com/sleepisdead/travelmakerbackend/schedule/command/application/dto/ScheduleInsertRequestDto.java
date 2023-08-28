package com.sleepisdead.travelmakerbackend.schedule.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInsertRequestDto {
    private Long planId;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
}
