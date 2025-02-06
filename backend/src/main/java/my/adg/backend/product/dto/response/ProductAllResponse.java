package my.adg.backend.product.dto.response;

import my.adg.backend.product.domain.Product;

public record ProductAllResponse(
	Long productId,
	String name,
	int price,
	int stock
) {
	public ProductAllResponse(Product product) {
		this(
			product.getId(),
			product.getName(),
			product.getPrice(),
			product.getStock()
		);
	}
}
