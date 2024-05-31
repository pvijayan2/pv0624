package com.toolrent.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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

}
