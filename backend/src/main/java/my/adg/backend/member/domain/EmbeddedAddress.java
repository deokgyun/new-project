package my.adg.backend.member.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedAddress {

	private String zipcode;
	private String address1;
	private String address2;

}
