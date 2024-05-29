package com.toolrent.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HolidayCheck {

	public static boolean checkIfIndpDayFallsBetn(String checkoutDate, int rentalDays) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		boolean flg = false;
		try {
			Date startDate = format.parse(checkoutDate);
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(startDate);
			System.out.println("startDate----> " + startDate);

			Calendar endCalendarDate = Calendar.getInstance();
			endCalendarDate = startCalendarDate;
			endCalendarDate.add(Calendar.DATE, 5); // Adding 5 days
			Date endDate = endCalendarDate.getTime();
			System.out.println("Date after adding rentalDays days----> " + endDate);

			Calendar july4thCalendar = Calendar.getInstance();
			int year = startCalendarDate.get(Calendar.YEAR);
			july4thCalendar.set(year, Calendar.JULY, 4);
			Date July4Date = july4thCalendar.getTime();

			boolean isRange = July4Date.compareTo(startDate) >= 0 && July4Date.compareTo(endDate) <= 0;

			if (isRange) {
				System.out.println("July 4th falls.");
				flg = true;
			} else {
				System.out.println("July 4th does not fall");
			}

		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}
		return flg;
	}

	public static Date getJuly4HolidayDate(String checkoutDate) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		Calendar calendar = Calendar.getInstance();
		try {
			Date startDate = format.parse(checkoutDate);
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(startDate);
			System.out.println("startCalendarDate----->" + startCalendarDate);

			Calendar july4thCalendar = Calendar.getInstance();
			int year = startCalendarDate.get(Calendar.YEAR);
			july4thCalendar.set(year, Calendar.JULY, 4);
			// Date July4Date = july4thCalendar.getTime();
			System.out.println("year----->" + year);
			System.out.println("july4thCalendar----->" + july4thCalendar);

			// Get the day of the week
			int dayOfWeek = july4thCalendar.get(Calendar.DAY_OF_WEEK);
			System.out.println("dayOfWeek----->" + dayOfWeek);

			// Map the day of the week to its corresponding name
			String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

			// Print the result
			String july4Day = daysOfWeek[dayOfWeek - 1];
			System.out.println("july4Day---> " + july4Day);

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
			System.out.println("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}

		Date finalHolidayDate = calendar.getTime();
		System.out.println("finalHolidayDate---> " + finalHolidayDate);
		return finalHolidayDate;
	}

}