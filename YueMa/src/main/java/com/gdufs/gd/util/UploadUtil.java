package com.gdufs.gd.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class UploadUtil {

	public static String generateFileName() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 解析request 获取文件 依赖于common-io和common-upload 这两个包 支持多文件上传 实现多文件上传
	 * 返回文件对应的存储位置
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static HashMap<String, String> uploadFiles(HttpServletRequest request) {
		HashMap<String, String> paths = new HashMap<String, String>();// 保存文件的路径
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						String fileName = generateFileName() + myFileName;
						// 项目路径
						String defaultPath = request.getSession()
								.getServletContext().getRealPath("images");
						if (!FileUtil.isDirExist(new File(defaultPath))) {
							FileUtil.createDir(defaultPath);// 創建目錄
						}
						File localFile = new File(defaultPath + File.separator
								+ fileName);
						try {
							file.transferTo(localFile);
							paths.put(myFileName, "images/" + fileName);
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}

		} else {
			System.out.println("can not fund upload file");
		}
		return paths;
	}

	/**
	 * 单文件上传 返回文件对应的存储位置
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String uploadAFile(CommonsMultipartFile file,
			HttpServletRequest request) throws IOException {
		String path = "";
		if (!file.isEmpty()) {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(request.getSession()
							.getServletContext().getRealPath("images")
							+ file.getOriginalFilename()));
			InputStream in = file.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			int n = 0;
			byte[] b = new byte[1024];
			while ((n = bis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			bos.flush();
			bos.close();
			bis.close();
		}
		return path;
	}
	
	 /**
	  * 文件下载 主要方法
	  * @param request
	  * @param response
	  * @param storeName
	  * @param contentType
	  * @throws Exception
	  */
    public static void download(HttpServletRequest request,  
        HttpServletResponse response, String storeName, String contentType
         ) throws Exception {  
      
      request.setCharacterEncoding("UTF-8");  
      BufferedInputStream bis = null;  
      BufferedOutputStream bos = null;  
    
      //获取项目根目录
      String ctxPath = request.getSession().getServletContext()  
          .getRealPath("");  
      System.out.println(ctxPath);
      
      //获取下载文件
      String downLoadPath = ctxPath+"/apk/"+ storeName;  
    
      //获取文件的长度
      long fileLength = new File(downLoadPath).length();  

      //设置文件输出类型
      response.setContentType("application/octet-stream");  
      response.setHeader("Content-disposition", "attachment; filename="  
          + new String(storeName.getBytes("utf-8"), "ISO8859-1")); 
      //设置输出长度
      response.setHeader("Content-Length", String.valueOf(fileLength));  
      //获取输入流
      bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
      //输出流
      bos = new BufferedOutputStream(response.getOutputStream());  
      byte[] buff = new byte[2048];  
      int bytesRead;  
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
        bos.write(buff, 0, bytesRead);  
      }  
      //关闭流
      bis.close();  
      bos.close();  
    }  
}
