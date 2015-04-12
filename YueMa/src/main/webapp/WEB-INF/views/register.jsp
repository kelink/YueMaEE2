<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
	<form name="login" method="post" action="/gd/user/register">
		<input name="phoneNum" type="text">
		<input name="pwd" type="password">
		<input name="registerCode" type="text">
		<input name="submit" type="submit">
	</form>
</body>
</html>
