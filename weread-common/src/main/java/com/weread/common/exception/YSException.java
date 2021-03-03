package com.weread.common.exception;

/**
 * 通用异常
 * @author lisheng
 *
 */
public class YSException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errCode = YSErrorCode.SYSTEM_ERR.getCode();

	public YSException() {
		super();
	}

	public YSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public YSException(String message, Throwable cause) {
		super(message, cause);
	}

	public YSException(String message) {
		super(message);
	}

	public YSException(Throwable cause) {
		super(cause);
	}

	public YSException(String errCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errCode = errCode;
	}

	public YSException(String errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
	}

	public YSException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}
	
	public YSException(YSErrorCode ysErrorCode) {
		super(ysErrorCode.getMsg());
		this.errCode = ysErrorCode.getCode();
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
