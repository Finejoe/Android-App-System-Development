let username=document.getElementById('username');
let username2=document.getElementById('username2');
let name=document.getElementById('name');
let age=document.getElementById('age');
let telenum=document.getElementById('telenum');
let p1=document.getElementById('p_1');
let p2=document.getElementById('p_2');
let btn1=document.getElementById('btn1');
let btn2=document.getElementById('btn2');
let div=document.getElementById('AfterClickBtn');
username.onblur=disposalUsername;
username2.onblur=disposalUsername2;
name.onblur=disposalName;

function disposalUsername(){
    if(username.value!==""&&name.value!=="")
        btn1.disabled=false;
    else
        btn1.disabled=true;
}
function disposalUsername2(){
    if(username2.value!=="")
        btn2.disabled=false;
    else
        btn2.disabled=true;
}
function disposalName(){
    if(username.value!==""&&name.value!=="")
        btn1.disabled=false;
    else
        btn1.disabled=true;
}
function AfterClickBtn1(){
    if(username.value!=="") {
        let p = document.createElement('p');
        p.textContent = "成功插入person:" + username.value;
        div.appendChild(p);
        setTimeout("delay()","5000")
    }
}
function delay(){
    let p=document.querySelector('div p');
    p.parentElement.removeChild(p);
}

function login() {
    let flag=false;
    if(username.value!==""&&name.value!=="")
        flag=confirm("确定要插入person?");
    if(flag===false)
        return;
    let value = "username=" + username.value + "&name=" + name.value + "&age=" + age.value + "&telenum=" + telenum.value;  //key/value类型
    let x = new XMLHttpRequest();   //创建ajax对象
    x.onreadystatechange = function () {     //对ajax对象进行监听
        if (x.readyState === 4) {         //4表示解析完毕
            if (x.status === 200) {            //200为正常返回
                console.log("插入成功");
                let str="person:"+username.value+"插入成功";
                let p=document.createElement('p');
                p.innerText=str;
                div.appendChild(p);
                setTimeout("delay()","8000")
                username.value="";
                name.value="";
                age.value="";
                telenum.value="";
            }
        }
    }
    //(1)发送方式
    //（2）发送地址
    //（3）是否异步，true为异步，false为同步
    x.open("POST","disposal",true);
    x.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    x.send(value);  //发送
}
function login2(){
    let flag0=false;
    if(username2.value!=="")
        flag0=confirm("确定要删除user?");
    if(flag0===false)
        return;
    let value = "username=" + username2.value;  //key/value类型
    let x = new XMLHttpRequest();   //创建ajax对象
    x.onreadystatechange = function () {     //对ajax对象进行监听
        console.log("1");
        if (x.readyState === 4) {         //4表示解析完毕
            console.log("2");
            if (x.status === 200) {            //200为正常返回
                console.log("3");
                let flag = x.responseText;
                let str="此user不存在";
                console.log(typeof flag);
                console.log(flag)
                if(flag==="true") {
                    console.log("删除成功");
                    str = "user:" + username2.value + "删除成功";
                }
                let p = document.createElement('p');
                p.innerText = str;
                div.appendChild(p);
                setTimeout("delay()", "8000")
                username2.value = "";
            }
        }
        console.log("4");
    }
    // readyState存有 XMLHttpRequest 的状态。从 0 到 4 发生变化。
    // 0: 请求未初始化
    // 1: 服务器连接已建立
    // 2: 请求已接收
    // 3: 请求处理中
    // 4: 请求已完成，且响应已就绪
    //(1)发送方式
    //（2）发送地址
    //（3）是否异步，true为异步，false为同步
    x.open("POST","delete",true);
    x.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    x.send(value);  //发送
}
