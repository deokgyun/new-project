package my.adg.backend.product.dto.response;

import my.adg.backend.product.domain.Product;

public record ProductGetResponse(
	String name,
	int price,
	String description,
	int stock,

	String deliveryMethod,
	String deliveryCompany,
	String deliveryPriceType,
	int deliveryPrice
) {

	public ProductGetResponse(Product product) {
		this(
			product.getName(),
			product.getPrice(),
			product.getDescription(),
			product.getStock(),

			product.getDelivery().getMethod().getDescription(),
			product.getDelivery().getCompany(),
			product.getDelivery().getPriceType().getDescription(),
			product.getDelivery().getPrice()
		);
	}

}
