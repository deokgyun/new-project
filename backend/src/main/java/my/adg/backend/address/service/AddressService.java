package my.adg.backend.address.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.auth.resolver.LoginMember;
import my.adg.backend.address.domain.Address;
import my.adg.backend.address.dto.request.CreateAndUpdateAddressRequest;
import my.adg.backend.address.dto.response.AddressResponse;
import my.adg.backend.address.repository.AddressRepository;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.member.service.MemberService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

	private final AddressRepository addressRepository;
	private final MemberService memberService;

	public List<Address> getAllDeliveryAddress(Long memberId) {
		return addressRepository.findByMemberId(memberId)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_ADDRESS));
	}

	public Address getDeliveryAddress(Long addressId) {
		return addressRepository.findById(addressId)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_ADDRESS));
	}

	public List<AddressResponse> getAllDeliveryAddress(LoginMember loginMember) {
		List<Address> allDeliveryAddress = getAllDeliveryAddress(loginMember.id());

		List<AddressResponse> list = allDeliveryAddress.stream()
			.map(delivery -> new AddressResponse(delivery))
			.toList();

		return list;
	}

	@Transactional
	public void createAddress(LoginMember loginMember, CreateAndUpdateAddressRequest request) {
		Member member = memberService.getMemberById(loginMember.id());
		Address entity = request.toEntity(member, request);
		addressRepository.save(entity);
	}

	@Transactional
	public void updateAddress(Long id, CreateAndUpdateAddressRequest request) {
		Address deliveryAddress = getDeliveryAddress(id);
		deliveryAddress.updateAddress(request);
	}

	public AddressResponse getAddress(Long id) {
		Address deliveryAddress = getDeliveryAddress(id);
		return new AddressResponse(deliveryAddress);
	}

	@Transactional
	public void deleteAddress(Long id) {
		Address deliveryAddress = getDeliveryAddress(id);
		addressRepository.deleteById(deliveryAddress.getId());
	}
}
