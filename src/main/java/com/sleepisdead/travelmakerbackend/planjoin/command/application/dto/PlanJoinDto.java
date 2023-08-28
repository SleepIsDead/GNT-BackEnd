package com.sleepisdead.travelmakerbackend.planjoin.command.application.dto;

import com.sleepisdead.travelmakerbackend.planjoin.command.domain.aggregate.entity.PlanJoin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanJoinDto {
    private Long planJoinId;
    private Long memberId;
    private Long planId;

    public PlanJoinDto(PlanJoin planJoin) {
        this.planJoinId = planJoin.getId();
        this.memberId = planJoin.getMember().getMemberId();
        this.planId = planJoin.getPlan().getId();
    }
}
