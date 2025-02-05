package my.adg.backend.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.auth.resolver.AuthMember;
import my.adg.backend.auth.resolver.LoginMember;
import my.adg.backend.cart.dto.request.CreateUpdateCartProductRequest;
import my.adg.backend.cart.dto.request.DeleteCartProductRequest;
import my.adg.backend.cart.dto.response.CartResponse;
import my.adg.backend.cart.service.CartService;

@Slf4j
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController implements CartSwaggerController {
	private final CartService cartService;

	@GetMapping
	public ResponseEntity<List<CartResponse>> getAllCarts(@AuthMember LoginMember loginMember) {
		return ResponseEntity.ok(cartService.getAllCarts(loginMember));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAllCarts(LoginMember loginMember, @RequestBody DeleteCartProductRequest request) {

		cartService.deleteAllCarts(loginMember, request);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/{productId}")
	public ResponseEntity<Void> addCartProduct(@PathVariable("productId") Long productId,
		@AuthMember LoginMember loginMember,
		@RequestBody CreateUpdateCartProductRequest request) {

		cartService.addCartProduct(productId, loginMember, request);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/{cartId}")
	public ResponseEntity<Void> updateCartProduct(@PathVariable("cartId") Long cartId,
		@AuthMember LoginMember loginMember,
		@RequestBody CreateUpdateCartProductRequest request) {

		cartService.updateCartProduct(cartId, request);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<Void> deleteCartProduct(@PathVariable("cartId") Long cartId,
		@AuthMember LoginMember loginMember) {

		cartService.deleteCartProduct(cartId);

		return ResponseEntity.ok().build();
	}
}
