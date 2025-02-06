package my.adg.backend.address.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my.adg.backend.address.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Optional<List<Address>> findByMemberId(Long memberId);
}
