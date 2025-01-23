package my.adg.backend.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProfileUpdateRequest(
	@NotBlank
	String zipcode,
	@NotBlank
	String address1,
	@NotBlank
	String address2
) {

}
