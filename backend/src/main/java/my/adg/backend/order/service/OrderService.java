package my.adg.backend.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.member.service.MemberService;
import my.adg.backend.order.domain.Order;
import my.adg.backend.order.domain.OrderProduct;
import my.adg.backend.order.dto.request.OrderRequest;
import my.adg.backend.order.dto.response.OrderFindResponse;
import my.adg.backend.order.dto.response.OrderItemResponse;
import my.adg.backend.order.dto.response.OrderItemsResponse;
import my.adg.backend.order.repository.OrderRepository;
import my.adg.backend.product.domain.Product;
import my.adg.backend.product.repository.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final MemberService memberService;

	@Transactional
	public void order(LoginMember loginMember, OrderRequest request) {

		Member member = memberService.getMemberById(loginMember.id());

		// 주문하려는 상품 찾기
		Product product = productRepository.findById(request.productId())
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_PRODUCT));

		// 주문 수량, 주문 가격
		int orderQuantity = request.quantity();
		int price = product.getPrice();

		// 주문 아이템 만들기
		OrderProduct orderProduct = OrderProduct.createOrderProduct(product, price, orderQuantity);
		List<OrderProduct> orderProducts = new ArrayList<>();
		orderProducts.add(orderProduct);

		// 주문 만들기
		Order order = Order.createOrder(member, orderProducts);

		orderRepository.save(order);
	}

	public Page<OrderFindResponse> getAllOrders(LoginMember loginMember, Pageable pageable) {

		// 주문자 찾기
		Member member = memberService.getMemberById(loginMember.id());

		Page<Order> orders = orderRepository.findAllByMember(member, pageable);

		Page<OrderFindResponse> result = orders.map(order -> {
			OrderFindResponse orderFindResponse = new OrderFindResponse(order);
			List<OrderProduct> orderProducts = order.getOrderProducts();

			for (OrderProduct orderProduct : orderProducts) {
				OrderItemResponse orderItemResponse = new OrderItemResponse(orderProduct);
				orderFindResponse.addOrderProduct(orderItemResponse);
			}

			return orderFindResponse;
		});

		return result;
	}

	public OrderItemsResponse getOrderDetail(Long orderId, LoginMember loginMember) {

		// 주문자 찾기
		Member member = memberService.getMemberById(loginMember.id());

		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_ORDER));

		OrderItemsResponse response = new OrderItemsResponse(order);

		order.getOrderProducts()
			.forEach(orderProduct -> {
				OrderItemResponse orderItemResponse = new OrderItemResponse(orderProduct);
				response.addOrderItem(orderItemResponse);
			});

		return response;
	}

	@Transactional
	public void cancelOrder(Long orderId, LoginMember loginMember) {

		// 주문자 찾기
		Member member = memberService.getMemberById(loginMember.id());

		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_ORDER));

		order.getOrderProducts()
			.forEach(orderProduct -> {
				order.cancelOrder(orderProduct);
			});
	}
}
