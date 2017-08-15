<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<title></title>
</head>
<body>
    <div class="header1">
        <div class="header2">
            <div class="logo">
                <strong>神华宁煤安全风险预控管理信息系统</strong>
            </div>
            <div class="contact">
                <div class="prompt">
                    <span id="today"></span>
                </div>
                <div id="headerMenu" class="headerMenu">
                    <ul>
                        <li><a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="exit()">安全退出</a></li>
                        <li><a href="#" class="easyui-menubutton" data-options="menu:'#mm2'"><div id="toDoList">待办事项[${sessionScope['countThing']}]</div></a></li>
                        <li><a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addNewTab('系统使用视频教程', 'BulletinBoard.html')" >系统使用视频教程</a></li>
                        <li><a href="#" class="easyui-menubutton" data-options="menu:'#personal'">您好，<s:property value="#session.personName"/></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id="mm2">
        <div onclick="addNewTab('检查任务', '${pageContext.request.contextPath}/inconformity/item/query/showByPerson');"><a id="mm24">检查任务[${sessionScope['checkTask']}]</a></div>
        <div onclick="addNewTab('需我整改的', '${pageContext.request.contextPath}/inconformity/item/correct');"><a id="mm21">需我整改的[${sessionScope['correctCount']}]</a></div>
        <div onclick="addNewTab('需我复查的', '${pageContext.request.contextPath}/inconformity/item/review');"><a id="mm22">需我复查的[${sessionScope['reviewCount']}]</a></div>
        <div onclick="addNewTab('需我审批的', '${pageContext.request.contextPath}/inconformity/item/query/showByDefer');"><a id="mm25">需我审批的[${sessionScope['DeferThing']}]</a></div>
        <div onclick="addNewTab('我的检查表', '${pageContext.request.contextPath}/inconformity/item/mychecktable');"><a id="mm23">我的检查表[${sessionScope['myCheckTable']}]</a></div>
    </div>
    <div id="personal">
        <div onclick="addNewTab('我的隐患', '${pageContext.request.contextPath}/inconformity/item/query/myUnsafeCondition');">我的隐患</div>
        <div onclick="addNewTab('不安全行为', '${pageContext.request.contextPath}/inconformity/item/query/myUnsafeAct');">不安全行为</div>
    </div>
    <script type="text/javascript">
    
		$(document).ready( function(){
			$("#today").text("");
			// 指定向JSONExample发送请求，将id为form1的表单所包含的表单控件转换为请求参数
			$.post("${pageContext.request.contextPath}/chinaDate" , function(data , statusText)
			{
				for(var propName in data)
				{
					$("#today").append(data[propName]);
				}
			},
			// 指定服务器响应为JSON数据
			"json");
			showTime();
		});
		//定时器判断session是否过期
		function showTime()

		{
			$.post("${pageContext.request.contextPath}/person/sessionJudgeActionP",{},function(result){
				if( !result.message ){
					$.messager.alert('提示', '登录超时，请重新登录！', 'info', function(){
						var url ='${pageContext.request.contextPath}/exitAction_exit.action';
						  $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
					});
				}else{
					setTimeout( "showTime()" ,  parseInt(result.timeDiff) * 60 * 1000 );
				}
			},"json");
// 			$.post("${pageContext.request.contextPath}/person/sessionJudge", function(data , statusText)
// 			{
// 				if( !data.message ){
// 					$.messager.alert('提示', '登录超时，请重新登录！', 'info', function(){
// 						var url ='${pageContext.request.contextPath}/exitAction_exit.action';
// 						  $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
// 					});
// 				}else{
// 					setTimeout( "showTime()" ,  Integer.parseInt(data.timeDiff) * 60 * 1000 );
// 				}
// 			},"json");
			
// 			var sessionTime = "${sessionScope.sessionTime}";
			
// 			if( sessionTime == null ){
// 				$.messager.alert('提示','登录超时，请重新登录！','info',function(){
// 					var url ='${pageContext.request.contextPath}/exitAction_exit.action';
// 					  $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
// 				});
// 			}else{
// 				var today = new Date();
// 				var a = ( today - new Date(sessionTime) ) / 60 / 1000;
// 				if( a < 30 ){//小于30分钟，则重新计时
// 					setTimeout( "showTime()" , (30 - a) * 60 * 1000 );
// 				}else{
// 					$.messager.alert('提示','登录超时，请重新登录！','info',function(){
// 						var url ='${pageContext.request.contextPath}/exitAction_exit.action';
// 						  $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
// 					});
// 				}
				//console.log( " restart time is: " + today.toString());
// 			}

		}
		//退出界面
		function exit(){
			$.messager.confirm('确认','您确认想要安全退出吗？',function(r){    
			    if (r){
					var url ='${pageContext.request.contextPath}/exitAction_exit.action';
					  $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
			    	//$.post("${pageContext.request.contextPath}/hazard/exitAction_exit.action");
			    }    
			});
		};
	</script>
</body>
</html>