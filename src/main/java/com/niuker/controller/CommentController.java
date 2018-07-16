package com.niuker.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niuker.model.Comment;
import com.niuker.model.EntityType;
import com.niuker.model.HostHolder;
import com.niuker.service.CommentService;
import com.niuker.service.QuestionService;
import com.niuker.util.WendaUtil;

@Controller
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	HostHolder hostHolder;
	
	@Autowired
	QuestionService questionService;
	
	@RequestMapping(path = {"/addComment"}, method = {RequestMethod.POST})
	public String addComment(@RequestParam("questionId") int questionId,
							 @RequestParam("content") String content) {
		Comment comment = new Comment();
		try {
			comment.setContent(content);
			if(hostHolder.getUser() != null) {
				comment.setUserId(hostHolder.getUser().getId());
			}else {
				comment.setUserId(WendaUtil.ANONYMOUS_USERID);
//				return "redirect:/reglogin";
			}
			comment.setCreateDate(new Date());
			comment.setEntityType(EntityType.ENTITY_QUESTION);
			comment.setEntityId(questionId);
			commentService.addComment(comment);
			
			int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
			questionService.updateCommentCount(comment.getEntityId(), count);
			
		}catch(Exception e) {
			logger.error("增加评论失败" + e.getMessage());
		}
		return "redirect:/question/" + questionId;
	}
}
