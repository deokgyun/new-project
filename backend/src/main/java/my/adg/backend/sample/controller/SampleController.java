package my.adg.backend.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.adg.backend.sample.service.SampleService;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class SampleController implements SampleSwaggerController {
	private final SampleService sampleService;

}
