package com.sleepisdead.travelmakerbackend.taedy.repository;

import com.sleepisdead.travelmakerbackend.taedy.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
