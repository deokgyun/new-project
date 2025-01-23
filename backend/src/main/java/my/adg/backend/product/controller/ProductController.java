package my.adg.backend.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.sample.controller.SampleSwaggerController;
import my.adg.backend.sample.service.SampleService;

@Slf4j
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProductController implements SampleSwaggerController {
	private final SampleService sampleService;
	
}
