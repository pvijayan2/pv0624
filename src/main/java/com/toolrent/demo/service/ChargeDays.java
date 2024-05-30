package com.toolrent.demo.service;

import java.util.Date;

public record ChargeDays(Date startDate, Date endDate, boolean checkIfIndpDayFallsBetn, boolean checkIfLaborDayFallsBetn, int rentalDays, int[] weekEndDaysCnt) {

}
