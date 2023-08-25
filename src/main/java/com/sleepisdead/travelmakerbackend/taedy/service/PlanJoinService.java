package com.sleepisdead.travelmakerbackend.taedy.service;

import com.sleepisdead.travelmakerbackend.taedy.dto.paln.PlanDto;
import com.sleepisdead.travelmakerbackend.taedy.entity.Plan;
import com.sleepisdead.travelmakerbackend.taedy.entity.PlanJoin;
import com.sleepisdead.travelmakerbackend.taedy.repository.PlanJoinRepository;
import com.sleepisdead.travelmakerbackend.taedy.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanJoinService {
    private final PlanJoinRepository planJoinRepository;
    private final PlanRepository planRepository;

    //memberId와 planId로 나의 계획 찾기
    public List<PlanDto> findMyPlan(Long memberId) {
        List<PlanJoin> planJoin = planJoinRepository.findByMember_memberId(memberId); //planJoin_id, plan_id, member_ID
        List<PlanDto> myPlans = new ArrayList<>();

        for (int i = 0; i < planJoin.size(); i++) {
            Long planId = planJoin.get(i).getPlan().getId();
            Plan plan = planRepository.findById(planId).orElseThrow();

            PlanDto planDto = PlanDto.builder()
                    .planId(plan.getId())
                    .planTitle(plan.getPlanTitle())
                    .planImage(plan.getPlanImage())
                    .startDate(plan.getStartDate())
                    .endDate(plan.getEndDate())
                    .build();

            myPlans.add(planDto);
        }
        return myPlans;
    }
}
