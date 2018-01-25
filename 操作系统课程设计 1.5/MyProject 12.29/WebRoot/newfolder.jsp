<%@page import="dao.FolderDao"%>
<%@page import="entity.Folder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String  folder_path="1";
String folder_name="新建文件夹";
  if(!(request.getParameter("path")==null)){
  folder_path=new String(request.getParameter("path").getBytes("iso-8859-1"), "utf-8");
  }
  if(!(request.getParameter("name")==null)){
  folder_name=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
  }
  Folder newfolder=new Folder();
  newfolder.setFolder_path(folder_path);
  newfolder.setFolder_name(folder_name);
  FolderDao.newFolder(newfolder);
  
  response.sendRedirect("createDirectory.jsp?path="+folder_path);
  

%>

