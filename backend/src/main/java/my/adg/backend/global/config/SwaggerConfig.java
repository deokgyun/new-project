package my.adg.backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(
	info = @Info(
		title = "API 명세서",
		description = "API 명세서"
	)
)
@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.components(
				new Components()
					.addSecuritySchemes("Authorization", new SecurityScheme()
						.name("Authorization")
						.type(SecurityScheme.Type.APIKEY)
						.in(SecurityScheme.In.HEADER)
						.bearerFormat("JWT")
					)
			);
	}
}
