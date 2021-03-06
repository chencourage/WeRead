package com.weread.service.read.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weread.service.read.entity.News;

public class NewsVO extends News {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7168555031340619888L;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
