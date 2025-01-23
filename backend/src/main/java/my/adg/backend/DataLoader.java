package my.adg.backend;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.member.domain.Grade;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.repository.GradeRepository;
import my.adg.backend.member.repository.MemberRepository;
import my.adg.backend.member.service.MemberService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final MemberRepository memberRepository;
	private final GradeRepository gradeRepository;

	private final MemberService memberService;

	@Override
	public void run(String... args) throws Exception {

		Grade grade1 = new Grade();
		grade1.setName("1단계");
		grade1.setDiscountRate(2);
		gradeRepository.save(grade1);

		Grade grade2 = new Grade();
		grade2.setName("2단계");
		grade2.setDiscountRate(4);
		gradeRepository.save(grade2);

		Grade grade3 = new Grade();
		grade3.setName("3단계");
		grade3.setDiscountRate(7);
		gradeRepository.save(grade3);

		Grade grade4 = new Grade();
		grade4.setName("4단계");
		grade4.setDiscountRate(10);
		Grade save = gradeRepository.save(grade4);

		MemberJoinRequest request = new MemberJoinRequest("test@test.com", "xptmxm1", "테스트이름", "01012345678",
			LocalDate.of(1988, 07, 05), "12345", "address1", "address2");

		memberService.joinMember(request);

	}
}
