package com.toolrent.demo.exception;

import java.util.List;

import com.toolrent.demo.dto.ErrorResponseMessageDTO;

import lombok.Data;

@Data
public class ServiceValidationException extends RuntimeException {

	private int statusCode;
	private String apiErrorMessage;
	private String userErrorCode;
	private List<ErrorResponseMessageDTO> emailErrorList;

	public ServiceValidationException() {
	}

	public ServiceValidationException(String message) {
		super(message);
	}

	public ServiceValidationException(Throwable cause) {
		super(cause);
	}

	public ServiceValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceValidationException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public ServiceValidationException(String message, String apiErrorMessage, int statusCode, String userErrorCode) {
		super(message);
		this.statusCode = statusCode;
		this.apiErrorMessage = apiErrorMessage;
		this.userErrorCode = userErrorCode;
	}

	public ServiceValidationException(String message, int statusCode, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public ServiceValidationException(List<ErrorResponseMessageDTO> emailErrorList) {
		// super(message);
		this.emailErrorList = emailErrorList;
	}

}
