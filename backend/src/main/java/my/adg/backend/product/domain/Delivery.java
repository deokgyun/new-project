package my.adg.backend.product.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import my.adg.backend.global.BaseEntity;
import my.adg.backend.product.domain.enums.DeliveryMethod;
import my.adg.backend.product.domain.enums.PriceType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_id")
	private Long id;
	private boolean isUse;
	private String company;
	private int price;
	@Enumerated(EnumType.STRING)
	private DeliveryMethod method;
	@Enumerated(EnumType.STRING)
	private PriceType priceType;

}
