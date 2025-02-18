package my.adg.backend.member.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.member.dto.request.ProfileUpdateRequest;
import my.adg.backend.member.dto.request.UpdatePasswordRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;

@Tag(name = "Member")
public interface MyPageSwaggerController {

	@Operation(summary = "나의 프로필 정보 조회")
	public ResponseEntity<MemberFindResponse> getMemberById(LoginMember loginMember);

	@Operation(summary = "나의 프로필 정보 수정")
	public ResponseEntity<Void> updateProfile(LoginMember loginMember, ProfileUpdateRequest request);

	@Operation(summary = "비밀번호 변경")
	public ResponseEntity<Void> updatePassword(LoginMember loginMember, UpdatePasswordRequest request);

}
