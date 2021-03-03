package com.weread.common.utils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 随机数工具类
 */
public class RandomUtil {
	private static Logger log = LoggerFactory.getLogger(RandomUtil.class);
	
	public static final String SCOPE_NUM = "0123456789";
	public static final String SCOPE_LOWER_CHAR = "abcdefghijklmnopqrstuvwxyz";
	public static final String SCOPE_UPPER_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String SCOPE_CHAR = SCOPE_LOWER_CHAR + SCOPE_UPPER_CHAR;
	public static final String SCOPE_NUM_LOWER_CHAR = SCOPE_NUM + SCOPE_LOWER_CHAR;
	public static final String SCOPE_NUM_UPPER_CHAR = SCOPE_NUM + SCOPE_UPPER_CHAR;
	public static final String SCOPE_NUM_CHAR = SCOPE_NUM + SCOPE_CHAR;
	public static final String SCOPE_PASSWORD = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; //密码范围
	public static final String SCOPE_SNSCODE = SCOPE_NUM; //短信验证码范围
	public static final String SCOPE_VALIDAECODE = SCOPE_NUM; //图片验证码范围
	
	/**
	 * 获得随机UUID32位字符串
	 * 
	 * @return 32位随机字符串
	 */
	public static String randomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 生成指定长度的随机字符串
	 * @param length 长度
	 * @param scope 范围
	 * @return
	 */
	public static String generateString(int length, String scope) {
		Random random = new Random();
		if(null == scope || 0 == scope.length()) return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(scope.charAt(random.nextInt(scope.length())));
		}
		return sb.toString();
	}

	/**
	 * 获取一定长度的随机字符串
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(SCOPE_NUM_LOWER_CHAR.length());
			sb.append(SCOPE_NUM_LOWER_CHAR.charAt(number));
		}
		return sb.toString();
	}
	
	public static String getRandomSecretByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-=,./;'~";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 获取一定长度的随机数字字符串
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomNumberByLength(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(SCOPE_NUM.length());
			sb.append(SCOPE_NUM.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * @param list
	 * @return
	 */
	public static <T> T  getRandomObj(List<T> list){
		if(null==list||list.size()==0){
			return null;
		}else{
			Random random = new Random();
			int number = random.nextInt(list.size());
			return list.get(number);
		}
		
	}
}
