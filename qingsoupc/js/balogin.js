$(document).ready(function() {

	/*登陆-头部*/
	$(".lbasitjbtn1").click(function() {
		var datalog = {
			account: $(".ksinlogte").val(),
			paw: $(".ksinlogpwd").val(),
			code: $(".ksinlogyzm").val(),
			key: getCaptchaKey()
		}
		$.ajax({
			type: "post",
			//url:"http://114.215.239.181:9090/auth-web-1.0/login/enterLogin",
			url: qs_base_config.auth.uri + "/login/enterLogin",
			async: true,
			data: datalog,
			dataType: "json",
			"headers": {
				"content-type": "application/x-www-form-urlencoded" //使用get 时  就不需要这个
			},
			success: function(aa) {
				if(aa.code == 2000) {
					Cookies.set('_userToken', aa.data, {path:'/'});
					//console.log('请求头是',aa.data)
					window.location.href = "person-index.html";
				} else {
					alert(aa.msg);
				}
			},
			error: function(aa) {
				//console.log(aa);
				alert("失败");
			}
		});
	});

	/*登陆-侧边*/
	$(".lbasitjbtn2").click(function() {
		var datalog = {
			account: $(".ksinlogte2").val(),
			paw: $(".ksinlogpwd2").val(),
			code: $(".ksinlogyzm2").val(),
			key: getCaptchaKey()
		}
		$.ajax({
			type: "post",
			//url:"http://114.215.239.181:9090/auth-web-1.0/login/enterLogin",
			url: qs_base_config.auth.uri+"/login/enterLogin",
			async: true,
			data: datalog,
			dataType: "json",
			"headers": {
				"content-type": "application/x-www-form-urlencoded" //使用get 时  就不需要这个
			},
			success: function(aa) {
				console.log(aa);
				if(aa.code == 2000) {
					Cookies.set('_userToken', aa.data, {path:'/'});
					window.location.href = "person-index.html";
				} else {
					alert(aa.msg);
				}
			},
			error: function(aa) {
				//console.log(aa);
				alert("失败");
			}
		});
	});

	

});

