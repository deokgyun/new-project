package my.adg.backend.address.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.adg.backend.authentication.resolver.AuthMember;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.address.dto.request.CreateAndUpdateAddressRequest;
import my.adg.backend.address.dto.response.AddressResponse;
import my.adg.backend.address.service.AddressService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController implements AddressSwaggerController {

	private final AddressService addressService;

	@GetMapping
	public ResponseEntity<List<AddressResponse>> getAllDeliveryAddress(@AuthMember LoginMember loginMember) {
		return ResponseEntity.ok().body(addressService.getAllDeliveryAddress(loginMember));
	}

	@PostMapping
	public ResponseEntity<Void> createAddress(@AuthMember LoginMember loginMember,
		@RequestBody @Valid CreateAndUpdateAddressRequest request) {

		addressService.createAddress(loginMember, request);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateAddress(@PathVariable(name = "id") Long id, @AuthMember LoginMember loginMember,
		@RequestBody CreateAndUpdateAddressRequest request) {

		addressService.updateAddress(id, request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getAddress(@PathVariable(name = "id") Long id,
		@AuthMember LoginMember loginMember) {

		return ResponseEntity.ok(addressService.getAddress(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable(name = "id") Long id, @AuthMember LoginMember loginMember) {

		addressService.deleteAddress(id);
		return ResponseEntity.ok().build();
	}
}
