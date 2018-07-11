package com.niuker.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niuker.aspect.LogAspect;
import com.niuker.service.QuestionService;
import com.niuker.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path = {"/reg/"}, method = {RequestMethod.POST})
	public String reg(Model model, 
						   @RequestParam("password") String password,
						   @RequestParam("username") String username,
						   HttpServletResponse response) {
		try {
			Map<String, String> map = userService.register(username, password);
			if(map.containsKey("ticket")) {
				Cookie cookie = new Cookie("ticket", map.get("ticket"));
				cookie.setPath("/");
				response.addCookie(cookie);
				return "redirect:/";
			}else {
				model.addAttribute("msg", map.get("msg"));
				return "login";
			}
			
		}catch(Exception e) {
			logger.error("注册异常" + e.getMessage());
			return "login";
		}
	}
	
	@RequestMapping(path = {"/login/"}, method = {RequestMethod.POST})
	public String login(Model model, 
						   @RequestParam("password") String password,
						   @RequestParam("username") String username,
						   @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberme,
						   HttpServletResponse response) {
		try {
			Map<String, String> map = userService.login(username, password);
			if(map.containsKey("ticket")) {
				Cookie cookie = new Cookie("ticket", map.get("ticket"));
				cookie.setPath("/");
				response.addCookie(cookie);
				return "redirect:/";
			}else {
				model.addAttribute("msg", map.get("msg"));
				return "login";
			}
			
			
		}catch(Exception e) {
			logger.error("注册异常" + e.getMessage());
			return "login";
		}
	}
	
	@RequestMapping(path = {"/reglogin"}, method = {RequestMethod.GET})
	public String getIndex(Model model) {
		return "login";
	}
	
	
	
	
}
