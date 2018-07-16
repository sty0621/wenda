package com.niuker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.niuker.model.Comment;
import com.niuker.model.Question;
import com.niuker.model.User;

@Mapper
public interface CommentDAO {
    String TABLE_NAME = " comment ";
    String INSET_FIELDS = " user_id, content, created_date, entity_id, entity_type, status ";
    String SELECT_FIELDS = " id, " + INSET_FIELDS;
    
    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{userId},#{content},#{createDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);
    
    @Select({" Select ", SELECT_FIELDS, " from ", TABLE_NAME, " where entity_id = #{entityId} and entity_type = #{entityType} order by created_date desc "})
    List<Comment> selectCommentByEntity(@Param("entityId") int entityId,
    		                            @Param("entityType") int entityType);
    
    @Select({"select count(id) from", TABLE_NAME, " where entity_id = #{entityId} and entity_type = #{entityType} "})
    int getCommentCount(@Param("entityId") int entityId,
            			   @Param("entityType") int entityType);
    
    @Update({" update comment set status = #{status} where id = #{id}"})
    int updateStatues(@Param("id") int id,
            @Param("status") int status);
    
}
