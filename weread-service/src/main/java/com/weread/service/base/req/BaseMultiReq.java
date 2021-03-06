package com.weread.service.base.req;


import com.weread.common.utils.NumberUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询列表数据
 * @author lisheng
 * @date 2019年1月11日
 */
public class BaseMultiReq extends BaseReq {
	
	public static final int DEF_PAGE_SIZE = 10;
	
	@ApiModelProperty(value="当前页")
	private Object currentPage;	//当前页
	@ApiModelProperty(value="页大小")
	private Object pageSize;	//页大小
	
	public int getCurrentPage() {
		int i = 1;
		try {
			i = NumberUtil.toBigDecimal(currentPage).intValue();
		} catch (Exception e) {
		}
		if(i < 1) {
			i = 1;
		}
		return i;
	}
	public void setCurrentPage(Object currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		int i = DEF_PAGE_SIZE;
		try {
			i = NumberUtil.toBigDecimal(pageSize).intValue();
		} catch (Exception e) {
		}
		if(i < 1) {
			i = DEF_PAGE_SIZE;
		}
		return i;
	}
	public void setPageSize(Object pageSize) {
		this.pageSize = pageSize;
	}
}
