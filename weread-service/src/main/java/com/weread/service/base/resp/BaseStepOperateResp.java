package com.weread.service.base.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分步响应父类
 * @author lisheng
 *
 */
@ApiModel(description="分步请求响应参数类")
public class BaseStepOperateResp {
	
	@ApiModelProperty(value="分步请求会话ID（下一步请求请带回）", required=true)
	private String stepOptSessionId;
	
	public String getStepOptSessionId() {
		return stepOptSessionId;
	}
	public void setStepOptSessionId(String stepOptSessionId) {
		this.stepOptSessionId = stepOptSessionId;
	}
}
