package my.adg.backend.address.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.authentication.resolver.LoginMember;
import my.adg.backend.address.dto.request.CreateAndUpdateAddressRequest;
import my.adg.backend.address.dto.response.AddressResponse;

@Tag(name = "배송지", description = "배송지 API")
public interface AddressSwaggerController {

	@Operation(summary = "배송지 전체 조회")
	public ResponseEntity<List<AddressResponse>> getAllDeliveryAddress(LoginMember loginMember);

	@Operation(summary = "배송지 추가")
	public ResponseEntity<Void> createAddress(LoginMember loginMember, CreateAndUpdateAddressRequest request);

	@Operation(summary = "배송지 수정")
	public ResponseEntity<Void> updateAddress(Long id, LoginMember loginMember, CreateAndUpdateAddressRequest request);

	@Operation(summary = "배송지 선택 조회")
	public ResponseEntity<AddressResponse> getAddress(Long id, LoginMember loginMember);
	
	@Operation(summary = "배송지 선택 삭제")
	public ResponseEntity<Void> deleteAddress(Long id, LoginMember loginMember);

}
