
package de.hsbremen.mkss.restservice.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomOrderError extends RuntimeException{
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> customHandleNotFound(EntityNotFoundException ex) {
		CustomErrorResponse errors = new CustomErrorResponse();
		// errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		//errors.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handelException (Exception ex) {
		CustomErrorResponse errors = new CustomErrorResponse();
		// errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		//errors.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}

