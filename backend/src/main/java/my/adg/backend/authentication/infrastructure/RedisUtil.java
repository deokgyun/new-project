package my.adg.backend.authentication.infrastructure;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisUtil {

	private final RedisTemplate<String, Object> redisTemplate;
	private final String REDIS_BLACK_LIST_KEY = "blacklist:";
	private final String REDIS_REFRESH_TOKEN_KEY = "refresh_token:";

	public void set(String key, Object value, long expiration) {
		redisTemplate.opsForValue().set(REDIS_REFRESH_TOKEN_KEY + key, value, expiration, TimeUnit.MILLISECONDS);
	}

	public void setBlacklist(String key, String value, long expiration) {
		if (!isBlacklist(value)) {
			redisTemplate.opsForValue().set(REDIS_BLACK_LIST_KEY + key, value, expiration, TimeUnit.MILLISECONDS);
		}
	}

	public boolean isBlacklist(String token) {
		Object getBlacklist = redisTemplate.opsForValue().get(REDIS_BLACK_LIST_KEY + token);
		return getBlacklist != null;
	}

	public void removeRefreshToken(String key) {
		redisTemplate.delete(REDIS_REFRESH_TOKEN_KEY + key);
	}

	public String get(String key) {
		return redisTemplate.opsForValue().get(REDIS_REFRESH_TOKEN_KEY + key).toString();
	}

}
