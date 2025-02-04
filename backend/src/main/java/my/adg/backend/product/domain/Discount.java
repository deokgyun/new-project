package my.adg.backend.product.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import my.adg.backend.global.BaseEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id")
	private Long id;
	private boolean isUse;
	private String type;
	private int discountPrice;
	private boolean isPeriod;
	private LocalDateTime basicStartDate;
	private LocalDateTime basicEndDate;
	private int reservationDiscountPrice;
	private String reservationDiscountType;
	private LocalDateTime reservationStartDate;
	private LocalDateTime reservationEndDate;

}
