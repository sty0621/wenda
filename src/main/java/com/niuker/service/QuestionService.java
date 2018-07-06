package com.niuker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niuker.dao.QuestionDAO;
import com.niuker.model.Question;

@Service
public class QuestionService {
	@Autowired
	QuestionDAO questionDAO;
	
	
	public List<Question> getLayestQuestion(int userId, int offset, int limit) {
		return questionDAO.selectLatestQuestion(userId, offset, limit);
	}
	
	
	
	
	
	
	
	
}
