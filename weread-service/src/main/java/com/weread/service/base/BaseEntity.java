package com.weread.service.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String toJson() {
		return JSON.toJSONString(this);
	}

}
