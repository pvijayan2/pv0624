package com.toolrent.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HolidayCheck {
	
	
	public static boolean checkIfIndpDayFallsBetn(String checkoutDate, int rentalDays) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		Calendar calendar3 = Calendar.getInstance();
		boolean flg = false;
		try {
			Date date = format.parse(checkoutDate);
			System.out.println("Start Date-----> " + date);
			Calendar startCalendarDate = Calendar.getInstance();
			startCalendarDate.setTime(date);
			System.out.println("startCalendar: " + startCalendarDate.getTime());

			Calendar endCalendarDate = startCalendarDate;
			endCalendarDate.add(Calendar.DAY_OF_MONTH, rentalDays);
			System.out.println("endCalendar: " + endCalendarDate.getTime());
			String output = format.format(endCalendarDate.getTime());
			System.out.println("output-----> " + output);
			Date endDate = format.parse(output);
			System.out.println("endDate-----> " + endDate);

			Date newDate = endCalendarDate.getTime();
			System.out.println("Date after adding rentalDays days: " + newDate);

			Calendar july4thCalendar = Calendar.getInstance();
			int year = startCalendarDate.get(Calendar.YEAR);
			july4thCalendar.set(year, Calendar.JULY, 4);
			System.out.println("july4thCalendar: " + july4thCalendar.getTime());
			
			String outputJu = format.format(july4thCalendar.getTime());
			System.out.println("outputJu-----> " + outputJu);
			Date July4Date = format.parse(outputJu);
			System.out.println("July4Date-----> " + July4Date);
			
			//boolean isInRange = (july4thCalendar.compareTo(startCalendarDate) >= 0) && (july4thCalendar.compareTo(endCalendarDate) <= 0);

			boolean isRange = July4Date.compareTo(date) >= 0 && July4Date.compareTo(endDate) <= 0;
			
			// Get the time in milliseconds for the start, end, and target dates
	        long startMillis = startCalendarDate.getTimeInMillis();
	        long endMillis = endCalendarDate.getTimeInMillis();
	        long july4thMillis = july4thCalendar.getTimeInMillis();

	        // Check if July 4th, 2021 falls between the start and end dates
	        boolean isInRange = (july4thMillis >= startMillis) && (july4thMillis <= endMillis);

			
			
			
			//if (july4thCalendar.after(startCalendarDate) && july4thCalendar.before(endCalendarDate)) {
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
	
	
	public static Calendar getJuly4HolidayDate(String checkoutDate) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		Calendar calendar3 = Calendar.getInstance();
		Date date;
		try {
			date = format.parse(checkoutDate);
			System.out.println("Parsed Original Date: " + date);

			Calendar july4thCalendar = Calendar.getInstance();
			int year = date.getYear();
			july4thCalendar.set(year, Calendar.JULY, 4);

			// Get the day of the week
			int dayOfWeek = july4thCalendar.get(Calendar.DAY_OF_WEEK);

			// Map the day of the week to its corresponding name
			String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

			// Print the result
			String july4Day = daysOfWeek[dayOfWeek - 1];
			System.out.println("July 4th, 2015 falls on a " + daysOfWeek[dayOfWeek - 1]);

			Calendar calendar2 = Calendar.getInstance();
			calendar3 = switch (july4Day) {
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
			e.printStackTrace();
		}
		return calendar3;
	}

	/*public static Calendar checkIfIndpDayFallsBetn1() {

		String dateString = "9/3/15";
		int rentalDays = 5;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		Calendar calendar3 = Calendar.getInstance();
		boolean flg = false;

		try {
			Date date = format.parse(dateString);
			System.out.println("Parsed Original Date: " + date);
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(date);

			Calendar endCalendar = Calendar.getInstance();
			endCalendar.add(Calendar.DAY_OF_MONTH, rentalDays);
			Date newDate = endCalendar.getTime();
			System.out.println("Date after adding rentalDays days: " + newDate);

			Calendar july4thCalendar = Calendar.getInstance();
			int year = date.getYear();
			july4thCalendar.set(year, Calendar.JULY, 4);

			if (july4thCalendar.after(startCalendar) && july4thCalendar.before(endCalendar)) {
				System.out.println("July 4th, 2015 falls between September 3rd, 2015 and September 8th, 2015.");
				flg = true;
			} else {
				System.out.println("July 4th, 2015 does not fall between September 3rd, 2015 and September 8th, 2015.");
			}
			
			if(flg) {
				// Get the day of the week
				int dayOfWeek = july4thCalendar.get(Calendar.DAY_OF_WEEK);
				
				// Map the day of the week to its corresponding name
				String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

				// Print the result
				String july4Day = daysOfWeek[dayOfWeek - 1];
				System.out.println("July 4th, 2015 falls on a " + daysOfWeek[dayOfWeek - 1]);

				Calendar calendar2 = Calendar.getInstance();
				calendar3 = switch (july4Day) {
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
			}
		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
			e.printStackTrace();
		}
		return calendar3;

	}*/
}
