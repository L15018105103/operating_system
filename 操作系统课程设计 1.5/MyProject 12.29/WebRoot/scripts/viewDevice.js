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
    login.innerHTML = '<div class="title" id="title">设备窗口' +
        '<a href="show.do" id="close"></a>' +
        '</div>'+
        '<div id="enterLabel">\n' +
        "            <label>设备类型</label><br>\n" +
        "            <br> <label id='typeA1'>A1:</label><br>\n" +
        '            <br> <label>A2:</label><br>\n' +
        '            <br> <label>B1:</label><br>\n' +
        '            <br> <label>B2:</label><br>\n' +
        '            <br> <label>B3:</label><br>\n' +
        '            <br> <label>C1:</label><br>\n' +
        '            <br> <label>C2:</label><br>\n' +
        '            <br> <label>C3:</label>\n' +
        '        </div>'+
        '<div id="deviceStatus">\n' +
        '            <label>设备状态</label><br>\n' +
        "            <br> <label id='statusA1'>空闲</label><br>\n" +
        '            <br> <label id=\'statusA2\'>空闲</label><br>\n' +
        '            <br> <label>空闲</label><br>\n' +
        '            <br> <label>空闲</label><br>\n' +
        '            <br> <label>空闲</label><br>\n' +
        '            <br> <label>空闲</label><br>\n' +
        '            <br> <label>空闲</label><br>\n' +
        '            <br> <label>空闲</label>\n' +
        '        </div>'+
        '<div id="useDevice">\n' +
        '            <label>使用进程</label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label>\n' +
        '        </div>'+
        '<div id="waitDevice">\n' +
        '            <label>等待进程</label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label>\n' +
        '        </div>'+
        '<div id="remainTime">\n' +
        '            <label>时间片</label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label><br>\n' +
        '            <br> <label></label>\n' +
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
}
function buttonOver(e) {
    var me = getActivatedObject(e);
    me.className = "active";
}
function buttonOut(e) {
    var me = getActivatedObject(e);
    me.className = "";
}

