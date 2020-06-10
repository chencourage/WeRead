package com.weread.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.weread.web.jwt.JwtAuthenticationFilter;
import com.weread.web.jwt.JwtLoginFilter;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailsService userDetailsService;

    public MyWebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /** 
     * http相关的配置，包括登入登出、异常处理、会话管理等
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	JwtLoginFilter loginFilter = new JwtLoginFilter(authenticationManager());
    	loginFilter.setUsernameParameter("user");
    	loginFilter.setFilterProcessesUrl("/login");
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(loginFilter)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    /**
     * 配置认证方式等
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());
    }
}
