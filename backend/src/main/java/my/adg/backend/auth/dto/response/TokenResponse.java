package my.adg.backend.auth.dto.response;

import jakarta.servlet.http.Cookie;

public record TokenResponse(
	String accessToken,
	String refreshToken
) {

	public Cookie createToken(String tokenName, String token) {
		Cookie cookie = new Cookie(tokenName, token);

		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setAttribute("SameSite", "None"); // 이 속성 추가

		return cookie;

	}
}
