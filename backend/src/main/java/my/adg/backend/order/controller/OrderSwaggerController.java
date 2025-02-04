package my.adg.backend.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.auth.resolver.LoginMember;
import my.adg.backend.global.dto.request.PageDataRequest;
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
	ResponseEntity<Page<OrderFindResponse>> getAllOrders(Optional<PageDataRequest> request, LoginMember loginMember);

	// 선택 주문 조회
	@Operation(summary = "선택 주문 상세 조회하기")
	ResponseEntity<OrderItemsResponse> getOrderDetail(Long orderId, LoginMember loginMember);

}
