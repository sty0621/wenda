package com.niuker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.niuker.interceptor.LoginRequiredInterceptor;
import com.niuker.interceptor.PassportInterceptor;

@Component
public class WendaWebConfiguration extends WebMvcConfigurerAdapter{
	@Autowired
	PassportInterceptor passportInterceptor;
	
	@Autowired
	LoginRequiredInterceptor loginRequiredInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(passportInterceptor);
		registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/*");
		super.addInterceptors(registry);
	}
	
}
