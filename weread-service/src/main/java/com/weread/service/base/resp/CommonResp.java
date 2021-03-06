package com.weread.service.base.resp;

import java.io.Serializable;

import com.weread.common.exception.REDErrorCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回响应码和描述
 * @author lisheng
 * @date 2019年1月11日
 */
@ApiModel(description="响应参数类")
public class CommonResp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String SUCCESS_RESP_CODE = "0000";
	public static final String SUCCESS_RESP_DESC = "success";
	public static final String FAIL_RESP_CODE = REDErrorCode.SYSTEM_ERR.getCode();
	
	@ApiModelProperty(value="响应码：0000-成功，其他均失败（8888-会话已超时，请重新登录）", required=true)
	private String respCode; //响应码
	@ApiModelProperty(value="响应描述", required=true)
	private String respDesc; //响应描述
	
	public CommonResp() {}

	public CommonResp(String respCode, String respDesc) {
		this.respCode = respCode;
		this.respDesc = respDesc;
	}

	@ApiModelProperty(value="是否成功，true-成功，false-失败", required=true)
	public boolean isSuccess() {
		return SUCCESS_RESP_CODE.equals(this.respCode);
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
}
