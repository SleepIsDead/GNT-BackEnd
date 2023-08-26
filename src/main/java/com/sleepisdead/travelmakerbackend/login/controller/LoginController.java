package com.sleepisdead.travelmakerbackend.login.controller;


import com.sleepisdead.travelmakerbackend.common.ResponseMessage;
import com.sleepisdead.travelmakerbackend.login.dto.AccessTokenDTO;
import com.sleepisdead.travelmakerbackend.login.dto.OauthTokenDTO;
import com.sleepisdead.travelmakerbackend.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "Login API")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PreAuthorize("permitAll()")
	@ApiOperation(value = "카카오 인가 코드 받아와서 액세스 토큰 발급")
	@PostMapping("/kakaocode")
	public ResponseEntity<?> getKakaoCode(@RequestBody Map<String, String> code) {

		/* 인가 코드로 액세스 토큰 발급 */
		OauthTokenDTO oauthToken = loginService.getAccessToken(code.get("code"));

		System.out.println(oauthToken.getAccess_token());

		/* 액세스 토큰으로 DB 저장or 확인 후 JWT 생성 */
		AccessTokenDTO jwtToken = loginService.getJwtToken(oauthToken);

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("token", jwtToken);

		/* JWT와 응답 결과를 프론트에 전달*/
		return ResponseEntity
				.ok()
				.body(new ResponseMessage(200, "로그인 성공", responseMap));
	}

	@ApiOperation(value = "jwt 액세스 토큰 만료되어 재발급")
	@PostMapping("/renew")
	public ResponseEntity<?> renewAccessToken(@RequestHeader(value = "Auth") String auth) {

		System.out.println("auth = " + auth);
		return null;
	}

}
