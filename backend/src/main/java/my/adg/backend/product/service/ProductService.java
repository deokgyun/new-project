package my.adg.backend.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;
import my.adg.backend.product.domain.Product;
import my.adg.backend.product.repository.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

	private final ProductRepository productRepository;

	public Product getProductById(Long id){
		return productRepository.findById(id)
			.orElseThrow(() -> new BalanceTalkException(ErrorCode.NOT_FOUND_PRODUCT));
	}

}
