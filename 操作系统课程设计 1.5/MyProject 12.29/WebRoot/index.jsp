<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><!-- 这里是什么意思？？？？？？？？？？？？ -->
    
    <title>OS Simulator </title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="style/style.css" />
	
    <script src="scripts/utils.js" type="text/javascript"></script>
    <script src="scripts/schedule.js" type="text/javascript"></script>
    <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="scripts/moveEvent.js" type="text/javascript"></script>
    <script src="scripts/ProtoBuf.min.js" type="text/javascript"></script>
    <script src="scripts/protobuf.js" type="text/javascript"></script>
  </head>
  
  <body>
<div id="navigation">
<a id="computers"  title="computers">computer</a>
</div>
<div id="userAreaImages">
<a id="userAreas" title="userAreas" href="userArea.jsp">userArea</a>
</div>
</body>
</html>
