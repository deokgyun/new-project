package my.adg.backend.cart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.adg.backend.global.BaseEntity;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.product.domain.Product;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Cart extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Long id;

	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	public Cart(Member member, Product product, int quantity) {
		if (product.getStock() < quantity) {
			throw new BalanceTalkException(ErrorCode.OUT_OF_STOCK);
		}

		this.member = member;
		this.product = product;
		this.quantity = quantity;
	}

	public void addQuantity(int totalQuantity, int quantity) {

		if (product.getStock() < totalQuantity) {
			throw new BalanceTalkException(ErrorCode.OUT_OF_STOCK);
		}

		this.quantity += quantity;
	}

	public void updateQuantity(int quantity) {

		if (product.getStock() < quantity) {
			throw new BalanceTalkException(ErrorCode.OUT_OF_STOCK);
		}

		this.quantity = quantity;
	}
}
