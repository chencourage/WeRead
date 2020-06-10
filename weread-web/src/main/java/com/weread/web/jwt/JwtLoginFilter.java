package com.weread.web.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JWT登录处理
 * @author chenk
 *
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter{

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
    	setAuthenticationManager(authenticationManager);
    }
    
    /**
     * 登录成功处理
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
    	JwtHelper.setCors(req,res);
    	String userId = ((User)auth.getPrincipal()).getUsername();
        JwtHelper.loginSuccess(userId, res);
    }
    
    /**
     * 登录失败处理
     */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		JwtHelper.setCors(request,response);
		JwtHelper.loginFailed(response);
	}
    
}
