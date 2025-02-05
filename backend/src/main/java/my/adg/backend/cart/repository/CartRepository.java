package my.adg.backend.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my.adg.backend.cart.domain.Cart;
import my.adg.backend.member.domain.Member;
import my.adg.backend.product.domain.Product;

public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findAllByMember(Member member);

	Optional<Cart> findByProduct(Product product);
}
