package com.weread.service.read.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weread.service.read.entity.BookComment;

public class BookCommentVO extends BookComment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3736823200335923548L;

	private String createUserName;

    private String createUserPhoto;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserPhoto() {
		return createUserPhoto;
	}

	public void setCreateUserPhoto(String createUserPhoto) {
		this.createUserPhoto = createUserPhoto;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
