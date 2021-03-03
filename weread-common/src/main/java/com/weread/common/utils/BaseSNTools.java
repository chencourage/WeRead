package com.weread.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.weread.common.secret.MD5Tools;


/***
 * 生成唯一流水
 *
 */
public class BaseSNTools {
	
	private static final String DEFAULT_GROUP_CODE = "WEP";

	private static final long MAX_INDEX = 99999;

	private static final int DEFAULT_SEQ_LENGTH = 32;

	private static Long index = 0l;

	public static String getSeq(String systemCode, String funcCode) throws Exception {

		if (StringUtils.isEmpty(systemCode) || systemCode.length() != 2) {
			throw new Exception("根据功能编号生成流水号失败，标准系统代码长度为2，当前系统代码值["
					+ systemCode + "]");
		}

		if (StringUtils.isEmpty(funcCode) || funcCode.length() != 2) {
			throw new Exception("根据功能编号生成流水号失败，标准功能编号长度为2，当前功能代码值["
					+ funcCode + "]");
		}

		StringBuilder sb = new StringBuilder();
		sb.append(DEFAULT_GROUP_CODE);
		sb.append(systemCode);
		sb.append(funcCode);
		sb.append(getTime());
		sb.append(generateIndex());
		sb.append(RandomUtil.generateString(4, RandomUtil.SCOPE_NUM));
		sb.append(getCheckNum(sb.toString()));

		if (DEFAULT_SEQ_LENGTH != sb.toString().length()) {
			throw new Exception("生成的SEQ长度不符合32位要求，seq值["
					+ sb.toString() + "]");
		}
		return sb.toString();
	}

	private static String generateIndex() {
		synchronized (index) {
			if (index > MAX_INDEX) {
				index = 0L;
			}
			return String.format("%05d", index++);
		}
	}

	private static String getCheckNum(String str) {
		String value = MD5Tools.getMD5String(str);
		return value.substring(0, 1);
	}

	public static String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		return format.format(new Date());
	}
}
