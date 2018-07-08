package com.niuker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET})
	public String getIndex(Model model, @PathVariable("userId") int userId) {
		
		model.addAttribute("vos", questionService.getQuestionbyId(userId));
		return "index";
	}
	
	@RequestMapping(path = {"/","/index"}, method = {RequestMethod.GET})
	public String index(Model model) {
		
		model.addAttribute("vos", getQuestions());
		return "index";
	}
	
	
	public List<ViewObject> getQuestions(){
		//List<Question> questionList = questionService.getLayestQuestion(0, 0, 10);
		List<Question> questionList = questionService.getAllQuestion();
		List<ViewObject> vos = new ArrayList<ViewObject>();
		for(Question question : questionList) {
			ViewObject vo = new ViewObject();
			vo.set("question", question);
			vo.set("user", userService.getUser(question.getUserId()));
			vos.add(vo);
		}	
		return vos;
	}
	
	
	
	
	
	
	
}
