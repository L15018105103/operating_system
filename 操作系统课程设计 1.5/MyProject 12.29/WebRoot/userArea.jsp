<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="entity.UserFile"%>
<%@ page import="util.Jdbc"%>
<%@ page import="java.sql.*"%>

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
            <a href="userArea.jsp" class="list-group-item active">文件审核</a>
            <a href="createFile.jsp" class="list-group-item">文件创建</a>
            <a href="#" class="list-group-item">文件拷贝</a>
            <a href="createDirectory.jsp" class="list-group-item">目录创建</a>
            <a href="#" class="list-group-item">目录删除</a>
          </div>
        </div>
        <!--右侧主要内容-->
        <div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4>文件审核</h4>
              <div class="form-group">
                <span>全选 <input type="checkbox"/></span>
                <button class="btn btn-success">通过</button>
                <button class="btn btn-primary">恢复</button>
                <button class="btn btn-danger">删除</button>
                <ul class="pagination visible-md visible-lg visible-sm" id="page-right">
                  <li><a href="#">&laquo;</a></li>
                  <li class="active"><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&raquo;</a></li>
                </ul>
              </div>
            </div>
            <div class="panel-body">
              <table class="table">
                <tr>
                  <th></th>
                  <th>审核状态</th>
                  <th>文件名称</th>
                  <th>文件大小</th>
                  <th>创建时间</th>
                  <th>文件内容</th>
                </tr>
                <%
                   String fileStatus, fileName, fileSize,fileDate,fileContent;
				   Connection conn = Jdbc.connect();
				   Statement smt = conn.createStatement();
				   ResultSet rs;
				   String sql;
				   int collapseNumber = 1;
				   String collapse;
				   sql = "select * from filetable order by fileDate";
				   rs = smt.executeQuery(sql);   
				   while(rs.next()){
                       fileStatus = rs.getString("fileStatus");
		               fileName = rs.getString("fileName");
		               fileSize = rs.getString("fileSize");
		               fileDate = rs.getString("fileDate");
		               fileContent = rs.getString("fileContent");
		               collapse = "collapse" + collapseNumber;
		               
		               out.print("<tr>");
		               out.print("<td><input type='checkbox'/></td>");
		               if(fileStatus.equals("未审核"))
		               {
		                  out.print("<td><span class='label label-default'>未审核</span></td>");
		               }
		               else if(fileStatus.equals("已通过"))
		               {
		                  out.print("<td><span class='label label-success'>已通过</span></td>");   
		               }
		               else if(fileStatus.equals("已删除")){
		                  out.print("<td><span class='label label-danger'>已删除</span></td>");
		               }
		               else{
		                  out.print("<td><span class='label label-primary'>已恢复</span></td>");
		               }
		               out.print("<td><a >" + fileName +"</a></td>");
		               out.print("<td><a >"+ fileSize +"</a></td>");
		               out.print("<td>"+ fileDate +"</td>");
		               out.print("<td>");
		               
		               /* if(collapseNumber == 1)
		               {
		                  out.print("<a class='detail-link' data-toggle='collapse' data-parent='#accordion' href='#collapse1'>");
		               }
		               else if(collapseNumber == 2)
		               {
		                  out.print("<a class='detail-link' data-toggle='collapse' data-parent='#accordion' href='#collapse2'>");
		               }
		               else
		               {
		                  out.print("<a class='detail-link' data-toggle='collapse' data-parent='#accordion' href='#collapse3'>");
		               } */
		               
		               
		               out.print("<a class='detail-link' data-toggle='collapse' data-parent='#accordion' href='#"+collapse+"'"+">"); 
		               out.print("<span class='glyphicon glyphicon-chevron-down'></span>");
		               out.print("</a>");
		               out.print("</td>");
		               out.print("</tr>");
		             /*   if(collapseNumber == 1)
		               {
		                  out.print("<tr id='collapse1' class='collapse'>");
		               }
		               else if(collapseNumber == 2)
		               {
		                  out.print("<tr id='collapse2' class='collapse'>");
		               }
		               else
		               {
		                  out.print("<tr id='collapse3' class='collapse'>");
		               } */
		               out.print("<tr id="+"'"+collapse+"'"+" class='collapse'>"); 
		               out.print("<td colspan='10'>"+ fileContent +"</td>");
		               out.print("</tr>");
		               collapseNumber++;
                   } 
                %>
                <!-- 从这里开始吗？ -->
                <!--  <tr>
                  <td><input type="checkbox"/></td>
                  <td><span class="label label-default">未审核</span></td>
                  <td><a href="#">job1.txt</a></td>
                  <td><a href="#">1KB</a></td>
                  <td>2017年11月8日 08:08</td>
                  <td>
                    <a class="detail-link" data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                      <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                  </td>
                </tr>
                <tr id="collapse1" class="collapse">
                  <td colspan="10">
                    mov ax,0. mov bx,0. add ax,bx. sub ax,bx. use deviceA. end.
                  </td>
                </tr>
                <!-- 到这里结束吗？？？ -->
                <!--  <tr>
                  <td><input type="checkbox"/></td>
                  <td><span class="label label-default">未审核</span></td>
                  <td><a href="#">job2.txt</a></td>
                  <td><a href="#">1KB</a></td>
                  <td>2017年11月8日 10:10</td>
                  <td>
                    <a class="detail-link" data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                      <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                  </td>
                </tr>
                <tr id="collapse2" class="collapse">
                  <td colspan="10">
                    mov ax,0. mov bx,0. add ax,bx. sub ax,bx. use deviceA. end.
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td><span class="label label-success">已通过</span></td>
                  <td><a href="#">job3.txt</a></td>
                  <td><a href="#">1KB</a></td>
                  <td>2017年11月18日 10:38</td>
                  <td>
                    <a class="detail-link" data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                      <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                  </td>
                </tr>
                <tr id="collapse3" class="collapse">
                  <td colspan="10">
                    mov ax,0. mov bx,0. add ax,bx. sub ax,bx. use deviceA. end.
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td><span class="label label-danger">已删除</span></td>
                  <td><a href="#">job4.txt</a></td>
                  <td><a href="#">1KB</a></td>
                  <td>2017年11月18日 15:50</td>
                 <td>
                    <a class="detail-link" data-toggle="collapse" data-parent="#accordion" href="#collapse4">
                      <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                  </td>
                </tr>
                <tr id="collapse4" class="collapse">
                  <td colspan="10">
                    mov ax,0. mov bx,0. add ax,bx. sub ax,bx. use deviceA. end.
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td><span class="label label-primary">已恢复</span></td>
                  <td><a href="#">job5.txt</a></td>
                  <td><a href="#">1KB</a></td>
                  <td>2017年11月20日 11:11</td>
                  <td>
                    <a class="detail-link" data-toggle="collapse" data-parent="#accordion" href="#collapse5">
                      <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                  </td>
                </tr>
                <tr id="collapse5" class="collapse">
                  <td colspan="10">
                    mov ax,0. mov bx,0. add ax,bx. sub ax,bx. use deviceA. end.
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox"/></td>
                  <td><span class="label label-success">已通过</span></td>
                  <td><a href="#">job6.txt</a></td>
                  <td><a href="#">1KB</a></td>
                  <td>2017年11月24日 09:58</td>
                  <td>
                    <a class="detail-link" data-toggle="collapse" data-parent="#accordion" href="#collapse6">
                      <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                  </td>
                </tr>
                <tr id="collapse6" class="collapse">
                  <td colspan="10">
                    mov ax,0. mov bx,0. add ax,bx. sub ax,bx. use deviceA. end.
                  </td>
                </tr>-->
              </table>
              <div class="visible-xs">
                <ul class="pagination">
                  <li><a href="#">&laquo;</a></li>
                  <li class="active"><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&raquo;</a></li>
                </ul>
              </div>
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
