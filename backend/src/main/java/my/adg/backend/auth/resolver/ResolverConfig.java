package my.adg.backend.auth.resolver;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResolverConfig implements WebMvcConfigurer {

	private final ArgumentResolver resolver;

	public ResolverConfig(ArgumentResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(resolver);
	}
}
