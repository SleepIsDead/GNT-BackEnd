package com.sleepisdead.travelmakerbackend.taedy.controller;

import com.sleepisdead.travelmakerbackend.taedy.dto.common.CommonResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.ListResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.SingleResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.schedule.ScheduleDto;
import com.sleepisdead.travelmakerbackend.taedy.dto.schedule.ScheduleInsertRequestDto;
import com.sleepisdead.travelmakerbackend.taedy.dto.schedule.ScheduleUpdateRequestDto;
import com.sleepisdead.travelmakerbackend.taedy.entity.Map;
import com.sleepisdead.travelmakerbackend.taedy.entity.MapStatus;
import com.sleepisdead.travelmakerbackend.taedy.repository.MapRepository;
import com.sleepisdead.travelmakerbackend.taedy.service.ScheduleService;
import com.sleepisdead.travelmakerbackend.taedy.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ResponseService responseService;
    private final MapRepository mapRepository;

    /**
     * 일정 저장
     */
    @PostMapping("/schedules")
    public SingleResult<Long> saveSchedules(@RequestBody ScheduleInsertRequestDto requestDto) {

        ScheduleDto scheduleDto = ScheduleDto.builder()
                .scheduleTitle(requestDto.getScheduleTitle())
                .price(requestDto.getPrice())
                .startDateTime(requestDto.getStartDateTime())
                .endDateTime(requestDto.getEndDateTime())
                .memo(requestDto.getMemo())
                .build();

        return responseService.getSingleResult(scheduleService.save(requestDto.getPlanId(), scheduleDto));
    }

    /**
     * 계획 별로 일정 전체목록 조회
     * 1. 시작 시간이 빠른 순서대로 오름차순 정렬
     */
    @GetMapping("/schedules/{planId}")
    public ListResult<ScheduleDto> findAllSchedules(@PathVariable Long planId) {
        return responseService.getListResult(scheduleService.findAllSchedules(planId));
    }

    /**
     * 일정 수정
     * 1. MAP 테이블에 일정위치 저장(MapStatus.SCHEDULE)
     * 2. 일정 수정할 때 일정위치 값 같이 UPDATE
     */
    @PutMapping("/schedules")
    public SingleResult<Long> updateSchedule(@RequestBody ScheduleUpdateRequestDto requestDto) {

        Map map = Map.builder()
                .mapImage(requestDto.getMap().get(0).getPlanImage())
                .locationX(requestDto.getMap().get(0).getLocationX())
                .locationY(requestDto.getMap().get(0).getLocationY())
                .address(requestDto.getMap().get(0).getAddress())
                .addressDetail(requestDto.getMap().get(0).getAddressDetail())
                .mapStatus(MapStatus.SCHEDULE)
                .build();

        mapRepository.save(map);

        ScheduleDto scheduleDto = ScheduleDto.builder()
                .scheduleTitle(requestDto.getScheduleTitle())
                .price(requestDto.getPrice())
                .startDateTime(requestDto.getStartDateTime())
                .endDateTime(requestDto.getEndDateTime())
                .memo(requestDto.getMemo())
                //.map(map)
                .build();

        return responseService.getSingleResult(scheduleService.update(requestDto.getScheduleId(), scheduleDto));
    }

    /**
     * 일정 삭제
     */
    @DeleteMapping("/schedules/{id}")
    public CommonResult deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return responseService.getSuccessResult();
    }
}
