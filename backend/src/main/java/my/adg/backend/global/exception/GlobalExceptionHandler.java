package my.adg.backend.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BalanceTalkException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleBalanceTalkException(BalanceTalkException e) {
		ErrorResponse response = ErrorResponse.from(e.getErrorCode().getHttpStatus(), e.getMessage());
		log.error("exception message = {}", e.getMessage());
		return ResponseEntity.status(response.httpStatus()).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {

		ErrorResponse response = ErrorResponse.from((HttpStatus)e.getStatusCode(), e.getBindingResult().getFieldError().getDefaultMessage());

		return ResponseEntity.status(response.httpStatus()).body(response);
	}

}
