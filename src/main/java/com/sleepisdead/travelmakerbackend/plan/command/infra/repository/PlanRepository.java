package com.sleepisdead.travelmakerbackend.plan.command.infra.repository;

import com.sleepisdead.travelmakerbackend.plan.command.domain.aggregate.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
