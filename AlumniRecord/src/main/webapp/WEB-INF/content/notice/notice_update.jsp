<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>公告编辑</title>
		<!-- include libraries(jQuery, bootstrap) -->
		<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
		<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
		
		<!-- include summernote css/js-->
		<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.4/summernote.css" rel="stylesheet">
		<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.4/summernote.js"></script>
		<script src="https://cdn.bootcss.com/summernote/0.8.4/lang/summernote-zh-CN.min.js"></script>
		
		<!-- sweetalert弹出框的js样式 -->
		<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet">
		<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
		
		<script type="text/javascript">
			$(function(){
				var parentHeight = parent.$("#win")["0"].offsetHeight - 120;
				$('#summernote').summernote({
					lang: 'zh-CN',
					height: parentHeight,
					minHeight: null,
					maxHeight: null,
					focus: true,
					placeholder: '请输入内容...',
					toolbar: [
				          // [groupName, [list of button]]
				          ['style', ['bold', 'italic', 'underline', 'clear']],
				          ['font', ['strikethrough', 'superscript', 'subscript']],
				          ['fontsize', ['fontsize']],
				          ['color', ['color']],
				          ['para', ['ul', 'ol', 'paragraph']],
				          ['height', ['height']]
				    ]
				});
				
				//数据回显
				var rows=parent.$("#dg").datagrid("getSelections");
				$('#id')["0"].value = rows[0].id;
				$('#title')["0"].value = rows[0].title;
				$('#summernote').summernote('code', rows[0].content);
				
				
				//botton的事件
				$("#ok_btn").click(function(){
					var titleValue = $('#title').val();
// 					alert($('#summernote').summernote('code'));
// 					alert(titleValue);
					if( !$('#summernote').summernote('isEmpty') && titleValue != null && titleValue != "" ){
						var summernoteValue = $('#summernote').summernote('code');
						//如果验证成功，则提交数据
						$.post('${pageContext.request.contextPath}/notice/noticeAction_updateNotice',
						{
							id : $('#id')["0"].value,
							title : titleValue,
							content : summernoteValue
						},function(result){
							if(result.message == "success"){
								swal({
									  title: "提示",
									  text: "公告更新成功!您要关闭页面吗?",
									  type: "success",
									  showCancelButton: true,
									  confirmButtonColor: "#DD6B55",
									  confirmButtonText: "确定",
									  cancelButtonText: "取消",
									  closeOnConfirm: false
								},
								function(){
									 //关闭当前页面
									 parent.$('#win').window("close");
								});
							}else{
								swal({
									  title: "警告",
									  text: "公告更新失败!您要关闭页面吗?",
									  type: "warning",
									  showCancelButton: true,
									  confirmButtonColor: "#DD6B55",
									  confirmButtonText: "确定",
									  cancelButtonText: "取消",
									  closeOnConfirm: false
								},
								function(){
									 //关闭当前页面
									 parent.$('#win').window("close");
								});
							}
							//重置
							$('#summernote').summernote('reset');
							$('#title')["0"].value = "";
							//刷新dg
							parent.$("#dg").datagrid("reload");
							parent.parent.$("#dgEast").datagrid("reload");
						},'json');
					}else{
						swal("信息不能为空!")
					}
				});
			});
	    </script>
</head>
<body>
	<div style="margin:5px;" class="input-group"> 
		<span class="input-group-addon">&emsp;标题：</span>
		<input id="title" name="title" class="form-control" placeholder="请输入标题..." style="width:500px;" />
		<button id="ok_btn" class="btn btn-default" type="button" style="margin-left:5px;">更新</button>
	</div>
	<div id="summernote" name="content"></div>
	<input type="hidden" id="id" />
</body>
</html>