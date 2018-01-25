function createRequest() {
  try {
    request = new XMLHttpRequest();
  } catch (tryMS) {
    try {
      request = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (otherMS) {
      try {
        request = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (failed) {
        request = null;
      }
    }
  }	
  return request;
}


function getActivatedObject(e) {
  var obj;
  if (!e) {
    // early version of IE
    obj = window.event.srcElement;
  } else if (e.srcElement) {
    // IE 7 or later
    obj = e.srcElement;
  } else {
    // DOM Level 2 browser
    obj = e.target;
  }
  return obj;
}

function addEventHandler(obj, eventName, handler) {
  if (document.attachEvent) {
    obj.attachEvent("on" + eventName, handler);
  } else if (document.addEventListener) {
    obj.addEventListener(eventName, handler, false);
  }
}

function removeEventHandler(obj, eventName, handler) {
    if (document.attachEvent) {
        obj.removeEvent("on" + eventName, handler);
    } else if (document.addEventListener) {
        obj.removeEventListener(eventName, handler, false);
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

function activeRunIcon(obj) {
    if(obj == document.getElementById("jobOneRun"))
    {
        addEventHandler(obj, "click", jobRun1);
    }
    if(obj == document.getElementById("jobTwoRun"))
    {
        addEventHandler(obj, "click", jobRun2);
    }
    if(obj == document.getElementById("jobThreeRun"))
    {
        addEventHandler(obj, "click", jobRun3);
    }
    addEventHandler(obj, "mouseover", buttonOver);
    addEventHandler(obj, "mouseout", buttonOut);
    obj.className = "";
}
function activeSubIcon(obj) {
    if(obj == document.getElementById("jobOneSub"))
    {
        addEventHandler(obj, "click", hideHint1);
    }
    if(obj == document.getElementById("jobTwoSub"))
    {
        addEventHandler(obj, "click", hideHint2);
    }
    if(obj == document.getElementById("jobThreeSub"))
    {
        addEventHandler(obj, "click", hideHint3);
    }
    addEventHandler(obj, "mouseover", buttonOver);
    addEventHandler(obj, "mouseout", buttonOut);
    obj.className = "";
}

function logoffRunIcon(obj) {
    if(obj == document.getElementById("jobOneRun"))
    {
        removeEventHandler(obj, "click", jobRun1);
    }
    if(obj == document.getElementById("jobTwoRun"))
    {
        removeEventHandler(obj, "click", jobRun2);
    }
    if(obj == document.getElementById("jobThreeRun"))
    {
        removeEventHandler(obj, "click", jobRun3);
    }
    removeEventHandler(obj, "mouseover", buttonOver);
    removeEventHandler(obj, "mouseout", buttonOut);
    obj.className = "active";
}
function logoffSubIcon(obj) {
    if(obj == document.getElementById("jobOneSub"))
    {
        removeEventHandler(obj, "click", hideHint1);
    }
    if(obj == document.getElementById("jobTwoSub"))
    {
        removeEventHandler(obj, "click", hideHint2);
    }
    if(obj == document.getElementById("jobThreeSub"))
    {
        removeEventHandler(obj, "click", hideHint3);
    }
    removeEventHandler(obj, "mouseover", buttonOver);
    removeEventHandler(obj, "mouseout", buttonOut);
    obj.className = "active";
}
function modifyCanvas(obj, firstWidth, lastX, lastWidth) {
    if (obj.getContext) {
        var context = obj.getContext("2d");
        context.fillStyle = "#000079";
        context.fillRect(10, 10, firstWidth, 15);
        context.fillStyle = "#FFFFFF";
        context.fillRect(lastX, 10, lastWidth, 15);
    }
}

function Process(id, status, waitTime, percentage)
{
    this.id = id;
    this.status = status;
    this.waitTime = waitTime;
    this.percentage = percentage;

    this.getId = function () {
        return this.id;
    }
    this.getStatus = function () {
        return this.status;
    }
    this.getWaitTime = function () {
        return this.waitTime;
    }
    this.getPercentage = function () {
        return this.percentage;
    }

    this.setId = function (id) {
        this.id = id;
    }
    this.setStatus = function (status) {
        this.status = status;
    }
    this.setWaitTime = function (waitTime) {
        this.waitTime = waitTime;
    }
    this.setPercentage = function (percentage) {
        this.percentage = percentage;
    }
}

function modifyMesageShow(process, obj)
{
    var objList = obj.getElementsByTagName("li");
    objList[0].innerHTML = process.getId();
    objList[1].innerHTML = process.getStatus();
    objList[2].innerHTML = process.getWaitTime();
    objList[3].innerHTML = process.getPercentage();
}

function cleanStatusScreen() {
    document.getElementById("statusWindowTA").value = "";
}
function cleanvirtualScreen() {
    document.getElementById("virtualDisplayTA").value = "";
}