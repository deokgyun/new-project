package my.adg.backend.global.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus httpStatus, String message) {

	public static ErrorResponse from(final HttpStatus httpStatus, final String message) {
		return new ErrorResponse(httpStatus, message);
	}
}
