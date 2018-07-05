package com.niuker.wenda;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.niuker.wenda.dao.UserDAO;
import com.niuker.wenda.model.User;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitDatabaseTests {
	@Autowired
	UserDAO userDAO;

	@Test
	public void InitDatabaseTests() {
		Random random = new Random();
		
		for(int i = 0; i < 11; ++i) {
			User user = new User();
			user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png", random.nextInt(1000)));
			user.setName(String.format("USER%d", i));
			user.setPassword("");
			user.setSalt("");
			userDAO.addUser(user);
			
			user.setPassword("xx");
			userDAO.updatePassword(user);
		}
		
		Assert.assertEquals("xx", userDAO.selectById(1).getPassword());
		
		userDAO.deleteById(1);
		
		Assert.assertNull(userDAO.selectById(1));
		
	}

}
