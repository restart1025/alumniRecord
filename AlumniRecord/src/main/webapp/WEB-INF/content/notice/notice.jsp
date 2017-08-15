<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>公告编辑</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
			$(function(){
					$('#dg').datagrid({
						url: '${pageContext.request.contextPath}/notice/noticeAction_showMyData',
						idField: 'id',
			            toolbar:'#toolbar',
			            rownumbers: true,	//显示一个行号列
			            fitColumns:true,	//自动适应列
			           	fit:true,			//表格宽高自适应
			            nowrap:false,
			            striped:true,		//斑马线效果
						singleSelect:true,	//单行选中
			            loadmsg:'请等待...',	//加载等待时显示
			            pagination:true,	//显示分页组件
			            pageNumber:1,
			            pageSize:10,
			            pageList:[5,10,15,20,25,30],
						columns:[[
							  {field:'id',hidden:true},
					          {field:'title',title:'标题',width:'20%',align:'center'},
					          {field:'content',title:'内容',width:'48%',align:'center'},
					          {field:'author',title:'发布人',width:'10%',align:'center'},
					          {field:'dateTime',title:'发布时间',width:'10%',align:'center'},
					          {field:'attachment',title:'附件',width:'10%',align:'center',
					        	  formatter: function(value, row, index) {
					        		  return "<a href='javascript:' onclick='showNoticeFile()' style='text-decoration:none'>附件[" + value + "]</a>";	
								  }
							  }
					     ]],
						 onDblClickCell: function(){
							$('#dg').datagrid("uncheckAll");
						 }
					});
					$('#add').bind('click',function(){
						$('#win').window({
							title:"新增公告",
							width:'90%',
							height:'80%',
							content:'<iframe src="${pageContext.request.contextPath}/notice/notice_add" frameborder="0" width="100%" height="100%" />'
						});
					});
					$('#edit').bind('click',function(){
						//判断是否选中
						var rows=$('#dg').datagrid("getSelections")
			 			if(rows.length!=1){
							$.messager.show({
								title:'提示信息',
								msg:'请选择一条记录',
								timeout:2000,
								showType:'slide'
							});														
						}else{
							$('#win').window({
								title:"修改信息",
								width:'90%',
								height:'80%',
								content:'<iframe src="${pageContext.request.contextPath}/notice/notice_update" frameborder="0" width="100%" height="100%" />'
							});
						}
					});
					$('#remove').bind('click',function(){
						 //判断是否有选中行记录
						var rows=$('#dg').datagrid("getSelections")
						if(rows.length==0){
							$.messager.show({
								title:'提示信息',
								msg:'请至少选中一行记录',
								timeout:2000,
								showType:'slide'
							});														
							}else{
								$.messager.confirm('删除确认对话框', '是否要删除选中的公告', function(r){
									if (r){
										var id = rows[0].id;
										$.post("${pageContext.request.contextPath}/notice/noticeAction_delete",
										{
											id:id
										},function(result){
											if(result.message == "success"){
												$.messager.show({
													title:'提示',
													msg:'成功删除公告！',
													timeout:2000,
													showType:'slide'
												});
												$('#dg').datagrid("reload");
											}else{
												$.messager.alert('警告','删除失败，请检查后重新操作！');
											}
										},"json");
										$('#dg').datagrid("unselectAll");
									}
								});
							}
					});
					$('#fb').filebox({    
					    buttonText: '选择文件', 
					    buttonAlign: 'right',
					    multiple:true,
					    prompt:'请选择文件'
					});
			});
			//附件详情
			function showNoticeFile(){
		    	$('#win').window({
					title:"附件详情",
					width:800,
					height:400,
					content:'<iframe src="${pageContext.request.contextPath}/notice/notice_showNoticeFile" frameborder="0" width="100%" height="100%" />'
				});
			};
	    </script>
</head>
<body>
		<table id="dg"></table>
		<div id="toolbar">
			<a id="add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" >新增</a>
			<a id="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >修改</a>
			<a id="remove" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" >删除</a>
		</div>
		
		<label for="fb">&emsp;选择文件：</label><input name="upload" id="fb" type="text" style="width:300px">
		<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="margin-left:10px">上传</a>
		
		<div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>
</body>
</html>