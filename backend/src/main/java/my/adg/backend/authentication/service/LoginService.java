package my.adg.backend.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.authentication.dto.request.LoginRequest;
import my.adg.backend.authentication.dto.request.TokenReissueRequest;
import my.adg.backend.authentication.dto.response.LoginResponse;
import my.adg.backend.authentication.dto.response.TokenResponse;
import my.adg.backend.authentication.infrastructure.JwtTokenProvider;
import my.adg.backend.authentication.infrastructure.RedisUtil;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
	private final MemberRepository memberRepository;
	private final JwtTokenProvider tokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final RedisUtil redisUtil;
	private final JwtTokenProvider jwtTokenProvider;

	public LoginResponse login(LoginRequest loginRequest) {

		Member member = memberRepository.findByEmail(loginRequest.email())
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.MISMATCHED_EMAIL_OR_PASSWORD));

		boolean matches = passwordEncoder.matches(loginRequest.password(), member.getPassword());

		if (!matches) {
			throw new BalanceTalkException(ErrorCode.PASSWORD_MISMATCH);
		}
		return LoginResponse.of(member, tokenProvider.createToken(member.getId()));
	}

	public LoginResponse reissueToken(TokenReissueRequest request) {
		String memberId = tokenProvider.decodeRefreshToken(request.refreshToken());

		Member member = memberRepository.findById(Long.valueOf(memberId))
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_MEMBER));

		String redisToken = redisUtil.get(memberId);

		String requestToken = request.refreshToken();

		if(!redisToken.equals(requestToken)) {
			throw new BalanceTalkException(ErrorCode.MISMATCHED_REFRESH_TOKEN);
		}

		TokenResponse token = tokenProvider.createToken(member.getId());
		Long expirationRefreshToken = tokenProvider.getExpirationRefreshToken(token.refreshToken());
		long currentTime = System.currentTimeMillis();
		Long calTime = expirationRefreshToken - currentTime;

		redisUtil.set(memberId, token.refreshToken(), calTime);

		return LoginResponse.of(member, token);
	}
}
