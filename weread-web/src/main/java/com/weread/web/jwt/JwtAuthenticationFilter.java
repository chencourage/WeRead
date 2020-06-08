package com.weread.web.jwt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.alibaba.fastjson.JSON;
import com.weread.common.model.Response;
import com.weread.web.model.LoginUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthenticationFilter  extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
    	JwtHelper.setCors(req,res);
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
        	denied(res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        if(authentication!=null) {
        	SecurityContextHolder.getContext().setAuthentication(authentication);
        	chain.doFilter(req, res);
        }else {
        	denied(res);
        }
    }

	private void denied(HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");    
		res.setContentType("application/json; charset=utf-8");  
		res.getWriter().write(JSON.toJSONString(new Response<String>(Response.DENIED,"无效的token")));
	}

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
        	String claims = token.replace("Bearer ", "");
            try {
				Claims claim = Jwts.parser()
				        .setSigningKey(JwtHelper.SECRET)
				        .parseClaimsJws(claims)
				        .getBody();
				String customerId = claim.getSubject();
				String merchantId = claim.getAudience();
				if (customerId != null) {
					LoginUser loginUser = new LoginUser();
					loginUser.setId(Integer.parseInt(customerId));
					loginUser.setMerchantId(Integer.parseInt(merchantId));
				    return new UsernamePasswordAuthenticationToken(loginUser, claims, new ArrayList<>());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
            return null;
        }
        return null;
    }
}