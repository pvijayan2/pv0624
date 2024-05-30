package com.toolrent.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Builder
public class ErrorResponseMessageDTO implements Serializable {

	private static final long serialVersionUID = 8897498079397194712L;

	@JsonProperty("status")
	private String status;

	@JsonProperty("error_code")
	private String errorCode;

	@JsonProperty("api_error_message")
	private String apiErrorMessage;

	@JsonProperty("user_error_message")
	private String userErrorMessage;
	/**
	 * this is kind of not required. based on the one of the API consumer we
	 * introduced this to the user whether to retry or not true means retry false
	 * means no retry
	 */
	@JsonProperty("retry")
	private boolean retry;

	@JsonProperty("request_id")
	private String requestId;

	@JsonProperty("time_stamp")
	private String time_stamp;

	public ErrorResponseMessageDTO(String errorCode, String apiErrorMessage, String userErrorMessage, boolean retry) {
		super();
		this.errorCode = errorCode;
		this.apiErrorMessage = apiErrorMessage;
		this.userErrorMessage = userErrorMessage;
		this.retry = retry;
	}

	public ErrorResponseMessageDTO(String errorCode, String apiErrorMessage, String userErrorMessage) {
		super();
		this.errorCode = errorCode;
		this.apiErrorMessage = apiErrorMessage;
		this.userErrorMessage = userErrorMessage;

	}

}
