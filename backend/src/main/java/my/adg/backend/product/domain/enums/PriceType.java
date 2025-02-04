package my.adg.backend.product.domain.enums;

import lombok.Getter;

@Getter
public enum PriceType {

	FREE("무료배송"),
	PAID("유료배송"),
	ETC("기타");

	private String description;

	PriceType(String description) {
		this.description = description;
	}
}
