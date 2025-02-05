package my.adg.backend.cart.dto.request;

import java.util.List;

public record DeleteCartProductRequest(
	List<Long> cartId
) {
}
