package com.weread.service.base.resp;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 多个结果响应类带汇总数据
 * @author lisheng
 * @date 2019年1月21日
 */
@ApiModel(description="多个结果响应类带汇总数据")
public class SumMultiResp<T1, T2> extends MultiResp<T1> {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="汇总数据")
	private T2 summary; //汇总数据

	public SumMultiResp() {
		super();
	}
	
	public SumMultiResp(String respCode, String respDesc, int currentPage, int pageSize, long total, List<T1> rows, T2 summary) {
		super(respCode, respDesc, currentPage, pageSize, total, rows);
		this.summary = summary;
	}

	public T2 getSummary() {
		return summary;
	}

	public void setSummary(T2 summary) {
		this.summary = summary;
	}

}
