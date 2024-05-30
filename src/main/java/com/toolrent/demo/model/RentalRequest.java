package com.toolrent.demo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {

	@NotBlank(message = "toolCode is required.")
	private String toolCode;

	@NotBlank(message = "checkoutDate is required.")
	private String checkoutDate;

	//@NotBlank(message = "rentalDays is required.")
	private int rentalDays;

	//@NotBlank(message = "discount is required.")
	private int discount;

}
