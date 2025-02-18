package my.adg.backend.authentication.infrastructure;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

	private final JwtTokenProvider tokenProvider;
	private final RedisUtil redisUtil;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		// 1.헤더에서 Access 토큰 확인 및 Bearer 제거
		String token = request.getHeader("Authorization");
		token = token.substring(7, token.length());

		// 2. 토큰에서 회원의 PK id 및 토큰 유효기간 추출
		String key = tokenProvider.decodeAccessToken(token);
		long expiration = tokenProvider.getExpirationAccessToken(token);
		long currentTime = System.currentTimeMillis();
		long calTime = expiration - currentTime;

		// 3. 추출한 토큰을 redis 에 blacklist 저장 및 refresh token 삭제
		redisUtil.setBlacklist(token, key, calTime);
		redisUtil.removeRefreshToken(key);
	}

}
