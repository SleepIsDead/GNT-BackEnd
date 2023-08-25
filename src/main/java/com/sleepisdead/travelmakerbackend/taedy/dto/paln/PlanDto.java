package com.sleepisdead.travelmakerbackend.taedy.dto.paln;

import com.sleepisdead.travelmakerbackend.taedy.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {
    private Long planId;
    private String planTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String planImage;

    public PlanDto(Plan plan) {
        this.planId = plan.getId();
        this.planTitle = plan.getPlanTitle();
        this.startDate = plan.getStartDate();
        this.endDate = plan.getEndDate();
        this.planImage = plan.getPlanImage();
    }
}
