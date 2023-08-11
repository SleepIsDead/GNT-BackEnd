package travelmakerbackend.plan.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelmakerbackend.plan.command.domain.aggregate.entity.PlanEntity;

@Repository
public
interface PlanRepository extends JpaRepository<PlanEntity, Integer> {

}

