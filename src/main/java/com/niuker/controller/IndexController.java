package com.niuker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.niuker.aspect.LogAspect;
import com.niuker.service.WendaService;

import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

//@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	WendaService wendaService;
	
//	@RequestMapping("/")
//	@RequestMapping(path = {"/","/index"}, method = {RequestMethod.GET})
//	@ResponseBody
//	public String index(HttpSession httpSession) {
//		logger.info("VISIT HOME");
//		return wendaService.getMessage(2) + "Hello NowCoder" + httpSession.getAttribute("msg");
//	}
	
//	@RequestMapping(path = {"/profile/{userId}"})
//	@ResponseBody
//	public String profile(@PathVariable("userId") int userId) {
//		return String.format("Profile Page of %d", userId);
//	}
	
	@RequestMapping(path = {"/profile/{groudId}/{userId}"})
	@ResponseBody
	public String profile(@PathVariable("userId") int userId, 
			              @PathVariable("groudId") String groudId,
			              @RequestParam(value = "type",defaultValue = "1") int type,
			              @RequestParam(value = "key",defaultValue = "zz", required = false) String key) {
		return String.format("Profile Page of %s / %d, t:%d, k:%s", groudId, userId, type, key);
	}
	
//	@RequestMapping("/list")
//    public String template(Map<String, Object> map){
//        map.put("name", "Joe");
//        map.put("sex", 1);    //sex:性别，1：男；0：女；  
//        
//        // 模拟数据
//        List<Map<String, Object>> friends = new ArrayList<Map<String, Object>>();
//        Map<String, Object> friend = new HashMap<String, Object>();
//        friend.put("name", "xbq");
//        friend.put("age", 22);
//        friends.add(friend);
//        friend = new HashMap<String, Object>();
//        friend.put("name", "July");
//        friend.put("age", 18);
//        friends.add(friend);
//        map.put("friends", friends);
//        return "list";
//    }
	
//	@RequestMapping("/free")
//    public String template(Model model){
//		model.addAttribute("value1", "vvvvv1");
//		List<String> colors = new ArrayList<String>();
//		colors.add("RED");
//		colors.add("BLUE");
//		colors.add("GREEN");
//		model.addAttribute("colors", colors);
//		
//        return "freemarker";
//    }
	
	@RequestMapping("/free")
    public String template(){
		
        return "test1";
    }
	
	
//	@RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
//    public String redirect(@PathVariable("code") int code,
//    		               HttpSession httpSession){
//		httpSession.setAttribute("msg", "jump from redirect");
//        return "redirect:/";
//    }
	
	@RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
    public RedirectView redirect(@PathVariable("code") int code,
    		               HttpSession httpSession){
		httpSession.setAttribute("msg", "jump from redirect");
		RedirectView red = new RedirectView("/", true);
		if(code == 301) {
			red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			
		}
        return red;
    }
	
	@RequestMapping(path = {"/admin"}, method = {RequestMethod.GET})
    @ResponseBody
	public String admin(@RequestParam("key") String key){
		if("admin".equals(key)) {
			return "hello admin";
		}
        throw new IllegalArgumentException("参数不对");
    }
	
	@ExceptionHandler()
	@ResponseBody
	public String error(Exception e) {
		return "error:" + e.getMessage();
	}
	
}
