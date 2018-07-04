package com.niuker.wenda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.niuker.wenda.model.User;

@Mapper
public interface UserDAO {
	String TABEL_NAME = " user ";
	String INSERT_FIELDS = " name, password, salt, head_url ";
	String SELECT_FIELDS = " id, " + INSERT_FIELDS;
	
	@Insert({"insert into", TABEL_NAME, "(", INSERT_FIELDS, 
			") values(#{name}, #{password}, #{salt}, #{headUrl})"})
	int addUser(User user);
	
	
	
	
	
	
	
}
