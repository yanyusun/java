$(document).ready(function() {
	
	
	 /*个人债券列表页收藏*/
	/*	$(document).delegate('.bapebondsc', 'click', function(){
			var id = $(this).data('id');
			console.log(id);return false;
			var baasdetailid = getQueryString('id');
			var ztf='';
			if(!!data.status)
			qsExec('business', '/business/collect', 'get', {objectType  :10,objectId :baasdetailid,status:ztf}, false, true, function(res){
				
			});
			
		});*/
		
		/*已发布列表切换*/
		$(".lktabqh>span").click(function(){
			var yfbnum=$(this).index();
			$(".lktabqh>span").eq(yfbnum).addClass("wzbtcur").siblings(".lktabqh>span").removeClass("wzbtcur");
			$(".lkyfbnrt").eq(yfbnum).show().siblings(".lkyfbnrt").hide();
		})
	


        //加载验证码
		$(".lbasinbtn>span>em").click(function(){
			getCaptchaFromApi("yzmlogk");
		});
		$(".lbacldlk>p").click(function(){
			getCaptchaFromApi("yzmlogkcb");
		});
		/*点击登陆时重新获取验证码*/
		$(".lbasitjbtn1").click(function(){
			getCaptchaFromApi("yzmlogk");
		})
		$(".lbasitjbtn2").click(function(){
			getCaptchaFromApi("yzmlogkcb");
		})




	//详情页小图切换
	
	$(".marquee1>ul>li").click(function(){
		var indexnum=$(this).index()+1;
		
		$(this).find("img").addClass("curimgb").parent("li").siblings("li").find("img").removeClass("curimgb");
		 var srcn=$(this).find("img").attr("src");
		 $(".lbaxqfqhu").find("img").attr("src",srcn);
	});
	
	

    /*注册页面的性别切换*/
	$(".lbasexreg>em").click(function() {
		var cunnum = $(this).find(".lbaradiusexscur").length;
		if(cunnum == 0) {
			$(this).addClass("lbaradiusexscur").siblings(".lbasexreg>em").removeClass("lbaradiusexscur");
		}
	});

	/*导航下拉*/
	$(".lbadhej").hover(function() {
		$(this).removeClass("lbadhlogx").addClass("lbadhbjcur");
		$(this).find("p").stop().slideDown(150);
	}, function() {
		$(this).find("p").stop().slideUp(150);
		$(this).removeClass("lbadhbjcur").addClass("lbadhlogx");
	});

	/*帮助中心*/
	$(".wtrztop>p>span").click(function() {
		var num = $(this).index(".wtrztop>p>span");
		$('.wtrztop>p>span').eq(num).addClass('wtrzcur').siblings('.wtrztop>p>span').removeClass('wtrzcur');
		$(".wtrzbm").eq(num).stop().show().siblings(".wtrzbm").stop().hide();
	});

	/*新闻资讯切换*/
	$(".lbanrqh>span").click(function() {
		var num = $(this).index(".lbanrqh>span");
		$(".lbanrqh>span").eq(num).addClass("lbaxqcur").siblings(".lbanrqh>span").removeClass("lbaxqcur");
		$(".newqhzv").eq(num).show().siblings(".newqhzv").hide();
	});

	/*登录弹窗*/

	$(".lbasinbtn>span>em").click(function() {
		$(".labcldlkh").hide();
		$(".sindv").fadeToggle(100);
	});

	/*	$(".aa2").click(function(){
			$(".bb2").hide();
		}); */

	$(".lbahebm").click(function() {
		$(".sindv").hide();
	});

	$(".lbanrbjv").click(function() {
		$(".sindv").hide();
	});

	/*注册弹窗*/
	$(".lbaregtop>em").click(function() {
		$(".lbaregdv").hide();
	});

	$(".lbaregtopb>em").click(function() {
		$(".lbaregdvb").hide();
	});

	$(".lbaregtopc>em").click(function() {
		$(".lbaregdvc").hide();
	});

	//反馈页面-文本输入框剩余字数计算  (反馈和个人中心的意见反馈)

	/*$(".lbafknk").focus(function() {
		$(".lbatextssp").show();
	});

	$(".lbafknk").blur(function() {
		$(".lbatextssp").hide();
	});

	$(".lbafknk").keyup(function() {
		var lbatxva = $(this).val().length;
		if(lbatxva > 399) {
			$(this).val($(this).val().substring(0, 400));
		}
		var synum = 400 - lbatxva;
		if(synum < 0) {
			$(".lbatexi").html(0);
		} else {
			$(".lbatexi").html(synum);
		}
	});*/

	/*首页新闻切换*/
	$(".lbasynrbztop>span").mouseenter(function() {
		var num = $(this).index();
		$(".lbasynrbztop>span").eq(num).addClass("lbasynrbztpcur").siblings(".lbasynrbztop>span").removeClass("lbasynrbztpcur");
		$(".lbasynrbzul").eq(num).show().siblings(".lbasynrbzul").hide();
	});

	/*地图上方切换(条件筛选)*/
	$(".map-title>li").click(function() {
		var num = $(this).index();
		$(".map-title>li").eq(num).addClass("lbamapdhcur").siblings(".map-title>li").removeClass("lbamapdhcur");
	});

	/*个人中心右侧小导航弹窗*/
	$(".lbagxtoprmc").hover(function() {
		$(".lbagrzxlbtc").stop().slideDown(200);
		$(".lbagxtoprmc>i").removeClass("lbagxtoprmcura").addClass("lbagxtoprmcur");
	}, function() {
		$(".lbagrzxlbtc").stop().slideUp(200);
		$(".lbagxtoprmc>i").removeClass("lbagxtoprmcur").addClass("lbagxtoprmcura");
	});

	/*个人中心-意见反馈-历史反馈*/
	$(".lbagx-fklsnrul>li>p").click(function() {
		$(this).find("span").removeClass("lbagxfklsucurb").addClass("lbagxfklsucura").parents("li").siblings("li").find("span").removeClass("lbagxfklsucura").addClass("lbagxfklsucurb");
		$(this).parent().find("div").slideDown().addClass("lbagxfklsxt").parents("li").siblings("li").find("div").slideUp().removeClass("lbagxfklsxt");
	});

	$(".lbagzscdh>ul>li").click(function() {
		var num = $(this).index(".lbagzscdh>ul>li");
		$(".lbagzscdh>ul>li").eq(num).addClass("lbagzsclicur").siblings(".lbagzscdh>ul>li").removeClass("lbagzsclicur");
		$(".lbagxmexv").eq(num).show().siblings(".lbagxmexv").hide();
	});
	/*未读 已读切换*/
	$(".lbaxxzxnrm>span").click(function() {
		var num = $(this).index(".lbaxxzxnrm>span");
		$(".lbaxxzxnrm>span").eq(num).addClass("lbaxxzxnrmcur").siblings(".lbaxxzxnrm>span").removeClass("lbaxxzxnrmcur");
		$(".kmessnrv").eq(num).show().siblings(".kmessnrv").hide();
	});

	//内页筛选按钮
	$(".lbanysxzdan").click(function() {
		var sxvalue = $(".lbanysxzdan").text();
		//alert(sxvalue);

		if(sxvalue == '收起') {
			$(".lbanysxvnrv").slideUp();
			$(".lbanysxzdan").html("展开");
		} else {
			$(".lbanysxvnrv").slideDown();
			$(".lbanysxzdan").html("收起");
		}

	});

	/*关注类估价弹窗*/

	$(".lbagztctitp>em").click(function() {
		$(".lbagjtcv").fadeOut();
	});

	/*估价列表弹窗*/

	$(".lbagzxqjgls>span").click(function() {
		$(".lbagjlbtc").fadeIn();
	});

	$(".gjlbtpa>em").click(function() {
		$(".lbagjlbtc").fadeOut();
	});

	/*提示语的hover(左到右)*/

	$(".lbacenrul>li").hover(function() {
		$(this).find(".lbacetsyv").stop().animate({
			'left': '-136px',
			'opacity': '1'
		}, 150)
	}, function() {
		$(this).find(".lbacetsyv").stop().animate({
			'left': '36px',
			'opacity': '0'
		}, 150)
	});

	$(".lbacenrul>li").hover(function() {
		$(this).find(".lbacetsyv2").stop().animate({
			'left': '-88px',
			'opacity': '1'
		}, 150)
	}, function() {
		$(this).find(".lbacetsyv2").stop().animate({
			'left': '36px',
			'opacity': '0'
		}, 150)
	});

	/*点击显示和隐藏*/
	$(".lbacenrul>li").click(function() {
		//$(this).find(".labcldlkh").stop().animate({'right':'47px','opacity':'1'},100).parents("li").siblings("li").find(".labcldlkh").animate({'right':'-522px','opacity':'0'},100);
		$(".sindv").hide();
		$(".labcldlkh").show();
		$(this).find(".labcldlkh").animate({
			'right': '47px',
			'opacity': '1'
		}, 100).parents("li").siblings("li").find(".labcldlkh").animate({
			'right': '-522px',
			'opacity': '0'
		}, 100);

		//$(this).find(".labcldlkh").show().parents("li").siblings("li").find(".labcldlkh").hide(50);
	});

	/*二维码的hover*/
	/*	$(".lbaceewm").hover(function(){
			$(".lbaceewmck").animate({'right':'50px','opacity':'1'});
		},function(){
			$(".lbaceewmck").animate('right':'-522px','opacity':'0');
		});*/

	$(".lbaceewm").hover(function() {
		$(".lbaceewmck").show();
	}, function() {
		$(".lbaceewmck").hide();
	});

	/*点击其他地方消失*/
	$(".lbafotv").click(function() {
		$(".labcldlkh").hide();
	});

	$(".lbahebm").click(function() {
		$(".labcldlkh").hide();
	});

	$(".lbanrbjv").click(function() {
		$(".labcldlkh").hide();
	});

	/*内页详情页滑动调到指定的位置*/

	$(".lbasffnxul>li").click(function() {
		/*var cvaz=$(".lbasfxqfbjlb").offset().top-$("body").scrollTop(); 
		alert(cvaz+"px");*/

		var uu = $(".lbasffnxul>li").offset().top;
		//alert(uu);/*606  546-(60)  660*/
		var numvz = $(this).index();
		$(".lbasffnxul>li").eq(numvz).addClass("lbasfflulcur").siblings(".lbasffnxul>li").removeClass("lbasfflulcur");
		if(uu > 606) {
			var oftpzz = $(".lbasfxqfb").eq(numvz).offset().top - 67;

			var len = $(".lbasfxqfb").length;
			for(var i = 0; i < len; i++) {
				$("html,body").animate({
					scrollTop: oftpzz
				}, 150)
			}
		} else if(645 < uu < 646) {
			var oftpzz = $(".lbasfxqfb").eq(numvz).offset().top - 50;
			//alert(oftpzz);
			var len = $(".lbasfxqfb").length;
			for(var i = 0; i < len; i++) {
				$("html,body").animate({
					scrollTop: oftpzz
				}, 150)
			}
		} else {
			var oftpzz = $(".lbasfxqfb").eq(numvz).offset().top - 50;
			//alert(oftpzz);
			var len = $(".lbasfxqfb").length;
			for(var i = 0; i < len; i++) {
				$("html,body").animate({
					scrollTop: oftpzz
				}, 150)
			}
		}

	});

	

	/*返回顶部*/
	$(".lbacebktp").click(function() {
		var timeid = setInterval(function() {
			$(window).scrollTop($(window).scrollTop() - 100);
			if($(window).scrollTop() == 0) {
				clearInterval(timeid);
			}
		}, 1)
	});

	//getCaptchaFromApi("lkyzmreg");/*注册的验证码*/
	//getCaptchaFromApi("yzmlogk");/*登陆的验证码*/
	//getCaptchaFromApi("yzmlogkcb");/*侧边框登陆的验证码*/

});

/*12.19注释*/
/*  $(function(){
	          var oftpva=$(".lbahebm").offset().top;
	          $(window).scroll(function(){
	          	var sctpva=$(this).scrollTop();
	          	if(sctpva>oftpva){
	          		$(".lbahebm").addClass("lbagwqcur1");
	          	}else if(sctpva<oftpva){
	          		$(".lbahebm").removeClass("lbagwqcur1");
	          	}
	          });
          });*/

/*详情页的小导航固定到顶部*/
/*$(function(){
				 var navH = $(".lbasffnxul").offset().top-50;
				$(window).scroll(function(){
				   var scroH = $(this).scrollTop();
					if(scroH>=navH){
					$(".lbasffnxul").parents().find(".lbahebm").removeClass("lbagwqcur1");
					$(".lbasffnxul").addClass("lbagwqcur");
					}else if(scroH<navH){
					$(".lbasffnxul").removeClass("lbagwqcur");
					}
				})
			}); */