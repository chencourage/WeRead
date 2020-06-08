package com.weread.web.model;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String token;
	
	private String isEnter;

	public LoginResponse(String userId, String token) {
		super();
		this.userId = userId;
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsEnter() {
		return isEnter;
	}

	public void setIsEnter(String isEnter) {
		this.isEnter = isEnter;
	}
	
}
