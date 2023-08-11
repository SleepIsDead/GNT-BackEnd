package travelmakerbackend.login.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelmakerbackend.login.command.domain.aggregate.entity.LoginEntity;

@Repository
public
interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

}

