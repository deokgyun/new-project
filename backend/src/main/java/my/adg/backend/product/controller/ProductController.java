package my.adg.backend.product.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.product.dto.response.ProductAllResponse;
import my.adg.backend.product.dto.response.ProductGetResponse;
import my.adg.backend.product.repository.ProductRepository;
import my.adg.backend.product.service.ProductService;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController implements ProductSwaggerController {
	private final ProductService productService;
	private final ProductRepository productRepository;

	private static final String SORT_ORDER_DATE = "createdDate";
	private static final int PAGE_SIZE = 10;

	@GetMapping
	public ResponseEntity<PagedModel<ProductAllResponse>> getProducts(
		@RequestParam(value = "page", defaultValue = "0") int page) {

		log.info("page: {}", page);


		Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, SORT_ORDER_DATE));
		Page<ProductAllResponse> products = productService.getProducts(pageable);

		return ResponseEntity.ok(new PagedModel<>(products));
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductGetResponse> getProduct(@PathVariable("productId") Long productId) {

		return ResponseEntity.ok(productService.getProduct(productId));
	}
}
