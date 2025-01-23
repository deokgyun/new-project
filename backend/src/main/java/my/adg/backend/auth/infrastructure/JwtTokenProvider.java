package my.adg.backend.auth.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import my.adg.backend.auth.dto.response.TokenResponse;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;

@Component
public class JwtTokenProvider {

	private final CustomUserDetailsService userDetailsService;

	private static final String MEMBER_ID_KEY = "id";

	private final String accessSecretKey;
	private final String refreshSecretKey;
	private final long accessExpiration;
	private final long refreshExpiration;

	public JwtTokenProvider(
		CustomUserDetailsService userDetailsService, @Value("${security.jwt.token.secret-key}") String accessSecretKey,
		@Value("${security.jwt.refresh.secret-key}") String refreshSecretKey,
		@Value("${security.jwt.token.expire-length}") long accessExpiration,
		@Value("${security.jwt.refresh.expire-length}") long refreshExpiration) {
		this.userDetailsService = userDetailsService;
		this.accessSecretKey = accessSecretKey;
		this.refreshSecretKey = refreshSecretKey;
		this.accessExpiration = accessExpiration;
		this.refreshExpiration = refreshExpiration;
	}

	public TokenResponse createToken(Long memberId) {
		String accessToken = createToken(memberId, accessSecretKey, accessExpiration);
		String refreshToken = createToken(memberId, refreshSecretKey, refreshExpiration);
		return new TokenResponse(accessToken, refreshToken);

	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(decodeAccessToken(token));
		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
			userDetails.getAuthorities());
	}

	private String createToken(Long memberId, String secretKey, long expiration) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + expiration);

		return Jwts.builder()
			.setSubject(memberId.toString())
			.claim(MEMBER_ID_KEY, memberId)
			.setExpiration(validity)
			.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
			.compact();
	}

	public String decodeAccessToken(String token) {
		return decode(token, accessSecretKey);
	}

	public String decodeRefreshToken(String token) {
		return decode(token, refreshSecretKey);
	}

	private String decode(String token, String secretKey) {
		try {
			return Jwts.parser()
				.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get(MEMBER_ID_KEY)
				.toString();
		} catch (ExpiredJwtException exception) {
			throw new BalanceTalkException(ErrorCode.EXPIRED_JWT_TOKEN);
		} catch (Exception exception) {
			throw new BalanceTalkException(ErrorCode.INVALID_JWT_TOKEN);
		}
	}

}
