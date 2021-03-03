package com.weread.common.desensitize;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *	日志脱敏工具类
 */
public class DesensitionUtil {
	
	public static String desPhone(Number phone) {
		return desPhone(null == phone ? null : phone.toString());
	}
	
	/**
	 * 手机号脱敏
	 * @param phone
	 * @return
	 */
	public static String desPhone(String phone) {
		if(null == phone || "".equals(phone)) return phone;
		return phone.replaceAll(DesensitionType.PHONE.getRegular()[0], DesensitionType.PHONE.getRegular()[1]);
	}
	
	/**
	 * 身份证号脱敏
	 * @param identityno
	 * @return
	 */
	public static String desIdentityno(String identityno) {
		if(null == identityno || "".equals(identityno)) return identityno;
		return identityno.replaceAll(DesensitionType.IDENTITYNO.getRegular()[0], DesensitionType.IDENTITYNO.getRegular()[1]);
	}
	
	/**
	 * 银行卡号脱敏
	 * @param bankcardno
	 * @return
	 */
	public static String desBankcardno(String bankcardno) {
		if(null == bankcardno || "".equals(bankcardno)) return bankcardno;
		return bankcardno.replaceAll(DesensitionType.BANKCARDNO.getRegular()[0], DesensitionType.BANKCARDNO.getRegular()[1]);
	}
	
	public static String desPassword(String password) {
		if(null == password || "".equals(password)) return password;
		return password.replaceAll(DesensitionType.PASSWORD.getRegular()[0], DesensitionType.PASSWORD.getRegular()[1]);
	}
	
	/**
	 * 匹配注解和属性名
	 * @param object
	 * @param features
	 * @return
	 */
	public static String desJSONString(Object object, SerializerFeature... features) {
		if(object instanceof String) {
			String temp = StringUtils.trim((String)object);
			if(null == temp || temp.length() == 0) return (String)object;
			try {
				char starC = temp.charAt(0);
				if('[' == starC) {
					return JSON.toJSONString(JSON.parseArray((String)object), ValueDesensitizeFilter.getInstal(), features);
				} else if ('{' == starC) {
					return JSON.toJSONString(JSON.parseObject((String)object), ValueDesensitizeFilter.getInstal(), features);
				}
			} catch (Exception e) {
			}
			return (String)object;
		}
		return JSON.toJSONString(object, ValueDesensitizeFilter.getInstal(), features);
	}
	
	/**
	 * 只匹配注解不匹配属性名（map也会匹配属性名）
	 * @param object
	 * @param features
	 * @return
	 */
	public static String desJSONString2(Object object, SerializerFeature... features) {
		if(object instanceof String) {
			String temp = StringUtils.trim((String)object);
			if(null == temp || temp.length() == 0) return (String)object;
			try {
				char starC = temp.charAt(0);
				if('[' == starC) {
					return JSON.toJSONString(JSON.parseArray((String)object), ValueDesensitizeFilter.getInstal2(), features);
				} else if ('{' == starC) {
					return JSON.toJSONString(JSON.parseObject((String)object), ValueDesensitizeFilter.getInstal2(), features);
				}
			} catch (Exception e) {
			}
			return (String)object;
		}
		return JSON.toJSONString(object, ValueDesensitizeFilter.getInstal2(), features);
	}
	
	/**
	 * 真实姓名脱敏
	 * @param realName
	 * @return
	 */
	public static String desRealName(String realName) {
		if(StringUtils.isBlank(realName)) return realName;
		if(realName.length() == 1) return "*";
		if(realName.length() == 2) return realName.substring(0, 1) + "*";
		return realName.substring(0, 1) + StringUtils.repeat("*", realName.length() - 2) + realName.substring(realName.length() - 1);
	}
	
}
