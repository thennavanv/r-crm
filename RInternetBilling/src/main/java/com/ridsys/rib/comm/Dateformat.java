package com.ridsys.rib.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Dateformat {

	static SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

	public static String getCurrentDatetime() {

		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	}

	public static String expiration(int adddays) {

		return LocalDate.now().plusDays(adddays - 1).format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " 23:59:59";

	}

	public static String expiryDate(int adddays) {

		return LocalDate.now().plusDays(adddays - 1).toString() + " 23:59:59";

	}

	public static String ddmmyyyy(String date) {

		return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	}

	public static String ddmmyyyyHHmmss(String date) {

		return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	}

	public static String todayDate() {
		Date date = new Date();
		return sm.format(date);
	}

	public static String yesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return sm.format(cal.getTime());
	}

	public static String tomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		return sm.format(cal.getTime());
	}

//	public static String weekStart() {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
//		java.util.Date currentWeekStart = cal.getTime();
//		return sm.format(currentWeekStart);
//	}

	public static String weekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.add(Calendar.DATE, 6); // add 6 days after Monday
		java.util.Date currentWeekEnd = cal.getTime();
		return sm.format(currentWeekEnd);
	}

	public static String weekfirst() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		java.util.Date currentWeekstart = cal.getTime();
		return sm.format(currentWeekstart);
	}

	public static String weeklast() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		java.util.Date currentWeekstart = cal.getTime();
		return sm.format(currentWeekstart);
	}

	public static String monthStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date monthFirstDay = cal.getTime();
		return sm.format(monthFirstDay);

	}

	public static String montEnd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date monthLastDay = cal.getTime();
		return sm.format(monthLastDay);
	}

	public static String timeCalculate(String dateStart) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStop = getCurrentDatetime();

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

		if (diffInDays > 0) {
			return diffInDays + " Days";

		} else if (diffHours > 0) {
			return diffHours + " Hours";

		} else if (diffMinutes > 0) {
			return diffMinutes + " Minutes";

		} else if (diffSeconds > 0) {
			return diffSeconds + " Seconds";

		}
		return null;
	}

	public static String getNextDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return sm.format(cal.getTime()) + " 00:00:00";
	}

	public static long differentBetweenTwoDates(String date1, String date2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);

			long diff = d2.getTime() - d1.getTime();
			TimeUnit time = TimeUnit.DAYS;
			long difference = time.convert(diff, TimeUnit.MILLISECONDS);
			System.out.println("diffrence is" + difference);
			return difference;
		} catch (Exception e) {

		}
		return 0;
	}

	public static String getExpiryDateFomat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("%D %M %Y");
		Date d1 = sdf.parse(date);
		System.out.println("formated date is=="+d1);
		return sdf.format(d1);
	}
}
