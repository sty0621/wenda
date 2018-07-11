package com.niuker.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.niuker.model.LoginTicket;
import com.niuker.model.User;

@Mapper
public interface LoginTicketDAO {
	String TABLE_NAME = " login_ticket ";
    String INSET_FIELDS = " user_id, expired, status, ticket ";
    String SELECT_FIELDS = " id, " + INSET_FIELDS;
    
    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
    ") values (#{userId},#{expired},#{status},#{ticket})"})
    int addTicket(LoginTicket ticket);
    
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);
    
//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
//    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
//
//    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
//    void deleteById(int id);
    
    
    
    
    
}
