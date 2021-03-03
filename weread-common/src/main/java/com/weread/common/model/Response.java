package com.weread.common.model;

public class Response<T> {
	
	public static final int SUCCESS = 1;
	public static final int FAILED = 0;
	public static final int DENIED = 2;

	private int code;
	
	private String msg;
	
	private T data;
	
	public Response() {
		
	}

	public Response(int code, String msg) {
		this(code,msg,null);
	}
	
	public Response(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
