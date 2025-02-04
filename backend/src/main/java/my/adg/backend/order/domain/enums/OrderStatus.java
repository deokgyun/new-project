package my.adg.backend.order.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

	PAYMENT("결제 완료"),
	RECEIVED("주문 접수"),
	CONFIRM("주문 확인"),
	READY("배송 준비중"),
	SHIPPING("배송중"),
	COMPLETED("배송 완료"),
	CANCEL("주문 취소"),
	;

	private String description;

	OrderStatus(String description) {
		this.description = description;
	}

}
