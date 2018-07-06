package com.niuker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niuker.dao.UserDAO;
import com.niuker.model.User;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	
	public User getUser(int id) {
		return userDAO.selectById(id);
	}
	
	
	
	
	
	
	
}
