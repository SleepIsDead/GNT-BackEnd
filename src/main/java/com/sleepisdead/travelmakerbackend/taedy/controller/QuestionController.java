package com.sleepisdead.travelmakerbackend.taedy.controller;

import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import com.sleepisdead.travelmakerbackend.taedy.dto.common.SingleResult;
import com.sleepisdead.travelmakerbackend.taedy.dto.question.QuestionDto;
import com.sleepisdead.travelmakerbackend.taedy.dto.question.QuestionInsertRequestDto;
import com.sleepisdead.travelmakerbackend.taedy.service.QuestionService;
import com.sleepisdead.travelmakerbackend.taedy.service.common.ResponseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Question API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestionController {

    private final QuestionService questionService;
    private final ResponseService responseService;

    /**
     * 문의 저장(일단 DB에 저장 -> 나중에 관리자 이메일로)
     */
    @PostMapping("/questions")
    public SingleResult<Long> saveQuestion(@AuthenticationPrincipal Member member, @RequestBody QuestionInsertRequestDto requestDto) {

        QuestionDto questionDto = QuestionDto.builder()
                .questionTitle(requestDto.getQuestionTitle())
                .questionContent(requestDto.getQuestionContent())
                .questionImage(requestDto.getQuestionImage())
                .build();

        return responseService.getSingleResult(questionService.save(member.getMemberId(), questionDto));
    }
}
