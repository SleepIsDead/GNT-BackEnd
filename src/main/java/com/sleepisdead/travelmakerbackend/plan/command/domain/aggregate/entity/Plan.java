package com.sleepisdead.travelmakerbackend.plan.command.domain.aggregate.entity;

import com.sleepisdead.travelmakerbackend.plan.command.application.dto.PlanDto;
import com.sleepisdead.travelmakerbackend.common.BaseTimeEntity;
import com.sleepisdead.travelmakerbackend.planjoin.command.domain.aggregate.entity.PlanJoin;
import com.sleepisdead.travelmakerbackend.schedule.command.domain.aggregate.entity.Schedule;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Plan extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Column(name = "plan_title")
    private String planTitle;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "plan_image")
    private String planImage;

    @OneToMany(mappedBy = "plan")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "plan", cascade = ALL)
    private List<PlanJoin> planJoins;

    public void addPlanJoins(PlanJoin planJoin) {
        planJoins.add(planJoin);
        planJoin.setPlan(this);
    }

    //비즈니스 로직
    public void updatePlan(String planTitle, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.planTitle = planTitle;
        this.startDate = startDateTime;
        this.endDate = endDateTime;
    }

    public void addFriends(PlanJoin planJoin) {
        planJoins.add(planJoin);
        planJoin.setPlan(this);
    }

    public void addPlan(String planTitle, LocalDateTime startDate, LocalDateTime endDate, String planImage) {
        this.planTitle = planTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planImage = planImage;
    }

    //생성 메서드
    public static Plan createPlan(PlanDto planDto, PlanJoin... planJoins) {

        Plan plan = new Plan();

        plan.addPlan(planDto.getPlanTitle(), planDto.getStartDate(), planDto.getEndDate(), planDto.getPlanImage());

        for (PlanJoin planJoin : planJoins) {
            plan.addFriends(planJoin);
        }

        return plan;
    }
}
