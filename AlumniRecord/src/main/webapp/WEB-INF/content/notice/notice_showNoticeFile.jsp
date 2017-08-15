<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	#fb{
	float:left
	}
	#submit{
		height:22px
	}
	

</style>
<script type="text/javascript">
	
	function deleteit(index)
	{
		$('#dg').datagrid('selectRow',index);//选中当前行
		var row = $('#dg').datagrid('getSelected');
		var ids = row.id;
		
		$.messager.confirm('删除确认','您确定要删除选中的数据吗？',function(r)
		{
			if(r)
			{
				//发送请求提交删除信息
                $.post("${pageContext.request.contextPath}/notice/noticeAction_deleteAttachment",{'id' : ids},function(result){
					
                	if( result.message == "success" ){
                		$.messager.show({
							title:'提示',
							msg:'成功删除附件！',
							timeout:2000,
							showType:'slide'
						});
                	}else{
                		$.messager.alert('警告','删除失败，请检查后重新操作！');
                	}
                	//删除页面，刷新
					$("#dg").datagrid("clearChecked");
					$("#dg").datagrid("reload");
					parent.$("#dg").datagrid("reload");
					parent.parent.$("#dgEast").datagrid("reload");
				},"JSON");
			}
		});
	}
	//判断附件是否存在并下载
	function downLoadFile(index)
	{
		var rows=$('#dg').datagrid('getRows');
		$.post("${pageContext.request.contextPath}/interior/work/interiorWork_queryInteriorWorkFile.action",
				{attachmentPath:rows[index].physicalFileName},function(result)
		{
			if(result=="success")
			{
				var url ='${pageContext.request.contextPath}/standard/download.action?fileName='+rows[index].physicalFileName+'&&newName='+rows[index].logicalFileName;
				$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
			}else
			{
				$.messager.alert('警告','文件已丢失，下载失败！');
			}
		},"json");
	}
	
	$(function(){
		var parentrow = parent.$('#dg').datagrid('getSelected');
		var noticeId = parentrow.id;
		$("#dg").datagrid({
		    url:'${pageContext.request.contextPath}/notice/noticeAction_showAttachment',
			idField:"id",// id字段为标识字段,那么此字段状态将会被dg记录下来
			queryParams:{id : parentrow.id},//请求远程数据发送额外的参数
			striped:true,/*斑马线效果  */
			nowrap:true,/*数据同一行*/
			loadmsg:'请等待',
			rownumbers:true,/* 行号*/
			remoteSort:false,/*禁止使用远程排序*/
			singleSelect:true,
			checkOnSelect:false,
			toolbar:[{
				id:'text',
				text:"<form id='upload' method='post' enctype='multipart/form-data'>"
						+ "<input id='fb' type='text' name='file' style='width:300px' />"
						+ "<input name='id' type='hidden'/>"
						+ "<a id='submit' href='#'>提交</a>"
					+ "</form>"				 
			}],
		    columns:[[ 
				{field:'id',title:'逻辑主键',hidden:true},
				{field:'attachmentType',title:'附件类型',width:'45%',align:'center'},
		        {field:'logicalFileName',title:'附件逻辑路径',width:'45%',align:'center',formatter:function(value,row,index){
		        	return "<a href='javascript:' onclick='downLoadFile("+index+")' style='text-decoration:none'>" + value + "</a>";
		        }},
		        {field:'physicalFileName',title:'附件物理路径',hidden:true,formatter:function(value,row,index){
					return "<a href="+ value +">" + value + "</a>";} 
		        },
		        
		        {field:'op',title:'操作',width:'8%',align:'center',formatter:function(value,row,index){
		        	return "<a href='javascript:;' id='btn' onclick='deleteit(" + index + ")' style='text-decoration:none'>" +"删除" + "</a>";
				}}
		    ]]    
		});
  		$('#fb').filebox({
		    buttonText: '选择文件', 
		    buttonAlign: 'right',
		    prompt : '按下ctrl可选择多个文件',
		    multiple : true
		});
		$('#submit').linkbutton({
		    iconCls:'icon-add'   
		});
		$('#upload').form('load',{
			id : noticeId,
		});
		//表单提交
		$("[name='file']").validatebox({
			required:true,//file文本域 validate不能实现及时验证
			missingMessage:'请选择一个文件',	
		});
		//开始禁止验证
 		$("#upload").form("disableValidation");
		$("#submit").click(function()
		{
				//开启验证
				$("#upload").form("enableValidation");
				if($("#upload").form("validate"))
				{
					//ajax提交
					$('#upload').form('submit',
					{
						url:'${pageContext.request.contextPath}/notice/noticeAction_uploadFile',
						success:function(data)
						{
							var result = eval( '(' + data + ')' );
							if( result.message != "success" )
							{
								$.messager.alert('警告','附件添加失败');
							}
							$("#upload").form("disableValidation");
							$("#upload").form("reset");
							$("#dg").datagrid("reload");
							parent.$("#dg").datagrid("reload");
							parent.parent.$("#dgEast").datagrid("reload");
						}
					});
				}
		});
	});
</script>
</head>
<body style="margin: 1px;">
	<table id="dg"></table>  
</body>
</html>