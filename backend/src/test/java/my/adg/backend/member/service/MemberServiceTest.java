package my.adg.backend.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import my.adg.backend.member.domain.Member;

class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	@DisplayName("회원가입을 테스트합니다.")
	void joinMember() {
		Member member = new Member();
		member.setEmail("test@adg.com");
		member.setNickname("test");

		assertEquals("test", member.getNickname());
		assertEquals("test@adg.com", member.getEmail());
	}
}