var cookieData = Cookies.get('_backend_userToken');
if(!!cookieData){
	location.href = 'index.html';
}

$(document).ready(function() {
	//加载验证码
	getCaptchaFromApi("htlogymm");
	/*点击登陆时重新获取验证码*/
	$(".htlogbtn").click(function(){
		getCaptchaFromApi("htlogymm");
	})
	
	/*登陆-头部*/
	$(".htlogbtn").click(function() {
		var account = $.trim($(".htlogna").val());
		var paw = $.trim($(".htlogpw").val());
		var code = $.trim($(".htlogyk").val());

		if(!!!account){
			$(".htlogna").focus();
			return false;
		}

		if(!!!paw){
			$(".htlogpw").focus();
			return false;
		}

		if(!!!code){
			$(".htlogyk").focus();
			return false;
		}

		var datalog = {
			account: account,
			paw: paw,
			code: code,
			key: getCaptchaKey()
		}

		$.ajax({
			type: "post",
			url: qs_base_config.auth.uri+ "/login/enterLogin",
			async: true,
			data: datalog,
			dataType: "json",
			"headers": {
				"content-type": "application/x-www-form-urlencoded" //使用get 时  就不需要这个
			},
			success: function(res) {
				if(res.code == 2000) {
					if(res.data['x-qs-type']==='1,'){
						Cookies.set('_backend_userToken', res.data, {path:'/'});
						layer.msg('登录成功！');
						location.href = 'index.html';
					}else{
						layer.msg('抱歉，您没有权限登录后台！');
					}
				} else {
					layer.alert(res.msg, {icon: 5});
				}
			},
			error: function(res) {console.log(res)}
		});
	});

	

	

});

