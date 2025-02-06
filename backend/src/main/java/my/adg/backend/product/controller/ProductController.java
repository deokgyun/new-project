package my.adg.backend.product.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.global.dto.request.PageDataRequest;
import my.adg.backend.product.domain.Product;
import my.adg.backend.product.dto.response.ProductResponse;
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
	public ResponseEntity<PagedModel<ProductResponse>> getProducts(
		@RequestBody(required = false) Optional<PageDataRequest> request) {

		int restPage = request
			.map(PageDataRequest::page)
			.map(page -> page.orElse(1) - 1)
			.orElse(0);

		Pageable pageable = PageRequest.of(restPage, PAGE_SIZE, Sort.by(Sort.Direction.DESC, SORT_ORDER_DATE));
		Page<ProductResponse> products = productService.getProducts(pageable);

		return ResponseEntity.ok(new PagedModel<>(products));
	}
}
