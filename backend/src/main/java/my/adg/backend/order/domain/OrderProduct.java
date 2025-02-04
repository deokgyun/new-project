package my.adg.backend.order.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.adg.backend.global.BaseEntity;
import my.adg.backend.product.domain.Product;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderProduct extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_product_id")
	private Long id;

	private int quantity;
	private int price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	public static OrderProduct createOrderProduct(Product product, int price, int orderQuantity) {
		product.removeStock(orderQuantity);

		return OrderProduct.builder()
			.product(product)
			.price(price)
			.quantity(orderQuantity)
			.build();
	}

	public void orderSet(Order order) {
		this.order = order;
	}

	public void cancel() {
		getProduct().addStock(quantity);
	}

	public void cancelOrder(OrderProduct orderProduct) {
		product.cancelOrder(orderProduct);
	}
}
