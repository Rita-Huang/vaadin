package demo.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Miscellaneous date time utility methods. 
 * <p>This class delivers some simple functionalities that should really
 * be provided by the core Java Calendar, Date, Timestamp and Time classes.
 * @author Ben Wang
 */
public class DateUtils {
	/**日期格式yyyy/MM/dd**/
	public static final String FMT_YYYYMMDD_SLASH 	   	= "yyyy/MM/dd";
	/**日期格式yyyy-MM-dd**/
	public static final String FMT_YYYYMMDD_DASH 	   	= "yyyy-MM-dd";
	/**日期格式/MM/dd/yyyy**/
	public static final String FMT_MMDDYYYY_SLASH 	   	= "MM/dd/yyyy";
	/**日期格式yyyy**/
	public static final String FMT_YYYY                 = "yyyy";
	/**日期格式yyyyMM**/
	public static final String FMT_YYYYMM				= "yyyyMM";
	/**日期格式MM/dd**/
	public static final String FMT_MMDD_SLASH			= "MM/dd";
	/**日期格式yyyyMMdd**/
	public static final String FMT_YYYYMMDD 	    	= "yyyyMMdd";
	/**日期格式yyyyMMddHHmmss**/
	public static final String FMT_YYYYMMDDHHMMSS  		= "yyyyMMddHHmmss";
	/**日期格式yyyyMMddHHmmssSSS**/
	public static final String FMT_YYYYMMDDHHMMSSSSS  	= "yyyyMMddHHmmssSSS";
	/**日期格式yyyyMMdd HH:mm:SS**/
	public static final String FMT_YYYYMMDDHHMMSS_SPACE = "yyyyMMDD HH:mm:SS";
	/**日期格式yyyy/MM/dd HH:mm**/
	public static final String FMT_YYYYMMDDHHMM_SLASH  	= "yyyy/MM/dd HH:mm";
	/**日期格式yyyy-MM-dd HH:mm**/
	public static final String FMT_YYYYMMDDHHMM_DASH  	= "yyyy-MM-dd HH:mm";
	/**日期格式yyyy/MM/dd HH:mm:ss**/
	public static final String FMT_YYYYMMDDHHMMSS_SLASH = "yyyy/MM/dd HH:mm:ss";
	/**日期格式yyyy-MM-dd HH:mm:ss**/
	public static final String FMT_YYYYMMDDHHMMSS_DASH  = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Field number for get and set indicating the day of the month.
	 */
	public static final int DAY							= Calendar.DAY_OF_MONTH;
	/**
	 * Field number for get and set indicating the hour of the day.
	 */
	public static final int HOUR						= Calendar.HOUR_OF_DAY;
	/**
	 * Field number for get and set indicating the minute within the hour.
	 */
	public static final int MINUTE						= Calendar.MINUTE;
	/**
	 * Field number for get and set indicating the month.
	 */
	public static final int MONTH						= Calendar.MONTH;
	/**
	 * Field number for get and set indicating the second within the minute.
	 */
	public static final int SECOND						= Calendar.SECOND;
	/**
	 * Field number for get and set indicating the year.
	 */
	public static final int YEAR						= Calendar.YEAR;
	
	/**
	 * Adds or subtracts the specified amount of time to the given calendar fields year, month and day,  based on the calendar's rules.
	 * <p>For example, to subtract 5 days from the current time of the calendar, you can achieve it by calling:
	 * <pre>DateUtils.addCalendar(null, 0, 0, -5)</pre>
	 * @param calendar the given calendar. If its value is null means it is current calendar.
	 * @param year the amount of date to be added to the year field.
	 * @param month the amount of date to be added to the month field.
	 * @param day the amount of date to be added to the day field.
	 * @return a Date
	 */
	public static Date addCalendar(Calendar calendar, int year, int month, int day){
		if (calendar == null) calendar = GregorianCalendar.getInstance();
		if (year != 0) calendar.add(Calendar.YEAR, year);
		if (month != 0) calendar.add(Calendar.MONTH, month);
		if (day != 0) calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	/**
	 * Adds or subtracts the specified amount of time to the given calendar fields year, month and day,  based on the calendar's rules.
	 * <p>For example, to subtract 5 days from the current date, you can achieve it by calling:
	 * <pre>DateUtils.addCalendar(null, 0, 0, -5)</pre>
	 * @param date the given date. If its value is null means it is current date.
	 * @param year the amount of date to be added to the year field.
	 * @param month the amount of date to be added to the month field.
	 * @param day the amount of date to be added to the day field.
	 * @return a Date
	 */
	public static Date addCalendar(Date date, int year, int month, int day) {
		Calendar cal = GregorianCalendar.getInstance();
		if (date != null) cal.setTime(date);
		return addCalendar(cal, year, month, day);
	}
	
	/**
	 * 根據特定格式格式化日期
	 * @param date 被格式化的日期
	 * @param format 格式
	 * @return 格式化後的字串
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * Returns the value of the given calendar field.This method throws an exception if any
	 * calendar fields have out-of-range values.
	 * @param date The given specific date. If its value is null, means it is current date.
	 * @param field the given calendar field.
	 * @return the value for the given calendar field.
	 * <p>The month is between 1-12, the day of the month is between 1-31,
	 * <p>the hours is between 0-23, the minutes is between 0-59, the seconds is between 0-59.
	 * <p>If the return value is -1 means the specified field is out of range.
	 */
	public static int getCalendar(Date date, int field) {
		try {
			Calendar cal = GregorianCalendar.getInstance();
			if (date != null) cal.setTime(date);
			if (field == MONTH) return cal.get(Calendar.MONTH) + 1;
			else return cal.get(field);
		}catch(Exception e) {
			return -1;
		}
	}
	
	/**
	 * Returns the value of the given current calendar field
	 * @param field the given calendar field
	 * @return the value for the given calendar field. If the return value is -1 means the specified field is out of range.
	 */
	public static int getCurrentCalendar(int field) {
		return getCalendar(null, field);
	}
	
	/**
	 * Return the current system date. It doesn't include time.
	 * @return the value of Date.
	 */	
	public static final Date getCurrentDate() {
		Calendar cal = GregorianCalendar.getInstance();
		truncateTime(cal);
		Date current = cal.getTime();
		return current;
	}
	
	/**
	 * Return the current system time.
	 * @return the value of Time.
	 */
	public static final Time getCurrentTime() {
		Date date = new Date();
		return getTime(date);
	}
	
	/**
	 * Return the current system timestamp.
	 * @return the value of Timestamp.
	 */
	public static final Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * Return the days of the given specific year and month. 
	 * @param year the specific year.
	 * @param month the specific month
	 * @return the value of days. If its value is -1 means the specified year or month is out of range.
	 */
	public static int getDaysOfMonth(int year, int month) {
		if (month < 1 || month > 12) return -1;
		
		int secondMonth = 28;
		
		if ((year % 4) == 0) {
			if ((year % 100) == 0 && (year % 400) != 0) secondMonth = 28;
			else secondMonth = 29;
		}else secondMonth = 28;
			
		int[] dayOfMonth = {31, secondMonth, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			
		return dayOfMonth[month-1];				
	}
	
	/**
	 * Return the time of the given specific date. If its value is null means it is current date.
	 * @param date The given specific date.
	 * @return the value of Time.
	 */	
	public static Time getTime(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		if ( date != null) cal.setTime(date);
		
		int hour 	= cal.get(Calendar.HOUR_OF_DAY);
		int minute 	= cal.get(Calendar.MINUTE);
		int second 	= cal.get(Calendar.SECOND);
		
		return Time.valueOf(hour+":"+minute+":"+second);
	}
	
	/**
	 * Return the time of the given specific timestamp. If its value is null means it is current timestamp.
	 * @param timestamp The given specific timestamp.
	 * @return the value of Time.
	 */	
	public static Time getTime(Timestamp timestamp) {
		Date date = (timestamp == null) ? null : new Date(timestamp.getTime());
		return getTime(date);
	}
	
	/**
	 * Check whether the given date is current date or not.
	 * @param date the given Date.
	 * @return the value of boolean.
	 */
	public static boolean isToday(Date date) {
		if (date == null) return false;
		Date currentDate = getCurrentDate();
		Date otherDate = truncateTime(date);
		return otherDate.equals(currentDate);
	}
	
	/**
	 * Check whether the given timestamp is current date or not.
	 * @param timestamp the given timestamp
	 * @return the value of boolean.
	 */
	public static boolean isToday(Timestamp timestamp) {
		if (timestamp == null) return false;
		return isToday(new Date(timestamp.getTime()));
	}
	
	/**
	 * Check whether the current date is a weekly holiday or not.
	 * @return the value of boolean is a weekly holiday or not.
	 */	
	public static boolean isWeeklyHoliday() {
		return isWeeklyHoliday(null);
	}
	
	/**
	 * Check whether the given specific date is a weekly holiday or not. If the given date is null means it is current date.
	 * @param date the given specific date.
	 * @return the value of boolean is a weekly holiday or not.
	 */	
	public static boolean isWeeklyHoliday(Date date) {
		int dayOfWeek = getCalendar(date, Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7) return true;
		else return false;
	}
	
	/**
	 * Parses text from the beginning of the given string to produce a date.
	 * The method may not use the entire text of the given string.
	 * @param date A String whose beginning should be parsed.
	 * @param pattern the pattern describing the date and time format 
	 * @return A Date parsed from the string.
	 * @throws ParseException if the beginning of the specified string cannot be parsed.
	 */
	public static Date parseDate(String date, String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(date);
	}
	
	/**
	 * Set a specific time to the given specific date.
	 * @param date the given specific date. If its value is null means it is current date.
	 * @param hours the hours between 0-23.
	 * @param mins the minutes between 0-59.
	 * @param secs the seconds between 0-59.
	 * @return a Date includes time.
	 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
	 */
	public static Date toDate(Date date, int hours, int mins, int secs) throws IllegalArgumentException{
		if (hours < 0 || hours > 23 || mins < 0 || mins > 59 || secs < 0 || secs > 59) throw new  IllegalArgumentException();
		
		Calendar cal = GregorianCalendar.getInstance();
		
		if ( date != null) cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, mins);
		cal.set(Calendar.SECOND, secs);
		
		return cal.getTime();
	}
	
	/**
	 * Return the Date of the given year, month, day, hours, minutes and seconds.
	 * @param year the year minus 1900.
	 * @param month the month between 1-12.
	 * @param day the day of the month between 1-31.
	 * @param hours the hours between 0-23.
	 * @param mins the minutes between 0-59.
	 * @param secs the seconds between 0-59.
	 * @return a Date.
	 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
	 */
	public static Date toDate(int year, int month, int day, int hours, int mins, int secs) throws IllegalArgumentException{
		if (month < 1 || month > 12 ||
			day < 1 || day > 31 ||
			hours < 0 || hours > 23 ||
			mins < 0 || mins > 59 ||
			secs < 0 || secs > 59) throw new  IllegalArgumentException();
		
		Calendar cal = new GregorianCalendar(year, month - 1, day, hours, mins, secs);
		return cal.getTime();
	}
	
	/**
	 * Return a Date for the given Timestamp.
	 * @param timestamp the value of the given timestamp.
	 * @return a Date. It is not include time.
	 */
	public static Date toDate(Timestamp timestamp) {
		if (timestamp == null) return null;
		return truncateTime(new Date(timestamp.getTime()));
	}
	
	/**
	 * Truncate the time of the given Calendar.
	 * @param calendar the given Calendar.
	 */
	public static void truncateTime(Calendar calendar) {
		if (calendar == null) return;
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	
	/**
	 * Truncate the time of the given Date.
	 * @param date the given Date
	 * @return the truncated Date
	 */
	public static Date truncateTime(Date date) {
		if (date == null) return null;
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		truncateTime(cal);
		return cal.getTime();
	}
	
	/**
	* 計算兩個日期的時間差
	* @param startTime
	* @param endTime
	* @param isChineseDate
	* @return String
	*/
	public static String getTimeDifference(Date startTime, Date endTime, boolean isChineseDate) {
        long diff = endTime.getTime() - startTime.getTime();
        long diffMilliSeconds = TimeUnit.MILLISECONDS.toMillis(diff) % 1000;
		long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
		long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
		long diffHours = TimeUnit.MILLISECONDS.toHours(diff) % 24;
		long diffDays = TimeUnit.MILLISECONDS.toDays(diff);
		String pattern = "%d days, %d hours, %d minutes, %d seconds %d.";
		if(isChineseDate) {
			pattern = "%d 天  %d 時  %d 分  %d 秒   %d";
		}
		return String.format(pattern, diffDays, diffHours, diffMinutes, diffSeconds, diffMilliSeconds);
	}
	/**
	 * Purpose:取得當前日期相差幾個月的時間
	 * @param iMonth:當前日期相差月數，+代表后幾個月，-代表前幾個月
	 * @return Date:返回日期
	 */
	public static Date getMonthDateAgo(int iMonth){
		Calendar calendar = GregorianCalendar.getInstance();
		truncateTime(calendar);
		calendar.add(Calendar.MONTH, iMonth);
		Date agoDate = calendar.getTime();
		return agoDate;
	}
	
	/**
	 * Purpose:取得當前月月初日期
	 * @return Date:月初日期
	 */
	public static Date getCurrentMonthStartDate() {
		Calendar calendar = Calendar.getInstance();
        truncateTime(calendar);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
        return calendar.getTime();
    }
	/**
	 * Purpose:取得當前月月末日期
	 * @return Date:月末日期
	 */
	public static Date getCurrentMonthEndDate() {
		Calendar calendar = Calendar.getInstance();
        truncateTime(calendar);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return calendar.getTime();
	}
	
}
