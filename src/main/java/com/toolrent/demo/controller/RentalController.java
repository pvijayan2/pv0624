package com.toolrent.demo.controller;

import java.util.Calendar;

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
import com.toolrent.demo.util.HolidayCheck;
import com.toolrent.demo.util.ValueMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Component

@RequestMapping("/rentalTool")
@RestController
@Slf4j
public class RentalController {

	@Autowired
	Environment env;

	@PostMapping("/createRentalAgreement")
	public ResponseEntity<RentalAPIResponse> createRentalAgreement(@RequestBody @Valid RentalInputDTO input,
			HttpServletRequest request) {
		log.info("UserRegisterController::registerUser request body {}", ValueMapper.jsonAsString(input));
		RentalAPIResponse responseDTO = new RentalAPIResponse();
		responseDTO.setFinalCharge(100);
		String val = env.getProperty("toolrental.tool.CHNS");
		System.out.println("val----->" + val);
		boolean flg = HolidayCheck.checkIfIndpDayFallsBetn(input.getCheckoutDate(), input.getRentalDays());
		if (flg) {
			Calendar cal = HolidayCheck.getJuly4HolidayDate(input.getCheckoutDate());
			System.out.println("cal----->" + cal);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

	}

}
