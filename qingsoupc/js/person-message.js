$(document).ready(function() {

	
	//获取消息列表
			$.ajax({
				type:"post",
				url:"http://114.215.239.181:9090/business-web-1.0/message/pageList",
				async:true,
				//data:{id:123},
				dataType:"json",
				"headers": {
			  	"x-qs-certified": "false,",
			    "x-qs-role": "1,",
			    "x-qs-status": "1",
			    "x-qs-type": "1,",
			    "x-qs-user": "YWVlODkwNDBmZjcyZTBiN2ZiZTRiN2E2OTVkYWIyZGN8fHx8Mjk2",
		
			    "content-type": "application/x-www-form-urlencoded"   //使用get 时  就不需要这个
			 },
				success:function(aa){
					console.log(aa);
					var bb = aa.data.list;
					//console.log(JSON.stringify(aa));
		
					for(var i=0;i<=bb.length;i++)
					{
						var ztvau=bb[i].typeName;
						//全部消息
						$(".lbagxmrxulall").append("<li><input type='checkbox'><span>"+bb[i].title+"</span><em>"+bb[i].sendTime+"</em><i>"+bb[i].typeName+"</i><strong class='kxxbq'>"+bb[i].label+"</strong><input type='hidden' class='contectnr' value="+bb[i].contant+" /><a class='detailstask'>查看</a></li>");
						$("#kallnum").find("i").text("("+$(".lbagxmrxulall3").find("li").length+")");//获取个数
					     //点击跳转 
						$(".detailstask").click(function(){
							var contant = $(this).parents("li").find(".contectnr").val();
							var title = $(this).parents("li").find("span").text();
							var sendTime = $(this).parents("li").find("em").text();
							var item = {"titlebl":title,"contantbl":contant,"sendTimebl":sendTime};
							var str = JSON.stringify(item);
							localStorage.setItem("strbl",str);
							window.location.href = "person-center-message-detail.html";
						});
						
						
						if(ztvau=='服务消息'){
							$(".lbagxmrxulservice").append("<li><input type='checkbox'><span>"+bb[i].title+"</span><em>"+bb[i].sendTime+"</em><i>"+bb[i].typeName+"</i><strong class='kxxbq'>"+bb[i].label+"</strong><input class='contectnr' type='hidden' value="+bb[i].contant+" /><a class='detailstask'>查看</a></li>");
						    $("#kservnum").find("i").text("("+$(".lbagxmrxulservice3").find("li").length+")");//获取个数
						     //点击跳转 
							$(".detailstask").click(function(){
								var contant = $(this).parents("li").find(".contectnr").val();
								var title = $(this).parents("li").find("span").text();
								var sendTime = $(this).parents("li").find("em").text();
								var item = {"titlebl":title,"contantbl":contant,"sendTimebl":sendTime};
								var str = JSON.stringify(item);
								localStorage.setItem("strbl",str);
								window.location.href = "person-center-message-detail.html";
							});
						}else if(ztvau=='任务消息'){
							$(".lbagxmrxultask").append("<li><input type='checkbox'><span>"+bb[i].title+"</span><em>"+bb[i].sendTime+"</em><i>"+bb[i].typeName+"</i><strong class='kxxbq'>"+bb[i].label+"</strong><input class='contectnr' type='hidden' value="+bb[i].contant+" /><a class='detailstask'>查看</a></li>");
							$("#ktasknum").find("i").text("("+$(".lbagxmrxultask3").find("li").length+")");//获取个数
							//点击跳转 
							$(".detailstask").click(function(){
								var contant = $(this).parents("li").find(".contectnr").val();
								var title = $(this).parents("li").find("span").text();
								var sendTime = $(this).parents("li").find("em").text();
								var item = {"titlebl":title,"contantbl":contant,"sendTimebl":sendTime};
								var str = JSON.stringify(item);
								localStorage.setItem("strbl",str);
								window.location.href = "person-center-message-detail.html";
							});	
						}else if(ztvau=='产品消息'){
							$(".lbagxmrxulaproduct").append("<li><input type='checkbox'><span>"+bb[i].title+"</span><em>"+bb[i].sendTime+"</em><i>"+bb[i].typeName+"</i><strong class='kxxbq'>"+bb[i].label+"</strong><input type='hidden' class='contectnr' value="+bb[i].contant+" /><a class='detailspro'>查看</a></li>");
						    $("#kpronum").find("i").text("("+$(".lbagxmrxulaproduct3").find("li").length+")");//获取个数
						     //点击跳转 
							$(".detailstask").click(function(){
								var contant = $(this).parents("li").find(".contectnr").val();
								var title = $(this).parents("li").find("span").text();
								var sendTime = $(this).parents("li").find("em").text();
								var item = {"titlebl":title,"contantbl":contant,"sendTimebl":sendTime};
								var str = JSON.stringify(item);
								localStorage.setItem("strbl",str);
								window.location.href = "person-center-message-detail.html";
							});	
						}else if(ztvau=='安全消息'){
							$(".lbagxmrxulsafe").append("<li><input type='checkbox'><span>"+bb[i].title+"</span><em>"+bb[i].sendTime+"</em><i>"+bb[i].typeName+"</i><strong class='kxxbq'>"+bb[i].label+"</strong><input type='hidden' class='contectnr' value="+bb[i].contant+" /><a class='detailssafe'>查看</a></li>");
						    $("#ksafenum").find("i").text("("+$(".lbagxmrxulsafe3").find("li").length+")")//获取个数
						    //点击跳转 
							$(".detailstask").click(function(){
								var contant = $(this).parents("li").find(".contectnr").val();
								var title = $(this).parents("li").find("span").text();
								var sendTime = $(this).parents("li").find("em").text();
								var item = {"titlebl":title,"contantbl":contant,"sendTimebl":sendTime};
								var str = JSON.stringify(item);
								localStorage.setItem("strbl",str);
								window.location.href = "person-center-message-detail.html";
							});	
						}
						
					}
				},
				error:function(aa){
					//console.log(aa);
					alert("失败");
				}
			});

	

});

