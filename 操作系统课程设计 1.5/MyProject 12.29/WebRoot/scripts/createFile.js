window.onload = initPage;
var socket;
var connected = false;
var responseValue = "";
function initPage()
{
    //ProtoBuf
    protobuf.load("protoFile/FileMessage.proto", function (err, root) {
        if (err) throw err;
        FileMessage = root.lookup("protoFile.FileMessage");
    });
    var buttonSubmit = document.getElementById("buttonSubmit");
    //addEventHandler(buttonSubmit, "click", sendValue);
   // buttonSubmit.addEventListener('click', sendValue);//用这一个！！！！！
}
function sendValue()
{
    if(!connected) {
        if (!window.WebSocket) {
            window.WebSocket = window.MozWebSocket;
        }
        if (window.WebSocket) {
            connected = true;
            alert("window.Websocket");
            //socket = new WebSocket("ws://a784068941.imwork.net:7778/ws");
            socket = new WebSocket("ws://a784068941.imwork.net:7778/ws");
            //socket = new WebSocket("ws://172.16.31.221:7778/ws");//小电脑
            socket.binaryType = 'arraybuffer';
            alert("socketOpen");
            socket.onmessage = function (event) {
                var buf = new Uint8Array(event.data);
                alert(buf);
                alert("Response: " +  FileMessage.decode(buf).id + FileMessage.decode(buf).fileName +
                    FileMessage.decode(buf).fileValue);
                responseValue = buf;
                //fileValue = FileMessage.decode(buf).fileValue;
                //modifyMemory(fileValue);

            };
            socket.onopen = function (event) {
                alert("onopen");
                connected = true;
                var valueID = "1";
                var fileName = document.getElementById("fileName").value;
                var fileContent = document.getElementById("fileContent").value;
                alert(fileName);
                alert(fileContent);
                alert("onopenNext");
                //查一下protobuf的repeated是怎样的一个数据类型，以及要怎样给它赋初值！！！！
                filemessage = FileMessage.create({ id: valueID, fileName: fileName, fileValue: fileContent});
                buffer = FileMessage.encode(filemessage).finish();
                alert("buffer");
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
    else
    {
        var valueID = "1";
        var fileName = document.getElementById("fileName").value;
        var fileContent = document.getElementById("fileContent").value;
        alert(fileName);
        alert(fileContent);
        filemessage = FileMessage.create({ id: valueID, fileName: fileName, fileValue: fileContent});
        buffer = FileMessage.encode(filemessage).finish();
        doSend(buffer);
    }
}
//发送数据给服务器
function doSend(buffer) {
    alert("dosend");
    if (socket.readyState == WebSocket.OPEN) {
        alert(buffer);
        var array = ("0,0," + buffer.toString()).split(",");
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
function check(){ //检查链接是否超时
    if(responseValue == ""){
        alert("服务器连接超时!");
        window.location.reload();
    }
}