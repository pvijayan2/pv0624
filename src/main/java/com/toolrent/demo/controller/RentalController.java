package com.toolrent.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toolrent.demo.dto.RentalAPIResponse;
import com.toolrent.demo.dto.RentalInputDTO;
import com.toolrent.demo.util.ValueMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Component

@RequestMapping("/rentalTool")
@RestController
@Slf4j
public class RentalController {

	@PostMapping("/createRentalAgreement")
	public ResponseEntity<RentalAPIResponse> createRentalAgreement(@RequestBody @Valid RentalInputDTO user,
			HttpServletRequest request) {
		log.info("UserRegisterController::registerUser request body {}", ValueMapper.jsonAsString(user));
		RentalAPIResponse responseDTO = null;
		
		return null;
	}

}
