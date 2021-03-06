package com.weread.service.read.vo;

import com.weread.service.read.entity.BookSetting;

public class BookSettingVO extends BookSetting {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1612441832644527101L;
	private String bookName;
    private String picUrl;
    private String authorName;
    private String bookDesc;
    private Float score;
    private Integer catId;
    private String catName;
    private Byte bookStatus;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

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

	public Byte getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(Byte bookStatus) {
		this.bookStatus = bookStatus;
	}

}
