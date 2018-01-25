window.onload = initPage;
var enterPaneShowing = false;
var enterPaneClosing = false;

function initPage() {
	var userAreas = document.getElementById("userAreas");
    addEventHandler(userAreas, "mouseover", buttonOver);
    addEventHandler(userAreas, "mouseout", buttonOut);
	
    var computersA = document.getElementById("computers");
    addEventHandler(computersA, "mouseover", buttonOver);
    addEventHandler(computersA, "mouseout", buttonOut);
    

    var iconClose = document.getElementById("iconClose");
    addEventHandler(iconClose, "mouseover", buttonOver);
    addEventHandler(iconClose, "mouseout", buttonOut);
    addEventHandler(iconClose, "click", hideHint);

    var buttonCancel = document.getElementById("buttonCancel");
    addEventHandler(buttonCancel, "click", hideHint);


}
function showHint() {
    enterPaneShowing = true;

    var hintText = "<div id=\"enterImage\">\n" +
        "    <img src=\"style/images/fwindow.png\"/>\n" +
        " </div>\n" +
        "    <div id=\"iconImage\">\n" +
        "        <a id=\"iconClose\" title=\"iconClose\">iconClose</a>\n" +
        "    </div>\n" +
        " <div id=\"enterLabel\">\n" +
        "    <label>系统时钟:</label><br><br>\n" +
        "    <label>内存块数(<9999):</label><br><br>\n" +
        "    <label>内存块的大小(<999):</label><br><br>\n" +
        "    <label>硬盘大小:</label><br><br>\n" +
        "    <label>每个作业分配的页面数:</label><br><br>\n" +
        "    <label>每个进程分配的时间片:</label><br><br>\n" +
        "    <label>页表缓冲:</label>\n" +
        " </div>\n" +
        " <div id=\"enterInput\">\n" +
        "    <input id=\"clock\" value=\"1000\"/><br><br>\n" +
        "    <input id=\"blockNumber\" value=\"10\"/><br><br>\n" +
        "    <input id=\"blockSize\" value=\"4\"/><br><br>\n" +
        "    <input id=\"hardDisk\" value=\"10\"/><br><br>\n" +
        "    <input id=\"pageCount\" value=\"2\"/><br><br>\n" +
        "    <input id=\"timeSlice\" value=\"1\"/><br><br>\n" +
        "    <input id=\"pageBuffer\" value=\"5\"/>\n" +
        " </div>";
    var enterPane = document.getElementById("enterPane");
    if(enterPaneShowing){
        enterPane.innerHTML = hintText;
    }
}
function hideHint() {
    enterPaneClosing = true;

    var hintText = "";
    var enterPane = document.getElementById("enterPane");
    if(enterPaneClosing){
        enterPane.innerHTML = hintText;
    }
}
function buttonOver(e) {
    var me = getActivatedObject(e);
    me.className = "active";
}
function buttonOut(e) {
    var me = getActivatedObject(e);
    me.className = "";
}
function cleanStatusScreen() {
    document.getElementById("statusWindowTA").value = "";
}
function cleanvirtualScreen() {
    document.getElementById("virtualDisplayTA").value = "";
}
