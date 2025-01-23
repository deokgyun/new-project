package my.adg.backend.member.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import my.adg.backend.global.pattern.Patterns;
import my.adg.backend.member.domain.EmbeddedAddress;
import my.adg.backend.member.domain.Member;

@Schema(description = "회원가입 정보")
public record MemberJoinRequest(
	@Email
	@NotBlank
	@Schema(example = "string@test.com")
	String email,

	@NotBlank
	@Pattern(regexp = Patterns.PASSWORD_REGEXP,
		message = Patterns.PASSWORD_MESSAGE)
	String password,

	@NotBlank
	String name,

	@NotBlank
	String phoneNumber,

	@NotNull
	@Past
	LocalDate birthday,

	@NotBlank
	String zipcode,

	@NotBlank
	String address1,

	@NotBlank
	String address2
) {

	public Member toEntity(MemberJoinRequest request, String generatePassword) {
		return Member.builder()
			.email(request.email())
			.password(generatePassword)
			.passwordUpdatedDate(LocalDateTime.now())
			.name(request.name())
			.phoneNumber(request.phoneNumber())
			.birthday(request.birthday())
			.address(new EmbeddedAddress(request.zipcode(), request.address1(), request.address2()))
			.build();
	}
}
