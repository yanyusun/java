$(document).ready(function() {

	
	/*注册-简写*/
	$(".lbaregluqd>input").click(function(){
		getCaptchaFromApi("lkyzmreg");
		var datareg2={
			name:$("#regnametrue").val(),
			email:$("#regeml").val(),
			password:$("#regpwd").val(),
			account:$("#regname").val(),
			sex:$(".lbaradiusexscur").data("vau"),
			mobile:$("#regtelnum").val(),
			smsCode:$("#regdxnum").val(),
			province:$(".lbaregprov").val(),
			city:$(".lbaregcity").val(),
			area:$(".lbaregqu").val(),
			code:$("#regdxyzm").val(),
			key:getCaptchaKey()
		}
		
		qsExec('auth', '/login/register', 'post', datareg2, false, true ,function(res){
			window.location.href = "regisc.html";
		});
		
		
	});

	/*注册-分开写*/
	/*$(".lbaregluqd>input").click(function() {
		//alert($(".lbaradiusexscur").data("vau"));
		console.log(data);
		var datareg = {
			name: $("#regname").val(),
			email: $("#regeml").val(),
			password: $("#regpwd").val(),
			account: $("#regnametrue").val(),
			sex: $(".lbaradiusexscur").data("vau"),
			mobile: $("#regtelnum").val(),
			smsCode: $("#regdxnum").val(),
			province: $(".lbaregprov").val(),
			city: $(".lbaregcity").val(),
			area: $(".lbaregqu").val(),
			code: $("#regdxyzm").val(),
			key: getCaptchaKey()
		}
		$.ajax({
			type: "post",
			//url:"http://114.215.239.181:9090/auth-web-1.0/login/register",
			url: "http://192.168.1.51:8080/a/login/register",
			async: true,
			data: datareg,
			dataType: "json",
			"headers": {

				"content-type": "application/x-www-form-urlencoded" //使用get 时  就不需要这个
			},
			success: function(aa) {
				console.log(aa);
				if(aa.code == 2000) {
					//console.log('OKKKKK');
					window.location.href = "regisc.html";
				} else {
					alert(aa.msg);
				}
				console.log(aa);

			},
			error: function(aa) {
				//console.log(aa);
				alert("失败");
			}
		});

	});*/

	/*注册的发送短信验证码*/
	 	$(".lbaregyzm").click(function(){
	 		var datareg={
	 			mobile:$("#regtelnum").val(),
	 			smsType:101
	 		}
	 		qsExec('auth', '/sendSmsByCode', 'post', datareg, false, true);

	 	});

	/*注册的发送短信验证码-分开写*/
	/*$(".lbaregyzm").click(function() {
		//alert($(".lbaradiusexscur").data("vau"));
		var datareg = {
			mobile: $("#regtelnum").val(),
			smsType: 101
		}
		$.ajax({
			type: "post",
			//url:"http://114.215.239.181:9090/auth-web-1.0/login/register",
			url: "http://192.168.1.51:8080/a/auth/sendSmsByCode",
			async: true,
			data: datareg,
			dataType: "json",
			"headers": {

				"content-type": "application/x-www-form-urlencoded" //使用get 时  就不需要这个
			},
			success: function(aa) {

				if(aa.code = 2000) {
					alert("发送成功");
				}

				console.log(aa);
			},
			error: function(aa) {
				//console.log(aa);
				alert("失败");
			}
		});

	});*/
	

});

