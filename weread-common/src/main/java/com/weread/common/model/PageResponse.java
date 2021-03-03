package com.weread.common.model;

import java.util.List;

public class PageResponse<T> {
	public static final int SUCCESS = 1;
	public static final int FAILED = 0;
	public static final int DENIED = 2;

	private int code;
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 总页数
	 */
	private int total;
	/**
	 * 总记录数
	 */
	private int records;
	/**
	 * 当前页数据
	 */
	private List<T> data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
