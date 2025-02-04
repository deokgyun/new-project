package my.adg.backend.order.dto.response;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import my.adg.backend.order.domain.Order;
import my.adg.backend.order.domain.OrderProduct;
import my.adg.backend.order.domain.enums.OrderStatus;

public record OrderItemResponse(

	Long productId,
	String productName,
	OrderStatus orderStatus,
	String orderDate,
	String completedDate,

	int price,
	int quantity
) {

	public OrderItemResponse(OrderProduct orderProduct) {
		this(
			orderProduct.getId(),
			orderProduct.getProduct().getName(),
			orderProduct.getOrder().getStatus(),
			orderProduct.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
			Optional.ofNullable(orderProduct.getOrder())
				.map(Order::getCompletedDate)
				.map(date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
				.orElse(null),
			orderProduct.getPrice(),
			orderProduct.getQuantity()
		);
	}
}
