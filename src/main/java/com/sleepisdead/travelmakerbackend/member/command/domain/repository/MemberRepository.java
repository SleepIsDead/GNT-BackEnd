package com.sleepisdead.travelmakerbackend.member.command.domain.repository;


import com.sleepisdead.travelmakerbackend.member.command.application.dto.MemberSimpleDTO;
import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("SELECT new com.sleepisdead.travelmakerbackend.member.command.application.dto.MemberSimpleDTO(m.memberId, m.nickname, m.imageSource) FROM Member m WHERE m.memberId = :memberId")
    MemberSimpleDTO findMemberByIdSimple(long memberId);

	Member findByMemberId(Long memberId);

	List<Member> findByNickname(String nickname);

	@Query("SELECT m FROM Member AS m WHERE m.socialLogin LIKE :socialLogin AND m.socialId LIKE :socialId")
	Member findBySocialId(String socialLogin, String socialId);

//	Object findTopByOrderByMemberIdDesc();
}
