package com.sleepisdead.travelmakerbackend.member.command.application.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberDTO {

	private long memberId;

	private String nickname;

	private String imageSource;

	private int reportCount;

	private String socialLogin;

	private String socialId;

	private String accessToken;

	private long accessTokenExpireDate;

	private String refreshToken;

	private long refreshTokenExpireDate;

	private String email;

	private String gender;

	private String isDeleted;

	private LocalDateTime signUpDate;

	private String preferredLocation;

	private String preferredType;

	@Builder
	public MemberDTO(long memberId, String nickname, String imageSource, int reportCount,
                     String socialLogin, String socialId, String accessToken, long accessTokenExpireDate,
                     String refreshToken, long refreshTokenExpireDate, String email, String gender,
                     String isDeleted, LocalDateTime signUpDate,
                     String preferredLocation, String preferredType) {
		this.memberId = memberId;
		this.nickname = nickname;
		this.imageSource = imageSource;
		this.reportCount = reportCount;
		this.socialLogin = socialLogin;
		this.socialId = socialId;
		this.accessToken = accessToken;
		this.accessTokenExpireDate = accessTokenExpireDate;
		this.refreshToken = refreshToken;
		this.refreshTokenExpireDate = refreshTokenExpireDate;
		this.email = email;
		this.gender = gender;
		this.isDeleted = isDeleted;
		this.signUpDate = signUpDate;
		this.preferredLocation = preferredLocation;
		this.preferredType = preferredType;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setSignUpDate(LocalDateTime signUpDate) {
		this.signUpDate = signUpDate;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	public void setPreferredType(String preferredType) {
		this.preferredType = preferredType;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setAccessTokenExpireDate(long accessTokenExpireDate) {
		this.accessTokenExpireDate = accessTokenExpireDate;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setRefreshTokenExpireDate(long refreshTokenExpireDate) {
		this.refreshTokenExpireDate = refreshTokenExpireDate;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}
}
