var cookieData = Cookies.get('_backend_userToken');
var formIndex;
var optType = '';
var userId = -1;

if(cookieData){
	cookieData = JSON.parse(cookieData);
}

var showQrcode = function(id, o){
	$(o).remove();
	$('#qr_show').qrcode({width: 64,height: 64,text: "size doesn't matter"});
}

var resetForm = function(){
	optType = '';
	userId = -1;
	$("#user_form")[0].reset();
	$("#registTime").html('');
}

//layui.render('select')

var showDetail = function(id, optType){
	optType = optType;
	if(optType=='detail'){
		$("#form_opt").hide();
	}else{
		userId = id;
		$("#form_opt").show();
	}

	qsExec('sale', '/saleUser/detail', 'get', {userId: id}, true, true, function(res){
		$("input[name='account']").val(res.account);
		$("input[name='name']").val(res.name);
		$("input[name='mobile']").val(res.mobile);
		$("input[name='email']").val(res.email);
		$("#registTime").text(res.createTime);
		var sexStr = res.sex?"男":"女";

		var $div = $("select[name='sex']").next();
		$div.find('div input').val(sexStr);
		$div.find('.layui-this').removeClass('layui-this');
		$div.find('ul li[lay-value='+res.sex+']').addClass('layui-this');

		formIndex = layer.open({
			title: '查看详情',	
		    type: 1,
		    skin: 'layui-layer-rim', //加上边框
		    area: ['765px', '620px'], //宽高
		    content: $("#user_form_wrap"),
		    cancel: function(){
		    	resetForm();
		    }
		});
	});
}

$(function(){
	//分页查询列表
	function getUserList(params, listSelector, pageSelector){
		//获取列表
		qsExec('sale', '/saleUser/list', 'get', params, true, true, function(res){

			var list = res.userList;

			var content = '';

			for(var i=0; i<list.length;i++){
				item = list[i];
				var status = '';
				var freezeStr = '';
				if(!item || item.status<1){
					stateflagStr = '<span style="color:#999">已禁用</span>';
					freezeStr = '启用';
				}else{
					stateflagStr = '<span style="color:green">正常</span>';
					freezeStr = '禁用';
				}
				
				var optCol = '<i>操作</i><em><i class="show_user_detail" data-val="'+item.id+'">查看详情</i>';
				optCol += '<i class="show_user_edit" data-val="'+item.id+'">编辑</i>';
				optCol += '<i class="show_user_freeze" data-status="'+item.status+'" data-val="'+item.id+'">'+freezeStr+'</i>';
				optCol += '<i class="show_user_del" data-val="'+item.id+'">删除</i></em>';

				content += '<li><span class="lzlglbtopyha lzlglbtopspacen"><i>'+(i+1)+'</i><em class="curdis"></em></span>';
				content += '<span class="lzlglbtopyhb lzlglbtopspcls">'+item.account+'</span>';
				content += '<span class="lzlglbtopyhc">'+item.name+'</span>';
				content += '<span class="lzlglbtopyhd">'+(item.sex?"男":"女")+'</span>';
				content += '<span class="lzlglbtopyhe">'+item.email+'</span>';
				content += '<span class="lzlglbtopyhf lzlglbtopspcls">'+item.account+'</span>';
				content += '<span class="lzlglbtopyhg">'+item.mobile+'</span>';
				content += '<span class="lzlglbtopyhh">--</span>';
				content += '<span class="lzlglbtopyhj">--</span>';
				content += '<span class="lzlglbtopyhk">'+stateflagStr+'</span>';
				content += '<span class="lzlglbtopspk lzlglbtopspcls">'+optCol+'</span>';
				content += '<span class="lzlglbtopyhm lzlglbtopspcls">--</span>';
			}

			$(listSelector).html(content);

			$(pageSelector).pagination({
				currentPage: params.page,
		        items: res.query.totalCount,
		        itemsOnPage: res.query.pageCount,
		        cssStyle: 'light-theme'
		    });
		});
	}

	getUserList({page:1, pageCount:qs_pagination.pageCount}, '.lzlgnrul', '.lbapagev');

	$.validator.setDefaults({
		errorClass: "asset-invalid",
	});

	$(".lzladdbtn").on('click', function(){
		formIndex = layer.open({
			title: '添加一个用户',	
		    type: 1,
		    skin: 'layui-layer-rim', //加上边框
		    area: ['765px', '620px'], //宽高
		    content: $("#user_form_wrap"),
		    cancel: function(){
		    	$("#user_form")[0].reset();
		    }
		});
	});

	$('#user_form').validate({
		rules: {
			'account': 'required',
			email: {
				required: true,
				email: true,
			}
		},
		messages:{
			'account': '请填写用户姓名',
			email: {
				required: '邮箱地址不能为空',
				email: '邮箱格式有误',
			}
		},
		submitHandler: function(form){
			var formData = $(form).serialize();
			var actionUrl = '/saleUser/add';
			if(userId>0){
				formData += '&id='+userId;
				actionUrl = '/saleUser/edit';
			}

			qsExec('sale', actionUrl, 'post', formData, true, false, function(res){
				resetForm();

				if(formIndex)
					layer.close(formIndex);
			});

			getUserList({page:1, pageCount:qs_pagination.pageCount}, '.lzlgnrul', '.lbapagev');
		},
	});

	//分页：跳转
	$(document).delegate(".page-link", 'click', function(){
		var pageHref = $(this).attr('href');
		var arr = pageHref.split('-');
		var params = {page: arr[1], pageCount:qs_pagination.pageCount};

		getUserList(params, '.lzlgnrul', '.lbapagev');
	})

	$(document).delegate('.show_user_detail', 'click', function(){
		showDetail($(this).data('val'), 'detail');
	});

	$(document).delegate('.show_user_edit', 'click', function(){
		showDetail($(this).data('val'), 'edit');
	});

	$(document).delegate('.show_user_freeze', 'click', function(){
		var userId = $(this).data('val');
		var showStatus = $(this).data('status')
		var status = showStatus?0:1;

		qsExec('sale', '/saleUser/setLogin', 'post', {'ids[0]': userId, status: status}, true, false, function(res){
			getUserList({page:1, pageCount:qs_pagination.pageCount}, '.lzlgnrul', '.lbapagev');
		});
	});

	var removeUser = function(id){
		qsExec('sale', '/saleUser/del', 'post', {userId: id}, true, false, function(res){
			getUserList({page:1, pageCount:qs_pagination.pageCount}, '.lzlgnrul', '.lbapagev');
		});
	}

	$(document).delegate('.show_user_del', 'click', function(){
		var userId = $(this).data('val');
		layer.msg('确定要删除该用户吗', {
			time: 0 //不自动关闭
			,btn: ['确定', '取消']
			,yes: function(index){
				layer.close(index);
				
				removeUser(userId);
			}
		});
	});
});