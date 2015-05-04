package com.gdufs.gd;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rossillo.spring.web.mvc.CacheControl;
import net.rossillo.spring.web.mvc.CachePolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.YMessage;
import com.gdufs.gd.entity.YMessageUser;
import com.gdufs.gd.entity.YUser;
import com.gdufs.gd.service.JPushServie;
import com.gdufs.gd.service.YMessageService;
import com.gdufs.gd.service.YUserService;
import com.gdufs.gd.util.IPAddressUtil;
import com.gdufs.gd.util.QRCodeUtil;
import com.google.zxing.WriterException;

/**
 * Handles requests for the application home page.
 */
@CacheControl
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Resource(name = "userService")
	private YUserService userService;

	@Resource(name = "messageService")
	private YMessageService messageService;
	
	@Resource(name = "jPushServie")
	private JPushServie jPushServie;
	
	
	@CacheControl(maxAge = 60, policy = { CachePolicy.PRIVATE })
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			final HttpServletResponse response, final HttpServletRequest request) {
		// 生成二维码
		try {
			projectEncodeQRCode(request);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initData();//初始化启动数据
		return "home";
	}

	/**
	 * 适应本项目的二维码生成
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public void projectEncodeQRCode(final HttpServletRequest request)
			throws WriterException, IOException {
		String content = "http://" + IPAddressUtil.getHostIp()
				+ ":8080/gd/file/downloadApk";// 内容
		String filePath = request.getSession().getServletContext()
				.getRealPath("resources");// 内容
		int width = 200; // 图像宽度
		int height = 200; // 图像高度
		String format = "png";// 图像类型
		String fileName = "downloadQRCode.png";
		QRCodeUtil.encodeQRCode(content, width, height, format, filePath,
				fileName);
		System.out.println(content);
		// System.out.println( QRCodeUtil.decodeQRCode(filePath+fileName));
	}
	/**
	 * 初始化数据
	 */
	private void initData(){
		YUser tester=userService.getUserById(1);
		if (tester==null) {
			// 创建新对象
			YUser user = new YUser();
			user.setPassword("111111");
			user.setPhoneNum("11111111111");
			user.setCreateTime(new Date());
			user.setFacePath(C.DefaultValues.DEFAULT_LOGO);
			user.setUserName("官方小助手");
			user.setGender("男");
			user.setOrigin("广外");
			user.setIntroduce("欢迎关注本应用");
			user.setLastLoginIp("127.0.0.1");
			user.setLastLoginTimeDate(new Date());
			userService.register(user);
		}
		
		
//		//欢迎的消息
//		// 消息实体
//		YMessage message = new YMessage();
//		message.setTitle("欢迎");//等待添加
//		message.setContent("欢迎使用悦吗手机应用，一起和朋友约起来！");
//		message.setCreateTime(new Date());
//		message.setCreator(user);
//		message.setType(C.DefaultValues.DEFAULT_MSG_TYPE_SYSTEM);// 1表示验证信息
//		messageService.add(message);
	}

}
