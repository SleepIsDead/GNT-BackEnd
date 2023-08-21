package travelmakerbackend.member.command.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelmakerbackend.common.ResponseMessage;
import travelmakerbackend.member.command.application.dto.MemberDTO;
import travelmakerbackend.member.command.application.dto.MemberSimpleDTO;
import travelmakerbackend.member.command.application.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "멤버 관련 기능 API")
@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberController(MemberService memberService, ModelMapper modelMapper) {

        this.memberService = memberService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "멤버 이름, 프사(예정), 마이페이지로 가는 링크만 id로 조회")
    @GetMapping("/members/simple/{memberId}")
    public ResponseEntity<ResponseMessage> findMemberByIdSimple(@PathVariable long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MemberSimpleDTO member = memberService.findMemberByIdSimple(memberId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("member", member);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "찾았다~", responseMap));
    }

    @ApiOperation(value = "멤버 id로 조회")
    @GetMapping("/members/{memberId}")
    public ResponseEntity<ResponseMessage> findMemberById(@PathVariable long memberId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MemberDTO foundMember = memberService.findMemberById(memberId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("member", foundMember);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "찾았다~", responseMap));
    }

    @ApiOperation(value = "멤버 전체 조회")
    @GetMapping("/members")
    public ResponseEntity<ResponseMessage> findAllMembers(@PageableDefault Pageable pageable) {

        System.out.println("pageable = " + pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Page<MemberDTO> memberList = memberService.findAllMembers(pageable);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("members", memberList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "찾았다~!!", responseMap));
    }

    @ApiOperation(value = "멤버 프로필 수정")
    @ApiResponses({
            @ApiResponse(code = 201, message = "수정성공..."),
            @ApiResponse(code = 400, message = "잘못된 파라미터....")
    })
    @PutMapping("/members/{memberId}")
    public ResponseEntity<?> modifyMember(MemberDTO modifyInfo, @PathVariable long memberId, @RequestParam String type) {

        System.out.println("modifyInfo = " + modifyInfo);

        System.out.println("type = " + type);

        memberService.modifyMember(modifyInfo, memberId, type);

        return ResponseEntity
                .noContent()
                .build();
    }

    @ApiOperation(value = "멤버 탈퇴 조치")
    @ApiResponses({
            @ApiResponse(code = 201, message = "수정성공..."),
            @ApiResponse(code = 400, message = "잘못된 파라미터....")
    })
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable int memberId) {

        memberService.deleteMember(memberId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @ApiOperation(value = "닉네임 중복 체크")
    @GetMapping("/members/duplicate/{nickname}")
    public ResponseEntity<ResponseMessage> checkIfRepeated(@PathVariable String nickname) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", memberService.checkIfRepeated(nickname));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "중복 검사 결과", responseMap));
    }

    @ApiOperation(value = "멤버 소셜 id로 조회")
    @GetMapping("/members/{socialLogin}/{socialId}")
    public ResponseEntity<ResponseMessage> findBySocialId(@PathVariable String socialLogin, @PathVariable String socialId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MemberDTO foundMember = memberService.findBySocialId(socialLogin, socialId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("member", foundMember);

        return ResponseEntity
            .ok()
            .headers(headers)
            .body(new ResponseMessage(200, "찾았다~", responseMap));
    }

    @ApiOperation(value = "인증된 멤버 정보 조회")
    @GetMapping("/members/auth")
    public ResponseEntity<?> getCurrentMember(HttpServletRequest request)
        throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        System.out.println(request.getHeader("Auth"));

        MemberDTO currentMember = memberService.getAuthedMember(request.getHeader("Auth"));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("member", currentMember);

        return ResponseEntity
            .ok()
            .headers(headers)
            .body(new ResponseMessage(200, "찾았다~", responseMap));
    }

}
