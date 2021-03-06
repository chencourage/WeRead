package com.weread.service.base.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单个结果响应类
 * @author lisheng
 * @date 2019年1月11日
 */
@ApiModel(description="单个结果响应类")
public class SingleResp<T> extends CommonResp {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="数据")
	private T data;

	public SingleResp() {
		super();
	}

	public SingleResp(String respCode, String respDesc, T data) {
		super(respCode, respDesc);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
