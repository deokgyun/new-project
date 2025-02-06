package my.adg.backend.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import my.adg.backend.member.domain.Member;
import my.adg.backend.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByMember(Member member);

	// List<Order> findAllByMember(Member member, Pageable pageable);

	Page<Order> findAllByMember(Member member, Pageable pageable);
}
