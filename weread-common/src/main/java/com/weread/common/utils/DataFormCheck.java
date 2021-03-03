package com.weread.common.utils;

import java.util.regex.Pattern;

/**
 * 数据格式检查工具类
 * @author lisheng
 *
 */
public class DataFormCheck {
	
	public static final String REGEX_PHONE = "^1\\d{10}$";
	public static final String REGEX_IDCARD = "^\\d{6}(((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}([0-9]|x|X))|(\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}))$";
	
	/**
	 * 是否是手机号
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if(null == phone || 11 != phone.length()) {
			return false;
		}
		return Pattern.matches(REGEX_PHONE, phone);
	}
	
	/**
	 * 是否是身份证
	 * @param phone
	 * @return
	 */
	public static boolean isIdcard(String idcard) {
		if(null == idcard) return false;
		return Pattern.matches(REGEX_IDCARD, idcard);
	}
}
