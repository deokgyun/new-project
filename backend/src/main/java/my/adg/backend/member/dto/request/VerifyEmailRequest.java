package my.adg.backend.member.dto.request;

public record VerifyEmailRequest(
	String email,
	String secured_code
) {
}
