package my.adg.backend.cart.domain.enums;

import lombok.Getter;

@Getter
public enum Sample {
	PARCEL("택배, 소포 등"),
	DIRECT("직접배송, 화물 등");

	private String description;

	Sample(String description) {
		this.description = description;
	}
}
