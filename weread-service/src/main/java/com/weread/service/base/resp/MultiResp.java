package com.weread.service.base.resp;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 多个结果响应类
 * @author lisheng
 * @date 2019年1月11日
 */
@ApiModel(description="多个结果响应类")
public class MultiResp<T> extends CommonResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="当前页")
	private int currentPage;	//当前页
	@ApiModelProperty(value="页大小")
	private int pageSize;		//页大小
	@ApiModelProperty(value="总数")
	private long total;			//总数
	@ApiModelProperty(value="数据")
	private List<T> data;   //数据

	public MultiResp() {
		super();
	}
	
	public MultiResp(String respCode, String respDesc, int currentPage, int pageSize, long total, List<T> data) {
		super(respCode, respDesc);
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		this.data = data;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
