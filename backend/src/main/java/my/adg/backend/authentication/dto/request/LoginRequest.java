package my.adg.backend.authentication.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@NotBlank(message = "이메일은 비어있을 수 없습니다.")
	@Email
	String email,

	@NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
	String password
) {
}
