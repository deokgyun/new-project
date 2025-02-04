package my.adg.backend.product.domain.enums;

import lombok.Getter;

@Getter
public enum DeliveryMethod {
	PARCEL("택배, 소포 등"),
	DIRECT("직접배송, 화물 등");

	private String description;

	DeliveryMethod(String description) {
		this.description = description;
	}
}
