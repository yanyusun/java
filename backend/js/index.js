var cookieData = Cookies.get('_backend_userToken');

if(!cookieData){
	layer.alert('您还没有登录系统！', {
		icon: 5,
		yes: function(index){
			layer.close(index);
			location.href = 'login.html';
		},
	});
}

$(document).ready(function() {
	
    /*固定资产内容区tab切换*/
	/*$(".batabqh>span").click(function(){
		var banum=$(this).index();
		$(this).addClass("wzbtcur").siblings().removeClass("wzbtcur");
		$(".bazyglnrvv").eq(banum).show().siblings(".bazyglnrvv").hide()
		
	});*/

	
	/*个人中心右侧小导航弹窗*/
	$(".lbagxtoprmc").hover(function(){
		$(".lbagrzxlbtc").stop().slideDown(200);
		$(".lbagxtoprmc>i").removeClass("lbagxtoprmcura").addClass("lbagxtoprmcur");
	},function(){
		$(".lbagrzxlbtc").stop().slideUp(200);
		$(".lbagxtoprmc>i").removeClass("lbagxtoprmcur").addClass("lbagxtoprmcura");
	});
	
	
	/*框架左侧导航*/
	$(".lbakjsyftp").hover(function(){
		$(this).parent("li").addClass("lbagxfdhcur1");
	},function(){
		$(this).parent("li").removeClass("lbagxfdhcur1");
	});
	
	$(".lbagxnrvf>ul>li").click(function(){
		$(this).find(".lbakjejdh").stop().slideDown(300).parent("li").siblings().find(".lbakjejdh").slideUp(300);
		$(this).find("p>i").addClass("lbakjjtcura").parents("li").siblings().find("p>i").removeClass("lbakjjtcura");
	});
	
	
	/*首页的概况,数据分析,代办事项切换*/
	$(".lbasynrb>ul>li").click(function(){
		var num=$(this).index();
		$(".lbasynrb>ul>li").eq(num).addClass("lbasynrbcur").siblings().removeClass("lbasynrbcur");
		$(".lbasynrbqhv").eq(num).removeClass("curdis").siblings(".lbasynrbqhv").addClass("curdis");
	});
	
	
	//select文本框
	$(".sewvtop").click(function(){
		$(this).find(".lletopimg").removeClass("lbaxztop2");
		$(this).next().slideToggle(150);
		if($(this).find(".lletopimg").hasClass("lbaxztop")){
			$(this).find(".lletopimg").addClass("lbaxztop2");
			$(this).find(".lletopimg").removeClass("lbaxztop");
		}else{
			$(this).find(".lletopimg").addClass("lbaxztop");
			$(this).find(".lletopimg").removeClass("lbaxztop2");
		}
	});
	
	/*点击下拉框内容 获取其值并赋值给显示框*/
	$(document).delegate('.sewvbm>li', 'click', function(){
		$(this).addClass('pca_cur');
		$(this).siblings().removeClass('pca_cur');
		var selva=$(this).text();
		$(this).parent("ul").prev().find("span").text(selva);
		//$(".sewvtop>span").text(selva);
		$(this).parent("ul").hide();
	});

	
	/*鼠标离开下拉框关闭*/
	$(".sewv").mouseleave(function(){
		$(".sewvbm").hide();
		$(this).find(".lletopimg").addClass("lbaxztop2");
	});
	

	/*清除条件*/
	$(".lzlgcctj").click(function(){
		//$(".sewv").find("span").empty();
		for(var i=0;i<$(".sewv").length;i++){
			var sekvlue=$(this).siblings(".sewv").eq(i).find(".sewvbm>li").first().text();
			$(this).siblings(".sewv").eq(i).find(".sewvtop>span").text(sekvlue);
		}
	});
	
	
	/*添加一个*/
	$(".lzladdbtn").click(function(){
		$(".ladtcv").show();
	});
	$(".ladtcmatop").click(function(){
		$(".ladtcv").hide();
	});
	
	/*搜索框出现关闭按钮*/
	$(".lzltopsskf").keyup(function(){
		$(".lztopssgbbn").removeClass("curdis");
	});
	$(".lztopssgbbn").click(function(){
		$(this).prev().val('');
		$(this).addClass("curdis");
	});
	
	
	/*添加一个的单选按钮*/
	$(".lacdxpa>span>em").click(function(){
		var cunnum=$(this).find(".radiuscurb").length;
		if(cunnum==0){
			$(this).addClass("radiuscurb").parent().siblings("span").find("em").removeClass("radiuscurb");
		}
	});
	
	/*资产管理-资产包的操作下拉菜单*/
	$(document).delegate('.lzlglbtopspk', 'click', function(){
		$(this).find("em").show();
	})
	
	$(document).delegate(".lzlglbtopspk", 'mouseleave', function(){
		$(this).find("em").hide();
	});
	
	/*删除*/
	$(".lzgzcbsc").click(function(){
		$(".ldeletc").show();
		//$(this).parents("li").addClass("aa");
	});
	
	$(".ldeanqx").click(function(){
		$(".ldeletc").hide();
	});
	
	
	/*资源管理列表左侧的多选框*/
	
	$(".lzlgnrul>li").hover(function(){
		$(this).find(".lzlglbtopspacen>i").addClass("curdis");
		$(this).find(".lzlglbtopspacen>em").removeClass("curdis");
	},function(){
		if($(this).find(".lzlglbtopspacen>em").hasClass("radiuscur")){
			$(this).find(".lzlglbtopspacen>i").addClass("curdis");
		    $(this).find(".lzlglbtopspacen>em").removeClass("curdis");
		}else{
		   $(this).find(".lzlglbtopspacen>i").removeClass("curdis");
		   $(this).find(".lzlglbtopspacen>em").addClass("curdis");
		}
	});
	/*资源管理类的多选*/
	$(".lzlglbtopspacen>em").click(function(){
		
		if($(this).hasClass("radiuscur")){
			$(this).removeClass("radiuscur");
			$(this).removeClass("curdis");
			$(this).prev().addClass("curdis");
			var emnum=$(".lzlglbtopspacen>em").size();
			var ranum=$(".radiuscur").size()-1;
			//alert("you总个数为:"+emnum+"，选中的个数为："+ranum)
			if(emnum==ranum){
				$(this).parents(".lzlgnrul").prev(".lzlglbtop").find(".lzlqxbtn").addClass("radiuscur");
			}else{
				$(this).parents(".lzlgnrul").prev(".lzlglbtop").find(".lzlqxbtn").removeClass("radiuscur");
			}
			
		}else{
			$(this).addClass("radiuscur");
			$(this).removeClass("curdis");
			$(this).prev().addClass("curdis");
			var emnum=$(".lzlglbtopspacen>em").size();
			var ranum=$(".radiuscur").size();
			//alert("meiyou总个数为:"+emnum+"，选中的个数为："+ranum)
			if(emnum==ranum){
				$(this).parents(".lzlgnrul").prev(".lzlglbtop").find(".lzlqxbtn").addClass("radiuscur");
			}else{
				$(this).parents(".lzlgnrul").prev(".lzlglbtop").find(".lzlqxbtn").removeClass("radiuscur");
			}
		}
		
	});

	/*资源管理类的全选*/
	$(".lzlqxbtn").click(function(){
		if($(this).hasClass("radiuscur")){
			$(this).removeClass("radiuscur");
			$(".lzlgnrul>li>.lzlglbtopspacen>em").removeClass("radiuscur");
		}else{
			$(this).addClass("radiuscur");
		    $(".lzlgnrul>li>.lzlglbtopspacen>i").addClass("curdis");
		    $(".lzlgnrul>li>.lzlglbtopspacen>em").removeClass("curdis").addClass("radiuscur");
		}
		
	});
	
	
	/*系统设置的开关按钮*/
	$(".lxtszkgan>i").click(function(){
		if($(this).parent().hasClass("kgancur")){
			$(this).parent().removeClass("kgancur");
			$(this).prev("em").text("已开启");
		}else{
			$(this).parent().addClass("kgancur");
			$(this).prev("em").text("已关闭");
		}
	});
	
	
	/*消息中心详情的显示和隐藏*/
	$(".lxxcenv>p>span").click(function(){
		var xxgnnum=$(this).index();
		$(this).addClass("lxxcenvcur").siblings().removeClass("lxxcenvcur");
		$(".lgrxxqhv").eq(xxgnnum).show().siblings(".lgrxxqhv").hide()
	});
	
	$(".lxxzxnrul>li>div").click(function(){
		$(this).next("p").show().parent("li").siblings("li").children("p").hide();
	});
	
	/*系统通知*/
	$(".lxttznrul>li>div").click(function(){
		$(this).addClass("lxttzydcur");
		$(this).next("p").slideDown().parent("li").siblings("li").find("p").slideUp();
	});
	
	//退出登录
	$(document).delegate('.backend_logout', 'click', function(){
		layer.msg('确定要退出登录吗', {
			time: 0 //不自动关闭
			,btn: ['确定', '取消']
			,yes: function(index){
				layer.close(index);
				Cookies.remove('_backend_userToken', {path: '/'});
				location.href = 'login.html';
			}
		});
	});
});
          
          

			








            
	       
