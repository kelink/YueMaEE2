package com.gdufs.gd;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.entity.YLike;
import com.gdufs.gd.service.YLikeService;
import com.gdufs.gd.util.JacksonUtil;

@Controller
@RequestMapping("/like")
public class LikeController {

	@Resource(name = "likeService")
	private YLikeService likeService;
	
	@RequestMapping(value = "/like", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String like(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "0") int userId,
			@RequestParam(value = C.ParamsName.AID, required = false, defaultValue = "0") int activityId){
		TransferMessage message = new TransferMessage();
		if (userId==0||activityId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {	
			YLike like=new YLike();
			like.setActivityId(activityId);
			like.setUserId(userId);
			if (likeService.addLike(like)) {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			}else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			}
			
		}	
		return JacksonUtil.writeEntity2JSON(message);
	}
	
	@RequestMapping(value = "/unLike", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String unLike(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam(value = C.ParamsName.UID, required = true, defaultValue = "0") int userId,
			@RequestParam(value = C.ParamsName.AID, required = false, defaultValue = "0") int activityId){
		TransferMessage message = new TransferMessage();
		if (userId==0||activityId==0) {
			message.setCode(C.ResponseCode.ERROR);
			message.setMessage(C.ResponseMessage.ERROR);
			message.setResultMap(null);
		} else {	
			if (likeService.cancleLike(activityId, userId)) {
				message.setCode(C.ResponseCode.SUCCESS);
				message.setMessage(C.ResponseMessage.SUCCESS);
				message.setResultMap(null);
			}else {
				message.setCode(C.ResponseCode.ERROR);
				message.setMessage(C.ResponseMessage.ERROR);
				message.setResultMap(null);
			}

		}	
		return JacksonUtil.writeEntity2JSON(message);
	}
}
