package my.adg.backend.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.member.dto.request.DuplicateEmail;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.dto.request.VerifyEmailRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;
import my.adg.backend.member.dto.response.VerifyEmailResponse;
import my.adg.backend.member.service.MemberService;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController implements MemberSwaggerController {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<MemberFindResponse> joinMember(@RequestBody @Valid MemberJoinRequest request) {
		return ResponseEntity.ok(memberService.joinMember(request));
	}

	@PostMapping("/email")
	public ResponseEntity<Void> duplicateEmail(@RequestBody DuplicateEmail request) {
		memberService.duplicateEmail(request.email());
		return ResponseEntity.ok().build();
	}


}
