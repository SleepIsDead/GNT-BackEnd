package travelmakerbackend.place.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelmakerbackend.place.command.domain.aggregate.entity.PlaceEntity;

@Repository
public
interface PlaceRepository extends JpaRepository<PlaceEntity, Integer> {

}

