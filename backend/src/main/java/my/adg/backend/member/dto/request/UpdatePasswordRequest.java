package my.adg.backend.member.dto.request;

import jakarta.validation.constraints.Pattern;
import my.adg.backend.global.pattern.Patterns;

public record UpdatePasswordRequest(
	@Pattern(regexp = Patterns.PASSWORD_REGEXP,
		message = Patterns.PASSWORD_MESSAGE)
	String currentPassword,

	@Pattern(regexp = Patterns.PASSWORD_REGEXP,
		message = Patterns.PASSWORD_MESSAGE)
	String newPassword,

	@Pattern(regexp = Patterns.PASSWORD_REGEXP,
		message = Patterns.PASSWORD_MESSAGE)
	String confirmPassword
) {
}
