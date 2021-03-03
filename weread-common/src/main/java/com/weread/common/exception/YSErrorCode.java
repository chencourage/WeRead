package com.weread.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 通用错误码
 * @author lisheng
 *
 */
public enum YSErrorCode {
	SYSTEM_ERR("9999", "系统繁忙，请稍后重试"),
	CHECK_ERR("9998", "请求参数校验失败"),
	VALIDATE_ERR("9997", "业务数据校验失败"),
	DECODE_ERR("9996", "数据解密失败"),
	OTHER_SYSERROR("9995", "系统繁忙，请稍后重试"), //其他系统错误
	REALNAME_FAIL("9994", "实名认证失败"),
	
	HTTP_ERROR("9404", "系统异常"),
	
	LOGIN_TIMEOUT("8888", "会话已失效，请重新登录"),
	PERMISSION_DENIED("8887", "没有权限"),
	NEED_AUTH_BIND("8886", "未授权绑定用户"),
	MULTI_ACCOUNT("8885", "多个账户"),
	NEED_REALNAME("8884", "需要实名认证"),
	NEED_SUBSCRIBE("8883", "需要订阅关注"),
	
	OCR_CLOSE("1001", "图片识别开关已关闭"),
	
	CALL_ERROR("CALL_ERROR", "其他系统错误码#@#其他系统错误描述#@#本系统错误码#@#给用户错误提示"), //第三方系统报错，用于结束流程，传递信息，请勿将该错误抛到控制层
	;
	
	private String code;
	private String msg;
	
	private YSErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static String buildCallError(String thirdErrCode, String thirdErrMsg, YSErrorCode errCode, String errMsg) {
		return String.format("%s#@#%s#@#%s#@#%s", StringUtils.trimToEmpty(thirdErrCode), StringUtils.trimToEmpty(thirdErrMsg),
				StringUtils.trimToEmpty(errCode.getCode()), StringUtils.trimToEmpty(errMsg));
	}
	
}
