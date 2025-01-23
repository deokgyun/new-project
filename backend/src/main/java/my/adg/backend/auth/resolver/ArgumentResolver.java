package my.adg.backend.auth.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;
import my.adg.backend.auth.infrastructure.JwtTokenProvider;
import my.adg.backend.global.exception.BalanceTalkException;
import my.adg.backend.global.exception.ErrorCode;

@Component
@RequiredArgsConstructor
public class ArgumentResolver implements HandlerMethodArgumentResolver {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AuthMember.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			throw new BalanceTalkException(ErrorCode.NOT_FOUND_MEMBER);
		}

		String authorization = webRequest.getHeader("Authorization");

		Long id = Long.parseLong(jwtTokenProvider.decodeAccessToken(authorization));

		return new LoginMember(id);
	}

}
