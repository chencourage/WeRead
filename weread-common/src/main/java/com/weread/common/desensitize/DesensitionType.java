package com.weread.common.desensitize;

public enum DesensitionType {
	
	PHONE("phone", "11位手机号", "^(\\d{3})\\d{4}(\\d{4})$", "$1****$2"),
	IDENTITYNO("identityNo", "15或者18身份证号", "^(\\d{4})(\\d{7}|\\d{10})(\\d{3}[\\d|X|x])$", "$1****$3"),
	BANKCARDNO("bankCardNo", "银行卡号", "^(\\d{4})\\d*(\\d{4})$", "$1****$2"), 
	PASSWORD("password", "密码", "^.+$", "******"), 
	CUSTOM("custom", "自定义正则处理", ""),
	TRUNCATE("truncate", "字符串截取处理", ""),
	;
	
	String type;
	String describe;
	String[] regular;

	DesensitionType(String type, String describe, String... regular) {
		this.type = type;
		this.describe = describe;
		this.regular = regular;
	}

	public String getType() {
		return type;
	}

	public String getDescribe() {
		return describe;
	}

	public String[] getRegular() {
		return regular;
	}
}
