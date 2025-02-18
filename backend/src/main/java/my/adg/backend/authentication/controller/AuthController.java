package my.adg.backend.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.adg.backend.authentication.dto.request.LoginRequest;
import my.adg.backend.authentication.dto.request.TokenReissueRequest;
import my.adg.backend.authentication.dto.response.LoginResponse;
import my.adg.backend.authentication.service.LoginService;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;
import my.adg.backend.member.service.MemberService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController implements AuthSwaggerController {

	private final LoginService loginService;
	private final MemberService memberService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(loginService.login(request));
	}

	@PostMapping("/signup")
	public ResponseEntity<MemberFindResponse> joinMember(@RequestBody @Valid MemberJoinRequest request) {
		return ResponseEntity.ok(memberService.joinMember(request));
	}

	@PostMapping("/reissue")
	public ResponseEntity<LoginResponse> reissueToken(@Valid @RequestBody TokenReissueRequest request) {
		return ResponseEntity.ok(loginService.reissueToken(request));
	}
}
