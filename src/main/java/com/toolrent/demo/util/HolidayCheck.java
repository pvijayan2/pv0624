package com.toolrent.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolidayCheck {

	/*
	 * This method checks if July4th falls in between the checkout and checkin days
	 */
	public static boolean checkIfIndpDayFallsBetn(Date startDate, Date endDate) {
		boolean flg = false;
		try {
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(startDate);
			log.debug("startDate----> " + startDate);

			Calendar july4thCalendar = Calendar.getInstance();
			int year = startCalendarDate.get(Calendar.YEAR);
			july4thCalendar.set(year, Calendar.JULY, 4);
			Date July4Date = july4thCalendar.getTime();

			boolean isRange = July4Date.compareTo(startDate) >= 0 && July4Date.compareTo(endDate) <= 0;

			if (isRange) {
				log.debug("July 4th falls.");
				flg = true;
			} else {
				log.debug("July 4th does not fall");
			}

		} catch (Exception e) {
			log.debug("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}
		return flg;
	}

	/*
	 * This method determines which day has to be considered as holiday for July 4th
	 */
	public static Date getJuly4HolidayDate(String checkoutDate) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		Calendar calendar = Calendar.getInstance();
		try {
			Date startDate = format.parse(checkoutDate);
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(startDate);
			log.debug("startCalendarDate----->" + startCalendarDate);

			Calendar july4thCalendar = Calendar.getInstance();
			int year = startCalendarDate.get(Calendar.YEAR);
			july4thCalendar.set(year, Calendar.JULY, 4);
			// Date July4Date = july4thCalendar.getTime();
			log.debug("year----->" + year);
			log.debug("july4thCalendar----->" + july4thCalendar);

			// Get the day of the week
			int dayOfWeek = july4thCalendar.get(Calendar.DAY_OF_WEEK);
			log.debug("dayOfWeek----->" + dayOfWeek);

			// Map the day of the week to its corresponding name
			String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

			// Print the result
			String july4Day = daysOfWeek[dayOfWeek - 1];
			log.debug("july4Day---> " + july4Day);

			Calendar calendar2 = Calendar.getInstance();
			calendar = switch (july4Day) {
			case "Sunday" -> {
				calendar2.set(year, Calendar.JULY, 5);
				yield calendar2;
			}
			case "Saturday" -> {
				calendar2.set(year, Calendar.JULY, 3);
				yield calendar2;
			}
			default -> {
				yield july4thCalendar;
			}
			};

		} catch (ParseException e) {
			log.debug("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.debug("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}

		Date finalHolidayDate = calendar.getTime();
		log.debug("finalHolidayDate---> " + finalHolidayDate);
		return finalHolidayDate;
	}

	/*
	 * This method checks if labor day falls in between the checkout and checkin
	 * days
	 */
	public static boolean checkIfLaborDayFallsBetn(Date startDate, Date endDate) {
		boolean flg = false;
		try {
			Date laborDayDate = getLaborHolidayDate(startDate);
			boolean isInRange = laborDayDate.compareTo(startDate) >= 0 && laborDayDate.compareTo(endDate) <= 0;
			if (isInRange) {
				log.debug("Labor day falls.");
				flg = true;
			} else {
				log.debug("Labor day does not fall");
			}

		} catch (Exception e) {
			log.debug("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}
		return flg;
	}

	/*
	 * Get the labor holiday date
	 */
	public static Date getLaborHolidayDate(Date startDate) {
		// SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		Calendar calendar = Calendar.getInstance();
		try {
			// Date startDate = format.parse(checkoutDate);
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(startDate);
			int year = startCalendarDate.get(Calendar.YEAR);

			// Create a Calendar instance and set it to January 1st of the specified year
			calendar.set(year, Calendar.JANUARY, 1);

			// Find the first Monday of September in the specified year
			while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY
					|| calendar.get(Calendar.MONTH) != Calendar.SEPTEMBER) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}

			// Print the date of Labor Day
			log.debug("Labor Day in " + year + " falls on: " + calendar.getTime());

		} catch (Exception e) {
			log.debug("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}
		Date laborHolidayDate = calendar.getTime();
		log.debug("laborHolidayDate---> " + laborHolidayDate);
		return laborHolidayDate;
	}

	/*
	 * Get the weekend counts
	 */
	public static int[] checkIfWeekEndFallsBetn(Date startDate, Date endDate) {
		LocalDate startLocalDate = convertToLocalDate(startDate);
		LocalDate endLocalDate = convertToLocalDate(endDate);
		int saturdayCount = 0;
		int sundayCount = 0;

		LocalDate date = startLocalDate;
		while (!date.isAfter(endLocalDate)) {
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			if (dayOfWeek == DayOfWeek.SATURDAY) {
				saturdayCount++;
			} else if (dayOfWeek == DayOfWeek.SUNDAY) {
				sundayCount++;
			}
			date = date.plusDays(1);
		}

		return new int[] { saturdayCount, sundayCount };
	}
	
	public static LocalDate convertToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                      .atZone(ZoneId.systemDefault())
                      .toLocalDate();
    }

}
