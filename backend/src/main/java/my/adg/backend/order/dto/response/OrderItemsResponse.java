package my.adg.backend.order.dto.response;

import java.util.ArrayList;
import java.util.List;

import my.adg.backend.address.domain.Address;
import my.adg.backend.order.domain.Order;

public record OrderItemsResponse(
	List<OrderItemResponse> orderItems,
	Address buyerAddress

) {

	// TODO : Address 받아서 처리 해야함.
	public OrderItemsResponse(Order order) {
		this(
			new ArrayList<>(),
			new Address()
		);
	}

	public void addOrderItem(OrderItemResponse orderItemResponse) {
		orderItems.add(orderItemResponse);
	}
}
