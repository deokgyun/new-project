package my.adg.backend.cart.dto.response;

import my.adg.backend.cart.domain.Cart;

public record CartResponse(
	Long cartId,
	Long productId,
	String productName,
	int price,
	int quantity
) {
	public CartResponse(Cart cart) {
		this(
			cart.getId(),
			cart.getProduct().getId(),
			cart.getProduct().getName(),
			cart.getProduct().getPrice(),
			cart.getQuantity()
		);
	}
}
