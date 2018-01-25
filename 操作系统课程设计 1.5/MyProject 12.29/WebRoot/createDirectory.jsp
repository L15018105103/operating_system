<%@page import="dao.FolderDao"%>
<%@page import="entity.Folder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户文件管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="scripts/createFile.js"></script>
    <script src="scripts/utils.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/createFile.css" rel="stylesheet">
    <script src="scripts/ProtoBuf.min.js" type="text/javascript"></script>
    <script src="scripts/protobuf.js" type="text/javascript"></script>
  </head>
  
  <script type="text/javascript">
  function gotopageX(){
 alert("hhhhhhhhh");
  var name=prompt("请输入文件夹名字","");
  if (name==null || name=="") {
         name="新建文件夹";
    }
  window.location.href="newfolder.jsp?path="+$("#thispath").val()+"&name="+name; 
  
  }
  
  function gotopageY(){
 alert("fdfdfd");
  
  
   window.location.href="createFile.jsp"; 
  
  }
  </script>
  
  <% 
  
  String  folder_path="1";
  if(!(request.getParameter("path")==null)){
  folder_path=request.getParameter("path");
  }
  
  ArrayList<Folder> fList=FolderDao.getcildrenFolder(folder_path);
  
  %>
  
  <body>
    <!--页眉-->
<div class="header">
    <nav class="navbar navbar-default " role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">用户文件管理系统</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">用户管理</a></li>
                    <li><a href="#">磁盘管理</a></li>
                    <li><a href="#">文件统计</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索">
                        </div>
                        <button type="submit" class="btn btn-default hidden">Submit</button>
                    </form>
                    <li><a href="#">未审核文件 <span class="badge">5</span></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Angelo<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">我删除的文件</a></li>
                            <li><a href="#">我修改的文件</a></li>
                            <li><a href="#">我恢复的文件</a></li>
                            <li class="divider"></li>
                            <li><a href="#">注销</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="container">
    <div class="row">
        <!--左侧目录-->
        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
            <div class="list-group">
                <a href="userArea.jsp" class="list-group-item">文件审核</a>
                <a href="createFile.jsp" class="list-group-item">文件创建</a>
                <a href="#" class="list-group-item">文件拷贝</a>
                <a href="newfolder.jsp" class="list-group-item active">目录创建</a>
                <a href="#" class="list-group-item">目录删除</a>
            </div>
        </div>
        <!--右侧主要内容-->
         <div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>目录创建</h4>
                  <div class="form-group">
                 <span>全选 <input type="checkbox"/></span>
                <button class="btn btn-success" onclick="gotopageX()">新建文件夹</button>
                <a class="btn btn-primary" href="createFile.jsp">新建文件</a>
              </div>
                </div>
                
                 <div data-role="content" data-theme="a">
                  <div class="panel-body">
                  <input type="hidden" id="thispath" value="<%=folder_path%>"/>
              <table class="table">
                <tr>
                  <th>名称</th>
                  <th>类型</th>
                  <th>大小</th>
                  <th>修改时间</th>
                </tr>
                <%for(Folder f:fList){ %>
                <tr>
                  <td> <a href="createDirectory.jsp?path=<%=f.getFolder_path()%>"><img   src="style/images/folder.png"   ></a><%=f.getFolder_name()%></td>
                  <td><a href="createDirectory.jsp?path=<%=f.getFolder_path()%>">文件夹</a></td>
                  <td><a href="createDirectory.jsp?path=<%=f.getFolder_path()%>"><%=FolderDao.getcildrenFolder(f.getFolder_path()).size()%>个子目录</a></td>
                  <td>2017年11月8日 08:08</td>
                  
                </tr>
                   <%} %> 
                  </table>  
                  <div>
                    
                </div> 
                
            </div>
        </div> 
    </div>
</div>
<!-- FOOTER -->
<footer>
    <p style="text-align: center">&copy; Postscript &copy; This is a simple file management system. Designed by Angelo</p>
</footer>
  </body>
</html>
