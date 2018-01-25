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
    <script src="scripts/createFile.js"></script>
    <script src="scripts/utils.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/createFile.css" rel="stylesheet">
    <script src="scripts/ProtoBuf.min.js" type="text/javascript"></script>
    <script src="scripts/protobuf.js" type="text/javascript"></script>
  </head>
  
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
                <a href="createFile.jsp" class="list-group-item  active">文件创建</a>
                <a href="#" class="list-group-item">文件拷贝</a>
                <a href="newfolder.jsp" class="list-group-item">目录创建</a>
                <a href="#" class="list-group-item">目录删除</a>
            </div>
        </div>
        <!--右侧主要内容-->
         <div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>文件创建</h4>
                </div>
                <div data-role="content" data-theme="a">
                    <form action="show1.do" method="post">
                    <%
								String folder_path = "1";
								if (!(request.getParameter("path") == null)) {
									folder_path = request.getParameter("path");
								}
							%>
							<%-- <div data-role="fieldcontain" class="fileDiv">
								<label for="fileName">文件路径：</label> <input type="text"
									name="lname" disabled="disabled" value=<%=folder_path%>><br />
							</div> --%>
                        <div data-role="fieldcontain" class="fileDiv">
                            <label for="fileDirectory">文件路径：</label>
                            <input type="text" name="fileDirectory" id="fileDirectory"  value=<%=folder_path%>  class="form-control" readonly="readonly"/>
                        </div>
                        <div data-role="fieldcontain" class="fileDiv">
                            <label for="fileName">文件名称：</label>
                            <input type="text" name="fileName" id="fileName"  value=""  class="form-control"/>
                        </div>
                        <div data-role="fieldcontain" class="fileDiv">
                            <label for="fileContent" id="fileValue">文件内容：</label>
                            <textarea cols="22" rows="5" name="fileContent" id="fileContent" class="form-control"></textarea>
                        </div>
                        <div data-role="fieldcontain" class="fileDiv">
                            <label for="slider2">可执行文件：</label>
                            <select name="slider2" id="slider2" data-role="slider" class="">
                                <option value="on" name="fileYes" id="fileYes">是</option>
                                <option value="off" name="fileNo" id="fileNo">否</option>
                            </select>
                        </div>
                        <div class="fileDiv" id="buttonDiv">
                            <fieldset class="ui-grid-a">
                                <div class="ui-block-a"><button type="reset" data-theme="a" id="buttonReset" class="btn btn-danger">重置</button></div>
                                <div class="ui-block-b"><button type="submit" data-theme="d" id="buttonSubmit" class="btn btn-success">提交</button></div>
                            </fieldset>
                        </div>
                    </form>
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
