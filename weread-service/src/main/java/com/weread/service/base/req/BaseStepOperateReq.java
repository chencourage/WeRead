package com.weread.service.base.req;


import com.weread.common.exception.REDAssert;
import com.weread.common.exception.REDErrorCode;
import com.weread.common.exception.REDException;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分步请求父类
 * @author lisheng
 *
 */
public class BaseStepOperateReq extends BaseReq {
	
	@ApiModelProperty(value="分步请求会话ID（上一步请求返回）", required=true)
	private String stepOptSessionId;
	
	@Override
	public void validate() throws REDException {
		super.validate();
		REDAssert.notBlank(stepOptSessionId, REDErrorCode.CHECK_ERR, "会话ID不能为空");
	}

	public String getStepOptSessionId() {
		return stepOptSessionId;
	}
	public void setStepOptSessionId(String stepOptSessionId) {
		this.stepOptSessionId = stepOptSessionId;
	}
}
