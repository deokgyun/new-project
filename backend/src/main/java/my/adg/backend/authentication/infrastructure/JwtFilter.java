package my.adg.backend.authentication.infrastructure;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final RedisUtil redisUtil;

	private static final List<String> WHITELIST_URLS = Arrays.asList(
		"/api/reissue", "/login"
	);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		// 1. 토큰 추출 및 Bearer 자르기
		String jwtToken = parseJwt(request);

		// 2. 등록 된 화이트 리스트는 토큰이 없어도 되는 부분
		if (WHITELIST_URLS.contains(request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;     // 종료
		}

		// 3. 토큰 값이 있을 때
		if (jwtToken != null) {

			// 3-1. access 토큰이 blacklist 등록 된 토큰인지 검증
			boolean isBlacklist = validateToken(jwtToken);
			if (isBlacklist) {
				throw new BalanceTalkException(ErrorCode.BLACKLIST_JWT_TOKEN);
			}

			// 4. blacklist 아닐 때 사용자 정보 저장
			Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

	private boolean validateToken(String token) {
		return redisUtil.isBlacklist(token);
	}

}
