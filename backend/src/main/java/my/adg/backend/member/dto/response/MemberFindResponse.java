package my.adg.backend.member.dto.response;

import my.adg.backend.member.domain.Member;

public record MemberFindResponse(
	Long id,
	String email,
	String name
) {

	public MemberFindResponse(Member m) {
		this(
			m.getId(),
			m.getEmail(),
			m.getName()
		);
	}
}
