package com.toolrent.demo.dto;

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
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private String checkoutDate;
	private String dueDate;
	private String dailyRentalCharge;
	private int chargeDays;
	private double preDiscountCharge;
	private long discountPercent;
	private long discountAmount;
	private long finalCharge;
}
