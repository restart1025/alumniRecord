<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>神华宁煤安全风险预控管理信息系统</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<form id="formlogin">
	<div id="login">	
			<input type="password" style="display:none" />
		<p>账号：<input type="text" name="personId" id="personId" class="easyui-validatebox" ></p>
		<p>密码：<input type="password" name="password"  id="password" data-oldPwd="" data-loginIp="" class="easyui-validatebox" ></p>

		<p id="remember"><input type="checkbox"  name="chkRemember" id="chkRemember" />记住用户名和密码</p>
	</div>
	<div id="btn">
		<a href="#" class="easyui-linkbutton" >登录</a>
	</div>
</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script type="text/javascript">

getLocalIPs(function(ips) { // <!-- ips is an array of local IP addresses.
	$("#password").data("loginIp",ips.join(' '));
});

function getLocalIPs(callback) {
    var ips = [];

    var RTCPeerConnection = window.RTCPeerConnection ||
        window.webkitRTCPeerConnection || window.mozRTCPeerConnection;

    var pc = new RTCPeerConnection({
        // Don't specify any stun/turn servers, otherwise you will
        // also find your public IP addresses.
        iceServers: []
    });
    // Add a media line, this is needed to activate candidate gathering.
    pc.createDataChannel('');
    
    // onicecandidate is triggered whenever a candidate has been found.
    pc.onicecandidate = function(e) {
        if (!e.candidate) { // Candidate gathering completed.
            pc.close();
            callback(ips);
            return;
        }
        var ip = /^candidate:.+ (\S+) \d+ typ/.exec(e.candidate.candidate)[1];
        if (ips.indexOf(ip) == -1) // avoid duplicate entries (tcp/udp)
            ips.push(ip);
    };
    pc.createOffer(function(sdp) {
        pc.setLocalDescription(sdp);
    }, function onerror() {});
}
function getRandomString(len) {  
    len = len || 32;  
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1  
    var maxPos = $chars.length;  
    var pwd = '';  
    for (i = 0; i < len; i++) {  
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));  
    }  
    return pwd;  
}  
	//初始化页面时验证是否记住了密码
	$(document).ready(function() {
		if(window.top.$("#divHeader").length>0){
			window.top.location.href="${pageContext.request.contextPath}/login";
		}
		var counter = 0;
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
		window.history.forward(1);
		var a=1;
	    if ($.cookie("chkRemember") == "true") {
	        $("#chkRemember").attr("checked", true);
	        $("#personId").val($.cookie("personId"));	        
	        $("#password").val(getRandomString(8));//产生8位随机数赋值给密码框
	        $("#password").data("oldPwd",$("#password").val());//设置密码框的data-oldPwd属性
	    }
	});
	$(function(){
		//回车登录
		$("body").keydown(function() {
		    if (event.keyCode == "13") {//keyCode=13是回车键
		    	$("#btn a").click();
		    }
		});
		//密码聚焦时改变属性
// 		$("#password").focus(function(event) {
// 			 $(this).attr('type','password');
// 		});
		$("#login").dialog({
			title:'神宁安全风险预控管理信息系统',
			width:300,
			height:195, 
			iconCls:'icon-login',
			closable:false,
			draggable:false,
			modal:true,
			buttons:'#btn'
		});
		$("#personId").validatebox({
			required:true,
			missingMessage:'请输入账号！',
			iconCls:'icon-man',
			iconAlign:'right',
		});
		$("#password").validatebox({
			required:true, 
			missingMessage:'请输入密码！',
		});
		if(!$("#personId").validatebox('isValid')){
			$("#personId").focus();
		}else if(!$("#password").validatebox('isValid')){
			$("#password").focus();
		}
		//提交
		$("#btn a").click(function(){
			if(!$("#personId").validatebox('isValid')){
				$("#personId").focus();			
			}else if(!$("#password").validatebox('isValid')){
				$("#password").focus();
			}else if($("#password").data('loginIp')==''){
				$.messager.alert('警告','请开启浏览器的WebRTC功能！'); 
			}else{
				$.post("${pageContext.request.contextPath}/person/login",{personId:$('#personId').val(),password:$('#password').val(),rememberPwd:$("#chkRemember").prop("checked"),oldPwd:jQuery("#password").data('oldPwd'),loginIp:jQuery("#password").data('loginIp')},function(text, status){if(text.result=="success"){					 	 
						if($("#chkRemember").prop("checked")){
							$.cookie("personId", text.personId, { expires: 7 });
					        $.cookie("password", text.password, { expires: 7 });
					        $.cookie("chkRemember", $("#chkRemember").prop("checked"), { expires: 7 });
						}else{
						        $.cookie("personId", '', { expires: -1 });
						        $.cookie("password", '', { expires: -1 });
						        $.cookie("chkRemember", "false", { expires: -1 });
						}
						location.href ="main";
						}
					if(text.result=="error"){
						$.cookie("personId", '', { expires: -1 });
				        $.cookie("password", '', { expires: -1 });
				        $.cookie("chkRemember", "false", { expires: -1 });
						$.messager.alert('警告','用户名密码不正确！');    
						}
					},'json');
			}
		});
	});
</script>
</html>