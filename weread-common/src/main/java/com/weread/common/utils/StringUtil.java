package com.weread.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 */
public class StringUtil {
	
	/**大写字母模式*/
	public static final Pattern PATTERN_UPPER_LETTER = Pattern.compile("[A-Z]");
	/**数字、字母模式*/
	public static final Pattern PATTERN_LETTER_NUM = Pattern.compile("[A-Za-z0-9]");
	
	/**
	 * 把单词首字母转成大写
	 * @param word
	 * @return
	 */
	public static String convertFirstLetter2Upper(String word) {
		return (word.charAt(0) + "").toUpperCase() + word.substring(1);
	}
	
	/**
	 * Camel命名->下划线命名，如：lastUpdateTime -> last_update_time 
	 * @param source
	 * @return
	 */
	public static String camelNaming2Underline(String source) {
		StringBuffer buffer = new StringBuffer();
		
		Matcher matcher = PATTERN_UPPER_LETTER.matcher(source);
		String upperLetter = null;
		while (matcher.find()) {
			upperLetter = matcher.group(0);
			matcher.appendReplacement(buffer, "_" + upperLetter.toLowerCase());
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}
	
	/**
	 * Pascal命名->Camel命名
	 * @param source
	 * @return
	 */
	public static String pascalNaming2Camel(String source) {
		return (source.charAt(0) + "").toLowerCase() + source.substring(1);
	}
	
	/**
	 * 判断字符串是否为
	 * 
	 * @param str 目标字符
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	/**
	 * 判断字符串是否为
	 * 
	 * @param str 目标字符
	 */
	public static boolean isStrEmpty(Object value) {
		return (value==null||"null".equals(value.toString()) || value.toString().isEmpty());
	}
	
	/**
	 * 判断字符是否非空
	 * @param str 目标字符
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * 判断字符串是否属于数据的子元素
	* @Title: isBelongArray 
	* @Description: 
	* @param array
	* @param str
	* @return    
	* boolean
	* @throws
	 */
	public static boolean isBelongArray(String[] array,String str){
		if(null==array || array.length==0 || isEmpty(str)){
			return false;
		}
		for(int i=0;i<array.length;i++){
			//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
		   if(equalsIgnoreCase(array[i], str)){
			 //查找到了就返回真，不在继续查询
			   return true;
		   }
		}
		//没找到返回false
		return false;
	}
	
	/**
	 * 判断字符串是否为数字可包含小数点
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
		   return false; 
		} 
		return true; 
	}
	
	/**
	 * 转换成字符串,空字符串返回""
	 * @param value
	 * @return
	 */
	public static String toString(Object value){
		return toString(value, false);
	}
	
	/**
	 * 转换成字符串
	 * @param value
	 * @param ifEmptyRetrunNull 是否为空字符串时返回null
	 * @return
	 */
	public static String toString(Object value, boolean ifEmptyRetrunNull){
		String defStr = ifEmptyRetrunNull ? null : "";
		return isStrEmpty(value)?defStr:value.toString().trim(); 
	}
	
	/**
	 * 去除空字符串
	 * @param value
	 * @return
	 */
	public static String trimEmptyToNull(String value){
		return isStrEmpty(value)?null:value;
	}
	
	public static float toFloat(Object value){
		return isStrEmpty(value)?0:Float.parseFloat(value.toString().trim()); 
	}
	public static Double toDouble(Object value){
		return isStrEmpty(value)?0:Double.parseDouble(value.toString().trim()); 
	}
	public static long toLong(Object value){
		return isStrEmpty(value)?0:Long.parseLong(value.toString().trim()); 
	}
	public static long toShort(Object value){
		return isStrEmpty(value)?0:Short.parseShort(value.toString().trim()); 
	}
	public static long toByte(Object value){
		return isStrEmpty(value)?0:Byte.parseByte(value.toString().trim()); 
	}
	public static int toInt(Object value){
		return isStrEmpty(value)?0:Integer.parseInt(value.toString().trim()); 
	}
	public static boolean equalsIgnoreCase(String val1,String val2){
		if(isStrEmpty(val1)) return false;
		if(isStrEmpty(val2)) return false;
		return val1.equalsIgnoreCase(val2);
	}
	public static boolean equals(String val1,String val2){
		if(isStrEmpty(val1)) return false;
		if(isStrEmpty(val2)) return false;
		return val1.equals(val2);
	}
	
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @return
	 */
	public static String formatAmount(Double amount) {
		if (amount == null) {
			return "";
		}
		return new DecimalFormat("0.00").format(amount);
	}
	
	/**
	 * 格式化百分数小数
	 * @param percent
	 * @return
	 */
	public static String formatPercent(Double percent) {
		if (percent == null) {
			return "";
		}
		return new DecimalFormat("0.00##").format(percent);
	}
	
	public static String subStringMaxLen(String str, int len) {
		if(null != str) {
			if(str.length() > len) {
				return str.substring(0, len);
			}
		}
		return str;
	}
	
	public static String subStringMaxLen(String str, int len, String charsetName) throws UnsupportedEncodingException {
		if(null != str) {
			if(str.length() > len) {
				byte[] bytes = str.getBytes(charsetName);
				if(bytes.length > len) {
					String ss = new String(Arrays.copyOf(bytes, len), charsetName);
					return ss.substring(0, ss.length() - 1);
				}
			}
		}
		return str;
	}
	
	public static void main(String[] args) {
//		System.out.println(equalsIgnoreCase("", ""));
//		System.out.println(underline2PascalNaming("last_update_time"));
		
//		System.out.println(underline2CamelNaming("last_update_time"));
//		System.out.println(underline2CamelNaming("User"));
		
//		System.out.println(convertFirstLetter2Upper("commodity"));
		
//		System.out.println(getPathByPackage("com.cg.model"));
		
//		System.out.println(camelNaming2Underline("lastUpdateTime"));
//		System.out.println(camelNaming2Underline("loginName"));
//		System.out.println(camelNaming2Underline("userName"));
		
		System.out.println(hideMiddle("123456789012345678", 4, 4, '*'));
		System.out.println(hideMiddle(null, 4, 4, '*'));
		System.out.println(hideMiddle("123456789012345678", 1, 3, '*'));
		System.out.println(hideMiddle("123456789012345678", 18, 3, '*'));
		System.out.println(hideMiddle("123456789012345678", 15, 3, '*'));
	}
	
	/**
	 * 隐藏中间的字符串
	 * @author lisheng
	 * @param src			原字符串
	 * @param startLen		开头明文显示长度
	 * @param endLen		结尾明文显示长度
	 * @param hideChar		替换字符
	 * @return
	 */
	public static String hideMiddle(String src, int startLen, int endLen, char hideChar) {
		if(null == src || src.length() == 0) return "";
		if(startLen <= 0) startLen = 0;
		if(endLen <= 0) endLen = 0;
		if(src.length() <= startLen + endLen) return src;
		StringBuilder sb = new StringBuilder();
		if(startLen > 0) {
			sb.append(src.substring(0, startLen));
		}
		sb.append(padding(src.length() - startLen - endLen, hideChar));
		if(endLen > 0) {
			sb.append(src.substring(src.length() - endLen));
		}
		return sb.toString();
		
	}
	
	/**
	 * 构建重复字符
	 * @author lisheng
	 * @param repeat	重复次数
	 * @param padChar	重复字符
	 * @return
	 */
	public static String padding(int repeat, char padChar) {
		final char[] buf = new char[repeat];
	    for (int i = 0; i < buf.length; i++) {
	        buf[i] = padChar;
	    }
	    return new String(buf);
	}
	
	/**
	 * 时耗，转换为易读的时耗
	 * @param millis
	 * @return
	 */
	public static String costTime(long millis) {
		if(millis < 0) return "无效时耗";
		if(millis == 0) return "0毫秒";
		StringBuilder resp = new StringBuilder();
		String[] unit = new String[] {"毫秒", "秒", "分", "小时", "天"};
		long[] radix = new long[] {1000, 60, 60, 24};
		long residue=0,quotien=millis; //余数 商
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
	
	/**
	 * 时耗，转换为易读的时耗
	 * @param millis
	 * @return
	 */
	public static String costTimeEn(long millis) {
		if(millis < 0) return "invalid time "+millis+"ms";
		if(millis == 0) return "0ms";
		StringBuilder resp = new StringBuilder();
		String[] unit = new String[] {"ms", "s", "mi", "h", "d"};
		long[] radix = new long[] {1000, 60, 60, 24};
		long residue=0,quotien=millis; //余数 商
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
	
	public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }
	
	
}
