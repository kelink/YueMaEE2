package com.gdufs.gd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.dao.YCommentDao;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YComment;
import com.gdufs.gd.response.modle.Comment;
import com.gdufs.gd.response.modle.FriendsActivityObj;
import com.gdufs.gd.service.YCommentService;
import com.gdufs.gd.service.YContactService;
import com.gdufs.gd.util.DateUtil;
import com.gdufs.gd.util.JacksonUtil;

/**
 * 对活动的评论的controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Resource(name = "commentService")
	private YCommentService commentService;
	
	@RequestMapping(value = "/newComment", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public String newComment(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "") int uId,
			@RequestParam(value = C.ParamsName.AID, required = true, defaultValue = "") int activityId,
			@RequestParam(value = C.ParamsName.COMMENT_CONTENT, required = true, defaultValue = "") String commentContent,
			@RequestParam(value = C.ParamsName.COMMENT_FATHER, required = false, defaultValue = "0") String fatherCommentId){
		YComment comment=new YComment();
		return null;
		
	}
	
	@RequestMapping(value = "/getActivityComments", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public String  getActivityComments(@RequestParam(value = C.ParamsName.AID,defaultValue = "0")int activityId){
		TransferMessage message = new TransferMessage();
		if (activityId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			List<YComment> comments=commentService.getCommentsByActivityId(activityId);
			ArrayList<Comment> commentList=new ArrayList<Comment>();
			for (YComment comment :comments) {
					Comment itemComment=new Comment();
					itemComment.setCommentPath(comment.getCommentPath());
					itemComment.setContent(comment.getContent());
					itemComment.setCreateTime(DateUtil.formatDateTime(comment.getCreateTime()));
					itemComment.setFatherCommentId(String.valueOf(comment.getFatherCommentId()));
					itemComment.setID(String.valueOf(comment.getId()));
					itemComment.setUserFace(comment.getUser().getFacePath());
					itemComment.setUserId(String.valueOf(comment.getUser().getId()));
					commentList.add(itemComment);
			}	
			HashMap<String,ArrayList<Comment>>  resultMap=new HashMap<String,ArrayList<Comment>>();
			resultMap.put("result", commentList);
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(message);
		
	}
	
}
