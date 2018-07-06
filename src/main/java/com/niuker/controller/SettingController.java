package com.niuker.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niuker.aspect.LogAspect;
import com.niuker.service.WendaService;

@Controller
public class SettingController {
	private static final Logger logger = LoggerFactory.getLogger(SettingController.class);
	
	@Autowired
	WendaService wendaService;
	
	@RequestMapping(path = {"/setting"}, method = {RequestMethod.GET})
	@ResponseBody
	public String setting(HttpSession httpSession) {
		return "Session OK: " + wendaService.getMessage(1);
	}
	
}
