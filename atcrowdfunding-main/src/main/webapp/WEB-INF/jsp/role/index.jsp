<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<%@ include file="/WEB-INF/jsp/common/css.jsp"%>
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

   <jsp:include page="/WEB-INF/jsp/common/navbar.jsp"></jsp:include>
    <div class="container-fluid">
      <div class="row">
      <jsp:include page="/WEB-INF/jsp/common/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="condition" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="querybtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;"  id="addBtn"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
							 </ul>
					 </td>
				 </tr>

			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

	<!-- 添加数据模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加角色</h4>
      </div>
      <div class="modal-body">
		  <div class="form-group">
			<label for="exampleInputPassword1">角色名称</label>
			<input type="text" class="form-control" id="rolename" name="rolename" placeholder="请输入角色名称">
		  </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="savaBtn">保存</button>
      </div>
    </div>
  </div>
</div>
	<!-- 修改数据模态框 -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改角色</h4>
      </div>
      <div class="modal-body">
		  <div class="form-group">
		  	<input type="hidden" name="id">
			<label for="exampleInputPassword1">角色名称</label>
			<input type="text" class="form-control" id="rolename" name="rolename" placeholder="请输入角色名称">
		  </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="updateBtn">修改</button>
      </div>
    </div>
  </div>
</div>

    <%@include file="/WEB-INF/jsp/common/script.jsp"%>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    initData(1);
            });
            var json = {
        			pageNum:1,
        			pageSize:2
        	};
            function initData(pageNum){
            	json.pageNum=pageNum;
            	var index = -1;
            	$.ajax({
            		type:'post',
            		url:"${PATH}/role/loadData",
            		data:json,
            		beforeSend:function(){
            			//一般用于表单校验
            			index = layer.load(0,{time:10*1000});
            			return true;
            		},	
            		success:function(result){
            			layer.close(index);
            			initShow(result);
            			initNavg(result);
            		}
            	});
            }
            //显示数据
            function initShow(result){
            	$('tbody').empty();
            	var list = result.list;
            	var content = '';
            	$.each(list,function(i,e){
            		content+='<tr>';
            		content+='  <td>'+(i+1)+'</td>';
            		content+='  <td><input type="checkbox"></td>';
            		content+='  <td>'+e.name+'</td>';
            		content+='  <td>';
            		content+='	  <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
            		content+='	  <button type="button" roleId="'+e.id+'" class="updateClass btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
            		content+='	  <button type="button" roleId="'+e.id+'" class="deleteClass btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            		content+='  </td>';
            		content+='</tr>';
            	});
            	$('tbody').html(content);
            }
            //显示分页
			function initNavg(result){
				$('.pagination').empty();
				if(result.isFirstPage){
					$('.pagination').append($('<li class="disabled"><a href="#">上一页</a></li>'));
				}else{
					$('.pagination').append($('<li><a href="#" onclick="initData('+(result.pageNum-1)+')">上一页</a></li>'));
				}
				var navigatepageNums = result.navigatepageNums;
				$.each(navigatepageNums,function(i,num){
					if(num==result.pageNum){
						$('.pagination').append($('<li class="active"><a href="#">'+num+'<span class="sr-only">(current)</span></a></li>'));
					}else{
						$('.pagination').append($('<li><a href="#" onclick="initData('+(num)+')">'+num+'</a></li>'));
					}
				});
				if(result.isLastPage){
					$('.pagination').append($('<li class="disabled"><a href="#">下一页</a></li>'));
				}else{
					$('.pagination').append($('<li><a href="#" onclick="initData('+(result.pageNum+1)+')">下一页</a></li>'));
				}
            	
            	
            }
            $('#querybtn').off('click').on('click',function(){
            	var condition = $('#condition').val();
            	json.condition=condition;
            	initData(1);
            });
            
            //===添加 开始============
            	$('#addBtn').off('click').on('click',function(){
            		$('#addModal').modal({
            			show:true,
            			backdrop:'static',
            			keyboard:false
            		});
            	});
            	$('#savaBtn').off('click').on('click',function(){
            		var roleName = $('#addModal input[name="rolename"]').val();
            		$.ajax({
            			type:'post',
            			url:"${PATH}/role/doAdd",
            			data:{
            				name:roleName
            			},
            			beforeSend:function(){
            				return true;
            			},
            			success:function(result){
            				if("ok"==result){
            					layer.msg('保存成功',{time:1000},function(){
                					$('#addModal').modal('hide');
                    				$('#addModal input[name="rolename"]').val("");
                    				initData(1);
                				});
            				}else{
            					layer.msg('保存失败',{time:1000});
            				}
            				
            			}
            		});
            	});
            	
            //===添加 结束============
            //===updateStart============
            $('tbody').off('click','.updateClass').on('click','.updateClass',function(){
            	var roleId = $(this).attr('roleId');
            	$.get("${PATH}/role/getRoleById",{id:roleId},function(result){
            		console.log(result);	
            		$('#updateModal input[name="rolename"]').val(result.name);
            		$('#updateModal input[name="id"]').val(result.id);
            		$('#updateModal').modal({
            			show:true,
            			backdrop:'static',
            			keyboard:false
            		});
            	});
            });
            $('#updateBtn').off('click').on('click',function(){
        		var roleName = $('#updateModal input[name="rolename"]').val();
        		var id = $('#updateModal input[name="id"]').val();
        		$.ajax({
        			type:'post',
        			url:"${PATH}/role/doUpdate",
        			data:{
        				name:roleName,
        				id:id
        			},
        			beforeSend:function(){
        				return true;
        			},
        			success:function(result){
        				if("ok"==result){
        					layer.msg('修改成功',{time:1000},function(){
            					$('#updateModal').modal('hide');
                				$('#updateModal input[name="rolename"]').val("");
                				$('#updateModal input[name="id"]').val("");
                				initData(json.pageNum);
            				});
        				}else{
        					layer.msg('保存失败',{time:1000});
        				}
        				initData(json.pageNum);	
        			}
        		});
        	});
            //===updateFinish=======================
            //=====deleteStart=================
            	
            	$('tbody').off('click','.deleteClass').on('click','.deleteClass',function(){
                	var roleId = $(this).attr('roleId');
                	
                	layer.confirm('您是否确定删除数据?',{btn:['确定','取消']},function(index){
                		$.post("${PATH}/role/doDelete",{id:roleId},function(result){
                    		console.log(result);	
    						if(result>0){
    							layer.msg("删除成功");
    							initData(json.pageNum);
    						}else{
    							layer.msg("删除失败");
    						}              		
                    	});
                	},function(index){
                		layer.close(index);
                	});
                	
                });
            //==deleteFinsih===========================
        </script>
  </body>
</html>
    