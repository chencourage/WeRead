package com.weread.web;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weread.web.security.CorsFilter;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer{

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    	
        for (HttpMessageConverter<?> messageConverter : converters) {
        	if(messageConverter instanceof MappingJackson2HttpMessageConverter) {
        		converters.remove(messageConverter);
        		break;
        	}
        }
    }
    
    @Bean
    public FilterRegistrationBean<CorsFilter> someFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<CorsFilter>();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        return registration;
    }
}
