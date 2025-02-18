package my.adg.backend.global.exception;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterExceptionHandler extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws
		ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			filterChain.doFilter(request, response);
		} catch (BalanceTalkException e) {
			response.setStatus(e.getErrorCode().getHttpStatus().value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding("UTF-8");
			objectMapper.writeValue(response.getWriter(),
				new ErrorResponse(e.getErrorCode().getHttpStatus(), e.getMessage()));
		}
	}
}
