package com.weread.service.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weread.common.model.Response;
import com.weread.service.sys.entity.SysUser;
import com.weread.service.sys.service.ISysUserService;

public abstract class BaseController {
	protected Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private ISysUserService userService;
	
	private static Set<String> imgExts = new HashSet<>(Arrays.asList("jpg","jpeg","png","bmp","tif","gif"));
	
	@RequestMapping("/{page}")
	protected String toPage(@PathVariable("page")String page) {
		return path()+"/"+page;
	}
	
	protected String path() {
		return "";
	}

	protected <T>Response<T> failed(String msg,T data) {
		return new Response<T>(Response.FAILED, msg, data);
	}
	protected <T>Response<T> failed(String msg) {
		return new Response<T>(Response.FAILED, msg);
	}
	protected <T>Response<T> failed() {
		return new Response<T>(Response.FAILED,"系统异常");
	}
	protected <T>Response<T> success() {
		return new Response<T>(Response.SUCCESS, "成功", null);
	}
	protected <T>Response<T> success(String msg) {
		return new Response<T>(Response.SUCCESS, msg, null);
	}
	protected <T>Response<T> success(T data) {
		return new Response<T>(Response.SUCCESS, "成功", data);
	}
	protected <T>Response<T> success(String msg,T data) {
		return new Response<T>(Response.SUCCESS, msg, data);
	}
	
	protected Integer getCurrentUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return new Integer(auth.getPrincipal().toString());
	}
	
	protected SysUser getCurrentUserObj() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser user = userService.selectById(new Integer(auth.getPrincipal().toString()));
		if(user==null) {
			log.error("can't get current user:{}",auth.getPrincipal());
		}
		return user;
	}
	
	protected boolean isImage(String ext) {
		return imgExts.contains(ext);
	}
}
