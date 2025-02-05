package my.adg.backend.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.auth.resolver.LoginMember;
import my.adg.backend.cart.domain.Cart;
import my.adg.backend.cart.dto.request.CreateUpdateCartProductRequest;
import my.adg.backend.cart.dto.request.DeleteCartProductRequest;
import my.adg.backend.cart.dto.response.CartResponse;
import my.adg.backend.cart.repository.CartRepository;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.member.domain.Member;
import my.adg.backend.member.service.MemberService;
import my.adg.backend.product.domain.Product;
import my.adg.backend.product.service.ProductService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

	private final CartRepository cartRepository;
	private final MemberService memberService;
	private final ProductService productService;

	public List<CartResponse> getAllCarts(LoginMember loginMember) {
		Member member = memberService.getMemberById(loginMember.id());
		List<Cart> allByMember = cartRepository.findAllByMember(member);

		List<CartResponse> result = new ArrayList<CartResponse>();

		for (Cart cart : allByMember) {
			CartResponse response = new CartResponse(cart);
			result.add(response);
		}

		return result;
	}

	@Transactional
	public void addCartProduct(Long productId, LoginMember loginMember, CreateUpdateCartProductRequest request) {

		Member member = memberService.getMemberById(loginMember.id());
		Product product = productService.getProductById(productId);
		int quantity = request.quantity();

		cartRepository.findByProduct(product).ifPresentOrElse(cart -> {
			int totalQuantity = cart.getQuantity() + quantity;
			cart.addQuantity(totalQuantity, quantity);
		}, () -> {
			Cart cart = new Cart(member, product, quantity);
			cartRepository.save(cart);
		});
	}

	@Transactional
	public void updateCartProduct(Long cartId, CreateUpdateCartProductRequest request) {

		cartRepository.findById(cartId).ifPresentOrElse(cart -> {
				int quantity = request.quantity();
				cart.updateQuantity(quantity);
			}, () -> {
				throw new BalanceTalkException(ErrorCode.NOT_FOUND_CART);
			}
		);
	}

	@Transactional
	public void deleteAllCarts(LoginMember loginMember, DeleteCartProductRequest request) {

		List<Long> cartIds = request.cartId();

		for (Long cartId : cartIds) {
			deleteCartProduct(cartId);
		}
	}

	@Transactional
	public void deleteCartProduct(Long cartId) {
		cartRepository.findById(cartId).ifPresentOrElse(cart -> {
			cartRepository.delete(cart);
		}, () -> {
			throw new BalanceTalkException(ErrorCode.NOT_FOUND_CART);
		});
	}

}
