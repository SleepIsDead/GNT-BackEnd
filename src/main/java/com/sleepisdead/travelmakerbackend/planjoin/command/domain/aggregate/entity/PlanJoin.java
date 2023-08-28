package com.sleepisdead.travelmakerbackend.planjoin.command.domain.aggregate.entity;

import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import com.sleepisdead.travelmakerbackend.plan.command.domain.aggregate.entity.Plan;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanJoin {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plan_join_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;


    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Builder
    public PlanJoin(Member member, Plan plan) {
        this.member = member;
        this.plan = plan;
    }
}
