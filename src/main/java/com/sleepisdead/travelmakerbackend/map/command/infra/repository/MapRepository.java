package com.sleepisdead.travelmakerbackend.map.command.infra.repository;

import com.sleepisdead.travelmakerbackend.map.command.domain.aggregate.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
}
