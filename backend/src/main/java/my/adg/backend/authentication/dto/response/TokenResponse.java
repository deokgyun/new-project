package my.adg.backend.authentication.dto.response;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "token")
public record TokenResponse(
	@Id
	String accessToken,
	String refreshToken
) {
}
