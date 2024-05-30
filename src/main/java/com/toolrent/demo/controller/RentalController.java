package com.toolrent.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toolrent.demo.dto.RentalAPIResponse;
import com.toolrent.demo.dto.RentalInputDTO;
import com.toolrent.demo.model.RentalRequest;
import com.toolrent.demo.service.RentalService;
import com.toolrent.demo.util.HolidayCheck;
import com.toolrent.demo.util.ValueMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Component

@RequestMapping("/rentalTool")
@RestController
@Slf4j
@Tag(
    name = "Tool Rental Agreement",
    description = "Create the tool rental agreement")
public class RentalController {
	
	@Autowired
	private RentalService rentalService;

	@Autowired
	Environment env;

	@PostMapping("/createRentalAgreement")
	public ResponseEntity<RentalAPIResponse> createRentalAgreement(@RequestBody @Valid RentalInputDTO input,
			HttpServletRequest request) {
		log.info("RentalController::createRentalAgreement request body {}", ValueMapper.jsonAsString(input));
		RentalAPIResponse responseDTO = new RentalAPIResponse();

		RentalRequest rentalRequest = RentalRequest.builder().toolCode(input.getToolCode())
				.checkoutDate(input.getCheckoutDate()).rentalDays(input.getRentalDays()).discount(input.getDiscount())
				.build();
		responseDTO = rentalService.process(rentalRequest);
		
		
		
		responseDTO.setFinalCharge(100);
		String val = env.getProperty("toolrental.tool.CHNS");
		log.debug("val----->" + val);
		
		 log.info(
			        "RentalController::createRentalAgreement response {}", ValueMapper.jsonAsString(responseDTO));
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

	}

}
