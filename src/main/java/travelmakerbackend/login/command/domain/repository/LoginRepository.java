package travelmakerbackend.login.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travelmakerbackend.member.command.domain.aggregate.entity.Member;

public interface LoginRepository extends JpaRepository<Member, Long> {
}

