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
import my.adg.backend.product.domain.Delivery;
import my.adg.backend.product.domain.Product;
import my.adg.backend.product.domain.enums.DeliveryMethod;
import my.adg.backend.product.domain.enums.PriceType;
import my.adg.backend.product.repository.DeliveryRepository;
import my.adg.backend.product.repository.ProductRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final MemberRepository memberRepository;
	private final GradeRepository gradeRepository;
	private final ProductRepository productRepository;
	private final DeliveryRepository deliveryRepository;

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

		MemberJoinRequest request = new MemberJoinRequest("test@test.com", "xptmxm1", "첫번째", "01012345678",
			LocalDate.of(2000, 2, 22), "12345", "서울시", "영등포");

		memberService.joinMember(request);

		MemberJoinRequest request2 = new MemberJoinRequest("string@test.com", "xptmxm1", "두번째", "01082749127",
			LocalDate.of(2000, 01, 24), "12345", "서울시", "구로구");
		memberService.joinMember(request2);

		for (int i = 0; i < 17; i++) {
			int min = 1;
			int deliveryMax = 10;
			double v = Math.floor(Math.random() * (deliveryMax - min + 1)) + min;

			double deliveryPrice = v * 1000;
			double price = v * 10000;

			Delivery delivery = new Delivery();
			delivery.setUse(true);
			delivery.setCompany("우리택배");
			delivery.setPrice((int)deliveryPrice);
			delivery.setMethod(DeliveryMethod.PARCEL);
			delivery.setPriceType(PriceType.PAID);
			Delivery save1 = deliveryRepository.save(delivery);

			Product p = new Product();
			p.setName(i + "번 상품");
			p.setPrice((int)price);
			p.setDescription("상품내용입니다.");
			p.setStock((int)(Math.random() * 100 + 300));
			p.setDelivery(save1);

			productRepository.save(p);

		}

	}
}
