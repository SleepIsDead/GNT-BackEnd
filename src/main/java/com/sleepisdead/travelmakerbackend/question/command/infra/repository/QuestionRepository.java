package com.sleepisdead.travelmakerbackend.question.command.infra.repository;

import com.sleepisdead.travelmakerbackend.question.command.domain.aggregate.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
