package my.adg.backend.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.auth.dto.request.LoginRequest;
import my.adg.backend.auth.dto.response.LoginResponse;
import my.adg.backend.auth.infrastructure.JwtTokenProvider;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	public LoginResponse login(LoginRequest loginRequest) {

		log.info("Login request: {}", loginRequest);

		Member member = memberRepository.findByEmail(loginRequest.email())
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.MISMATCHED_EMAIL_OR_PASSWORD));

		boolean matches = passwordEncoder.matches(loginRequest.password(), member.getPassword());

		if (!matches) {
			throw new BalanceTalkException(ErrorCode.PASSWORD_MISMATCH);
		}
		return LoginResponse.of(member, jwtTokenProvider.createToken(member.getId()));
	}
}
