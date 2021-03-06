package com.weread.service.read.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weread.service.read.entity.UserFeedback;

public class UserFeedbackVO extends UserFeedback {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394494976452356087L;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
