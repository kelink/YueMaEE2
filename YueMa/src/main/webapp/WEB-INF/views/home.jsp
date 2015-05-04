<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="layout.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/layout.css" rel="stylesheet">
<title>app下载页</title>
</head>

<body>
<div class="left">
	<div class="main">
    	<div class="title">
        	<img src="<%=request.getContextPath()%>/resources/logo.png" />
            <span>悦吗</span>
        </div>
        <div class="text">
        	<ul>
            	<li>在熟悉的圈子中挖掘潜在人脉</li>
                <li>通过活动参与方式拓展人脉</li>
                <li>活动交友两不误，从此爱上“悦吗”</li>
            </ul>
        </div>
    </div>
</div>


<div class="right">
	<div class="code-area">
    	<p>叫上你的小伙伴一起吃喝玩乐吧！</p>
        <div class="code">
        	<a href="file/downloadApk"><img src="resources/downloadQRCode.png"  alt="扫一扫下载" /></a>
            <p>手机扫描二维码下载</p>
        </div>
    </div>
</div>

<footer>
	<center>
		<p>copyright@悦吗YueMa App</p>
	</center>
</footer>

</body>
</html>
