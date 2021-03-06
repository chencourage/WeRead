package com.weread.service.read.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weread.service.read.entity.Book;

public class BookVO extends Book {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9167384937841219028L;
	@JsonFormat(timezone = "GMT+8", pattern = "MM/dd HH:mm")
    private Date lastIndexUpdateTime;

	public Date getLastIndexUpdateTime() {
		return lastIndexUpdateTime;
	}

	public void setLastIndexUpdateTime(Date lastIndexUpdateTime) {
		this.lastIndexUpdateTime = lastIndexUpdateTime;
	}

}
