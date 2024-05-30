package com.toolrent.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ResponseHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ServiceException.class })
	public ResponseEntity<Object> handleServiceException(ServiceException serviceException) {
		RentalToolException reantalToolException = new RentalToolException(serviceException.getUserErrorCode(),
				serviceException.getApiErrorMessage(), serviceException.getUserErrorCode());
		return new ResponseEntity<>(reantalToolException, HttpStatus.BAD_REQUEST);
	}

}
