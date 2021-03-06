package com.weread.common.exception;

/**
 * 通用异常
 * @author lisheng
 *
 */
public class REDException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errCode = REDErrorCode.SYSTEM_ERR.getCode();

	public REDException() {
		super();
	}

	public REDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public REDException(String message, Throwable cause) {
		super(message, cause);
	}

	public REDException(String message) {
		super(message);
	}

	public REDException(Throwable cause) {
		super(cause);
	}

	public REDException(String errCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errCode = errCode;
	}

	public REDException(String errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
	}

	public REDException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}
	
	public REDException(REDErrorCode ysErrorCode) {
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
