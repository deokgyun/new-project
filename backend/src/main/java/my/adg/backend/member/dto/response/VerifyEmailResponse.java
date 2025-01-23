package my.adg.backend.member.dto.response;

public record VerifyEmailResponse(
	String message,
	String securedCode
) {
}
