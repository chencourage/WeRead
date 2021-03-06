package com.weread.common.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lisheng
 * @date 2019年1月11日
 */
public class NumberUtil {

	public static final String ZERO_YUAN = "0.00";

	/**
	 * 保留两位小数，默认四舍五入
	 */
	public static final DecimalFormat YUAN_FORMAT = new DecimalFormat("0.00");

	public static final BigDecimal BD_HUNDRED = new BigDecimal("100");

	/**
	 * 类型转换为BigDecimal，转换失败返回null
	 * @param val
	 * @return
	 */
	public static BigDecimal toBigDecimal(Object val) {
		return toBigDecimal(val, null);
	}

	/**
	 * 类型转换为BigDecimal，转换失败返回defVal
	 * @param val
	 * @return
	 */
	public static BigDecimal toBigDecimal(Object val, BigDecimal defVal) {
		if(null == val) {
			return defVal;
		}
		if(val instanceof BigDecimal) {
			return (BigDecimal) val;
		}
		try {
			return new BigDecimal(val.toString().trim());
		} catch (Exception e) {
			return defVal;
		}
	}

	public static String format(Number num, DecimalFormat df) {
		return format(num, df, null);
	}

	public static String format(Number num, DecimalFormat df, String defVal) {
		if(null != num) {
			try {
				return df.format(num);
			} catch (Exception e) {
			}
		}
		return defVal;
	}

	public static String formatScale(Number num, int newScale, int roundingMode) {
		return formatScale(num, newScale, roundingMode, null);
	}

	public static String formatScale(Number num, int newScale, int roundingMode, String defVal) {
		if(null != num) {
			try {
				return toBigDecimal(num).setScale(newScale, roundingMode).toString();
			} catch (Exception e) {
			}
		}
		return defVal;
	}

	/**
	 * 分转元
	 * @param val
	 * @param defVal
	 * @return
	 */
	public static String fenToYuan(Object val, String defVal) {
		if(null == val)
			return defVal;
		try {
			return toBigDecimal(val).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_FLOOR).toString();
		} catch (Exception e) {
			return defVal;
		}
	}

	/**
	 * 元转分
	 * @param val
	 * @param defVal
	 * @return
	 */
	public static String yuanToFen(Object val, String defVal) {
		if(null == val)
			return defVal;
		try {
			return  toBigDecimal(val).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_FLOOR).toString();
		} catch (Exception e) {
			return defVal;
		}
	}

	/**
	 * 求最小值
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal min(BigDecimal a, BigDecimal b) {
		if(null == a)
			return b;
		if(null == b)
			return a;
		return a.compareTo(b) > 0 ? b : a;
	}

	/**
	 * 求最大值
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal max(BigDecimal a, BigDecimal b) {
		if(null == a)
			return b;
		if(null == b)
			return a;
		return a.compareTo(b) > 0 ? a : b;
	}

	/**
	 * <p>
	 * ""             false
	 * "123aSJH.01"   false
	 * ".01"          false
	 * "10."          false
	 * "10.001"       false
	 * "0.1"          true
	 * "1.11"         true
	 * "10"           true
	 * "0"            true
	 * "0.0"          true
	 * "0.00"         true
	 * "0.000"        false
	 * </p>
	 *
	 * @param str 需要校验的字符串
	 * @return boolean
	 * @author shunyi
	 * @date 2020/12/4 15:01
	 */
	public static boolean checkNumber(String str) {
		if (StringUtil.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(([1-9]\\d*)|([0]))(\\.(\\d){1,2})?$");
		Matcher match = pattern.matcher(str);
		return match.matches();
	}

}
