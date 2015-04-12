package com.gdufs.gd;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gdufs.gd.util.UploadUtil;

@Controller
@RequestMapping("/file")
public class UploadController {

	@RequestMapping("/index")
	public String index() {
		return "/upload";
	}

	/**
	 * 参数接收形式上传文件
	 */
	@RequestMapping("/upload")
	public String uploadFile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException {
		new UploadUtil().uploadAFile(file, request);
		return "/success";
	}

	/**
	 * springMVC封装的解析request上传文件（快捷、与springMVC很好结合，首选）
	 */
	@RequestMapping(value = "/upload2", method = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.PUT })
	@ResponseBody
	public String uploadFile2(HttpServletRequest request,@RequestParam("title") String title,
			HttpServletResponse response) throws IOException {
		new UploadUtil().uploadFiles(request, response).toString();
		return "success";
	}

}
