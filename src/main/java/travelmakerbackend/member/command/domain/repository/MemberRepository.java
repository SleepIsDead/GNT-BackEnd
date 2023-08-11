package travelmakerbackend.member.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelmakerbackend.member.command.domain.aggregate.entity.MemberEntity;

@Repository
public
interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

}

