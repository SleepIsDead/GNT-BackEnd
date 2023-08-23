package com.sleepisdead.travelmakerbackend.member.command.application.dto;

import java.time.LocalDateTime;

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

	private LocalDateTime deletedDate;

	private int level;

	private int mileage;

	private String preferredLocation;

	private String preferredType;

	public MemberDTO() {}

	public MemberDTO(long memberId, String nickname, String imageSource, int reportCount,
                     String socialLogin, String socialId, String accessToken, long accessTokenExpireDate,
                     String refreshToken, long refreshTokenExpireDate, String email, String gender,
                     String isDeleted,
                     LocalDateTime signUpDate, LocalDateTime deletedDate, int level, int mileage,
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
		this.deletedDate = deletedDate;
		this.level = level;
		this.mileage = mileage;
		this.preferredLocation = preferredLocation;
		this.preferredType = preferredType;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getSocialLogin() {
		return socialLogin;
	}

	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(LocalDateTime signUpDate) {
		this.signUpDate = signUpDate;
	}

	public LocalDateTime getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(LocalDateTime deletedDate) {
		this.deletedDate = deletedDate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getPreferredLocation() {
		return preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	public String getPreferredType() {
		return preferredType;
	}

	public void setPreferredType(String preferredType) {
		this.preferredType = preferredType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getAccessTokenExpireDate() {
		return accessTokenExpireDate;
	}

	public void setAccessTokenExpireDate(long accessTokenExpireDate) {
		this.accessTokenExpireDate = accessTokenExpireDate;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getRefreshTokenExpireDate() {
		return refreshTokenExpireDate;
	}

	public void setRefreshTokenExpireDate(long refreshTokenExpireDate) {
		this.refreshTokenExpireDate = refreshTokenExpireDate;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	@Override
	public String toString() {
		return "MemberDTO{" +
			"memberId=" + memberId +
			", nickname='" + nickname + '\'' +
			", imageSource=" + imageSource +
			", reportCount=" + reportCount +
			", socialLogin='" + socialLogin + '\'' +
			", socialId=" + socialId +
			", accessToken='" + accessToken + '\'' +
			", accessTokenExpireDate=" + accessTokenExpireDate +
			", refreshToken='" + refreshToken + '\'' +
			", refreshTokenExpireDate=" + refreshTokenExpireDate +
			", email='" + email + '\'' +
			", gender='" + gender + '\'' +
			", isDeleted='" + isDeleted + '\'' +
			", signUpDate=" + signUpDate +
			", deletedDate=" + deletedDate +
			", level=" + level +
			", mileage=" + mileage +
			", preferredLocation='" + preferredLocation + '\'' +
			", preferredType='" + preferredType + '\'' +
			'}';
	}
}
