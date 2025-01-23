package my.adg.backend.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.adg.backend.auth.dto.request.LoginRequest;
import my.adg.backend.auth.dto.response.LoginResponse;
import my.adg.backend.auth.service.LoginService;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;
import my.adg.backend.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController implements AuthSwaggerController {

    private final LoginService loginService;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberFindResponse> joinMember(@RequestBody @Valid MemberJoinRequest request) {
        return ResponseEntity.ok(memberService.joinMember(request));
    }
}
