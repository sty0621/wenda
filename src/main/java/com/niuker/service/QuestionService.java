package com.niuker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.niuker.dao.QuestionDAO;
import com.niuker.model.Question;

@Service
public class QuestionService {
	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	SensitiveService sensitiveService;
	
	public int addQuestion(Question question) {
		//敏感词过滤
		//html过滤
		question.setContent(HtmlUtils.htmlEscape(question.getContent()));
		question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
		//敏感词过滤
		question.setContent(sensitiveService.filter(question.getContent()));
		question.setTitle(sensitiveService.filter(question.getTitle()));
		return questionDAO.addQuestion(question) > 0 ? question.getId() : 0;
	}
	
	public Question selectById(int id) {
		return questionDAO.selectById(id);
	}
	
	public List<Question> getLayestQuestion(int userId, int offset, int limit) {
		return questionDAO.selectLatestQuestions(userId, offset, limit);
	}
	
	public List<Question> getAllQuestion() {
		return questionDAO.selectAll();
	}
	
}
