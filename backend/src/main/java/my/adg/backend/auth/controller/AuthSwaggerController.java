package my.adg.backend.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.auth.dto.request.LoginRequest;
import my.adg.backend.auth.dto.request.TokenReissueRequest;
import my.adg.backend.auth.dto.response.LoginResponse;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;

import org.springframework.http.ResponseEntity;

@Tag(name = "Login", description = "Login API")
public interface AuthSwaggerController {

    @Operation(summary = "자체 로그인")
    ResponseEntity<LoginResponse> login(LoginRequest request);

    @Operation(summary = "회원가입")
    ResponseEntity<MemberFindResponse> joinMember(MemberJoinRequest request);

    @Operation(summary = "토큰 재발급")
    ResponseEntity<LoginResponse> reissueToken(TokenReissueRequest request);

}
