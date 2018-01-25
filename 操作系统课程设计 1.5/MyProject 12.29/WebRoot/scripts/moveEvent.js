function b() {
    //创建遮罩层div并插入body
    var mask = document.createElement("div");
    mask.id = "mask";
    mask.style.height = cheight + "px";
    //宽度直接用100%在样式里
    document.body.appendChild(mask);

    //创建登录层div并插入body
    var login = document.createElement("div");
    login.id = "login";
    login.innerHTML = '<div class="title" id="title">初始化窗口' +
        '<a href="#" id="close"></a>' +
        '</div>' +
        '<form action="show.do" method="post">' +
        '<div class="content">' +
        '<div class="user">' +
        '<input class="pt" type="input" id="clocks" name="clocks" value="1000">' +
        '</div>' +
        '<div class="password">' +
        '<input class="pt" type="input" id="blockNumbers" name="blockNumbers" value="6">' +
        '</div>' +
        '<div class="blockSize">' +
        '<input class="pt" type="input" id="blockSizes" name="blockSizes" value="4">' +
        '</div>' +
        '<div class="diskSize">' +
        '<input class="pt" type="input" id="diskSizes" name="diskSizes" value="10">' +
        '</div>' +
        '<div class="pageNumber">' +
        '<input class="pt" type="input" id="pageNumbers" name="pageNumbers" value="2">' +
        '</div>' +
        '<div class="timeSlice">' +
        '<input class="pt" type="input" id="timeSlices" name="timeSlices" value="1">' +
        '</div>' +
        '<div class="pageBuffer">' +
        '<input class="pt" type="input" id="pageBuffers" name="pageBuffers" value="5">' +
        '</div>' +
        '<div class="submit">' +
        '<input id="confirm" class="sm" type="submit" value="确定">' +
        '</div>' +
        '</div>'+
        '</form>' +
        '<div id="enterLabel">\n' +
        '            <label>系统时钟:</label><br>\n' +
        '            <br> <label>内存块数(<9999):</label><br>\n' +
        '            <br> <label>内存块的大小(<999):</label><br>\n' +
        '            <br> <label>硬盘大小:</label><br>\n' +
        '            <br> <label>每个作业分配的页面数:</label><br>\n' +
        '            <br> <label>每个进程分配的时间片:</label><br>\n' +
        '            <br> <label>页表缓冲:</label>\n' +
        '        </div>';
    document.body.appendChild(login);

    //窗口可视区域宽度
    var cwidth = document.documentElement.clientWidth || document.body.clientWidth;
    //窗口可视区域高度
    var cheight = document.documentElement.clientHeight || document.body.clientHeight;
    //登录框宽度
    var lwidth = login.offsetWidth;
    //登录框高度
    var lheight = login.offsetHeight;

    //设置登录框的居中显示
    login.style.left = (cwidth - lwidth) / 2 + "px";
    login.style.top = (cheight - lheight) / 2 + "px";
    //设置遮罩层的高度
    mask.style.height = cheight + "px";
    //改变窗口大小后依然居中显示
    window.onresize = function () {
        if (document.compatMode == "CSS1Compat") {
            cwidth = document.documentElement.clientWidth;
            cheight = document.documentElement.clientHeight;
        } else {
            cwidth = document.body.clientWidth;
            cheight = document.body.clientHeight;
        }
        login.style.left = (cwidth - lwidth) / 2 + "px";
        login.style.top = (cheight - lheight) / 2 + "px";
        mask.style.height = cheight + "px";
    }
    //获取拖拽容器
    var title = document.getElementById("title");
    var isDraging = false;
    var mouseOffsetX;
    var mouseOffsetY;
    //鼠标按下事件
    title.onmousedown = function (e) {
        var e = e || window.event;
        /*var el=e.srcElement;
        if(!el){
            el=e.target;//兼容火狐
        }*/
        //鼠标相对于登录框的位置
        mouseOffsetX = e.pageX - login.offsetLeft;
        mouseOffsetY = e.pageY - login.offsetTop;
        //鼠标摁下时为true
        isDraging = true;
        /*console.log(mouseOffsetY, mouseOffsetX)*/
    }
    //鼠标移动事件
    document.onmousemove = function (e) {
        var e = e || window.event;
        //鼠标移动时的坐标
        var newMX = e.pageX;
        var newMY = e.pageY;
        //判断为true时可以拖拽
        if (isDraging === true) {
            //登录框的偏移值=当前位置-鼠标到登录框的距离
            var loginL = newMX - mouseOffsetX;
            var loginT = newMY - mouseOffsetY;
            //如果left top值超过边缘时就让他等于边缘
            if (loginL < 0) {
                loginL = 0;
            } else if (loginL > (cwidth - lwidth)) {
                loginL = cwidth - lwidth;
            }
            if (loginT < 0) {
                loginT = 0;
            } else if (loginT > (cheight - lheight)) {
                loginT = cheight - lheight;
            }

            login.style.left = loginL + "px";
            login.style.top = loginT + "px";

        }
    }
    //鼠标弹起时设置为不可拖拽
    document.onmouseup = function () {
        isDraging = false;
    }
    //点击X关闭登录框和弹出层
    var close = document.getElementById("close");
    close.onclick = function () {
        document.body.removeChild(mask);
        document.body.removeChild(login);
    }
    addEventHandler(close, "mouseover", buttonOver);
    addEventHandler(close, "mouseout", buttonOut);
    
    //开机的配置的链接
    protobuf.load("protoFile/ConfigureMessage.proto", function (err, root) {
        if (err) throw err;
        ConfigureMessage = root.lookup("protoFile.ConfigureMessage");
    });
    var confirm = document.getElementById("confirm");
    addEventHandler(confirm, "click", configureThread);
}

//点击登录弹出登录框和弹出层
window.onload = function () {
    var computers = document.getElementById("computers");
    computers.onclick = function () {
        b();
    }
    addEventHandler(computers, "mouseover", buttonOver);
    addEventHandler(computers, "mouseout", buttonOut);
    
    var userAreas = document.getElementById("userAreas");
    addEventHandler(userAreas, "mouseover", buttonOver);
    addEventHandler(userAreas, "mouseout", buttonOut);
}
function buttonOver(e) {
    var me = getActivatedObject(e);
    me.className = "active";
}
function buttonOut(e) {
    var me = getActivatedObject(e);
    me.className = "";
}
function configureThread()
{
	if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }
    if (window.WebSocket) {
        connected = true;
        //alert("window.Websocket");
        //socket = new WebSocket("ws://a784068941.imwork.net:7778/ws");
        socket = new WebSocket("ws://127.0.0.1:7778/ws");//本地的端口。
        //socket = new WebSocket("ws://172.18.174.219:7778/ws");/*大电脑*/
        //socket = new WebSocket("ws://172.16.31.221:7778/ws");//小电脑
        socket.binaryType = 'arraybuffer';
        //alert("socketOpen");
        socket.onmessage = function (event) {
            var buf = new Uint8Array(event.data);
           // alert(buf);
           // alert("Response: " +  ConfigureMessage.decode(buf).timePeriod + ConfigureMessage.decode(buf).blockNum +
            	//	ConfigureMessage.decode(buf).blockSize);
            responseValue = buf;
            //fileValue = FileMessage.decode(buf).fileValue;
            //modifyMemory(fileValue);

        };
        socket.onopen = function (event) {
            var timePeriod = document.getElementById("clocks").value;
            var blockNum = document.getElementById("blockNumbers").value;
            var blockSize = document.getElementById("blockSizes").value; 
            var allotNum = document.getElementById("pageNumbers").value;
            var timeSlice = document.getElementById("timeSlices").value;
            //查一下protobuf的repeated是怎样的一个数据类型，以及要怎样给它赋初值！！！！
            configureMessage = ConfigureMessage.create({ timePeriod: timePeriod, blockNum: blockNum, blockSize: blockSize, 
            	allotNum: allotNum, timeSlice: timeSlice});
            buffer = ConfigureMessage.encode(configureMessage).finish();
            alert("this");
            doSend(buffer);
        };
        socket.onclose = function (event) {
           alert("onclose");
        };
        socket.onerror = function (event) {
            alert("Error! The message is " + event.data);
        };
    } else {
        alert("你的浏览器不支持 WebSocket！");
    }
}
//发送数据给服务器
function doSend(buffer) {
  //alert("dosend");
  if (socket.readyState == WebSocket.OPEN) {
      alert(buffer);
      var array = ("3,3," + buffer.toString()).split(",");/*这个编号是多少？*/
      buffer = new Uint8Array(array);
      socket.send(buffer);


      setTimeout("check()",10000);

  } else {
      alert("无法连接到服务器");
      window.location.reload(); //刷新页面
  }
}
function check(){ //检查链接是否超时
  if(responseValue == ""){
      alert("服务器连接超时!");
      window.location.reload();
  }
}