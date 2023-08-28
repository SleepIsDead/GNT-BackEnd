package com.sleepisdead.travelmakerbackend.map.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapUpdateRequestDto {
    private Long mapId;
    private String locationX;
    private String locationY;
    private String address;
    private String addressDetail;
}
