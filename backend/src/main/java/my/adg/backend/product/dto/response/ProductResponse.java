package my.adg.backend.product.dto.response;

import my.adg.backend.product.domain.Product;

public record ProductResponse(
	Long productId,
	String name,
	int price,
	int stock
) {
	public ProductResponse(Product product) {
		this(
			product.getId(),
			product.getName(),
			product.getPrice(),
			product.getStock()
		);
	}
}
