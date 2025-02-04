package my.adg.backend.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.adg.backend.order.domain.Order;
import my.adg.backend.order.domain.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
