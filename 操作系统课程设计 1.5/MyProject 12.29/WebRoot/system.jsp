<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%!String blockNumber = "0";
   String diskSize = "0"; 
%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="entity.SysParameter"%>
<%@ page import="util.Jdbc"%>
<%@ page import="java.sql.*"%> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Welcome to system page.</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="style/systemPage.css" />
    <link rel="stylesheet" type="text/css" href="style/viewDevice.css" />
    <script src="scripts/utils.js" type="text/javascript"></script>
    <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="scripts/my_scripts.js" type="text/javascript"></script>
    <script src="scripts/systemPage.js" type="text/javascript"></script>
    <script src="scripts/viewDevice.js" type="text/javascript"></script>
    <script src="scripts/ByteBufferAB.min.js" type="text/javascript"></script>
    <script src="scripts/Long.min.js" type="text/javascript"></script>
    <script src="scripts/ProtoBuf.min.js" type="text/javascript"></script>
    <script src="scripts/protobuf.js" type="text/javascript"></script>

     <script language="javascript">  
    function addPerson(){    
        alert("Please continue!");  
	    $.getJSON("*.do",function call(data) {   
	        var list = data.person;
	        $.each(list, function(i, p) {   
            var row = $("#tr").clone();   
            row.find("#name").text(p.name);   
            row.find("#age").text(p.age);   
            row.find("#sex").text(p.sex);   
            row.find("#address").text(p.address.province + p.address.city + p.address.country);   
            row.appendTo("#tbody");   
        });      
	        
	    });   
    }      
</script> 

</head>

<body id="systemPage">
   <div id="viewDevice">
       <a id="viewDeviceImage" title="viewDeviceImage">viewDevice</a>
   </div>
   <div id="exitSystem">
       <a id="exitSystemImage" title="exitSystemImage" href="index.jsp">exitSystem</a>
   </div>
   <div id="userArea">
       <a id="userAreaImage" title="userAreaImage" href="userArea.jsp">userArea</a>
   </div>
   <div id="systemDisk">
       <img src="style/images/systemDisk.png"/>
       <div id="systemClock"></div>
   </div>
   <div id="virtualDisk">
       <img src="style/images/vDisk.png"/>
       <div id="relativeClock"></div>
   </div>
   <div id="systemDiskShow">
        <div id="textOne">
            <img src="style/images/txtImage.png"/>
            <label id="textOneName">job.txt</label>
            <label id="textOneSize">2 KB</label>
            <label id="textOneTime">2017-9-29</label>
            <a id="textOneImage"></a>
        </div>

       <div id="textTwo">
           <img src="style/images/txtImage.png"/>
           <label id="textTwoName">job2.txt</label>
           <label id="textTwoSize">3 KB</label>
           <label id="textTwoTime">2017-9-30</label>
           <a id="textTwoImage"></a>
       </div>

       <div id="textThree">
           <img src="style/images/txtImage.png"/>
           <label id="textThreeName">job3.txt</label>
           <label id="textThreeSize">5 KB</label>
           <label id="textThreeTime">2017-10-31</label>
           <a id="textThreeImage"></a>
       </div>

   </div>
   <div id="virtualDiskShow">
       <div id="jobOne" class="job">

       </div>

       <div id="jobTwo" class="job">

       </div>

       <div id="jobThree" class="job">

       </div>



   </div>
   <div id="virtualMemory">
       <div id="vMemoryAddressOne">
       <%-- <%
                   String  blockSize = "0";
                   int count = 0;
                   boolean isSecond = false;
				   Connection conn = Jdbc.connect();
				   Statement smt = conn.createStatement();
				   ResultSet rs;
				   String sql;
				   int collapseNumber = 1;
				   String collapse;
				   sql = "select * from configure";
				   rs = smt.executeQuery(sql);   
				   while(rs.next()){
                       blockNumber = rs.getString("blockNumber");
		               blockSize = rs.getString("blockSize");
		               diskSize = rs.getString("diskSize");
		               }
		           int bloNum = Integer.parseInt(blockNumber);
		           int bloSize = Integer.parseInt(blockSize);
		           for(int i = 0; i < Integer.parseInt(blockNumber); i++)
		           {
		             for(int j = 0;j<Integer.parseInt(blockSize);j++)
		             {
		                 out.print("<div id='addr"+i+j+"'"+">");
		                 out.print("<a id='addr"+i+j+"Content"+"'"+"></a>");
		                 out.print("<label id='address"+i+j+"'"+">"+i+":"+j+"</label>");
		                 out.print("<label id='content"+count+"'"+">null</label>");
		                 out.print("</div>");
		                 out.print("<br>");
		                 count++;
		                  if(count == 12)
		                 {
		                   out.print("</div>");
		                   out.print("<div id='vMemoryAddressTwo'>");
		                   isSecond = true;
		                 } 
		             }
		           }    
		           if(isSecond)
		           {
		             out.print("</div>");
		           }
		               
	   %> --%>
	   
             <div id="addr00">
               <a id="addr00Content"></a>
               <label id="address00">0:0</label>
               <label id="content0">null</label>
           </div>
           <br>

           <div id="addr01">
               <a id="addr01Content"></a>
               <label id="address01">0:1</label>
               <label id="content1">null</label>
           </div>
           <br>

           <div id="addr02">
               <a id="addr02Content"></a>
               <label id="address02">0:2</label>
               <label id="content2">null</label>
           </div>
           <br>

           <div id="addr03">
               <a id="addr03Content"></a>
               <label id="address03">0:3</label>
               <label id="content3">null</label>
           </div>
           <br>

           <div id="addr10">
               <a id="addr10Content"></a>
               <label id="address10">1:0</label>
               <label id="content4">null</label>
           </div>
           <br>

           <div id="addr11">
               <a id="addr11Content"></a>
               <label id="address11">1:1</label>
               <label id="content5">null</label>
           </div>
           <br>

           <div id="addr12">
               <a id="addr12Content"></a>
               <label id="address12">1:2</label>
               <label id="content6">null</label>
           </div>
           <br>

           <div id="addr13">
               <a id="addr13Content"></a>
               <label id="address13">1:3</label>
               <label id="content7">null</label>
           </div>
           <br>

           <div id="addr20">
               <a id="addr20Content"></a>
               <label id="address20">2:0</label>
               <label id="content8">null</label>
           </div>
           <br>

           <div id="addr21">
               <a id="addr21Content"></a>
               <label id="address21">2:1</label>
               <label id="content9">null</label>
           </div>
           <br>

           <div id="addr22">
               <a id="addr22Content"></a>
               <label id="address22">2:2</label>
               <label id="content10">null</label>
           </div>
           <br>

           <div id="addr23">
               <a id="addr23Content"></a>
               <label id="address23">2:3</label>
               <label id="content11">null</label>
           </div>
           <br>

           <div id="addr30">
               <a id="addr30Content"></a>
               <label id="address30">3:0</label>
               <label id="content12">null</label>
           </div>
           <br>
       </div>

       <div id="vMemoryAddressTwo">
           <div id="addr31">
               <a id="addr31Content"></a>
               <label id="address31">3:1</label>
               <label id="content13">null</label>
           </div>
           <br>

           <div id="addr32">
               <a id="addr32Content"></a>
               <label id="address32">3:2</label>
               <label id="content14">null</label>
           </div>
           <br>

           <div id="addr33">
               <a id="addr33Content"></a>
               <label id="address33">3:3</label>
               <label id="content15">null</label>
           </div>
           <br>

           <div id="addr40">
               <a id="addr40Content"></a>
               <label id="address40">4:0</label>
               <label id="content16">null</label>
           </div>
           <br>

           <div id="addr41">
               <a id="addr41Content"></a>
               <label id="address41">4:1</label>
               <label id="content17">null</label>
           </div>
           <br>

           <div id="addr42">
               <a id="addr42Content"></a>
               <label id="address42">4:2</label>
               <label id="content18">null</label>
           </div>
           <br>

           <div id="addr43">
               <a id="addr43Content"></a>
               <label id="address43">4:3</label>
               <label id="content19">null</label>
           </div>
           <br>

           <div id="addr50">
               <a id="addr50Content"></a>
               <label id="address50">5:0</label>
               <label id="content20">null</label>
           </div>
           <br>

           <div id="addr51">
               <a id="addr51Content"></a>
               <label id="address51">5:1</label>
               <label id="content21">null</label>
           </div>
           <br>

           <div id="addr52">
               <a id="addr52Content"></a>
               <label id="address52">5:2</label>
               <label id="content22">null</label>
           </div>
           <br>

           <div id="addr53">
               <a id="addr53Content"></a>
               <label id="address53">5:3</label>
               <label id="content23">null</label>
           </div>
           <br>

           <div id="addr60">
               <a id="addr60Content"></a>
               <label id="address60">6:0</label>
               <label id="content24">null</label>
           </div>
           <br>

           <div id="addr61">
               <a id="addr61Content"></a>
               <label id="address61">6:1</label>
               <label id="content25">null</label>
           </div>
           <br> 
           
       </div>
   </div>
   <div id="messageShow">
       <div id="processOne">
           <li class="processId"></li>
           <li class="processStatus"></li>
           <li class="processWaitTime"></li>
           <li class="processSchedule"></li>
       </div>

       <div id="processTwo">
           <li class="processId"></li>
           <li class="processStatus"></li>
           <li class="processWaitTime"></li>
           <li class="processSchedule"></li>
       </div>

       <div id="processThree">
           <li class="processId"></li>
           <li class="processStatus"></li>
           <li class="processWaitTime"></li>
           <li class="processSchedule"></li>
       </div>
   </div>
   <div id="statusWindow">
       <textarea id="statusWindowTA" cols="94" rows="8.5"></textarea>
   </div>
   <div id="virtualDisplay">
       <textarea id="virtualDisplayTA" cols="94" rows="8.5"></textarea>
   </div>
   <div id="virtualSize">
       <div id="virtualSize1">
           <label for="diskSize">虚拟硬盘容量:</label>
           <span id="diskSizeOne">0</span>
           <span>/</span>
           <%-- <span id="diskSizeTwo"><%out.print(diskSize);%></span> --%>
           <span id="diskSizeTwo">10</span>
           <canvas id="diskSize"></canvas>
       </div>
       <div id="virtualSize2">
           <label for="memorySize">虚拟内存容量:</label>
           <span id="memorySizeOne">0</span>
           <span>/</span>
           <%-- <span id="memorySizeTwo"><%out.print(blockNumber);%></span> --%>
           <span id="memorySizeTwo">10</span>
           <canvas id="memorySize"></canvas>
       </div>
   </div>
   <div id="messageWindow">
       <label  for="statusWindowTA" id="statusWindowLabel">中间结果显示窗口</label>
       <label  for="virtualDisplayTA" id="virtualScreenLabel">最终结果显示窗口</label>
       <input type="button" value="        清屏        " id="statusWindowButton" onclick="cleanStatusScreen()"/>
       <input type="button" value="        清屏        " id="virtualScreenButton" onclick="cleanvirtualScreen()"/>
   </div>
<!--  <input type="button" value="JsonView" onClick="addPerson();">      
<div id="dateMessage">      
    <table id="planTable" border="1">      
        <tr> 
            <td>Name</td> 
            <td>Sex</td> 
            <td>Age</td> 
            <td>Address</td> 
        </tr>      
        <tbody id="tbody"> 
            <tr id="tr"> 
                <td id="name"></td> 
                <td id="sex"></td> 
                <td id="age"></td> 
                <td id="address"></td> 
            </tr>      
        </tbody> 
    </table>      
</div> -->
</body>
</html>
