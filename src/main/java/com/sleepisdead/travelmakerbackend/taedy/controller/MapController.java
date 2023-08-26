package com.sleepisdead.travelmakerbackend.taedy.controller;

import com.sleepisdead.travelmakerbackend.taedy.dto.common.CommonResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.ListResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.SingleResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.map.MapDto;
import com.sleepisdead.travelmakerbackend.taedy.dto.map.MapInsertRequestDto;
import com.sleepisdead.travelmakerbackend.taedy.dto.map.MapUpdateRequestDto;
import com.sleepisdead.travelmakerbackend.taedy.service.MapService;
import com.sleepisdead.travelmakerbackend.taedy.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"MAP API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MapController {

    private final MapService mapService;
    private final ResponseService responseService;

    /**
     * 지도에 위치 저장
     */
    @ApiOperation(value = "saveMap method", notes = "지도에 위치 저장 POST API")
    @PostMapping("/maps")
    public SingleResult<Long> saveMap(@RequestBody MapInsertRequestDto requestDto) {
        Long map_id = mapService.save(requestDto);
        return responseService.getSingleResult(map_id);
    }

    /**
     * 지도 전체 조회
     */
    @GetMapping("/maps")
    public ListResult<MapDto> findAllMaps() {
        return responseService.getListResult(mapService.findAllMaps());
    }

    /**
     * 위치 한개 조회
     */
    @GetMapping("/maps/{id}")
    public SingleResult<MapDto> findMapById(@PathVariable Long id) {
        return responseService.getSingleResult(mapService.findOne(id));
    }

    /**
     * 위치 수정
     */
    @PutMapping("/maps")
    public SingleResult<Long> updateMap(@RequestBody MapUpdateRequestDto requestDto) {

        Long map_id = requestDto.getMapId();

        MapDto mapDto = MapDto.builder()
                .locationX(requestDto.getLocationX())
                .locationY(requestDto.getLocationY())
                .address(requestDto.getAddress())
                .addressDetail(requestDto.getAddressDetail())
                .build();

        return responseService.getSingleResult(mapService.update(map_id, mapDto));
    }

    /**
     * 위치 삭제
     */
    @DeleteMapping("/maps/{id}")
    public CommonResult deleteMap(@PathVariable Long id) {
        mapService.delete(id);
        return responseService.getSuccessResult();
    }
}
