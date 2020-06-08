package com.weread.web.model;

import java.io.Serializable;

public class LoginUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String loginNo;
	
	private String userName;
	
	private String openId;
	
	private Integer merchantId;
	
	public LoginUser() {
		
	}

	public LoginUser(int id, String loginNo, String userName) {
		super();
		this.id = id;
		this.loginNo = loginNo;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMerchantId() {
		if(merchantId!=null&&merchantId==-1)
			return null;
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
