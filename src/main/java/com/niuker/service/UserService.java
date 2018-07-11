package com.niuker.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niuker.dao.LoginTicketDAO;
import com.niuker.dao.UserDAO;
import com.niuker.model.LoginTicket;
import com.niuker.model.User;
import com.niuker.util.WendaUtil;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
    private LoginTicketDAO loginTicketDAO;
	
	public Map<String, String> register(String username, String password){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isBlank(username)){
			map.put("msg", "用户名不能为空");
			return map;
		}
		if(StringUtils.isBlank(password)) {
			map.put("msg", "密码不能为空");
			return map;
		}
		User user = userDAO.selectByName(username);
		if(user != null) {
			map.put("msg", "用户名已被注册");
			return map;
		}
		
		user = new User();
		user.setName(username);
		user.setSalt(UUID.randomUUID().toString().substring(0, 5));
		user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png", new Random().nextInt(1000)));
		
		user.setPassword(WendaUtil.MD5(password + user.getSalt()));
		
		userDAO.addUser(user);
		
		String ticket = addLoginTicket(user.getId());
		map.put("ticket", ticket);
		
		return map;
	}
	
	public Map<String, String> login(String username, String password){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isBlank(username)){
			map.put("msg", "用户名不能为空");
			return map;
		}
		if(StringUtils.isBlank(password)) {
			map.put("msg", "密码不能为空");
			return map;
		}
		User user = userDAO.selectByName(username);
		if(user == null) {
			map.put("msg", "用户名不存在");
			return map;
		}
		
		if(!WendaUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
			map.put("msg", "密码错误");
			return map;
		}
		
		String ticket = addLoginTicket(user.getId());
		map.put("ticket", ticket);
		
		return map;
	}
	
	public String addLoginTicket(int userId) {
		LoginTicket loginTicket = new LoginTicket();
		loginTicket.setUserId(userId);
		Date now = new Date();
		now.setTime(3600 * 24 * 100 * 1000 + now.getTime());
		loginTicket.setExpired(now);
		loginTicket.setStatus(0);
		loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("_", ""));
		loginTicketDAO.addTicket(loginTicket);
		return loginTicket.getTicket();
	}
	
	public User getUser(int id) {
		return userDAO.selectById(id);
	}

	
}
