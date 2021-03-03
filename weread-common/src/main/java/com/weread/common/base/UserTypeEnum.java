/**
 * 
 */
package com.weread.common.base;

import org.apache.commons.lang3.StringUtils;

/**
 * 表：用户信息表 CDB_USER_INFO
 * 字段：USER_TYPE
 * 描述：用户类型：01-代理商，00-商户
 * @author lisheng
 * @date 2020年8月28日
 */
public enum UserTypeEnum {
	AGENT("01", "代理商", "A", SystemConfig.AGENT_URL_PREFIX),
	MERC("00", "商户", "M", SystemConfig.MERC_URL_PREFIX),
	;
	
	public final String code;
	public final String name;
	public final String userCodePrefix; //用户号前缀
	public final String urlPrefix; //用户号前缀
	/**
	 * @param code
	 * @param name
	 * @param prefix
	 */
	private UserTypeEnum(String code, String name, String userCodePrefix, String urlPrefix) {
		this.code = code;
		this.name = name;
		this.userCodePrefix = userCodePrefix;
		this.urlPrefix = urlPrefix;
	}
	
	public static boolean exists(String code) {
		if(StringUtils.isNotBlank(code)) {
			for(UserTypeEnum en : UserTypeEnum.values()) {
				if(code.equals(en.getCode())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static UserTypeEnum getByCode(String code) {
		if(StringUtils.isNotBlank(code)) {
			for(UserTypeEnum en : UserTypeEnum.values()) {
				if(code.equals(en.getCode())) {
					return en;
				}
			}
		}
		return null;
	}
	
	public static String getUserTypeName(String userType) {
		UserTypeEnum userTypeEnum = getByCode(userType);
		if(null == userTypeEnum) {
			return "用户";
		}
		return userTypeEnum.getName();
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
