package my.adg.backend.product.controller;

import java.util.Optional;

import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.global.dto.request.PageDataRequest;
import my.adg.backend.product.dto.response.ProductAllResponse;
import my.adg.backend.product.dto.response.ProductGetResponse;

@Tag(name = "상품", description = "상품 API")
public interface ProductSwaggerController {

	// 리스트 출력(전체 상품)
	@Operation(summary = "전체 상품 조회하기")
	ResponseEntity<PagedModel<ProductAllResponse>> getProducts(Optional<PageDataRequest> request);

	// 상세 조회
	@Operation(summary = "상품 상세 조회하기")
	ResponseEntity<ProductGetResponse> getProduct(Long productId);


}
