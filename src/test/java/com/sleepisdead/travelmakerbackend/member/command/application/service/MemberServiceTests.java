package com.sleepisdead.travelmakerbackend.member.command.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleepisdead.travelmakerbackend.member.command.application.dto.MemberDTO;
import com.sleepisdead.travelmakerbackend.member.command.application.dto.MemberSimpleDTO;
import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import com.sleepisdead.travelmakerbackend.member.command.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTests {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public MemberServiceTests(MemberService memberService, MemberRepository memberRepository,
                              ModelMapper modelMapper, ObjectMapper objectMapper){
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("DTO를 통해 새로운 회원 등록하기")
    @Transactional
    void registNewUser() {
        //given
        MemberDTO memberDTO1 = MemberDTO.builder()
                .reportCount(0)
                .socialLogin("test1")
                .socialId("test2")
                .accessToken("testToken")
                .accessTokenExpireDate(123123)
                .refreshToken("reToken")
                .refreshTokenExpireDate(123123)
                .email("test3@test.com")
                .preferredLocation("EDIT")
                .preferredType("DEACTIVATE")
                .build();
        MemberDTO memberDTO2 = MemberDTO.builder()
                .reportCount(0)
                .socialLogin("test3")
                .socialId("test4")
                .accessToken("testToken")
                .accessTokenExpireDate(234234)
                .refreshToken("reToken")
                .refreshTokenExpireDate(234234)
                .email("test4@test.com")
                .preferredLocation("EDIT")
                .preferredType("DEACTIVATE")
                .build();

        Long memberId1 = memberService.registNewUser(memberDTO1);
        Long memberId2 = memberService.registNewUser(memberDTO2);

        //when
        Member member1 = memberRepository.findAll().get(0);
        Member member2 = memberRepository.findAll().get(1);

        //when & then
        assertEquals(2, memberRepository.count());
        assertEquals("test3@test.com", member1.getEmail());
        assertEquals("test4@test.com",member2.getEmail());

    }

    @Test
    @DisplayName("회원번호로 회원정보 찾기")
    @Transactional
    void findMemberById() {
        //given
        MemberDTO memberDTO1 = MemberDTO.builder()
                .reportCount(0)
                .socialLogin("test1")
                .socialId("test2")
                .accessToken("testToken")
                .accessTokenExpireDate(123123)
                .refreshToken("reToken")
                .refreshTokenExpireDate(123123)
                .email("test3@test.com")
                .preferredLocation("EDIT")
                .preferredType("DEACTIVATE")
                .build();
        Long memberId = memberService.registNewUser(memberDTO1);

        // When
        MemberDTO foundMember = memberService.findMemberById(memberId);

        // Then
        assertNotNull(foundMember, "회원 정보가 null입니다.");
        assertEquals(memberDTO1.getSocialLogin(), foundMember.getSocialLogin(), "Social Login 정보가 일치하지 않습니다.");
        assertEquals(memberDTO1.getSocialId(), foundMember.getSocialId(), "Social ID 정보가 일치하지 않습니다.");
        assertEquals(memberDTO1.getEmail(), foundMember.getEmail(), "Email 정보가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("회원 프로필 불러오기 (심플DTO)")
    @Transactional
    void findMemberByIdSimple() {
        //given
        MemberDTO memberDTO1 = MemberDTO.builder()
                .reportCount(0)
                .socialLogin("test1")
                .socialId("test2")
                .accessToken("testToken")
                .accessTokenExpireDate(123123)
                .refreshToken("reToken")
                .refreshTokenExpireDate(123123)
                .email("test3@test.com")
                .preferredLocation("EDIT")
                .preferredType("DEACTIVATE")
                .build();
        Long memberId = memberService.registNewUser(memberDTO1);

        // When
        MemberSimpleDTO simpleDTO = memberService.findMemberByIdSimple(memberId);
        Member member = memberRepository.findByMemberId(memberId);

        // Then
        assertNotNull(simpleDTO, "회원 정보가 null입니다.");
        assertEquals(simpleDTO.getNickname(), member.getNickname(), "Social Login 정보가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("모든 회원 보기")
    @Transactional
    void findAllMembers() {
        //given
        MemberDTO memberDTO1 = MemberDTO.builder()
                .reportCount(0)
                .socialLogin("test1")
                .socialId("test2")
                .accessToken("testToken")
                .accessTokenExpireDate(123123)
                .refreshToken("reToken")
                .refreshTokenExpireDate(123123)
                .email("test3@test.com")
                .preferredLocation("EDIT")
                .preferredType("DEACTIVATE")
                .build();
        MemberDTO memberDTO2 = MemberDTO.builder()
                .reportCount(0)
                .socialLogin("test3")
                .socialId("test4")
                .accessToken("testToken")
                .accessTokenExpireDate(234234)
                .refreshToken("reToken")
                .refreshTokenExpireDate(234234)
                .email("test4@test.com")
                .preferredLocation("EDIT")
                .preferredType("DEACTIVATE")
                .build();

        Long memberId1 = memberService.registNewUser(memberDTO1);
        Long memberId2 = memberService.registNewUser(memberDTO2);

        //when
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberDTO> memberDTOPage = memberService.findAllMembers(pageable);

        //then
        assertNotNull(memberDTOPage);
        assertFalse(memberDTOPage.isEmpty());
        assertEquals(memberDTOPage.getTotalElements(),2);
    }

    @Test
    void modifyMember() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void checkIfRepeated() {
    }

    @Test
    void findBySocialId() {
    }

    @Test
    void getAuthedMember() {
    }
}