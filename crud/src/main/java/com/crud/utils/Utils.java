package com.crud.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static enum CustomDateFromat {
		FORMAT_CODE_01("dd/MM/yyyy");
		
		String format;
		
		CustomDateFromat(String format) {
			this.format = format;
		}
		
		public String getFormat() {
			return this.format;
		}
	}
	
	public static Date now() {
		return  new Date();
	}
	
	/**
	 * Get previous date from now to mention date
	 * 
	 * @param duration
	 * @return
	 */
	public static Date dayBeforeFromNow(int duration) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -duration);
		return cal.getTime();
	}
	
	/**
	 * Convert given string to Date object for specified date format
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String date, CustomDateFromat format) {
		 Date result = null; 
		try { 
			 result = new SimpleDateFormat(format.getFormat()).parse(date);
		 } catch (Exception e) {
			// TODO: handle exception
		}
		 return result;
	}
}
