package com.weread.service.base.req.book;

import java.util.Date;

public class BookSP {
	
	private String keyword;

    private Byte workDirection;

    private Integer catId;

    private Byte isVip;

    private Byte bookStatus;

    private Integer wordCountMin;

    private Integer wordCountMax;

    private Date updateTimeMin;

    private Long updatePeriod;

    private String sort;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Byte getWorkDirection() {
		return workDirection;
	}

	public void setWorkDirection(Byte workDirection) {
		this.workDirection = workDirection;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Byte getIsVip() {
		return isVip;
	}

	public void setIsVip(Byte isVip) {
		this.isVip = isVip;
	}

	public Byte getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(Byte bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Integer getWordCountMin() {
		return wordCountMin;
	}

	public void setWordCountMin(Integer wordCountMin) {
		this.wordCountMin = wordCountMin;
	}

	public Integer getWordCountMax() {
		return wordCountMax;
	}

	public void setWordCountMax(Integer wordCountMax) {
		this.wordCountMax = wordCountMax;
	}

	public Date getUpdateTimeMin() {
		return updateTimeMin;
	}

	public void setUpdateTimeMin(Date updateTimeMin) {
		this.updateTimeMin = updateTimeMin;
	}

	public Long getUpdatePeriod() {
		return updatePeriod;
	}

	public void setUpdatePeriod(Long updatePeriod) {
		this.updatePeriod = updatePeriod;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
