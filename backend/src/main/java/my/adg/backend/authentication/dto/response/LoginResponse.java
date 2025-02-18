package my.adg.backend.authentication.dto.response;

import lombok.Builder;
import my.adg.backend.member.domain.Member;

@Builder
public record LoginResponse(
	Long memberId,
	String accessToken,
	String refreshToken
) {
	public static LoginResponse of(Member member, TokenResponse token) {
		return LoginResponse.builder()
			.memberId(member.getId())
			.accessToken(token.accessToken())
			.refreshToken(token.refreshToken())
			.build();
	}
}
