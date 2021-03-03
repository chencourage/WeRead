package com.weread.common.exception;

import java.text.MessageFormat;

import com.weread.common.utils.StringUtil;


/**
 * 通用断言类
 * @author lisheng
 *
 */
public class YSAssert {
	
	public static final YSErrorCode DEF_ERR_CODE = YSErrorCode.SYSTEM_ERR;
	
	public static void isTrue(boolean expression, String pattern, Object ... arguments) {
		isTrue(expression, DEF_ERR_CODE, pattern, arguments);
	}
	
	public static void notTrue(boolean expression, String pattern, Object ... arguments) {
		notTrue(expression, DEF_ERR_CODE, pattern, arguments);
	}
	
	public static void notNull(Object object, String pattern, Object ... arguments) {
		notNull(object, DEF_ERR_CODE, pattern, arguments);
	}
	
	public static void isNull(Object object, String pattern, Object ... arguments) {
		isNull(object, DEF_ERR_CODE, pattern, arguments);
	}
	
	public static void isBlank(String str, String pattern, Object ... arguments) {
		isBlank(str, DEF_ERR_CODE, pattern, arguments);
	}
	
	public static void notBlank(String str, String pattern, Object ... arguments) {
		notBlank(str, DEF_ERR_CODE, pattern, arguments);
	}
	
	
	public static void isTrue(boolean expression, YSErrorCode errCode, String pattern, Object ... arguments) {
		if (!expression) {
			throwYSException(errCode, pattern, arguments);
		}
	}
	
	public static void notTrue(boolean expression, YSErrorCode errCode, String pattern, Object ... arguments) {
		if (expression) {
			throwYSException(errCode, pattern, arguments);
		}
	}
	
	public static void notNull(Object object, YSErrorCode errCode, String pattern, Object ... arguments) {
		if (object == null) {
			throwYSException(errCode, pattern, arguments);
		}
	}
	
	public static void isNull(Object object, YSErrorCode errCode, String pattern, Object ... arguments) {
		if (object != null) {
			throwYSException(errCode, pattern, arguments);
		}
	}
	
	public static void isBlank(String str, YSErrorCode errCode, String pattern, Object ... arguments) {
		if (StringUtil.isNotBlank(str)) {
			throwYSException(errCode, pattern, arguments);
		}
	}
	
	public static void notBlank(String str, YSErrorCode errCode, String pattern, Object ... arguments) {
		if (StringUtil.isBlank(str)) {
			throwYSException(errCode, pattern, arguments);
		}
	}
	
	private static void throwYSException(YSErrorCode errCode, String pattern, Object ... arguments) {
		
		if(null == errCode) {
			errCode = DEF_ERR_CODE;
		}
		
		String throwMessage = pattern;
		if(null != pattern && null != arguments && arguments.length != 0) {
			try {
				throwMessage = MessageFormat.format(pattern, arguments);
			} catch (Exception e) {
				//do nothing
			}
		}
		if(StringUtil.isBlank(throwMessage)) {
			throwMessage = errCode.getMsg();
		}
		
		throw new YSException(errCode.getCode(), throwMessage);
	}
	
}
