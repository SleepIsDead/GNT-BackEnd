package travelmakerbackend.report.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelmakerbackend.report.command.domain.aggregate.entity.ReportEntity;

@Repository
public
interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

}

