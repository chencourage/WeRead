package com.weread.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * @author lisheng
 * @date 2019年1月12日
 */
public class DateUtil {
	
	public static final String SENC_DAY_START= " 00:00:00";
	public static final String SENC_DAY_END = " 23:59:59";
	public static final String DAY_START= " 00:00:00.000";
	public static final String DAY_END = " 23:59:59.999";
	public static final String DAY_PATTERN = "yyyy-MM-dd";
	public static final String SENCOND_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String MILLIS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String YEAR2_SENCOND_PATTERN = "yy-MM-dd HH:mm:ss";
	
	/**
	 * 精确到毫秒
	 * @param expire 向后推迟时间
	 * @param tu 单位
	 * @return
	 */
	public static Date getExpiryDate(long expire, TimeUnit tu) {
		return new Date(System.currentTimeMillis() + tu.toMillis(expire));
	}
	
	/**
	 * 精确到毫秒
	 * @param baseTime 基准日期
	 * @param expire 向后推迟时间
	 * @param tu 单位
	 * @return
	 */
	public static Date getExpiryDate(Date baseTime, long expire, TimeUnit tu) {
		return new Date(baseTime.getTime() + tu.toMillis(expire));
	}
	
	/**
	 * 判断日期是否合法
	 * @param text		日期字符串
	 * @param pattern	日期格式
	 * @return
	 */
	public static boolean isValidDate(String text, String pattern) {
		if (null == text || null == pattern || text.length() != pattern.length()) {
			return false;
		}
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			format.setLenient(false);
			format.parse(text);
		} catch (Exception e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
	
	/**
	 * 日期转换
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static Date parse(String text, String pattern) {
		return parse(text, pattern, null);
	}
	
	/**
	 * 日期转换
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static Date parseWithValid(String text, String pattern) {
		if (null == text || null == pattern || text.length() != pattern.length()) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			format.setLenient(false);
			return format.parse(text);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 日期转换
	 * @param text		日期字符串
	 * @param pattern	日期格式
	 * @param defVal	默认值
	 * @return
	 */
	public static Date parse(String text, String pattern, Date defVal) {
       SimpleDateFormat format = new SimpleDateFormat(pattern);
       return parse(text, format, defVal);
	}
	
	/**
	 * 日期转换
	 * @param text		日期字符串
	 * @param format	日期格式
	 * @param defVal	默认值
	 * @return
	 */
	public static Date parse(String text, SimpleDateFormat format, Date defVal) {
       try {
          return format.parse(text);
       } catch (Exception e) {
       } 
       return defVal;
	}
	
	/**
	 * 获取开始时间
	 * @param date
	 * @return
	 */
	public static Date getDayStart(Date date) {
		if(null == date)
			date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(DAY_PATTERN);
		String dayHour = format.format(date);
		String dayMillis = dayHour + DAY_START;
		SimpleDateFormat format2 = new SimpleDateFormat(MILLIS_PATTERN);
		try {
			return format2.parse(dayMillis);
		} catch (ParseException e) {
		}
		return null;
	}
	
	/**
	 * 获取结束时间
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		if(null == date)
			date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(DAY_PATTERN);
		String dayHour = format.format(date);
		String dayMillis = dayHour + DAY_END;
		SimpleDateFormat format2 = new SimpleDateFormat(MILLIS_PATTERN);
		try {
			return format2.parse(dayMillis);
		} catch (ParseException e) {
		}
		return null;
	}
	
	/**
	 * 日期格式化
	 * @param date		日期
	 * @param pattern	日期格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return format(date, pattern, "");
	}
	
	/**
	 * 日期格式化
	 * @param date		日期
	 * @param pattern	日期格式
	 * @param defVal    异常时，默认返回值
	 * @return
	 */
	public static String format(Date date, String pattern, String defVal) {
		try {
			return format(date, new SimpleDateFormat(pattern), defVal);
		} catch (Exception e) {
			return defVal;
		}
	}
	
	/**
	 * 日期格式化
	 * @param date		日期
	 * @param format	日期格式
	 * @return
	 */
	public static String format(Date date, SimpleDateFormat format) {
       return format(date, format, "");
	}
	
	/**
	 * 日期格式化
	 * @param date		日期
	 * @param format	日期格式
	 * @param defVal    异常时，默认返回值
	 * @return
	 */
	public static String format(Date date, SimpleDateFormat format, String defVal) {
		if(null == date || null == format) {
			return defVal;
		}
		try {
			return format.format(date);
		} catch (Exception e) {
			return defVal;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String costTime(Date startTime,Date endTime) {
		if(null==startTime||endTime==null) {
			return "0秒";
		}
		long start = startTime.getTime();
		long end = endTime.getTime();
		
		StringBuilder resp = new StringBuilder();
		String[] unit = new String[] {"秒", "分", "小时", "天"};
		long[] radix = new long[] {60, 60, 24};
		long residue=0,quotien=end-start; //余数 商
		int i=0;
		do {
			residue = quotien % radix[i];
			quotien = quotien / radix[i];
			resp.insert(0, unit[i]).insert(0, residue);
			i++;
		} while(i < radix.length && quotien > 0);
		if(quotien > 0) {
			resp.insert(0, unit[i]).insert(0, quotien);
		}
		return resp.toString();
	}
	
	public static Date getNextDay() {
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,1);//1表示明天,-1表示昨天
		calendar.set(calendar.HOUR_OF_DAY,0);
        calendar.set(calendar.MINUTE,0);
        calendar.set(calendar.SECOND,0);
        calendar.set(calendar.MILLISECOND,0);
		date=calendar.getTime(); //这个时间就是明天
		return date;
	}
	
	public static String getNextDays() {
		String date = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault()).format(Instant.now().plus(1, ChronoUnit.DAYS));
		return date;
	}
	
	public static void main(String[] args) {
		System.out.println(format(getNextDay(),"yyyy-MM-dd"));
		System.out.println(getNextDays());
	}
}
