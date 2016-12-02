package com.hadoop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 */

public class DateUtil {
	/**
	 * 获得中文星期
	 * 
	 * @param dayofweek
	 *            星期数：1-7
	 * @return
	 */
	public static String getCapitalWeek(int dayofweek) {
		if (dayofweek > 7)
			dayofweek = 7;
		else if (dayofweek < 1)
			dayofweek = 1;
		String[] week = { "", "天", "一", "二", "三", "四", "五", "六" };
		return "星期" + week[dayofweek];
	}

	/**
	 * 判断年份是否为闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式，默认为yyyy-Mm-dd HH:mm:ss
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null)
			return "";
		if (pattern == null || "".equals(pattern.trim())) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * yyyy-Mm-dd HH:mm:ss格式化日期
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String longFormat(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * yyyy-MM-dd格式化日期
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String shortFormat(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 将字符串解析成日期
	 * 
	 * @param source
	 *            日期字符串
	 * @param format
	 *            日期格式，默认格式：yyyy-MM-dd
	 * @return
	 */
	public static Date parse(String source, String format) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (format != null)
			df.applyPattern(format);
		if (source == null) {
			return null;
		}
		try {
			return df.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串解析中日期，默认格式：yyyy-MM-dd
	 * 
	 * @param source
	 * @return
	 */
	public static Date shortParse(String source) {
		return parse(source, "yyyy-MM-dd");
	}

	/**
	 * 将字符串解析中日期，默认格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param source
	 * @return
	 */
	public static Date longParse(String source) {
		return parse(source, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得两个日期之间的月份数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR)
				- startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH)
				- startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	/**
	 * 获得两个日期之间的天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(String startDate, String endDate) {
		Date sDate = DateUtil.shortParse(startDate);
		Date eDate = DateUtil.shortParse(endDate);
		return getDaysBetween(sDate, eDate);
	}
	
	/**
	 * 获得两个日期之间的天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(Date startDate, Date endDate){
		Calendar d1 = new GregorianCalendar();
		d1.setTime(startDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(endDate);
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}

		return days;
	}

	/**
	 * 判断d1是否在d2之后的日期，不考虑时间
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean after(Date d1, Date d2) {
		Calendar c1 = new GregorianCalendar();
		c1.setTime(d1);
		Calendar c2 = new GregorianCalendar();
		c2.setTime(d2);
		if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
			return true;
		} else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH)) {
			return true;
		} else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DATE) > c2.get(Calendar.DATE)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断d1是否在d2之前的日期，不考虑时间
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean before(Date d1, Date d2) {
		Calendar c1 = new GregorianCalendar();
		c1.setTime(d1);
		Calendar c2 = new GregorianCalendar();
		c2.setTime(d2);
		if (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)) {
			return true;
		} else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH)) {
			return true;
		} else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DATE) < c2.get(Calendar.DATE)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个日期是否同一天，不考虑时间
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSame(Date d1, Date d2) {
		Calendar c1 = new GregorianCalendar();
		c1.setTime(d1);
		Calendar c2 = new GregorianCalendar();
		c2.setTime(d2);

		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DATE) == c2.get(Calendar.DATE)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为日期时间类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDateTime(String str) {
		if (str == null) {
			return true;
		}
		if ("".equals(str.trim())) {
			return false;
		}
		// 2015-01-9 12:33:20
		return str
				.matches("^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}\\s*(0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$");
	}

	/**
	 * 判断日期是否为指定日期所在的月份的最后一天
	 * @param date
	 * @return
	 */
	public static boolean isLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DATE);
		int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return d == day;
	}

	/**
	 * 获得指定日期的相同月份的第一天
	 * 
	 * @param date
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}

	/**
	 * 获得指定日期所在的季度的第一天
	 * 
	 * @param date
	 */
	public static Date getFirstDateOfQuarter(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;// 获得月份
		if (month >= 1 && month <= 3) {// 第一季度
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DATE, 1);
			return c.getTime();
		} else if (month >= 4 && month <= 6) {// 第二季度
			c.set(Calendar.MONTH, 3);
			c.set(Calendar.DATE, 1);
			return c.getTime();
		} else if (month >= 7 && month <= 9) {// 第三季度
			c.set(Calendar.MONTH, 6);
			c.set(Calendar.DATE, 1);
			return c.getTime();
		} else {
			c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			return c.getTime();
		}
	}
	
	/**
	 * 获得指定日期所在的季度的最一天
	 * 
	 * @param date
	 */
	public static Date getLastDateOfQuarter(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;// 获得月份
		if (month >= 1 && month <= 3) {// 第一季度
			c.set(Calendar.MONTH, 2);
			c.set(Calendar.DATE, 31);
			return c.getTime();
		} else if (month >= 4 && month <= 6) {// 第二季度
			c.set(Calendar.MONTH, 5);
			c.set(Calendar.DATE, 30);
			return c.getTime();
		} else if (month >= 7 && month <= 9) {// 第三季度
			c.set(Calendar.MONTH, 8);
			c.set(Calendar.DATE, 30);
			return c.getTime();
		} else {
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 31);
			return c.getTime();
		}
	}

	/**
	 * 获得指定日期的相同年份的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}
	
	/**
	 * 判断日期是否为1900
	 * @param date
	 * @return
	 */
	public static boolean is1900Date(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);	//年
		int month = c.get(Calendar.MONTH)+1;//月
		int d = c.get(Calendar.DATE);//日
		if(year == 1900 && month == 1 && d == 1){
			return true;
		}
		return false;
	}
	
	/**
	 * 计算几天后的日期
	 * @param startDate 开始日期
	 * @param n 天数
	 * @return
	 */
	public static Date add(Date startDate, int n){
		if(n == 0) return startDate;
		if(startDate == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, n-1);
		return c.getTime();
	}
	
	public static Date getLastDateOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
		return c.getTime();
	}
}
