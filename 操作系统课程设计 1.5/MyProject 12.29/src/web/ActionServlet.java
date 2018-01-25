package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SysParameterDao;
import dao.UserFileDao;
import entity.Address;
import entity.Person;
import entity.SysParameter;
import entity.UserFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Jdbc;



public class ActionServlet extends HttpServlet {

SysParameter sysParameter = new SysParameter();

public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	doPost(request, response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");
	
	String uri = request.getRequestURI();
	uri = uri.substring(uri.indexOf("MyProject/") + 10, uri.lastIndexOf("."));//查一下这句话是什么意思
    System.out.println(uri); 
	
	if(uri.equals("show")){
		System.out.println("use the computer");
	try{
		//show(request, response);
		show(request,response);
	} catch(Exception e){
		e.printStackTrace();
	}
	}
	
	if(uri.equals("show1")){
		System.out.println("use the File");
	try{
		show1(request,response);
	} catch(Exception e){
		e.printStackTrace();
	}
	}
	
	
	try {
        //鍒濆鍖杙erson瀵硅薄
		Person person1 = new Person();
		person1.setName("Gogo");
		person1.setSex("man");
		person1.setAge(23);
		Address address =  new Address("china", "guangdong", "guangzhou");
		person1.setAddress(address);
        //娣诲姞鍒板垪琛�
		List<Person> list = new ArrayList<Person>();
		list.add(person1);
		list.add(person1);

		//json鏁扮粍瀵硅薄锛屽瓨鏀緇ist
		JSONArray jsonArray = JSONArray.fromObject(list);
        //json瀵硅薄锛岀敤浜庢墦鍗颁紶缁欓〉闈�
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("person", jsonArray);

		response.getWriter().write(jsonObject.toString());

	} catch (Exception e) {
		e.printStackTrace();
	}
}

/*public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
response.setContentType("text/html;charset=utf-8");
request.setCharacterEncoding("utf-8");
String uri = request.getRequestURI();
uri = uri.substring(uri.indexOf("MyProject/") + 10, uri.lastIndexOf("."));//查一下这句话是什么意思

if(uri.equals("show")){
	System.out.println("use the computer");
try{
	//show(request, response);
	show(request,response);
} catch(Exception e){
	e.printStackTrace();
}
}

}

	
*/
//进程管理初始化窗口
public void show(HttpServletRequest request, HttpServletResponse response)
throws Exception{
	
String clock = request.getParameter("clocks");
String blockNumber = request.getParameter("blockNumbers");//*****
String blockSize = request.getParameter("blockSizes");//*****
String hardDisk = request.getParameter("diskSizes");//*****
String pageCount = request.getParameter("pageNumbers");
String timeSlice = request.getParameter("timeSlices");
String pageBuffer = request.getParameter("pageBuffers");
String blockId = "1";

sysParameter.setClock(clock);
sysParameter.setBlockNumber(blockNumber);
sysParameter.setBlockSize(blockSize);
sysParameter.setHardDisk(hardDisk);
sysParameter.setPageCount(pageCount);
sysParameter.setTimeSlice(timeSlice);
sysParameter.setPageBuffer(pageBuffer);

System.out.println(sysParameter.getClock());
System.out.println(sysParameter.getBlockNumber());
System.out.println(sysParameter.getBlockSize());
System.out.println(sysParameter.getHardDisk());
System.out.println(sysParameter.getPageCount());
System.out.println(sysParameter.getTimeSlice());
System.out.println(sysParameter.getPageBuffer());
int point = 0;

/*try{
	   Connection conn=Jdbc.connect();
	   Statement smt = conn.createStatement();
	   ResultSet rs;
	   String sql1;
	   sql1 = "select * from messa";
	   rs = smt.executeQuery(sql1);
	   while (rs.next()) {
		   point++;
	   }
	   //System.out.println(point+"啊啊啊啊55555555555555555555555555555555");
	   String sql="update configure set blockNumber =?,blockSize=?,diskSize=? where blockId =?";
	   PreparedStatement pst=conn.prepareStatement(sql);
	    pst.setString(1,blockNumber);
	    pst.setString(2,blockSize);
	    pst.setString(3,hardDisk);
	    pst.setString(4,blockId);
	    int result =pst.executeUpdate();
	     if(result>0)
	       {
	        System.out.println("系统初始配置成功！");
	       }else{
	          System.out.println("系统初始配置失败！");
	          }
	   }catch(Exception ex){
	     ex.printStackTrace();
	    }
//根据fileDate查询
List<SysParameter> SysParameterList = new SysParameterDao().search(blockId);

request.setAttribute("SysParameterList",SysParameterList);*/
request.getRequestDispatcher("system.jsp").forward(request, response);
}


//文件管理系统
public void show1(HttpServletRequest request, HttpServletResponse response)
throws Exception{
	
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
String fileName = request.getParameter("fileName");
String fileContent = request.getParameter("fileContent");
String fileDate = df.format(new Date());
String fileSize = "0KB";
String fileStatue = "未审核";
String folder_path = request.getParameter("fileDirectory");
int point = 1;
String count = null;


String [] fileContentArray = fileContent.split(";");
fileSize = fileContentArray.length + "KB";
//System.out.println(fileSize + "55555555555555555555555555555555555555555555");
//System.out.println(name+" "+memo+" "+time+"4444444444444444444444");
try{
	   Connection conn=Jdbc.connect();
	   Statement smt = conn.createStatement();
	   ResultSet rs;
	   String sql1;
	   sql1 = "select * from messa";
	   rs = smt.executeQuery(sql1);
	   while (rs.next()) {
		   point++;
	   }
	   //System.out.println(point+"啊啊啊啊55555555555555555555555555555555");
	   String sql="insert into filetable(fileStatus,fileName,fileSize,fileDate,fileContent,folder_path) values(?,?,?,?,?,?)";
	   PreparedStatement pst=conn.prepareStatement(sql);
	    pst.setString(1,fileStatue);
	    pst.setString(2,fileName);
	    pst.setString(3,fileSize);
	    pst.setString(4,fileDate);
	    pst.setString(5,fileContent);
	    pst.setString(6,folder_path);
	    int result =pst.executeUpdate();
	     if(result>0)
	       {
	        System.out.println("文件创建成功！");
	       }else{
	          System.out.println("文件创建失败！");
	          }
	   }catch(Exception ex){
	     ex.printStackTrace();
	    }
//根据fileDate查询
List<UserFile> userFileList = new UserFileDao().search(fileDate);

request.setAttribute("userFileList",userFileList);
request.getRequestDispatcher("userArea.jsp").forward(request, response);
}

}



