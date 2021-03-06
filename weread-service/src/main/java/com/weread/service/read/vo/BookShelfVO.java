package com.weread.service.read.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weread.service.read.entity.UserBookshelf;

public class BookShelfVO extends UserBookshelf {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 879624972271731305L;
	private Integer catId;
    private String catName;
    private Long lastIndexId;
    private String lastIndexName;
    private String bookName;
    @JsonFormat(timezone = "GMT+8", pattern = "MM/dd HH:mm:ss")
    private Date lastIndexUpdateTime;
    
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Long getLastIndexId() {
		return lastIndexId;
	}
	public void setLastIndexId(Long lastIndexId) {
		this.lastIndexId = lastIndexId;
	}
	public String getLastIndexName() {
		return lastIndexName;
	}
	public void setLastIndexName(String lastIndexName) {
		this.lastIndexName = lastIndexName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Date getLastIndexUpdateTime() {
		return lastIndexUpdateTime;
	}
	public void setLastIndexUpdateTime(Date lastIndexUpdateTime) {
		this.lastIndexUpdateTime = lastIndexUpdateTime;
	}

}
