package com.sleepisdead.travelmakerbackend.question.command.application.service;

import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import com.sleepisdead.travelmakerbackend.member.command.domain.repository.MemberRepository;
import com.sleepisdead.travelmakerbackend.question.command.application.dto.QuestionDto;
import com.sleepisdead.travelmakerbackend.question.command.domain.aggregate.entity.Question;
import com.sleepisdead.travelmakerbackend.exception.UserNotFoundException;
import com.sleepisdead.travelmakerbackend.question.command.infra.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    //문의 저장
    @Transactional
    public Long save(Long member_id, QuestionDto questionDto) {

        Member member = memberRepository.findById(member_id).orElseThrow(UserNotFoundException::new);
        Question question = Question.createQuestion(member, questionDto);
        questionRepository.save(question);

        return question.getId();
    }
}
