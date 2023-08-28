package com.sleepisdead.travelmakerbackend.schedule.command.application.dto;

import com.sleepisdead.travelmakerbackend.map.command.application.dto.MapDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
    private Long scheduleId;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
    private List<MapDto> map;
}
