package my.adg.backend.product.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.adg.backend.global.BaseEntity;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.order.domain.OrderProduct;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	private String name;
	private int price;
	private String description;
	private int stock;
	private String taxType;
	private boolean isPeriod;
	private LocalDateTime saleStartDate;
	private LocalDateTime saleEndDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discount_id")
	private Discount discount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "product")
	private List<OrderProduct> orderProducts = new ArrayList<>();

	public void removeStock(int orderCount) {
		int restStock = this.stock - orderCount;

		if (restStock < 0) {
			throw new BalanceTalkException(ErrorCode.OUT_OF_STOCK);
		}

		this.stock = restStock;
	}

	public void addStock(int quantity) {
		this.stock += quantity;
	}

	public void cancelOrder(OrderProduct orderProduct) {
		this.stock += orderProduct.getQuantity();
	}
}
