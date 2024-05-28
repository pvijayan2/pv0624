package com.toolrent.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class RentalAPIResponse {

	private String first_name;
	private String last_name;
	private String email;
	private String user_name;
	private String payload_token;
	private String token_time;
	private String provider_uid;
	private String access_token;
	private List<String> appAccess;
	private String tinypass_token;
	private String session_token;
	private Boolean isTempPassword;
	private String testConfig;
}
