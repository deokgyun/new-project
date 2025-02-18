package my.adg.backend.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.member.dto.request.ProfileUpdateRequest;
import my.adg.backend.member.dto.request.UpdatePasswordRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;

@RequiredArgsConstructor
@Service
public class MyPageFacadeService {

	private final MemberService memberService;

	public void updateProfile(LoginMember loginMember, ProfileUpdateRequest request) {
		memberService.updateProfile(loginMember, request);
	}

	public MemberFindResponse getMember(Long id) {
		return memberService.getMember(id);
	}

	public void updatePassword(LoginMember loginMember, UpdatePasswordRequest request) {
		memberService.updatePassword(loginMember, request);
	}
}
