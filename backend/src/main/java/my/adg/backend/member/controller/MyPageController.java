package my.adg.backend.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.adg.backend.authentication.resolver.AuthMember;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.member.dto.request.ProfileUpdateRequest;
import my.adg.backend.member.dto.request.UpdatePasswordRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;
import my.adg.backend.member.service.MyPageFacadeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MyPageController implements MyPageSwaggerController {

	private final MyPageFacadeService memberService;

	// TODO : 어노테이션 처리 예정
	@GetMapping("/me")
	public ResponseEntity<MemberFindResponse> getMemberById(@AuthMember LoginMember loginMember) {
		MemberFindResponse data = memberService.getMember(loginMember.id());
		return ResponseEntity.ok(data);
	}

	@PutMapping("/me")
	public ResponseEntity<Void> updateProfile(
		@AuthMember LoginMember loginMember, @RequestBody @Valid ProfileUpdateRequest request) {

		memberService.updateProfile(loginMember, request);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/me/password")
	public ResponseEntity<Void> updatePassword(@AuthMember LoginMember loginMember,
		@RequestBody @Valid UpdatePasswordRequest request) {
		memberService.updatePassword(loginMember, request);
		return ResponseEntity.ok().build();
	}
}
