package com.toolrent.demo.exception;

public class ServiceException extends RuntimeException{
	
	private int statusCode;
	private String apiErrorMessage;
	private String userErrorCode;
	
	public ServiceException() {		
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
	
	public ServiceException(String message, String apiErrorMessage, int statusCode, String userErrorCode) {
		super(message);
		this.statusCode = statusCode;
		this.apiErrorMessage = apiErrorMessage;
		this.userErrorCode = userErrorCode;
	}
	
	public ServiceException(String message, int statusCode,Throwable cause) {
		super(message,cause);
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public String getApiErrorMessage() {
		return apiErrorMessage;
	}

	public void setApiErrorMessage(String apiErrorMessage) {
		this.apiErrorMessage = apiErrorMessage;
	}

	public String getUserErrorCode() {
		return userErrorCode;
	}

	public void setUserErrorCode(String userErrorCode) {
		this.userErrorCode = userErrorCode;
	}

	
	
	

}
