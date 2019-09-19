package com.adegas.twittertest.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TwitterTestUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295243624737996192L;

	public static Date toDate(LocalDateTime dateTime) {
		return Date.from(dateTime.atZone(ZoneOffset.systemDefault()).toInstant());
	}
	
	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.systemDefault());
	}
	
	public static LocalDateTime convertDateAndTruncate(Date date) {
		LocalDateTime dateTime = toLocalDateTime(date);
	
		return dateTime.truncatedTo(ChronoUnit.HOURS);
	}
	
	public static String convertDateAndReturnHour(Date date, TimeZone timezone) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		DateFormat formatter = new SimpleDateFormat("HH");
		return formatter.format(cal.getTime());
	}
}