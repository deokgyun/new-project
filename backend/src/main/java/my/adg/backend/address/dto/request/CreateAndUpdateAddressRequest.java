package my.adg.backend.address.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import my.adg.backend.address.domain.Address;
import my.adg.backend.member.domain.Member;

public record CreateAndUpdateAddressRequest(

	@NotBlank
	String addressName,
	@NotBlank
	String receiverName,
	@NotBlank
	String zipcode,
	@NotBlank
	String address1,
	@NotBlank
	String address2,
	@NotNull
	boolean isDefault,
	@NotBlank
	String phoneNumber
) {
	public Address toEntity(Member member, CreateAndUpdateAddressRequest request) {
		return Address.builder()
			.addressName(request.addressName())
			.receiverName(request.receiverName())
			.zipcode(request.zipcode())
			.address1(request.address1())
			.address2(request.address2())
			.isDefault(request.isDefault())
			.phoneNumber(request.phoneNumber())
			.member(member)
			.build();
	}
}
