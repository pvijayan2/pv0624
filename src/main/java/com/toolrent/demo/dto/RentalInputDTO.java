package com.toolrent.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalInputDTO {

	@NotBlank(message = "toolCode is required.")
	private String toolCode;

	@NotBlank(message = "checkoutDate is required.")
	private String checkoutDate;

	//@NotBlank(message = "rentalDays is required.")
	private int rentalDays;

	//@NotBlank(message = "discount is required.")
	private int discount;

}
