package my.adg.backend.address.dto.response;

import my.adg.backend.address.domain.Address;

public record AddressResponse(
	Long id,
	String addressName,
	String receiverName,
	String zipcode,
	String address1,
	String address2,
	boolean isDefault,
	String phoneNumber
) {
	public AddressResponse(Address delivery) {
		this(
			delivery.getId(),
			delivery.getAddressName(),
			delivery.getReceiverName(),
			delivery.getZipcode(),
			delivery.getAddress1(),
			delivery.getAddress2(),
			delivery.isDefault(),
			delivery.getPhoneNumber()
		);
	}
}
