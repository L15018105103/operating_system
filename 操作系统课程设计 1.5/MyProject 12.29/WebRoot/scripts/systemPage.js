window.onload = initPage;
var socket;
var messageGet = "";
var connected = false;
var WSMessage;
var wsmessage;
var buffer;
var jobPosition = 0;
var jobOnePosition = 0;
var jobTwoPosition = 0;
var jobThreePosition = 0;
var jobOneActive = true;
var jobTwoActive = true;
var jobThreeActive = true;
var dfirstWidth = 0;
var dlastWidth = 160;
var dlastX = 10;
var mfirstWidth = 0;
var mlastWidth = 160;
var mlastX = 10;
var processPosition = 0;
var proOnePosition = 0;
var proTwoPosition = 0;
var proThreePosition = 0;
var diskSizeValue = 0;
var memorySizeValue = 0;
var process1;
var systemTime;
var isSend = false;

/*var textOneImage = document.getElementById("textOneImage");
var textTwoImage = document.getElementById("textTwoImage");*/

function initPage() {
    protobuf.load("protoFile/TimerMessage.proto", function (err, root) {
        if (err) throw err;
        TimerMessage = root.lookup("protoFile.TimerMessage");
    });

    timerThread();//系统时钟连接

    var diskSize = document.getElementById("diskSize")
    modifyCanvas(diskSize, dfirstWidth, dlastX, dlastWidth);
    var memorySize = document.getElementById("memorySize")
    modifyCanvas(memorySize, mfirstWidth, mlastX, mlastWidth);
    if(jobOneActive)
    {
        var textOneImage = document.getElementById("textOneImage");
        addEventHandler(textOneImage, "mouseover", buttonOver);
        addEventHandler(textOneImage, "mouseout", buttonOut);
    }
    else
    {
        var textOneImage = document.getElementById("textOneImage");
        removeEventHandler(textOneImage, "mouseover", buttonOver);
        removeEventHandler(textOneImage, "mouseout", buttonOut);
    }
    if(jobTwoActive)
    {
        var textTwoImage = document.getElementById("textTwoImage");
        addEventHandler(textTwoImage, "mouseover", buttonOver);
        addEventHandler(textTwoImage, "mouseout", buttonOut);
    }
    else
    {
        var textTwoImage = document.getElementById("textTwoImage");
        removeEventHandler(textTwoImage, "mouseover", buttonOver);
        removeEventHandler(textTwoImage, "mouseout", buttonOut);
    }
    if(jobThreeActive)
    {
        var textThreeImage = document.getElementById("textThreeImage");
        addEventHandler(textThreeImage, "mouseover", buttonOver);
        addEventHandler(textThreeImage, "mouseout", buttonOut);
    }
    else
    {
        var textThreeImage = document.getElementById("textThreeImage");
        removeEventHandler(textThreeImage, "mouseover", buttonOver);
        removeEventHandler(textThreeImage, "mouseout", buttonOut);
    }


   /* var textTwoImage = document.getElementById("textTwoImage");
    addEventHandler(textTwoImage, "mouseover", buttonOver);
    addEventHandler(textTwoImage, "mouseout", buttonOut);

    var textThreeImage = document.getElementById("texThreeImage");
    addEventHandler(textThreeImage, "mouseover", buttonOver);
    addEventHandler(textThreeImage, "mouseout", buttonOut);*/

    var textOneImage = document.getElementById("textOneImage");
    var textTwoImage = document.getElementById("textTwoImage");
    var textThreeImage = document.getElementById("textThreeImage");
    addEventHandler(textThreeImage, "click", showHint3);
    addEventHandler(textTwoImage, "click", showHint2);
    addEventHandler(textOneImage, "click", showHint1);

    //ProtoBuf
    protobuf.load("protoFile/FileMessage.proto", function (err, root) {
        if (err) throw err;
        FileMessage = root.lookup("protoFile.FileMessage");
    });

    protobuf.load("protoFile/ProcessMessage.proto", function (err, root) {
        if (err) throw err;
        ProcessMessage = root.lookup("protoFile.ProcessMessage");
    });
     
    protobuf.load("protoFile/MemoryMessage.proto", function (err, root) {
        if (err) throw err;
        MemoryMessage = root.lookup("protoFile.MemoryMessage");
    });


    //点击登录弹出登录框和弹出层
    var viewDeviceImages = document.getElementById("viewDeviceImage");
    viewDeviceImages.onclick = function () {
        b();
    }
    addEventHandler(viewDeviceImages, "mouseover", buttonOver);
    addEventHandler(viewDeviceImages, "mouseout", buttonOut);

    var today = new Date();
    var seconds = today.getSeconds();
    seconds = seconds < 10 ? "0" + seconds : seconds;
    var strSys = seconds;
    var strRel = 3 -( seconds % 4 );
    systemClock = document.getElementById("systemClock");
    var relativeClock = document.getElementById("relativeClock");
    //systemClock.innerHTML = systemTime;
    relativeClock.innerHTML = strRel;
    window.setTimeout("initPage()", 1000);


    var addr61Content = document.getElementById("addr61Content");
    addEventHandler(addr61Content, "mouseover", buttonOver);
    addEventHandler(addr61Content, "mouseout", buttonOut);

    var addr60Content = document.getElementById("addr60Content");
    addEventHandler(addr60Content, "mouseover", buttonOver);
    addEventHandler(addr60Content, "mouseout", buttonOut);

    var addr53Content = document.getElementById("addr53Content");
    addEventHandler(addr53Content, "mouseover", buttonOver);
    addEventHandler(addr53Content, "mouseout", buttonOut);

    var addr52Content = document.getElementById("addr52Content");
    addEventHandler(addr52Content, "mouseover", buttonOver);
    addEventHandler(addr52Content, "mouseout", buttonOut);

    var addr52Content = document.getElementById("addr52Content");
    addEventHandler(addr52Content, "mouseover", buttonOver);
    addEventHandler(addr52Content, "mouseout", buttonOut);

    var addr51Content = document.getElementById("addr51Content");
    addEventHandler(addr51Content, "mouseover", buttonOver);
    addEventHandler(addr51Content, "mouseout", buttonOut);

    var addr50Content = document.getElementById("addr50Content");
    addEventHandler(addr50Content, "mouseover", buttonOver);
    addEventHandler(addr50Content, "mouseout", buttonOut);

    var addr43Content = document.getElementById("addr43Content");
    addEventHandler(addr43Content, "mouseover", buttonOver);
    addEventHandler(addr43Content, "mouseout", buttonOut);

    var addr42Content = document.getElementById("addr42Content");
    addEventHandler(addr42Content, "mouseover", buttonOver);
    addEventHandler(addr42Content, "mouseout", buttonOut);

    var addr41Content = document.getElementById("addr41Content");
    addEventHandler(addr41Content, "mouseover", buttonOver);
    addEventHandler(addr41Content, "mouseout", buttonOut);

    var addr40Content = document.getElementById("addr40Content");
    addEventHandler(addr40Content, "mouseover", buttonOver);
    addEventHandler(addr40Content, "mouseout", buttonOut);

    var addr33Content = document.getElementById("addr33Content");
    addEventHandler(addr33Content, "mouseover", buttonOver);
    addEventHandler(addr33Content, "mouseout", buttonOut);

    var addr32Content = document.getElementById("addr32Content");
    addEventHandler(addr32Content, "mouseover", buttonOver);
    addEventHandler(addr32Content, "mouseout", buttonOut);

    var addr31Content = document.getElementById("addr31Content");
    addEventHandler(addr31Content, "mouseover", buttonOver);
    addEventHandler(addr31Content, "mouseout", buttonOut);

    var addr30Content = document.getElementById("addr30Content");
    addEventHandler(addr30Content, "mouseover", buttonOver);
    addEventHandler(addr30Content, "mouseout", buttonOut);

    var addr23Content = document.getElementById("addr23Content");
    addEventHandler(addr23Content, "mouseover", buttonOver);
    addEventHandler(addr23Content, "mouseout", buttonOut);

    var addr22Content = document.getElementById("addr22Content");
    addEventHandler(addr22Content, "mouseover", buttonOver);
    addEventHandler(addr22Content, "mouseout", buttonOut);

    var addr21Content = document.getElementById("addr21Content");
    addEventHandler(addr21Content, "mouseover", buttonOver);
    addEventHandler(addr21Content, "mouseout", buttonOut);

    var addr20Content = document.getElementById("addr20Content");
    addEventHandler(addr20Content, "mouseover", buttonOver);
    addEventHandler(addr20Content, "mouseout", buttonOut);

    var addr13Content = document.getElementById("addr13Content");
    addEventHandler(addr13Content, "mouseover", buttonOver);
    addEventHandler(addr13Content, "mouseout", buttonOut);

    var addr12Content = document.getElementById("addr12Content");
    addEventHandler(addr12Content, "mouseover", buttonOver);
    addEventHandler(addr12Content, "mouseout", buttonOut);

    var addr11Content = document.getElementById("addr11Content");
    addEventHandler(addr11Content, "mouseover", buttonOver);
    addEventHandler(addr11Content, "mouseout", buttonOut);

    var addr10Content = document.getElementById("addr10Content");
    addEventHandler(addr10Content, "mouseover", buttonOver);
    addEventHandler(addr10Content, "mouseout", buttonOut);

    var addr03Content = document.getElementById("addr03Content");
    addEventHandler(addr03Content, "mouseover", buttonOver);
    addEventHandler(addr03Content, "mouseout", buttonOut);

    var addr02Content = document.getElementById("addr02Content");
    addEventHandler(addr02Content, "mouseover", buttonOver);
    addEventHandler(addr02Content, "mouseout", buttonOut);

    var addr01Content = document.getElementById("addr01Content");
    addEventHandler(addr01Content, "mouseover", buttonOver);
    addEventHandler(addr01Content, "mouseout", buttonOut);

    var addr00Content = document.getElementById("addr00Content");
    addEventHandler(addr00Content, "mouseover", buttonOver);
    addEventHandler(addr00Content, "mouseout", buttonOut);

}
function buttonOver(e) {
    var me = getActivatedObject(e);
    me.className = "active";
}
function buttonOut(e) {
    var me = getActivatedObject(e);
    me.className = "";
}
function showHint1() {
    if(jobOneActive){
        var diskSize = document.getElementById("diskSize");
        dlastWidth -= 15;
        dlastX += 15;
        dfirstWidth += 15;
        modifyCanvas(diskSize, dfirstWidth, dlastX, dlastWidth);
        var textOneImage = document.getElementById("textOneImage");
        removeEventHandler(textOneImage, "mouseover", buttonOver);
        removeEventHandler(textOneImage, "mouseout", buttonOut);
        document.getElementById("textOneImage").className = "active";
        document.getElementById("jobOne").style.top = 12 + 13 * jobPosition + '%';
        jobOnePosition = jobPosition;
        jobPosition++;
        var hintText = "<label id=\"jobOneId\" class='jobId'>1914150924</label>\n" +
            "           <label id=\"jobOneName\" class='jobName'>job10.txt</label>\n" +
            "           <label id=\"jobOneOrder\" class='jobOrder'>1</label>\n" +
            "           <label id=\"jobOneSize\" class='jobSize'>2 KB</label>\n" +
            "           <a id='jobOneRun'></a>" +
            "           <span id='jobOneSub'></span>" ;
        var jobOne = document.getElementById("jobOne");
        jobOne.innerHTML = hintText;

        //进程信息展示
        var id = $("label#jobOneId").text();
        process1 = new Process(id, '就绪', 0, 0);


        var diskSizeOne = document.getElementById("diskSizeOne");
        diskSizeValue++;
        diskSizeOne.innerHTML = diskSizeValue;

        var jobOneSub = document.getElementById("jobOneSub");
        addEventHandler(jobOneSub, "click", hideHint1);
        addEventHandler(jobOneSub, "mouseover", buttonOver);
        addEventHandler(jobOneSub, "mouseout", buttonOut);

        var jobOneRun = document.getElementById("jobOneRun");
        addEventHandler(jobOneRun, "click", jobRun1);
        addEventHandler(jobOneRun, "mouseover", buttonOver);
        addEventHandler(jobOneRun, "mouseout", buttonOut);
    }
    jobOneActive = false;
}
function hideHint1() {
    var hintText = "";
    var jobOne = document.getElementById("jobOne");
    jobOne.innerHTML = hintText;
    jobPosition--;
    if(jobThreePosition - jobOnePosition > 0)
    {
        jobThreePosition -= 1;
    }
    document.getElementById("jobThree").style.top = 12 + 13 * jobThreePosition + '%';
    if(jobTwoPosition - jobOnePosition > 0)
    {
        jobTwoPosition -= 1;
    }
    document.getElementById("jobTwo").style.top = 12 + 13 * jobTwoPosition + '%';
    jobOneActive = true;
    var textOneImage = document.getElementById("textOneImage");
    textOneImage.className = "";
    addEventHandler(textOneImage, "mouseover", buttonOver);
    addEventHandler(textOneImage, "mouseout", buttonOut);
    var diskSize = document.getElementById("diskSize");
    dlastWidth += 15;
    dlastX -= 15;
    dfirstWidth -= 15;
    modifyCanvas(diskSize, dfirstWidth, dlastX, dlastWidth);
    var diskSizeOne = document.getElementById("diskSizeOne");
    diskSizeValue--;
    diskSizeOne.innerHTML = diskSizeValue;
}
function showHint2() {
    if(jobTwoActive){
        var diskSize = document.getElementById("diskSize");
        dlastWidth -= 15;
        dlastX += 15;
        dfirstWidth += 15;
        modifyCanvas(diskSize, dfirstWidth, dlastX, dlastWidth);
        var textTwoImage = document.getElementById("textTwoImage");
        removeEventHandler(textTwoImage, "mouseover", buttonOver);
        removeEventHandler(textTwoImage, "mouseout", buttonOut);
        document.getElementById("textTwoImage").className = "active";
        document.getElementById("jobTwo").style.top = 12 + 13 * jobPosition + '%';
        jobTwoPosition = jobPosition;
        jobPosition++;
        var hintText = "<label id=\"jobTwoId\" class='jobId'>1914150925</label>\n" +
            "           <label id=\"jobTwoName\" class='jobName'>job2.txt</label>\n" +
            "           <label id=\"jobTwoOrder\" class='jobOrder'>2</label>\n" +
            "           <label id=\"jobTwoSize\" class='jobSize'>3 KB</label>\n" +
            "           <a id='jobTwoRun'></a>" +
            "           <span id='jobTwoSub'></span>" ;
        var jobTwo = document.getElementById("jobTwo");
        jobTwo.innerHTML = hintText;
        //进程状态信息显示
        var id = $("label#jobTwoId").text();
        process2 = new Process(id, '就绪', 0, 0);

        var diskSizeOne = document.getElementById("diskSizeOne");
        diskSizeValue++;
        diskSizeOne.innerHTML = diskSizeValue;

        var jobTwoSub = document.getElementById("jobTwoSub");
        addEventHandler(jobTwoSub, "click", hideHint2);
        addEventHandler(jobTwoSub, "mouseover", buttonOver);
        addEventHandler(jobTwoSub, "mouseout", buttonOut);
        var jobTwoRun = document.getElementById("jobTwoRun");
        addEventHandler(jobTwoRun, "click", jobRun2);
        addEventHandler(jobTwoRun, "mouseover", buttonOver);
        addEventHandler(jobTwoRun, "mouseout", buttonOut);
    }
    jobTwoActive = false;
}
function hideHint2() {
    var hintText = "";
    var jobTwo = document.getElementById("jobTwo");
    jobTwo.innerHTML = hintText;
    jobPosition--;
    if(jobThreePosition - jobTwoPosition > 0)
    {
        jobThreePosition -= 1;
    }
    document.getElementById("jobThree").style.top = 12 + 13 * jobThreePosition + '%';
    if(jobOnePosition - jobTwoPosition > 0)
    {
        jobOnePosition -= 1;
    }
    document.getElementById("jobOne").style.top = 12 + 13 * jobOnePosition + '%';
    jobTwoActive = true;
    var textTwoImage = document.getElementById("textTwoImage");
    textTwoImage.className = "";
    addEventHandler(textTwoImage, "mouseover", buttonOver);
    addEventHandler(textTwoImage, "mouseout", buttonOut);
    var diskSize = document.getElementById("diskSize");
    dlastWidth += 15;
    dlastX -= 15;
    dfirstWidth -= 15;
    modifyCanvas(diskSize, dfirstWidth, dlastX, dlastWidth);
    var diskSizeOne = document.getElementById("diskSizeOne");
    diskSizeValue--;
    diskSizeOne.innerHTML = diskSizeValue;
}
function showHint3() {
    if(jobThreeActive){
        var diskSize = document.getElementById("diskSize");
        dlastWidth -= 15;
        dlastX += 15;
        dfirstWidth += 15;
        modifyCanvas(diskSize, dfirstWidth, dlastX, dlastWidth);
        var textThreeImage = document.getElementById("textThreeImage");
        removeEventHandler(textThreeImage, "mouseover", buttonOver);
        removeEventHandler(textThreeImage, "mouseout", buttonOut);
        document.getElementById("textThreeImage").className = "active";
        document.getElementById("jobThree").style.top = 12 + 13 * jobPosition + '%';
        jobThreePosition = jobPosition;
        jobPosition++;
        var hintText = "<label id=\"jobThreeId\" class='jobId'>1914150926</label>\n" +
            "           <label id=\"jobThreeName\" class='jobName'>job3.txt</label>\n" +
            "           <label id=\"jobThreeOrder\" class='jobOrder'>3</label>\n" +
            "           <label id=\"jobThreeSize\" class='jobSize'>5 KB</label>\n" +
            "           <a id='jobThreeRun'></a>" +
            "           <span id='jobThreeSub'></span>" ;
        var jobThree = document.getElementById("jobThree");
        jobThree.innerHTML = hintText;
        //进程状态信息显示
        var id = $("label#jobThreeId").text();
        process3 = new Process(id, '就绪', 0, 0);

        var diskSizeOne = document.getElementById("diskSizeOne");
        diskSizeValue++;
        diskSizeOne.innerHTML = diskSizeValue;

        var jobThreeSub = document.getElementById("jobThreeSub");
        addEventHandler(jobThreeSub, "click", hideHint3);
        addEventHandler(jobThreeSub, "mouseover", buttonOver);
        addEventHandler(jobThreeSub, "mouseout", buttonOut);
        var jobThreeRun = document.getElementById("jobThreeRun");
        addEventHandler(jobThreeRun, "click", jobRun3);
        addEventHandler(jobThreeRun, "mouseover", buttonOver);
        addEventHandler(jobThreeRun, "mouseout", buttonOut);
    }
    jobThreeActive = false;
}
function hideHint3() {
    var hintText = "";
    var jobThree = document.getElementById("jobThree");
    jobThree.innerHTML = hintText;
    jobPosition--;
    if(jobOnePosition - jobThreePosition > 0)
    {
        jobOnePosition -= 1;
    }
    document.getElementById("jobOne").style.top = 12 + 13 * jobOnePosition + '%';
    if(jobTwoPosition - jobThreePosition > 0) {
        jobTwoPosition -= 1;
    }
    document.getElementById("jobTwo").style.top = 12 + 13 * jobTwoPosition + '%';
    jobThreeActive = true;
    var textThreeImage = document.getElementById("textThreeImage");
    textThreeImage.className = "";
    addEventHandler(textThreeImage, "mouseover", buttonOver);
    addEventHandler(textThreeImage, "mouseout", buttonOut);
    var diskSize = document.getElementById("diskSize");
    dlastWidth += 15;
    dlastX -= 15;
    dfirstWidth -= 15;
    modifyCanvas(diskSize, dfirstWidth, dlastX, dlastWidth);
    var diskSizeOne = document.getElementById("diskSizeOne");
    diskSizeValue--;
    diskSizeOne.innerHTML = diskSizeValue;
}
function jobRun1() {
    var processOne = document.getElementById("processOne");
    processOne.style.top = 18 + 15 * processPosition + '%';
    proOnePosition = processPosition;
    processPosition++;
    modifyMesageShow(process1, processOne);
    if(!connected) {
        jobOneRunActive = false;
        var jobOneRun = document.getElementById("jobOneRun");
        logoffRunIcon(jobOneRun);
        var jobOneSub = document.getElementById("jobOneSub");
        logoffSubIcon(jobOneSub);
        if (!window.WebSocket) {
            window.WebSocket = window.MozWebSocket;
        }
        if (window.WebSocket) {
            connected = true;
            alert("window.Websocket");
            //socket = new WebSocket("ws://172.17.134.31:7778/ws");
            socket = new WebSocket("ws://172.16.31.221:7778/ws");/*大电脑*/
            //socket = new WebSocket("ws://127.0.0.1:7778/ws");
            //socket = new WebSocket("ws://a784068941.imwork.net:7778/ws");
            socket.binaryType = 'arraybuffer';
            socket.onmessage = function (event) {
                var buf = new Uint8Array(event.data);
                alert("处理前："+buf);
                alert(buf.subarray(0,2));
                var fore = buf.subarray(0,2);
                alert("处理后:"+buf);
                /*alert("Response: " +  FileMessage.decode(buf).id + FileMessage.decode(buf).fileName +
                    FileMessage.decode(buf).fileValue);*/
                
                if(fore == "4,4")
                {
                	alert("Response: " +  MemoryMessage.decode(buf).runningIndex + MemoryMessage.decode(buf).replaceBlock +
                    		MemoryMessage.decode(buf).memoryValue);
                    memoryValue = MemoryMessage.decode(buf).memoryValue;
                	modifyMemory(memoryValue);
               	}
            };
            socket.onopen = function (event) {
                alert("onopen");
                connected = true;
                 var labelID = document.getElementById("jobOneId");
                 var valueID = labelID.innerText.trim();
                 var labelJob = document.getElementById("jobOneName");
                 var valueJob = labelJob.innerText.trim();
                 alert("onopenNext");
                 //查一下protobuf的repeated是怎样的一个数据类型，以及要怎样给它赋初值！！！！
                filemessage = FileMessage.create({ id: valueID, fileName: valueJob, fileValue: ""});
                buffer = FileMessage.encode(filemessage).finish();
                alert("buffer");
                doSend(buffer);
            };
            socket.onclose = function (event) {
                var jobOneRun = document.getElementById("jobOneRun");
                activeRunIcon(jobOneRun);

                var jobOneSub = document.getElementById("jobOneSub");
                activeSubIcon(jobOneSub);
                alert("onclose");
            };
            socket.onerror = function (event) {
                alert("Error! The message is " + event.data);
            };
        } else {
            alert("你的浏览器不支持 WebSocket！");
        }
    }
    else
    {
            var jobOneRun = document.getElementById("jobOneRun");
            logoffRunIcon(jobOneRun);
            var jobOneSub = document.getElementById("jobOneSub");
            logoffSubIcon(jobOneSub);
            var labelID = document.getElementById("jobOneId");
            var valueID = labelID.innerText.trim();
            var labelJob = document.getElementById("jobOneName");
            var valueJob = labelJob.innerText.trim();
            filemessage = FileMessage.create({ id: valueID, fileName: valueJob, fileValue: " "});
            buffer = FileMessage.encode(filemessage).finish();
            doSend(buffer);
    }
}
function jobRun2() {
    var processTwo = document.getElementById("processTwo");
    processTwo.style.top = 18 + 15 * processPosition + '%';
    proTwoPosition = processPosition;
    processPosition++;
    modifyMesageShow(process2, processTwo);
    if(!connected) {
        jobTwoActive = false;
        var jobTwoRun = document.getElementById("jobTwoRun");
        logoffRunIcon(jobTwoRun);
        var jobTwoSub = document.getElementById("jobTwoSub");
        logoffSubIcon(jobTwoSub);
        if (!window.WebSocket) {
            window.WebSocket = window.MozWebSocket;
        }
        if (window.WebSocket) {
            connected = true;
            alert("window.Websocket");
            socket = new WebSocket("ws://172.16.31.221:7778/ws");
            //socket = new WebSocket("ws://a784068941.imwork.net:7778/ws");
            //socket = new WebSocket("ws://127.0.0.1:7778/ws");
            socket.binaryType = 'arraybuffer';
            socket.onmessage = function (event) { //这个部分还得该，因为接收的就不是这个了！！！！
                var buf = new Uint8Array(event.data);
                alert(buf);
                alert(buf.subarray(0, 2));
                alert("Response: " +  FileMessage.decode(buf).id + FileMessage.decode(buf).filename +
                    FileMessage.decode(buf).fileValue);
                fileValue = FileMessage.decode(buf).fileValue;
                modifyMemory(fileValue);
            };
            socket.onopen = function (event) {
                alert("onopen");
                connected = true;
                var labelID = document.getElementById("jobTwoId");
                var valueID = labelID.innerText.trim();
                var labelJob = document.getElementById("jobTwoName");
                var valueJob = labelJob.innerText.trim();
                filemessage = FileMessage.create({ id: valueID, fileName: valueJob, fileValue: " "});
                buffer = FileMessage.encode(filemessage).finish();
                doSend(buffer);
            };
            socket.onclose = function (event) {
                var jobTwoRun = document.getElementById("jobTwoRun");
                activeRunIcon(jobTwoRun);

                var jobTwoSub = document.getElementById("jobTwoSub");
                activeSubIcon(jobTwoSub);
                alert("onclose");
            };
            socket.onerror = function (event) {
                alert("Error! The message is " + event.data);
            };
        } else {
            alert("你的浏览器不支持 WebSocket！");
        }
    }
    else
    {
        var jobTwoRun = document.getElementById("jobTwoRun");
        logoffRunIcon(jobTwoRun);
        var jobTwoSub = document.getElementById("jobTwoSub");
        logoffSubIcon(jobTwoSub);
        var labelID = document.getElementById("jobTwoId");
        var valueID = labelID.innerText.trim();
        var labelJob = document.getElementById("jobTwoName");
        var valueJob = labelJob.innerText.trim();
        filemessage = FileMessage.create({ id: valueID, fileName: valueJob, fileValue: " "});
        buffer = FileMessage.encode(filemessage).finish();
        doSend(buffer);
    }
}

function jobRun3() {
    var processThree = document.getElementById("processThree");
    processThree.style.top = 18 + 15 * processPosition + '%';
    proThreePosition = processPosition;
    processPosition++;
    modifyMesageShow(process3, processThree);
    if(!connected) {
        jobThreeActive = false;
        var jobThreeRun = document.getElementById("jobThreeRun");
        logoffRunIcon(jobThreeRun);

        var jobThreeSub = document.getElementById("jobThreeSub");
        logoffSubIcon(jobThreeSub);
        //alert("Please link to the server!");
        if (!window.WebSocket) {
            window.WebSocket = window.MozWebSocket;
        }
        if (window.WebSocket) {
            connected = true;
            alert("window.Websocket");
            //socket = new WebSocket("ws://a784068941.imwork.net:7778/ws");
            //socket = new WebSocket("ws://127.0.0.1:7778/ws");
            socket = new WebSocket("ws://172.16.31.221:7778/ws");
            socket.binaryType = 'arraybuffer';
            socket.onmessage = function (event) {
                var buf = new Uint8Array(event.data);
                alert(buf);
                alert(buf.subarray(0,2));
                alert("Response: " +  FileMessage.decode(buf).id + FileMessage.decode(buf).filename +
                    FileMessage.decode(buf).fileValue);
                fileValue = FileMessage.decode(buf).fileValue;
                modifyMemory(fileValue);
            };
            socket.onopen = function (event) {
                alert("onopen");
                connected = true;
                var labelID = document.getElementById("jobThreeId");
                var valueID = labelID.innerText.trim();
                var labelJob = document.getElementById("jobThreeName");
                var valueJob = labelJob.innerText.trim();
                filemessage = FileMessage.create({ id: valueID, fileName: valueJob, fileValue: " "});
                buffer = FileMessage.encode(filemessage).finish();
                doSend(buffer);
            };
            socket.onclose = function (event) {
                var jobThreeRun = document.getElementById("jobThreeRun");
                activeRunIcon(jobThreeRun);

                var jobThreeSub = document.getElementById("jobThreeSub");
                activeSubIcon(jobThreeSub);
                alert("onclose");
            };
            socket.onerror = function (event) {
                alert("Error! The message is " + event.data);
            };
        } else {
            alert("你的浏览器不支持 WebSocket！");
        }
    }
    else
    {
        var jobThreeRun = document.getElementById("jobThreeRun");
        logoffRunIcon(jobThreeRun);
        var jobThreeSub = document.getElementById("jobThreeSub");
        logoffSubIcon(jobThreeSub);
        var labelID = document.getElementById("jobThreeId");
        var valueID = labelID.innerText.trim();
        var labelJob = document.getElementById("jobThreeName");
        var valueJob = labelJob.innerText.trim();
        filemessage = FileMessage.create({ id: valueID, fileName: valueJob, fileValue: " "});
        buffer = FileMessage.encode(filemessage).finish();
        doSend(buffer);
    }
}

//发送数据给服务器
function doSend(buffer) {
    alert("dosend");
    if (socket.readyState == WebSocket.OPEN) {
        alert(buffer);
        var array = ("0,1," + buffer.toString()).split(",");
        alert(array);
       // buffer = new Uint8Array([1,2,buffer]);
        //buffer=new Uint8Array(){1,2,3};
       //alert(buffer);
        buffer = new Uint8Array(array);
       alert(buffer);
        socket.send(buffer);

        alert("send success");

        setTimeout("check()",10000);

    } else {
        alert("无法连接到服务器");
        window.location.reload(); //刷新页面
    }
}
function modifyMemory(fileValue) {//到时要弄一个记录指针，记录当前可以放进内存的位置，为了后续的执行
    //alert(this.messageGet);
	//buf.subarray(0,2)
	var fore = fileValue.split('\n')
	//alert(fore[0]);
	if(fore[0] != "null")
	{
		var fileContent = new Array();
	    fileContent = fileValue.split("\n");
	    for(var i = 0; i < fileContent.length - 1; i++)
	    {
	        var contentId = "content" + i;
	        // alert(contentId);
	        document.getElementById(contentId).innerHTML = fileContent[i];

	        /*var deviceStatus = "statusA" + (i+1);
	        // alert(contentId);
	        document.getElementById(deviceStatus).innerHTML = fileContent[i];*/
	    }
	    var index = (fileContent.length - 1) % 4;
	    var value = parseInt((fileContent.length - 1) / 4);
	    if(index == 0)
	    {
	        memorySizeValue = value;
	    }
	    else
	    {
	        memorySizeValue = value + 1;
	    }
	    var memorySizeOne = document.getElementById("memorySizeOne");
	    //alert("memorySizeValue: " + memorySizeValue);
	    memorySizeOne.innerHTML = memorySizeValue;

	    var memorySize = document.getElementById("memorySize");
	    mfirstWidth += 15 * (value + 1);
	    mlastWidth -= 15 * (value + 1);
	    mlastX += 15 * (value + 1);
	    modifyCanvas(memorySize, mfirstWidth, mlastX, mlastWidth);
	}
}
function check(){ //检查链接是否超时
    if(fileValue == ""){
        alert("服务器连接超时!");
        window.location.reload();
    }
}

function timerThread() {
	if(!connected)
	{
		alert("connected")
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }
    if (window.WebSocket) {
        // alert("window.Websocket");
        //socket = new WebSocket("ws://172.17.134.31:7778/ws");/*小电脑*/
        //socket = new WebSocket("ws://172.18.174.219:7778/ws");/*大电脑*/
    	socket = new WebSocket("ws://127.0.0.1:7778/ws");//本地的端口。
        //socket = new WebSocket("ws://a784068941.imwork.net:7778/ws");
        socket.binaryType = 'arraybuffer';
        socket.onmessage = function (event) {
            var buf = new Uint8Array(event.data);
          //  alert("处理前： "+buf);
          //  alert(buf.subarray(0,2));
            var fore = buf.subarray(0,2);
            buf = buf.subarray(2, buf.length);
           // alert("处理后： "+buf);
            //alert("Response: " +  TimerMessage.decode(buf).currentTime.toString());
            if(fore == "1,2")
            {
            	systemTime = TimerMessage.decode(buf).currentTime.toString();
                //alert("systemTime:"+systemTime);
                systemClock.innerHTML = systemTime;
            }
            
            if(fore == "4,4")
            {
            	//alert("Response: " +  MemoryMessage.decode(buf).runningIndex + MemoryMessage.decode(buf).replaceBlock +
                		//MemoryMessage.decode(buf).memoryValue);
                memoryValue = MemoryMessage.decode(buf).memoryValue;
            	modifyMemory(memoryValue);
           	}
            /*if(fore == "4,4")
            {
            	//alert("Response: " +  MemoryMessage.decode(buf).runningIndex + MemoryMessage.decode(buf).replaceBlock +
                		//MemoryMessage.decode(buf).memoryValue);
                memoryValue = MemoryMessage.decode(buf).memoryValue;
            	modifyMemory(memoryValue);
           	}*/
           // alert("onmessage");
            //fileValue = FileMessage.decode(buf).fileValue;
            //modifyMemory(fileValue);
        };
        socket.onopen = function (event) {
            connected = true;
           // alert("onopen");
            var currentTime = 1;
            //查一下protobuf的repeated是怎样的一个数据类型，以及要怎样给它赋初值！！！！
            timermessage = TimerMessage.create({ currentTime: currentTime});
            buffer = TimerMessage.encode(timermessage).finish();
            //alert("buffer");
            if(!isSend)
            {
            	isSend = true;
            	doSendClock(buffer);
            	//alert("sended!");
            }
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
	else
	{
		
	}

}
//发送时钟
function doSendClock(buffer) {
   // alert("dosend");
    if (socket.readyState == WebSocket.OPEN) {
      //  alert(buffer);
        var array = ("1,2," + buffer.toString()).split(",");
      //  alert(array);
        // buffer = new Uint8Array([1,2,buffer]);
        //buffer=new Uint8Array(){1,2,3};
        //alert(buffer);
        buffer = new Uint8Array(array);
        //alert(buffer);
        socket.send(buffer);
       // alert("send success");
        setTimeout("check()",10000);

    } else {
        alert("无法连接到服务器");
        window.location.reload(); //刷新页面
    }
}