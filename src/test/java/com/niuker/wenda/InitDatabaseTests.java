package com.niuker.wenda;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.niuker.wenda.dao.QuestionDAO;
import com.niuker.wenda.dao.UserDAO;
import com.niuker.wenda.model.Question;
import com.niuker.wenda.model.User;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitDatabaseTests {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	QuestionDAO questionDAO;

	@Test
	public void InitDatabaseTests() {
		Random random = new Random();
		
		for(int i = 0; i < 11; ++i) {
//			User user = new User();
//			user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png", random.nextInt(1000)));
//			user.setName(String.format("USER%d", i));
//			user.setPassword("");
//			user.setSalt("");
//			userDAO.addUser(user);
//			
//			user.setPassword("xx");
//			userDAO.updatePassword(user);
			
//			Question question = new Question();
//			question.setCommentCount(i);
//			Date date = new Date();
//			date.setTime(date.getTime() + 1000 * 3600 * i);
//			question.setCreateDate(date);
//			question.setUserId(i + 1);
//			question.setTitle(String.format("TITLE%d", i));
//			question.setContent(String.format("BalaBala Content %d", i));
//			
//			questionDAO.addQuestion(question);
		}
		
//		Assert.assertEquals("xx", userDAO.selectById(1).getPassword());
//		
//		userDAO.deleteById(1);
//		
//		Assert.assertNull(userDAO.selectById(1));
		System.out.println(questionDAO.selectLatestQuestion(0, 0, 10));
	}

}
