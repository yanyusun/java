var cookieData = Cookies.get('_backend_userToken');
$(function(){
	if(cookieData)
		qsExec('sale', '/saleUser/personal', 'post', {}, true, true, function(res){
			console.log(res)
			var manDetail = res.detail;
			var roleStr = manDetail.roleType?"管理员":"普通员工";
			var user_name = manDetail.account+"("+roleStr+")/"+manDetail.name;
			var provinceName = manDetail.provinceName?manDetail.provinceName:'';
			var cityName = manDetail.cityName?manDetail.cityName:'';
			var areaName = manDetail.areaName?manDetail.areaName:'';
			var user_addr = provinceName+" "+manDetail.cityName+" "+areaName;

			$("#user_name").text(user_name);
			$("#user_addr").text(user_addr);
			var last_login = "上次登录时间：" + manDetail.loginTime;
			$("#user_last_login").text(last_login);
		});
});