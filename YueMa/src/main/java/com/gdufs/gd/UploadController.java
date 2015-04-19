package com.gdufs.gd;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView uploadFile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException {
		UploadUtil.uploadAFile(file, request);
		return null;
	}

	/**
	 * springMVC封装的解析request上传文件（快捷、与springMVC很好结合，首选）
	 */
	@RequestMapping(value = "/upload2", method = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.PUT })
	@ResponseBody
	public String uploadFile2(HttpServletRequest request,
			@RequestParam("title") String title, HttpServletResponse response)
			throws IOException {
		UploadUtil.uploadFiles(request).toString();
		return "success";
	}
	
	/**
	 * 下载
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="/downloadApk")
    public ModelAndView downloadApk(HttpServletRequest request,  
    	      HttpServletResponse response) throws IOException{
    	 String storeName="YueMa.apk";
    	 String contentType = "application/octet-stream";  
    	 try {
			UploadUtil.download(request, response, storeName, contentType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;  
    }
   
}
