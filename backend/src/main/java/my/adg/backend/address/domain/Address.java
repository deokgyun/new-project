package my.adg.backend.address.domain;

import java.io.Serializable;

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
import my.adg.backend.address.dto.request.CreateAndUpdateAddressRequest;
import my.adg.backend.global.BaseEntity;
import my.adg.backend.member.domain.Member;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Address extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String addressName;
	private String receiverName;
	private String zipcode;
	private String address1;
	private String address2;
	private boolean isDefault;
	private String phoneNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	public void updateAddress(CreateAndUpdateAddressRequest request) {
		this.addressName = request.addressName();
		this.receiverName = request.receiverName();
		this.zipcode = request.zipcode();
		this.address1 = request.address1();
		this.address2 = request.address2();
		this.isDefault = request.isDefault();
		this.phoneNumber = request.phoneNumber();
	}
}
