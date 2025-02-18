package my.adg.backend.order.controller;

import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.order.dto.request.OrderRequest;
import my.adg.backend.order.dto.response.OrderFindResponse;
import my.adg.backend.order.dto.response.OrderItemsResponse;

@Tag(name = "주문", description = "주문 API")
public interface OrderSwaggerController {

	// 주문 하기
	@Operation(summary = "선택 주문 상세 조회하기")
	ResponseEntity<Void> order(LoginMember loginMember, OrderRequest request);

	// 주문 취소
	@Operation(summary = "선택 주문 취소하기")
	ResponseEntity<Void> cancelOrder(Long orderId, LoginMember loginMember);

	// 전체 주문 조회
	@Operation(summary = "전체 주문 조회하기")
	ResponseEntity<PagedModel<OrderFindResponse>> getAllOrders(int page, LoginMember loginMember);

	// 선택 주문 조회
	@Operation(summary = "선택 주문 상세 조회하기")
	ResponseEntity<OrderItemsResponse> getOrderDetail(Long orderId, LoginMember loginMember);

}
