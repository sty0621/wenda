package com.niuker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.niuker.model.Question;
import com.niuker.model.User;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSET_FIELDS = " title, content, created_date, user_id, comment_count ";
    String SELECT_FIELDS = " id, " + INSET_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{title},#{content},#{createDate},#{userId},#{commentCount})"})
    int addQuestion(Question question);

    List<Question> selectLatestQuestion(@Param("userId") int userId,
    		                            @Param("offset") int offset,
    		                            @Param("limit") int limit);
    
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME})
    List<Question> selectAll();
    
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Question selectbyId(int id);


}
