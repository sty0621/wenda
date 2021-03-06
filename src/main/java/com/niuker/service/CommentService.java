package com.niuker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.niuker.dao.CommentDAO;
import com.niuker.model.Comment;

@Service
public class CommentService {
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	SensitiveService sensitiveService;
	
	public List<Comment> getCommentByEntity(int entityId, int entityType){
		return commentDAO.selectCommentByEntity(entityId, entityType);
	}
	
	public int addComment(Comment comment) {
		comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
		comment.setContent(sensitiveService.filter(comment.getContent()));
		return commentDAO.addComment(comment) > 0 ? comment.getId() : 0;
	}
	
	public int getCommentCount(int entityId, int entityType) {
		return commentDAO.getCommentCount(entityId, entityType);
	}
	
	public boolean deleteComment(int commentId) {
		return commentDAO.updateStatues(commentId, 1) > 0;
	}
	
	
}
