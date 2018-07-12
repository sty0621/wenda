package com.niuker.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niuker.model.HostHolder;
import com.niuker.model.Question;
import com.niuker.service.QuestionService;
import com.niuker.util.WendaUtil;

@Controller
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	QuestionService questionService;
	
	@Autowired
	HostHolder hostHolder;
	
	@RequestMapping(value = "/question/add", method = {RequestMethod.POST})
	@ResponseBody
	public String addQuestion(@RequestParam("title") String title,
			                  @RequestParam("content") String content) {
		try {
			Question question = new Question();
			question.setContent(content);
			question.setTitle(title);
			question.setCreateDate(new Date());
			question.setCommentCount(0);
			if(hostHolder.getUser() == null) {
				return WendaUtil.getJSONString(999);
			}else {
				question.setUserId(hostHolder.getUser().getId());
			}
			if(questionService.addQuestion(question) > 0) {
				return WendaUtil.getJSONString(0);
			}
			
		}catch(Exception e) {
			logger.error("增加题目失败" + e.getMessage());
		}
		return WendaUtil.getJSONString(1, "失败");
	}
	
	
	
	
	
	
	
	
}
