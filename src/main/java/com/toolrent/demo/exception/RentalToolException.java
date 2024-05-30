package com.toolrent.demo.exception;

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
public class RentalToolException {
	
	@JsonProperty("status")
	private String status;

	@JsonProperty("error_code")
	private String errorCode;

	@JsonProperty("api_error_message")
	private String apiErrorMessage;

	@JsonProperty("user_error_message")
	private String userErrorMessage;
	
	@JsonProperty("retry")
	private boolean retry;

	@JsonProperty("request_id")
	private String requestId;

	@JsonProperty("time_stamp")
	private String time_stamp;
	
	public RentalToolException(String errorCode, String apiErrorMessage, String userErrorMessage, boolean retry) {
		super();
		this.errorCode = errorCode;
		this.apiErrorMessage = apiErrorMessage;
		this.userErrorMessage = userErrorMessage;
		this.retry = retry;
	}

	public RentalToolException(String errorCode, String apiErrorMessage, String userErrorMessage) {
		super();
		this.errorCode = errorCode;
		this.apiErrorMessage = apiErrorMessage;
		this.userErrorMessage = userErrorMessage;

	}

}
