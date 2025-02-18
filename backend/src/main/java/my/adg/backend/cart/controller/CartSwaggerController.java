package my.adg.backend.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.cart.dto.request.CreateUpdateCartProductRequest;
import my.adg.backend.cart.dto.request.DeleteCartProductRequest;
import my.adg.backend.cart.dto.response.CartResponse;

@Tag(name = "Sample", description = "Sample API")
public interface CartSwaggerController {

	// 장바구니 전체 조회
	ResponseEntity<List<CartResponse>> getAllCarts(LoginMember loginMember);

	// 장바구니 선택된 여러개 삭제
	ResponseEntity<Void> deleteAllCarts(LoginMember loginMember, DeleteCartProductRequest request);

	// 장바구니 추가
	ResponseEntity<Void> addCartProduct(Long productId, LoginMember loginMember, CreateUpdateCartProductRequest request);

	// 장바구니 수량 수정
	ResponseEntity<Void> updateCartProduct(Long cartId, LoginMember loginMember, CreateUpdateCartProductRequest request);

	// 장바구니 개별 삭제
	ResponseEntity<Void> deleteCartProduct(Long cartId, LoginMember loginMember);

}
