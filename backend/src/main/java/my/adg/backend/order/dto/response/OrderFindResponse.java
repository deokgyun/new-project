package my.adg.backend.order.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import my.adg.backend.order.domain.Order;
import my.adg.backend.order.domain.enums.OrderStatus;

public record OrderFindResponse(
	OrderStatus orderStatus,
	LocalDateTime orderDate,
	List<OrderItemResponse> orderProducts,
	Long orderId
) {
	public OrderFindResponse(Order order) {
		this(
			order.getStatus(),
			order.getCreatedDate(),
			new ArrayList<>(),
			order.getId()
		);
	}

	public void addOrderProduct(OrderItemResponse orderItemResponse) {
		this.orderProducts.add(orderItemResponse);
	}
}
