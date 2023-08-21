package travelmakerbackend.member.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import travelmakerbackend.login.command.application.dto.RenewTokenDTO;
import travelmakerbackend.member.command.application.dto.MemberDTO;
import travelmakerbackend.member.command.application.dto.MemberSimpleDTO;
import travelmakerbackend.member.command.domain.aggregate.entity.Member;
import travelmakerbackend.member.command.domain.repository.MemberRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	private final ObjectMapper objectMapper;

	@Autowired
	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper,
                         ObjectMapper objectMapper) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.objectMapper = objectMapper;
	}

	@Transactional
	public long registNewUser(MemberDTO newMember) {

		newMember.setNickname("새로운회원" + (Math.random() * 100 + 1));
		newMember.setLevel(1);
		newMember.setIsDeleted("N");

		return memberRepository.save(modelMapper.map(newMember, Member.class)).getMemberId();
	}

	public MemberDTO findMemberById(long memberId) {

		Member member = memberRepository.findById(memberId).get();

		return modelMapper.map(member, MemberDTO.class);
	}

	public MemberSimpleDTO findMemberByIdSimple(long memberId) {

		MemberSimpleDTO member = memberRepository.findMemberByIdSimple(memberId);

		member.setLinkToMyPage("/mypage/" + member.getMemberId());  //마이페이지 링크 기억안나서 아직 예시

		return member;
	}

	public Page<MemberDTO> findAllMembers(Pageable pageable) {

		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
			pageable.getPageSize(),
			Sort.by("memberId"));

		return memberRepository.findAll(pageable)
			.map(member -> modelMapper.map(member, MemberDTO.class));
	}

	@Transactional
	public void modifyMember(MemberDTO modifyInfo, long memberId, String type) {

		Member foundMember = memberRepository.findById(memberId).get();

		switch (type) {
			case "edit":
				if (modifyInfo.getNickname().length() > 0) {
					foundMember.setNickname(modifyInfo.getNickname());
				}
				if (modifyInfo.getPreferredLocation().length() > 0) {
					foundMember.setPreferredLocation(modifyInfo.getPreferredLocation());
				}
				if (modifyInfo.getPreferredType().length() > 0) {
					foundMember.setPreferredType(modifyInfo.getPreferredType());
				}
				break;

			case "deactivate":

				RestTemplate rt = new RestTemplate();

				/* 카카오 로그인일 때 */
				if (foundMember.getSocialLogin().equals("KAKAO")) {

					HttpHeaders headers = new HttpHeaders();
					headers.add("Authorization", "Bearer " + foundMember.getAccessToken());

					HttpEntity<MultiValueMap<String, String>> kakaoDeactivateRequest =
						new HttpEntity<>(headers);

					ResponseEntity<String> kakaoDeactivateResponse = rt.exchange(
						"https://kapi.kakao.com/v1/user/unlink",
						HttpMethod.POST,
						kakaoDeactivateRequest,
						String.class
					);

					String kakaoDeactivateResult = "";

					try {
						kakaoDeactivateResult = objectMapper.readValue(
							kakaoDeactivateResponse.getBody(),
							String.class);
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}

					foundMember.setIsDeleted("Y");
					break;

					/* 네이버 로그인일 때 */
				} else if (foundMember.getSocialLogin().equals("NAVER")) {

					/* 갱신 먼저 */
					Date expireDate = new Date(foundMember.getAccessTokenExpireDate());

					if (expireDate.before(new Date())) {

						RestTemplate rtForRenew = new RestTemplate();

						HttpHeaders headers = new HttpHeaders();

						MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
						params.add("client_id", System.getenv("NaverClientIdKey"));
						params.add("client_secret", System.getenv("NaverClientSecretKey"));
						params.add("refresh_token", foundMember.getRefreshToken());
						params.add("grant_type", "refresh_token");

						HttpEntity<MultiValueMap<String, String>> naverRenewRequest =
							new HttpEntity<>(params, headers);

						ResponseEntity<String> naverRenewResponses = rtForRenew.exchange(
							"https://nid.naver.com/oauth2.0/token",
							HttpMethod.GET,
							naverRenewRequest,
							String.class
						);

						ObjectMapper objectMapper = new ObjectMapper();
						RenewTokenDTO renewToken = null;
						try {
							renewToken = objectMapper.readValue(naverRenewResponses.getBody(), RenewTokenDTO.class);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

						if (renewToken.getRefresh_token() != null) {

							foundMember.setRefreshToken(renewToken.getRefresh_token());
							foundMember.setRefreshTokenExpireDate(
								(1000 * 60 * 60 * 6) + System.currentTimeMillis());
						}

						foundMember.setAccessToken(renewToken.getAccess_token());
						foundMember.setAccessTokenExpireDate(
							renewToken.getExpires_in() + System.currentTimeMillis());

					}

					/* 업데이트 된 멤버 다시 가져옴 */
					foundMember = memberRepository.findById(memberId).get();

					/* 탈퇴 요청 */
					HttpHeaders headers = new HttpHeaders();

					MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
					params.add("client_id", System.getenv("NaverClientIDKey"));
					params.add("client_secret", System.getenv("NaverClientSecretKey"));
					params.add("access_token", foundMember.getAccessToken());
					params.add("grant_type", "delete");

					HttpEntity<MultiValueMap<String, String>> naverDeactivateRequest =
						new HttpEntity<>(headers, params);

					ResponseEntity<String> naverDeactivateResponse = rt.exchange(
						"https://nid.naver.com/oauth2.0/token",
						HttpMethod.POST,
						naverDeactivateRequest,
						String.class
					);

					System.out.println(naverDeactivateResponse.getBody());

					String naverDeactivateResult = "";

					try {
						naverDeactivateResult = objectMapper.readValue(
							naverDeactivateResponse.getBody(),
							String.class);
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}

					foundMember.setIsDeleted("Y");
					break;
				}
		}
	}

	@Transactional
	public void deleteMember(long memberId) {

		Member foundMember = memberRepository.findById(memberId).get();

		memberRepository.delete(foundMember);
	}

	public boolean checkIfRepeated(String nickname) {

		List<Member> foundMember = memberRepository.findByNickname(nickname);

		if (foundMember.size() < 1) {
			return false;
		} else {
			return true;
		}
	}

	public MemberDTO findBySocialId(String socialLogin, String socialId) {

		Member foundMember = memberRepository.findBySocialId(socialLogin, socialId);

		if (foundMember == null) {
			return null;
		} else {
			return modelMapper.map(foundMember, MemberDTO.class);
		}
	}

	public MemberDTO getAuthedMember(String header) throws JsonProcessingException {

		Map<String, String> headerMap = objectMapper.readValue(header, Map.class);

		String id = String.valueOf(headerMap.get("memberId"));

		Long memberId = Long.parseLong(id);

		System.out.println("memberId = " + memberId);
		System.out.println(memberId.getClass().getName());

		Member authedMember = memberRepository.findById(memberId).get();

		System.out.println("authedMember = " + authedMember);

		return modelMapper.map(authedMember, MemberDTO.class);
	}
}
