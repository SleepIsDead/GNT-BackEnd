package com.sleepisdead.travelmakerbackend.jwt;


import com.sleepisdead.travelmakerbackend.exception.TokenException;
import com.sleepisdead.travelmakerbackend.login.dto.AccessTokenDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.sleepisdead.travelmakerbackend.member.command.application.dto.MemberDTO;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	private static final String AUTHORITIES_KEY = "auth";
	private final String secret;
	private static final String BEARER_TYPE = "bearer";
	private final long tokenValidityInMilliseconds; // 엑세스토큰
	private final long refreshTokenValidityInMilliseconds; //리프레시 토큰
	private Key key;

	//yml 에서 secret 참조
	public TokenProvider(@Value("${jwt.secret}") String secret,
						 @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds){
		this.secret = secret;
		this.tokenValidityInMilliseconds= tokenValidityInSeconds * 1000;
		this.refreshTokenValidityInMilliseconds = tokenValidityInSeconds*10000;
	}

	@Override
	public void afterPropertiesSet(){
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key= Keys.hmacShaKeyFor(keyBytes);
	}

	public AccessTokenDTO generateMemberTokenDTO(MemberDTO foundmember) {
		logger.info("[TokenProvider] generateTokenDto Start ===================================");

		Claims claims = Jwts
				.claims()
				.setSubject(String.valueOf(foundmember.getMemberId()));
		long now = (new Date()).getTime();

		// Access Token 생성
		Date accessTokenExpiresIn = new Date(now + tokenValidityInMilliseconds);
		String jwtToken = Jwts.builder()
				.setClaims(claims)
				.setExpiration(accessTokenExpiresIn)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();

		return new AccessTokenDTO(BEARER_TYPE, foundmember.getMemberId(), jwtToken,
				accessTokenExpiresIn.getTime());
	}


	//토큰생성
	public String createAccessToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		// 토큰의 expire 시간을 설정
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.tokenValidityInMilliseconds);

		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities) // 정보 저장
				.signWith(key, SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘과 , signature 에 들어갈 secret값 세팅
				.setExpiration(validity) // set Expire Time 해당 옵션 안넣으면 expire안함
				.compact();
	}


	public String createRefreshToken(){
		Date now = new Date();
		Date validity = new Date(now.getTime() + this.refreshTokenValidityInMilliseconds);

		return Jwts.builder()
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(key)
				.compact();
	}

	//리프레시 토큰을 이용한 엑세스 토큰 재발급
	public String renewAccessTokenUsingRefreshToken(String refreshToken) {
		// 리프레시 토큰의 유효성 검사
		if (!validateToken(refreshToken)) {
			throw new RuntimeException("로그인 정보가 만료되었습니다.");
		}

		// 리프레시 토큰에서 Authentication 객체를 가져옵니다.
		Authentication authentication = getAuthentication(refreshToken);

		// 새로운 액세스 토큰을 생성하고 반환합니다.
		return createAccessToken(authentication);
	}

	// 토큰으로 클레임을 만들고 이를 이용해 유저 객체를 만들어서 최종적으로 authentication 객체를 리턴
	public Authentication getAuthentication(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();

		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	private String getUserId(String accessToken) {
		return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(accessToken)
				.getBody()
				.getSubject();
	}

	// 토큰의 유효성 검증을 수행
	public boolean validateToken(String token) throws ExpiredJwtException {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			logger.info("[TokenProvider] Malformed JWT Sign");
			throw new TokenException("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			logger.info("[TokenProvider] Expired JWT Token");
			throw new TokenException("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			logger.info("[TokenProvider] Unsupported JWT token");
			throw new TokenException("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			logger.info("[TokenProvider] JWT Token Illegal");
			throw new TokenException("JWT 토큰이 잘못되었습니다.");
		}
	}


	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
