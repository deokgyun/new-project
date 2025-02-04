package my.adg.backend.order.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.adg.backend.global.BaseEntity;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.order.domain.enums.OrderStatus;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Order extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	private LocalDateTime deliveryDate;
	private LocalDateTime completedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderProduct> orderProducts = new ArrayList<>();

	public static Order createOrder(Member member, List<OrderProduct> orderProducts) {

		Order order = Order.builder()
			.member(member)
			.status(OrderStatus.RECEIVED)
			.orderProducts(orderProducts)
			.build();

		for (OrderProduct orderProduct : orderProducts) {
			order.addOrderProduct(orderProduct);
		}

		return order;
	}

	public void addOrderProduct(OrderProduct orderProduct) {
		orderProduct.orderSet(this);
	}

	public void cancelOrder(OrderProduct orderProduct) {
		if(this.status.equals(OrderStatus.CANCEL)) {
			throw new BalanceTalkException(ErrorCode.ALREADY_CANCEL_ORDER);
		}

		this.status = OrderStatus.CANCEL;
		this.completedDate = LocalDateTime.now();

		orderProduct.cancelOrder(orderProduct);
	}
}
