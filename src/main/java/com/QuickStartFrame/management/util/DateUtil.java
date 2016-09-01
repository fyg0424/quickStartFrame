package com.QuickStartFrame.management.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {
	private static final Logger logger = Logger.getLogger(DateUtil.class);
	
	private static final String[] WEEKDAYS_CN = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	
	private static final String[] WEEKDAYS_EN = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
	
	public static String getENWeekDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return WEEKDAYS_EN[w];
	}
	
	public static String getCNWeekDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return WEEKDAYS_CN[w];
	}
	
	public static String getCurrentHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		return String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
	}
	
	public static String getBeforeOneHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		return String.valueOf(cal.get(Calendar.HOUR_OF_DAY) == 0 
				? 23 : cal.get(Calendar.HOUR_OF_DAY) - 1);
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
		return format;
	}
	
	public static int computeElapsetime(String departureTime, String arrivalTime) {
		int elapsetime = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date departureDate = sdf.parse(departureTime);
			Date arrivalDate = sdf.parse(arrivalTime);
			
			elapsetime = (int)(arrivalDate.getTime() - departureDate.getTime()) / (1000 * 60);
		} catch (Exception e) {
			logger.warn("时间转换异常", e);
		}
		
		return elapsetime;
	}
	
	public static void main(String[] args) {
		String departureTime = "2016-03-27 12:00:00:00";
		String arrivalTime = "2016-03-27 15:10:00:12";
		System.out.println(computeElapsetime(departureTime, arrivalTime));
	}
}
