package com.toolrent.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.toolrent.demo.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RentalUtil {

	/*
	 * Format date to mm/dd/yy
	 */
	public static String formatDateToMMDDYY(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		return dateFormat.format(date);
	}
	
	/*
	 * Calculates the due date based on the rental days
	 */
	public static Date calculateDueDate(Date startDate, int rentalDays) {
		Date endDate = new Date();
		try {
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(startDate);
			log.debug("startDate----> " + startDate);

			Calendar endCalendarDate = Calendar.getInstance();
			endCalendarDate = startCalendarDate;
			endCalendarDate.add(Calendar.DATE, rentalDays);
			endDate = endCalendarDate.getTime();
			System.out.println("Date after adding rentalDays days----> " + endDate);
		} catch (Exception e) {
			throw new ServiceException("Issue with Due Date calculation", "Date Parsing Error", 120, "UE 189");
		}
		return endDate;
	}

}
