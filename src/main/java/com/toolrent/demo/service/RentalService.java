package com.toolrent.demo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.toolrent.demo.dto.RentalAPIResponse;
import com.toolrent.demo.exception.ServiceException;
import com.toolrent.demo.model.RentalRequest;
import com.toolrent.demo.util.HolidayCheck;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RentalService {

	@Autowired
	Environment env;

	public RentalAPIResponse process(RentalRequest rentalRequest) {

		log.debug("Entering RentalService : RentalAPIResponse:process--->");
		log.debug("rentalRequest in service--->" + rentalRequest);
		RentalAPIResponse rentalAPIResponse = new RentalAPIResponse();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		try {
			Date startDate = format.parse(rentalRequest.getCheckoutDate());
			Date endDate = calculateDueDate(startDate, rentalRequest.getRentalDays());
			System.out.println("Due date------>" + endDate);

			boolean checkIfIndpDayFallsBetn = HolidayCheck.checkIfIndpDayFallsBetn(startDate, endDate);
			System.out.println("checkIfIndpDayFallsBetn------>" + checkIfIndpDayFallsBetn);

			boolean checkIfLaborDayFallsBetn = HolidayCheck.checkIfLaborDayFallsBetn(startDate, endDate);
			System.out.println("checkIfLaborDayFallsBetn------>" + checkIfLaborDayFallsBetn);

			int[] weekEndDaysCnt = HolidayCheck.checkIfWeekEndFallsBetn(startDate, endDate);

			ChargeDays chargeDays = new ChargeDays(startDate, endDate, checkIfIndpDayFallsBetn,
					checkIfLaborDayFallsBetn, rentalRequest.getRentalDays(), weekEndDaysCnt);

			double numOfChargeDays = calculateChargeDays(chargeDays, rentalRequest);

		} catch (Exception e) {
			throw new ServiceException("Issue with RentalService", "process method", 112, "UE 112");
		}
		log.debug("<---Exiting RentalService : RentalAPIResponse:process");
		return rentalAPIResponse;
	}

	private double calculateChargeDays(ChargeDays chargeDays, RentalRequest rentalRequest) {
		int rentalDays = chargeDays.rentalDays();
		double totalCharge = 0;
		try {
			String toolNameBrand = env.getProperty("toolrental.tool." + rentalRequest.getToolCode());
			System.out.println("toolNameBrand--->" + toolNameBrand);
			String[] stArray = toolNameBrand.split("_");
			String name = stArray[0];
			String brand = stArray[1];
			System.out.println("name--->" + name);
			System.out.println("brand--->" + brand);

			String dailyCharge = env.getProperty("toolrental.tooldailycharge.daily." + name);
			System.out.println("dailyCharge--->" + dailyCharge);
			String weekDayChargeFlg = env.getProperty("toolrental.tooldailycharge.weekday." + name);
			String weekEndChargeFlg = env.getProperty("toolrental.tooldailycharge.weekend." + name);
			String holidayChargeFlg = env.getProperty("toolrental.tooldailycharge.holiday." + name);

			// count the number of holidays
			int holidayCount = 0;
			if (chargeDays.checkIfIndpDayFallsBetn())
				holidayCount++;
			if (chargeDays.checkIfLaborDayFallsBetn())
				holidayCount++;
			System.out.println("holidayCount--->" + holidayCount);

			// count weekend days
			int weekendDays = 0;
			int[] weekEndDaysCnt = chargeDays.weekEndDaysCnt();
			int saturdayCount = weekEndDaysCnt[0];
			int sundayCount = weekEndDaysCnt[1];
			System.out.println("saturdayCount------>" + saturdayCount);
			System.out.println("sundayCount------>" + sundayCount);
			weekendDays = saturdayCount + sundayCount;
			System.out.println("weekendDays--->" + weekendDays);

			// count week days
			int weekDays = 0;
			weekDays = rentalDays - (weekendDays + holidayCount);
			System.out.println("weekDays--->" + weekDays);

			// count total number of chargeable days
			int totalChargeableDays = 0;
			if (Boolean.parseBoolean(weekDayChargeFlg)) {
				totalChargeableDays = totalChargeableDays + weekDays;
			}
			if (Boolean.parseBoolean(weekEndChargeFlg)) {
				totalChargeableDays = totalChargeableDays + weekendDays;
			}
			if (Boolean.parseBoolean(holidayChargeFlg)) {
				totalChargeableDays = totalChargeableDays + holidayCount;
			}

			System.out.println("totalChargeableDays--->" + totalChargeableDays);

			// calculate total weekday charge
			totalCharge = totalChargeableDays * Double.valueOf(dailyCharge);

			System.out.println("totalCharge--->" + totalCharge);
		} catch (Exception e) {
			throw new ServiceException("Issue with calculation", "calculateChargeDays method", 113, "UE 113");
		}

		return totalCharge;
	}

	public Date calculateDueDate(Date startDate, int rentalDays) {
		Date endDate = new Date();
		try {
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(startDate);
			log.debug("startDate----> " + startDate);

			Calendar endCalendarDate = Calendar.getInstance();
			endCalendarDate = startCalendarDate;
			endCalendarDate.add(Calendar.DATE, 5); // Adding 5 days
			endDate = endCalendarDate.getTime();
			System.out.println("Date after adding rentalDays days----> " + endDate);
		} catch (Exception e) {
			throw new ServiceException("Issue with Due Date calculation", "Date Parsing Error", 120, "UE 189");
		}
		return endDate;
	}

}
