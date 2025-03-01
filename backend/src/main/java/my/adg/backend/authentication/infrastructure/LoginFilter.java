package my.adg.backend.authentication.infrastructure;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.authentication.dto.request.LoginRequest;
import my.adg.backend.authentication.dto.response.TokenResponse;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final RedisUtil redisUtil;

	private final int REFRESH_TOKEN_EXPIRE = 1_209_600_000;
	private final String REFRESH_TOKEN_PREFIX = "refresh_token_";

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response) throws AuthenticationException {

		LoginRequest loginRequest;

		try {
			ServletInputStream inputStream = request.getInputStream();
			String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
			loginRequest = objectMapper.readValue(messageBody, LoginRequest.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String username = loginRequest.email();
		String password = loginRequest.password();

		obtainPassword(request);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			username, password, null);

		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
		HttpServletResponse response, FilterChain chain, Authentication authResult)
		throws IOException, ServletException {

		CustomUserDetails customUserDetails = (CustomUserDetails)authResult.getPrincipal();

		Long memberId = customUserDetails.getId();

		TokenResponse token = jwtTokenProvider.createToken(memberId);

		//TODO : 추후 방식이 달라질 수 있음. (예: 쿠키, 헤더 등등 다른 방식으로...)
		Map<String, String> json = new HashMap<>();
		json.put("refresh_token", token.refreshToken());
		json.put("access_token", token.accessToken());
		String jsonResponse = objectMapper.writeValueAsString(ResponseEntity.status(200).body(json));

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse);
		response.addHeader("Authorization", "Bearer " + token.accessToken());

		redisUtil.set(String.valueOf(memberId), token.refreshToken(), REFRESH_TOKEN_EXPIRE);

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
		HttpServletResponse response, AuthenticationException failed)
		throws IOException, ServletException {

		response.setStatus(401);
	}
}
