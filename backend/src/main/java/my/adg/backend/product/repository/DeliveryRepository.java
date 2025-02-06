package my.adg.backend.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.adg.backend.product.domain.Delivery;
import my.adg.backend.product.domain.Product;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
