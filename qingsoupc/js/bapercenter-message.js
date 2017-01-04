$(document).ready(function() {

	/*头部铃铛旁边获取消息总数*/
	//qsExec('business', '/message/pageList', 'post', {}, true, true, renderMsg);

	/*$.ajax({
		type: "post",
		url: "http://114.215.239.181:9090/business-web-1.0/message/pageList",
		async: true,
		//data:{id:123},
		dataType: "json",
		"headers": {
			"x-qs-certified": "dfg,",
			"x-qs-role": "1,",
			"x-qs-status": "1",
			"x-qs-type": "1,",
			"x-qs-user": "YWVlODkwNDBmZjcyZTBiN2ZiZTRiN2E2OTVkYWIyZGN8fHx8Mjk2",

			"content-type": "application/x-www-form-urlencoded" //使用get 时  就不需要这个
		},
		success: function(aa) {
			//console.log(aa);
			var bb = aa.data.list;
			$(".lbagxtoprxx>i").text(bb.length);
		},
		error: function(aa) {
			//console.log(aa);
			alert("失败");
		}
	});*/

	/*消息列表及详情*/
	qsExec('business', '/message/pageList', 'post', {}, true, true, renderMsg);
	function renderMsg(data) {
		console.log('消息',data);
		var bb = data.list;
		//console.log(JSON.stringify(aa));
		for(var i = 0; i <= bb.length; i++) {
		    
		      
				
				//bb[i].title
				var batitle='';
				if(!!bb[i]&&!!bb[i].title){
					batitle=bb[i].title;
				}
				//bb[i].sendTime
				var basendtime='';
				if(!!bb[i]&&!!bb[i].sendTime){
					basendtime=bb[i].sendTime;
				}
				//bb[i].typeName
				  var ztvau='';
				if(!!bb[i]&&!!bb[i].typeName){
					ztvau=bb[i].typeName;
				}
				
				//bb[i].label
				var balabel='';
				if(!!bb[i]&&!!bb[i].label){
					balabel=bb[i].label;
				}
				
				//bb[i].contant
				var bacontant='';
				if(!!bb[i]&&!!bb[i].contant){
					bacontant=bb[i].contant;
				}
		    
		    
			//var ztvau = bb[i].typeName;
			//全部消息
			$(".lbagxmrxulall").append("<li><input type='checkbox'><span>" + batitle + "</span><em>" + basendtime + "</em><i>" + ztvau + "</i><strong class='kxxbq'>" + balabel + "</strong><input type='hidden' class='contectnr' value=" + bacontant + " /><a class='detailstask'>查看</a></li>");
			$("#kallnum").find("i").text("(" + $(".lbagxmrxulall3").find("li").length + ")"); //获取个数
			// 点击跳转 
			$(".detailstask").click(function() {
				var contant = $(this).parents("li").find(".contectnr").val();
				var title = $(this).parents("li").find("span").text();
				var sendTime = $(this).parents("li").find("em").text();
				var item = {
					"titlebl": title,
					"contantbl": contant,
					"sendTimebl": sendTime
				};
				var str = JSON.stringify(item);
				localStorage.setItem("strbl", str);
				window.location.href = "person-center-message-detail.html";
			});

			if(ztvau == '服务消息') {
				$(".lbagxmrxulservice").append("<li><input type='checkbox'><span>" + batitle + "</span><em>" + basendtime + "</em><i>" + ztvau + "</i><strong class='kxxbq'>" + balabel + "</strong><input class='contectnr' type='hidden' value=" + bacontant + " /><a class='detailstask'>查看</a></li>");
				$("#kservnum").find("i").text("(" + $(".lbagxmrxulservice3").find("li").length + ")"); //获取个数
				// 点击跳转 
				$(".detailstask").click(function() {
					var contant = $(this).parents("li").find(".contectnr").val();
					var title = $(this).parents("li").find("span").text();
					var sendTime = $(this).parents("li").find("em").text();
					var item = {
						"titlebl": title,
						"contantbl": contant,
						"sendTimebl": sendTime
					};
					var str = JSON.stringify(item);
					localStorage.setItem("strbl", str);
					window.location.href = "person-center-message-detail.html";
				});
			} else if(ztvau == '任务消息') {
				$(".lbagxmrxultask").append("<li><input type='checkbox'><span>" + batitle + "</span><em>" + basendtime + "</em><i>" + ztvau + "</i><strong class='kxxbq'>" + balabel + "</strong><input class='contectnr' type='hidden' value=" + bacontant + " /><a class='detailstask'>查看</a></li>");
				$("#ktasknum").find("i").text("(" + $(".lbagxmrxultask3").find("li").length + ")"); //获取个数
				// 点击跳转 
				$(".detailstask").click(function() {
					var contant = $(this).parents("li").find(".contectnr").val();
					var title = $(this).parents("li").find("span").text();
					var sendTime = $(this).parents("li").find("em").text();
					var item = {
						"titlebl": title,
						"contantbl": contant,
						"sendTimebl": sendTime
					};
					var str = JSON.stringify(item);
					localStorage.setItem("strbl", str);
					window.location.href = "person-center-message-detail.html";
				});
			} else if(ztvau == '产品消息') {
				$(".lbagxmrxulaproduct").append("<li><input type='checkbox'><span>" + batitle + "</span><em>" + basendtime + "</em><i>" + ztvau + "</i><strong class='kxxbq'>" + balabel + "</strong><input type='hidden' class='contectnr' value=" + bacontant + " /><a class='detailspro'>查看</a></li>");
				$("#kpronum").find("i").text("(" + $(".lbagxmrxulaproduct3").find("li").length + ")"); //获取个数
				// 点击跳转 
				$(".detailstask").click(function() {
					var contant = $(this).parents("li").find(".contectnr").val();
					var title = $(this).parents("li").find("span").text();
					var sendTime = $(this).parents("li").find("em").text();
					var item = {
						"titlebl": title,
						"contantbl": contant,
						"sendTimebl": sendTime
					};
					var str = JSON.stringify(item);
					localStorage.setItem("strbl", str);
					window.location.href = "person-center-message-detail.html";
				});
			} else if(ztvau == '安全消息') {
				$(".lbagxmrxulsafe").append("<li><input type='checkbox'><span>" + batitle + "</span><em>" + basendtime + "</em><i>" + ztvau + "</i><strong class='kxxbq'>" + balabel + "</strong><input type='hidden' class='contectnr' value=" + bacontant + " /><a class='detailssafe'>查看</a></li>");
				$("#ksafenum").find("i").text("(" + $(".lbagxmrxulsafe3").find("li").length + ")") //获取个数
					// 点击跳转 
				$(".detailstask").click(function() {
					var contant = $(this).parents("li").find(".contectnr").val();
					var title = $(this).parents("li").find("span").text();
					var sendTime = $(this).parents("li").find("em").text();
					var item = {
						"titlebl": title,
						"contantbl": contant,
						"sendTimebl": sendTime
					};
					var str = JSON.stringify(item);
					localStorage.setItem("strbl", str);
					window.location.href = "person-center-message-detail.html";
				});
			}

		}
	};

	

});

