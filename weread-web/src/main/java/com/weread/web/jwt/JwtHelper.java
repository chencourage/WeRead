package com.weread.web.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.weread.common.model.Response;
import com.weread.common.utils.RandomUtil;
import com.weread.common.utils.SpringContextUtil;
import com.weread.service.sys.entity.SysUser;
import com.weread.service.sys.service.ISysUserService;
import com.weread.web.model.LoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {

	public static final String SECRET = RandomUtil.getRandomSecretByLength(16);
	private static final long EXPIRE_TIME_MS = 10 * 60 * 60 * 24 * 1000;
	
	public static void loginSuccess(String userId,HttpServletResponse res) throws IOException {
        Response<LoginResponse> respone = loginSuccess(userId);
        res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json;charset=utf-8");  
		res.getWriter().write(JSON.toJSONString(respone));
	}
	
	public static Response<LoginResponse> loginSuccess(String userId){
		LoginResponse resp = new LoginResponse(userId,null);
		EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
		wrapper.eq("id", userId);
		SysUser sysUser = SpringContextUtil.getBean(ISysUserService.class).selectOne(wrapper);
		if(sysUser!=null) {
			resp.setIsEnter("1");
		}else {
			resp.setIsEnter("0");
		}
		String sysId = sysUser.getSysId().toString();
		String token = Jwts.builder()
                .setSubject(userId)
                .setAudience(sysId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME_MS))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
		resp.setToken(token);
        return new Response<>(Response.SUCCESS,"成功",resp);
	}
	
	public static void loginFailed(HttpServletResponse response) throws IOException {
		Response<String> res = new Response<>(Response.FAILED, "用户名或密码错误");
		response.setCharacterEncoding("UTF-8");    
		response.setContentType("application/json;charset=utf-8");  
		response.getWriter().write(JSON.toJSONString(res));
	}
	
	public static void setCors(HttpServletRequest request,HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "*");
		String header = request.getHeader("Access-Control-Request-Headers");
		if(StringUtils.isBlank(header)) {
			header = "*";
		}
		res.setHeader("Access-Control-Allow-Headers", header);
	}
}
