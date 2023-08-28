package com.sleepisdead.travelmakerbackend.plan.command.application.controller;

import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.CommonResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.ListResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.SingleResult;
import com.sleepisdead.travelmakerbackend.plan.command.application.dto.PlanDto;
import com.sleepisdead.travelmakerbackend.plan.command.application.dto.PlanInsertRequestDto;
import com.sleepisdead.travelmakerbackend.plan.command.application.dto.PlanUpdateRequestDto;
import com.sleepisdead.travelmakerbackend.map.command.application.service.MapService;
import com.sleepisdead.travelmakerbackend.planjoin.command.application.service.PlanJoinService;
import com.sleepisdead.travelmakerbackend.plan.command.application.service.PlanService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Plan API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlanController {

    private final PlanService planService;
    private final PlanJoinService planJoinService;
    private final ResponseService responseService;
    private final MapService mapService;

    /**
     * 계획 저장
     * 1. 여행명, 여행기간, 이미지 저장
     * 2. 출발위치(MapStatus.START), 도착위치(MapStatus.END)는 MAP 테이블에 따로 저장
     * 3. 이미지는 도착위치 이미지로 저장
     */
    @PostMapping("/plans")
    public SingleResult<Long> savePlan(@AuthenticationPrincipal Member member, @RequestBody PlanInsertRequestDto requestDto) {

        //1
        PlanDto planDto = PlanDto.builder()
                .planTitle(requestDto.getPlanTitle())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .planImage(requestDto.getMap().get(1).getPlanImage()) //3
                .build();
        //2
        mapService.saveMapStatus(requestDto);

        return responseService.getSingleResult(planService.save(member.getMemberId(), planDto));
    }

    /**
     * 나의 계획 전체목록 조회
     */
    @GetMapping("/plans/my")
    public ListResult<PlanDto> findMyPlans(@AuthenticationPrincipal Member member) {
        List<PlanDto> myPlan = planJoinService.findMyPlan(member.getMemberId());
        return responseService.getListResult(myPlan);
    }

    /**
     * 계획 전체목록 조회
     */
    @GetMapping("/plans")
    public ListResult<PlanDto> findAllPlans() {
        return responseService.getListResult(planService.findAllPlans());
    }

    /**
     * 계획 단건 조회
     */
    @GetMapping("/plans/{id}")
    public SingleResult<PlanDto> findPlanById(@PathVariable Long id) {
        return responseService.getSingleResult(planService.findOne(id));
    }

    /**
     * 계획 수정
     */
    @PutMapping("/plans")
    public SingleResult<Long> updatePlan(@RequestBody PlanUpdateRequestDto requestDto) {

        PlanDto planDto = PlanDto.builder()
                .planTitle(requestDto.getPlanTitle())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .build();

        return responseService.getSingleResult(planService.update(requestDto.getPlanId(), planDto));
    }

    /**
     * 계획 삭제
     */
    @DeleteMapping("/plans/{id}")
    public CommonResult deletePlan(@AuthenticationPrincipal Member member, @PathVariable Long id) {
        planService.delete(member, id);
        return responseService.getSuccessResult();
    }
}
