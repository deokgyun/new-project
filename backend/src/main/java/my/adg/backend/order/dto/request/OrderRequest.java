package my.adg.backend.order.dto.request;

public record OrderRequest(
	Long productId,
	int quantity

) {
}
