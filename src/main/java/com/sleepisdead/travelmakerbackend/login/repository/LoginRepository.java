package com.sleepisdead.travelmakerbackend.login.repository;

import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Member, Long> {
}

