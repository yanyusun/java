var dtypeOptionArr = ['常规催收', '司法化解', '市场处置', '民间转贷', '拍卖', '转让'];
var ptypeOptionArr = ['元', '%', '折'];
var ptypeOptionStrArr = ['佣金', '百分比', '转让折扣'];
var countArr = ['一', '二', '三', '四', '五', '六'];
var cookieStr = Cookies.get('_backend_userToken');
var cookieData;

if(!!cookieStr){
	cookieData = JSON.parse(cookieStr)
}else{
	layer.msg('您还没有登录', function(){
		location.href = "login.html";
	});
}

//分页查询列表
function getFixedList(params, listSelector, pageSelector){
	//获取列表
	qsExec('sale', '/fixed/list', 'get', params, true, true, function(res){
		$(listSelector).html('');
		var data = res.fixedAssetList;
 		var content = '<li>';
		data.map(function(item, i){
			var o = item.fixedAsset;
			console.log(o)
			var disposes = item.disposes;
			var business = item.business;
			business = business[0]?business[0]:{};
			
			var optCol = '<span class="lzlglbtopspk lzlglbtopspcls"><i>操作</i>';
			switch(pageSelector){
				case '#working':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看</i></em>';
					break;
				case '#toBeAudit':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看</i>';
					optCol += '<i class="show_asset_pass" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">审核通过</i>';
					optCol += '<i class="show_asset_refund" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">驳回</i>';
					optCol += '<i class="show_asset_invalid" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">无效</i></em>';
					break;
				case '#retorted':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看</i>';
					optCol += '<i class="show_asset_canceldeal" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">取消处置</i>';
					optCol += '<i class="show_asset_hasdealed" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">已处置</i></em>';
					break;
				case '#toBePub':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看</i></em>';
					break;
				case '#hasPubed':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看</i>';
					optCol += '<i class="show_asset_restart" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">重启</i></em>';
					break;
				case '#hasOff':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看</i></em>';
					break;
				case '#useless':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看</i></em>';
					break;
			}				
			optCol += '</span>';

			var disposesStr = '';
			disposes.map(function(o){
				disposesStr += dtypeOptionArr[parseInt(o.disposeType)-1]+o.disposeType+ptypeOptionArr[parseInt(o.alg)-1]+"、";
			});

			disposesStr = disposesStr.substring(0, disposesStr.length-1)

			var assetType = '';
			if(o.assetType==0){
				assetType = '房产';
			}else if(o.assetType==1){
				assetType = '设备';
			}else if(o.assetType==2){
				assetType = '土地';
			}

			var disposeStatusStr = o.disposeStatus==0?'待处置':(o.disposeStatus==1?"处置中":"已处置");

			content += '<span class="lzlglbtopspa"><i>'+(i+1)+'</i><em class="curdis"></em></span>';
			content += '<span class="lzlglbtopspb">'+disposeStatusStr+'</span>';
			content += '<span class="lzlglbtopspg">'+(!!o.title?o.title:'--')+'</span>';
			content += '<span class="lzlglbtopsph">'+(!!o.assetRental?o.assetRental:'0.00')+'</span>';
			content += '<span class="lzlglbtopspg">'+disposesStr+'</span>';
			content += '<span class="lzlglbtopsph">'+'审核状态'+'</span>';
			content += '<span class="lzlglbtopspj">'+(!!business.createName?business.createName:'')+'</span>';
			content += optCol;
			content += '<span class="lzlglbtopsph">'+o.createTime+'</span>';
		});
		content += '</li>';

		$(listSelector).html(content);

		$(pageSelector).pagination({
			currentPage: params.page,
	        items: res.query.totalCount,
	        itemsOnPage: res.query.pageCount,
	        cssStyle: 'light-theme'
	    });
	});
}

//业务流转
var busiTrans = function(data){
	layer.msg('确定要执行当前操作吗', {
	  	time: 0 //不自动关闭
	  	,btn: ['确定', '取消']
	  	,yes: function(index){
	    	layer.close(index);
	    		
	    	var businessLevel;
			var curType = 0;
			$(".batabqh>span").each(function(i, item){
				if($(this).hasClass('wzbtcur')){
					curType = i;
				}
			});

			switch(curType){
				case 0:
					businessLevel = 1100;
					break;
				case 1:
					businessLevel = 1110;
					break;
				case 2:
					businessLevel = 1120;
					break;
				case 3:
					businessLevel = 1130;
					break;
				case 4:
					businessLevel = 1140;
					break;
				case 5:
					businessLevel = 1150;
					break;
				case 6:
					businessLevel = 1160;
					break;
			}

			data.businessLevel = businessLevel;

			qsExec('sale', '/business/dispose', 'post', data, true, false, function(res){
				console.log(res)
			});

			var listSelector, pageSelector;
			switch(curType){
				case 0:
					listSelector = '#table_working';
					pageSelector = '#working';
					break;
				case 1:
					listSelector = '#table_toBeAudit';
					pageSelector = '#toBeAudit';
					break;
				case 2:
					listSelector = '#table_retorted';
					pageSelector = '#retorted';
					break;
				case 3:
					listSelector = '#table_toBePub';
					pageSelector = '#toBePub';
					break;
				case 4:
					listSelector = '#table_hasPubed';
					pageSelector = '#hasPubed';
					break;
				case 5:
					listSelector = '#table_hasOff';
					pageSelector = '#hasOff';
					break;
				case 6:
					listSelector = '#table_useless';
					pageSelector = '#useless';
					break;
			}

			getFixedList({page: 1, pageCount:qs_pagination.pageCount, businessStatus:businessLevel}, listSelector, pageSelector);
	  	}
	});
}

$(function(){
	//资产列表转态切换
	$(".batabqh>span").click(function(){
		var banum=$(this).index();
		$(this).addClass("wzbtcur").siblings().removeClass("wzbtcur");
		$(".bazyglnrvv").eq(banum).show().siblings(".bazyglnrvv").hide()
		
	});

	//分页：跳转
	$(document).delegate(".page-link", 'click', function(){
		var curType = 0;
		$(".batabqh>span").each(function(i, item){
			if($(this).hasClass('wzbtcur')){
				curType = i;
			}
		});

		var listSelector, pageSelector;
		var pageHref = $(this).attr('href');
		var arr = pageHref.split('-');
		var params = {page: arr[1], pageCount:qs_pagination.pageCount};

		switch(curType){
			case 0:
				params.businessStatus = 1100;
				listSelector = '#table_working';
				pageSelector = '#working';
				break;
			case 1:
				params.businessStatus = 1110;
				listSelector = '#table_toBeAudit';
				pageSelector = '#toBeAudit';
				break;
			case 2:
				params.businessStatus = 1120;
				listSelector = '#table_retorted';
				pageSelector = '#retorted';
				break;
			case 3:
				params.businessStatus = 1130;
				listSelector = '#table_toBePub';
				pageSelector = '#toBePub';
				break;
			case 4:
				params.businessStatus = 1140;
				listSelector = '#table_hasPubed';
				pageSelector = '#hasPubed';
				break;
			case 5:
				params.businessStatus = 1150;
				listSelector = '#table_hasOff';
				pageSelector = '#hasOff';
				break;
			case 6:
				params.businessStatus = 1160;
				listSelector = '#table_useless';
				pageSelector = '#useless';
				break;
		}

		getFixedList({page: arr[1], pageCount:qs_pagination.pageCount}, listSelector, pageSelector);
	})

	getFixedList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1100}, '#table_working', '#working');
	getFixedList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1110 }, '#table_toBeAudit', '#toBeAudit');
	getFixedList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1120}, '#table_retorted', '#retorted');
	getFixedList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1030}, '#table_toBePub', '#toBePub');
	getFixedList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1040}, '#table_hasPubed', '#hasPubed');
	getFixedList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1050}, '#table_hasOff', '#hasOff');
	getFixedList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1060}, '#table_useless', '#useless');

	//列表操作项
	$(document).delegate('.show_asset_detail', 'click', function(){
		//查看
		console.log($(this).data('val'))
	})

	$(document).delegate('.show_asset_pass', 'click', function(){
		//审核通过
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 114;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_refund', 'click', function(){
		//驳回
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 115;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_invalid', 'click', function(){
		//无效
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 111;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_canceldeal', 'click', function(){
		//取消处置
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 112;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_hasdealed', 'click', function(){
		//已处置
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 117;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_restart', 'click', function(){
		//重启
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 116;

		busiTrans(data);
	})
});