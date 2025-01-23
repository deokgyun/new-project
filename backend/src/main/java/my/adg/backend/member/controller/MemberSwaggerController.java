package my.adg.backend.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.member.dto.request.DuplicateEmail;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;

@Tag(name = "Member", description = "Member API")
public interface MemberSwaggerController {

	@Operation(summary = "회원 가입", description = "회원 가입을 합니다.")
	ResponseEntity<MemberFindResponse> joinMember(MemberJoinRequest request);

	@Operation(summary = "이메일 중복 검사", description = "이메일 중복검사를 합니다.")
	ResponseEntity<Void> duplicateEmail(DuplicateEmail request);



}
