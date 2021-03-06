package com.gdufs.gd;

import java.util.ArrayList;
import java.util.Date;
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
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.response.modle.Comment;
import com.gdufs.gd.response.modle.FriendsActivityObj;
import com.gdufs.gd.service.YActivityService;
import com.gdufs.gd.service.YCommentService;
import com.gdufs.gd.service.YContactService;
import com.gdufs.gd.service.YUserService;
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
	private static final Logger logger = LoggerFactory
			.getLogger(CommentController.class);

	@Resource(name = "commentService")
	private YCommentService commentService;

	@Resource(name = "activityService")
	private YActivityService activityService;

	@Resource(name = "userService")
	private YUserService userService;

	/**
	 * 新添加一个评论
	 * @param request
	 * @param response
	 * @param uId
	 * @param activityId
	 * @param commentContent
	 * @param fatherCommentId
	 * @return
	 */
	@RequestMapping(value = "/newComment", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String newComment(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "") int uId,
			@RequestParam(value = C.ParamsName.AID, required = true, defaultValue = "") int activityId,
			@RequestParam(value = C.ParamsName.COMMENT_CONTENT, required = true, defaultValue = "") String commentContent,
			@RequestParam(value = C.ParamsName.COMMENT_FATHER, required = false, defaultValue = "0") String fatherCommentId) {
		TransferMessage message = new TransferMessage();
		if (uId == 0 || activityId == 0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			YComment comment = new YComment();
			comment.setActivity(activityService
					.getActivityByActivityId(activityId));
			comment.setUser(userService.getUserById(uId));
			comment.setCommentPath("");
			comment.setContent(commentContent);
			comment.setCreateTime(new Date());
			comment.setFatherCommentId(0);
			comment.setIsDelete(0);
			if (commentService.add(comment)) {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			} else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.WRONG_USER_INFO);
				message.setResultMap(null);
			}
		}
		return JacksonUtil.writeEntity2JSON(message);
	}

	/**
	 * 删除一个评论
	 * @param request
	 * @param response
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value = "/deleteComment", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String deleteComment(
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.CID, required = true, defaultValue = "0") int commentId) {
		TransferMessage message = new TransferMessage();
		if (commentId == 0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			if (commentService.delete(commentId)) {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			} else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.WRONG_USER_INFO);
				message.setResultMap(null);
			}

		}
		return JacksonUtil.writeEntity2JSON(message);
	}

	/**
	 * 获取活动的评论
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value = "/getActivityComments", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getActivityComments(
			@RequestParam(value = C.ParamsName.AID, defaultValue = "0") int activityId) {
		TransferMessage message = new TransferMessage();
		if (activityId == 0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {
			List<YComment> comments = commentService
					.getCommentsByActivityId(activityId);
			ArrayList<Comment> commentList = new ArrayList<Comment>();
			for (YComment comment : comments) {
				Comment itemComment = new Comment();
				itemComment.setCommentPath(comment.getCommentPath());
				itemComment.setContent(comment.getContent());
				itemComment.setCreateTime(DateUtil.formatDateTime(comment
						.getCreateTime()));
				itemComment.setFatherCommentId(String.valueOf(comment
						.getFatherCommentId()));
				itemComment.setID(String.valueOf(comment.getId()));
				itemComment.setUserFace(comment.getUser().getFacePath());
				itemComment
						.setUserId(String.valueOf(comment.getUser().getId()));
				commentList.add(itemComment);
			}
			HashMap<String, ArrayList<Comment>> resultMap = new HashMap<String, ArrayList<Comment>>();
			resultMap.put("result", commentList);
			message.setCode(C.ResponseCode.SUCCESS);
			message.setMessage(C.ResponseMessage.SUCCESS);
			message.setResultMap(resultMap);
		}
		return JacksonUtil.writeEntity2JSON(message);

	}

}
