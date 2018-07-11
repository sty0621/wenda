package com.niuker.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.niuker.dao.LoginTicketDAO;
import com.niuker.dao.UserDAO;
import com.niuker.model.HostHolder;
import com.niuker.model.LoginTicket;
import com.niuker.model.User;

@Component
public class PassportInterceptor implements HandlerInterceptor{

	@Autowired
	LoginTicketDAO loginTicketDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HostHolder hostHolder;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		hostHolder.clear();
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		if(modelAndView != null) {
			modelAndView.addObject("user", hostHolder.getUser());
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String ticket = null;
		if(request.getCookies() != null) {
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("ticket")) {
					ticket = cookie.getValue();
					break;
				}
			}
		}
		
		if(ticket != null) {
			LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
			if(loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
				return true;
			}
			
			User user = userDAO.selectById(loginTicket.getUserId());
			hostHolder.setUser(user);
			
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
	
	
}
