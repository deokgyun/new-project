package my.adg.backend.order.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.auth.resolver.AuthMember;
import my.adg.backend.auth.resolver.LoginMember;
import my.adg.backend.global.dto.request.PageDataRequest;
import my.adg.backend.order.dto.request.OrderRequest;
import my.adg.backend.order.dto.response.OrderFindResponse;
import my.adg.backend.order.dto.response.OrderItemsResponse;
import my.adg.backend.order.service.OrderService;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController implements OrderSwaggerController {
	private final OrderService orderService;

	private static final String SORT_ORDER_DATE = "createdDate";
	private static final int PAGE_SIZE = 10;

	@PostMapping
	public ResponseEntity<Void> order(@AuthMember LoginMember loginMember, @RequestBody OrderRequest request) {
		orderService.order(loginMember, request);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<PagedModel<OrderFindResponse>> getAllOrders(
		@RequestBody(required = false) Optional<PageDataRequest> request,
		@AuthMember LoginMember loginMember) {

		int restPage = request
			.map(PageDataRequest::page)
			.map(page -> page.orElse(1) - 1)
			.orElse(0);

		Pageable pageable = PageRequest.of(restPage, PAGE_SIZE, Sort.by(Sort.Direction.DESC, SORT_ORDER_DATE));
		Page<OrderFindResponse> orders = orderService.getAllOrders(loginMember, pageable);

		return ResponseEntity.ok(new PagedModel<>(orders));
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderItemsResponse> getOrderDetail(@PathVariable("orderId") Long orderId,
		@AuthMember LoginMember loginMember) {
		return ResponseEntity.ok(orderService.getOrderDetail(orderId, loginMember));
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> cancelOrder(@PathVariable("orderId") Long orderId,
		@AuthMember LoginMember loginMember) {
		orderService.cancelOrder(orderId, loginMember);
		return ResponseEntity.ok().build();
	}

}
