package my.adg.backend.member.domain.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {

	ACTIVE("활성"),
	INACTIVE("비활성"),
	;

	private String name;

	AccountStatus(String name) {
		this.name = name;
	}
}