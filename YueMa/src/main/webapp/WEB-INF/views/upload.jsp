<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<script type="text/javascript">

</script>
</head>
<body>
<!-- 
		activity.setTitle("中心湖");
		activity.setIntroduce("骑单车");
		activity.setCreateTimeDate(new Date());
		activity.setBeginTime(new Date());
		activity.setEndTime(new Date());
		activity.setPerCost(10.5);
		activity.setBulkLink("http://www.baidu.com");
		activity.setActivityAddress("中心湖");
		activity.setActivityAddressLatitude("15.25685");
		activity.setActivityAddressLongitude("18.25685");
		activity.setCollectAddress("中行");
		activity.setCollectAddressLatitude("465.06548465");
		activity.setCollectAddressLongitude("465.06548465");
		activity.setMaxCount(10);
		activity.setCreator(creator);
		activityService.add(activity);

 -->
	<h4>上传文件1</h4>
	<form name="userForm" action="/gd/file/upload2" method="post" enctype="multipart/form-data" >
		title:<input type="text" name="title"/><br/>
	
		Begin time:<input type="text" name="beginTime"/><br/>
		End time:<input type="text" name="endTime"/><br/>
		perCost<input type="text" name="perCost"/><br/>
		BulkLink:<input type="text" name="bulkLink"/><br/>
		ActivityAddress:<input type="text" name="activityAddress"/><br/>
		activityAddressLatitude:<input type="text" name="activityAddress"/><br/>
		ActivityAddressLongitude:<input type="text" name="activityAddress"/><br/>
		CollectAddress:<input type="text" name="collectAddress"/><br/>
		CollectAddressLatitude:<input type="text" name="collectAddress"><br/>
		CollectAddressLongitude:<input type="text" name="collectAddress"/><br/>
		MaxCount:<input type="text" name="maxCount"/><br/>
		creatorId:<input type="text" name="uId"/>
		选择文件：<input type="file" name="file"><br/>
			  <input type="submit" value="上传" >
	</form>
</body>
</html>