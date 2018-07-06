package com.niuker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niuker.model.Question;
import com.niuker.model.ViewObject;
import com.niuker.service.QuestionService;
import com.niuker.service.UserService;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuestionService questionService;
	
	@RequestMapping(path = {"/","/index"}, method = {RequestMethod.GET})
	public String index(Model model) {
		List<Question> questionList = questionService.getLayestQuestion(0, 0, 10);
		List<ViewObject> vos = new ArrayList<ViewObject>();
		for(Question question : questionList) {
			ViewObject vo = new ViewObject();
			vo.set("question", question);
			vo.set("user", userService.getUser(question.getUserId()));
		}
		model.addAttribute("vos", vos);
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
}
