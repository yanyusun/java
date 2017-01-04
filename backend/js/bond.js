var bondType = getQueryString('bondType');

var dtypeOptionArr = ['常规催收', '司法化解', '市场处置', '民间转贷', '拍卖', '转让'];
var ptypeOptionArr = ['元', '%', '折'];
var ptypeOptionStrArr = ['佣金', '百分比', '转让折扣'];
var countArr = ['一', '二', '三', '四', '五', '六'];

var tokenToBeUpload = [];		//待上传的凭证
var mimgToBeUpload = [];		//待上传的主图
//默认选中的处置方式
var dealWay = [{dtype: 1, ptype:1, pvalue:''}];
var curVal = 1;
var recordId = -1;

var cookieStr = Cookies.get('_backend_userToken');
var cookieData;

if(!!cookieStr){
	cookieData = JSON.parse(cookieStr)
}else{
	layer.msg('您还没有登录', function(){
		location.href = "login.html";
	});
}

function removeMainImg(o){
	$(o).parent().remove();
	mimgToBeUpload.map(function(item, i){
		if(item==$(o).data('val')){
			mimgToBeUpload.splice(i, 1);
		}
	});
}

//表单重置
function resetForm(){
	recordId = -1;
	$("#bondForm")[0].reset();
	tokenToBeUpload = [];
	mimgToBeUpload = [];
	dealWay = [{dtype: 1, ptype:1, pvalue:''}];
	curVal = 1;
	reloadDealWay();
}

var reloadDealWay = function (){
	dealWay = [];
	$(".dtype").map(function(i, o){
		if(!dealWay[i]){
			dealWay[i] = {};
		}

		curVal = dealWay[i].dtype = parseInt($(o).find('option:selected').val());
		var ptypeSelects = $(".ptype");
		dealWay[i].ptype = parseInt($(ptypeSelects[i]).find('option:selected').val());
		var pvalueInputs = $(".pvalue");
		dealWay[i].pvalue = parseFloat(!!$(pvalueInputs[i]).val()?$(pvalueInputs[i]).val():0);
	});

	var sdw = '';
	dealWay.map(function(item){
		sdw += '<span>'+dtypeOptionArr[item.dtype-1]+'</span>';
	});

	$("#selectedDealWay").html(sdw);
}

//自动填充省份数据
function autoProvinc(res){
	$("#province").parent().siblings(".sewvbm").html('');
	$("#province").parent().siblings(".sewvbm").append("<li data-type='province' data-val=''>请选择</li>");

	$("#bond_pca").find("select[name='userBond.bondProvince']").html('');
	$("#bond_pca").find("select[name='userBond.bondProvince']").append("<option value=''>请选择</option>");

	$("#debt_pca").find("select[name='userBond.debtProvince']").html('');
	$("#debt_pca").find("select[name='userBond.debtProvince']").append("<option value=''>请选择</option>");

	res.map(function(o){
		$("#province").parent().siblings(".sewvbm").append("<li data-type='province' data-val='"+o.value+"'>"+o.label+"</li>");
		$("#bond_pca").find("select[name='userBond.bondProvince']").append("<option value='"+o.value+"'>"+o.label+"</option>");
		$("#debt_pca").find("select[name='userBond.debtProvince']").append("<option value='"+o.value+"'>"+o.label+"</option>");
	});
}

//自动填充市数据
function autoCity(res, id){
	$("#city").parent().siblings(".sewvbm").html('');
	$("#city").parent().siblings(".sewvbm").append("<li data-type='city' data-val=''>请选择</li>");
	
	if(res){
		res.map(function(o){
			$("#city").parent().siblings(".sewvbm").append("<li data-type='city' data-val='"+o.value+"'>"+o.label+"</li>");
		});

		if(id){
			$("#city").parent().siblings('.sewvbm').find('li').each(function(i, item){
				if($(item).data('val')==id){
					$(item).addClass('pca_cur');
					$(item).siblings().removeClass('pca_cur');

					var selva=$(item).text();
					$("#city").text(selva);
				}
			});
		}
	}
}

function autoBondCity(res, id){
	$("select[name='userBond.bondCity']").html('');
	$("select[name='userBond.bondCity']").append("<option value=''>请选择</option>");
	if(res){
		res.map(function(o){
			$("select[name='userBond.bondCity']").append("<option value='"+o.value+"'>"+o.label+"</option>");
		});

		if(id){
			$("#bondCity").val(id);
		}
	}
}

function autoDebtCity(res, id){
	$("select[name='userBond.debtCity']").html('');
	$("select[name='userBond.debtCity']").append("<option value=''>请选择</option>");

	if(res){
		res.map(function(o){
			$("select[name='userBond.debtCity']").append("<option value='"+o.value+"'>"+o.label+"</option>");
		});

		if(id){
			$("#debtCity").val(id);
		}
	}
}

//根据已有的数据渲染处置方式
var renderDealWay = function(){
	$(".lbagfbxqnre").html('<div class="lbagqnadd">+添加一类</div>');

	var sdw = '';
	var content = '';
	dealWay.map(function(item, i){
		var dtypeSel = '<select class="dtype">';
		dtypeOptionArr.map(function(o, i){
			var v = i+1;
			var selected = '';
			if(item.dtype==v){
				selected = 'selected';
			}
			dtypeSel += '<option value='+v+' '+selected+'>'+o+'</option>';
		});
		dtypeSel += '</select>';

		var ptypeSel = '<select class="ptype">';
		ptypeOptionStrArr.map(function(o, i){
			var v = i+1;
			var selected = '';
			if(item.ptype==v){
				selected = 'selected';
			}
			ptypeSel += '<option value='+v+' '+selected+'>'+o+'</option>';
		});
		ptypeSel += '</select>';

		content += '<div class="lbagfbxqnrev">';
		content += '<em>【'+countArr[i]+'】：</em>';
		content += dtypeSel;
		content += ptypeSel;
		content += '<p><input value="'+item.pvalue+'" class="pvalue" type="text">';
		content += '<em>'+ptypeOptionArr[item.ptype-1]+'</em></p>';
		content += '<span><em></em><i>注释</i></span>';
		content += '<a class="delDealWay">删除</a>';
		content += '</div>';
		sdw += '<span>'+dtypeOptionArr[item.dtype-1]+'</span>';
	});

	$(".lbagfbxqnre").prepend(content);
	$("#selectedDealWay").html(sdw);
}

var editAssets = function(id, o){
	//查询债权详情
	recordId = id;

	qsExec('sale', '/bond/noVerify/getDetail', 'get', {bondId: id}, false, true, function(res){
		var record = res.userBond;		//资产包对象
		var assetFile = res.assetFile;		//附件
		var disposes = res.disposes;		//处置方式
		var labels = res.labels;			//标签
		console.log(record)

		$("#title").val(record.title);
		$("#source").val(record.source);
		$("#entrust_start").val(record.startTime);
		$("#entrust_end").val(record.endTime);
		$("#totalMoney").val(record.totalMoney);

		if(record.province){
			//$("#province").parent().siblings('.sewvbm').find('li.pca_cur').data('val')
			$("#province").parent().siblings('.sewvbm').find('li').each(function(i, item){
				if($(item).data('val')==record.province){
					$(item).addClass('pca_cur');
					$(item).siblings().removeClass('pca_cur');

					var selva=$(item).text();
					$("#province").text(selva);
				}
			});

			qsExec('res', '/area/list', 'post', {aid: record.province}, false, true, function(res){
				if(record.city)
					autoCity(res, record.city);
				else
					autoCity(res);
			});
		}

		$("#address").val(record.address);

		$("#borrowTime").val(record.borrowTime);
		$("#refundTime").val(record.refundTime);
		$("#noAgreement").val(record.noAgreement);
		$("#totalInterestMoney").val(record.totalInterestMoney);
		$("#loanMoney").val(record.loanMoney);

		$("#assessTotalPrice").val(record.assessTotalPrice);
		$("#loanType").val(record.loanType);
		$("#loanWay").val(record.loanWay);
		$("#loanName").val(record.loanName);
		$("#collStage").val(record.assesscollStageTotalPrice);
		$("#assureTypeOne").val(record.assureTypeOne);
		$("#assureWayOne").val(record.assureWayOne);

		$("#assureTypeTwo").val(record.assureTypeTwo);
		$("#assureWayTwo").val(record.assureWayTwo);
		$("#assureTypeThree").val(record.assureTypeThree);
		$("#assureWayThree").val(record.assureWayThree);
		$("#bondsmanContact").val(record.bondsmanContact);

		$("#bondsmanEconomic").val(record.bondsmanEconomic);
		$("#isWorth").val(record.isWorth);
		$("#canContact").val(record.canContact);
		$("#canPay").val(record.canPay);
		$("#isLawsuit").val(record.isLawsuit);
		$("#isDecision").val(record.isDecision);

		$("#realUrgeNum").val(record.realUrgeNum);
		$("#phoneUrgeNum").val(record.phoneUrgeNum);
		$("#entrustUrgeNum").val(record.entrustUrgeNum);
		$("#describes").val(record.describes);
		$("#bondName").val(record.bondName);

		$("#bondProvince").val(record.bondProvince);

		if(record.bondProvince){
			qsExec('res', '/area/list', 'post', {aid: record.bondProvince}, false, true, function(res){
				if(record.bondCity)
					autoBondCity(res, record.bondCity);
				else
					autoBondCity(res);
			});		}

		$("#bondAddress").val(record.bondAddress);
		$("#bondIdcode").val(record.bondIdcode);
		$("#bondPhone").val(record.bondPhone);


		$("#bondContactsName").val(record.bondContactsName);
		$("#bondContactsPhone").val(record.bondContactsPhone);
		$("#bondContactsIdcode").val(record.bondContactsIdcode);
		$("#debtName").val(record.debtName);

		$("#debtAddress").val(record.debtAddress);
		$("#debtProvince").val(record.debtProvince);

		if(record.debtProvince){
			qsExec('res', '/area/list', 'post', {aid: record.debtProvince}, false, true, function(res){
				if(record.debtCity)
					autoDebtCity(res, record.debtCity);
				else
					autoDebtCity(res);
			});
			
		}

		$("#debtPhone").val(record.debtPhone);
		$("#debtType").val(record.debtType);
		$("#debtIsAddress").val(record.debtIsAddress);

		$("#debtPhone").val(record.debtPhone);
		$("#debtIsOperate").val(record.debtIsOperate);
		$("#debtIdcode").val(record.debtIdcode);
		$("#debtSituationAsset").val(record.debtSituationAsset);

		$("#debtSituationOperate").val(record.debtSituationOperate);
		$("#debtSituationIndustry").val(record.debtSituationIndustry);
		$("#debtSituationMarriage").val(record.debtSituationMarriage);
		$("#debtSituationEducation").val(record.debtSituationEducation);

		$("#debtSituationBody").val(record.debtSituationBody);
		$("#debtSituationMember").val(record.debtSituationMember);
		$("#debtSituationSocial").val(record.debtSituationSocial);

		//评分部分
		if(record.source){
			$('#qs_rate').raty({
				score: record.source/2,
				path: 'common/raty-2.5.2/lib/img',
				scoreName: 'fixedAsset.source',
				click: function(score){
					var realScore = score * 2;
					$("#score_str").text(realScore+"分");
				}
			});

			$("#score_str").text(record.source+"分");
		}

		//标签
		labels.map(function(){});

		$("#bondType").find('em').each(function(){
			if($(this).data('val')==record.bondType){
				$(this).addClass('radiuscurb');
			}else{
				$(this).removeClass('radiuscurb');
			}
		});

		dealWay = [];
		//处置方式
		disposes.map(function(item){
			dealWay.push({dtype:item.disposeType, ptype:item.alg, pvalue:item.value});
		});

		renderDealWay();
	});

	$(".ladtcv").show();
}

//分页查询列表
function getBondList(params, listSelector, pageSelector){
	params.bondType = bondType;
	//获取列表
	qsExec('sale', '/bond/list', 'get', params, true, true, function(res){
		$(listSelector).html('');
		var data = res.userBondList;
 		var content = '<li>';
		data.map(function(item, i){
			var business = item.business;
			business = business[0]?business[0]:{};
			var o = item.userBond;
			var disposes = item.disposes;
			
			var optCol = '<span class="lzlglbtopspk lzlglbtopspcls"><i>操作</i>';
			switch(pageSelector){
				case '#toBeAudit':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看详情</i>';
					optCol += '<i class="show_asset_edit" data-val="'+o.id+'">编辑</i>';
					optCol += '<i class="show_asset_pass" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">审核通过</i>';
					optCol += '<i class="show_asset_refund" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">驳回</i>';
					optCol += '<i class="show_asset_invalid" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">无效</i>';
					optCol += '<i class="show_asset_remove" data-val="'+o.id+'">删除</i></em>';
					break;
				case '#retorted':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看详情</i>';
					optCol += '<i class="show_asset_edit" data-val="'+o.id+'">编辑</i>';
					optCol += '<i class="show_asset_pass" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">审核通过</i>';
					optCol += '<i class="show_asset_remove" data-val="'+o.id+'">删除</i></em>';
					break;
				case '#toBePub':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看详情</i>';
					optCol += '<i class="show_asset_edit" data-val="'+o.id+'">编辑</i>';
					optCol += '<i class="show_asset_pub" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">发布</i>';
					optCol += '<i class="show_asset_invalid" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">无效</i>';
					optCol += '<i class="show_asset_remove" data-val="'+o.id+'">删除</i></em>';
					break;
				case '#hasPubed':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看详情</i>';
					optCol += '<i class="show_asset_edit" data-val="'+o.id+'">编辑</i>';
					optCol += '<i class="show_asset_down" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">下架</i>';
					optCol += '<i class="show_asset_remove" data-val="'+o.id+'">删除</i></em>';
					break;
				case '#hasOff':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看详情</i>';
					optCol += '<i class="show_asset_edit" data-val="'+o.id+'">编辑</i>';
					optCol += '<i class="show_asset_up" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">上架</i>';
					optCol += '<i class="show_asset_invalid" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">无效</i>';
					optCol += '<i class="show_asset_remove" data-val="'+o.id+'">删除</i></em>';
					break;
				case '#useless':
					optCol += '<em><i class="show_asset_detail" data-val="'+o.id+'">查看详情</i>';
					optCol += '<i class="show_asset_edit" data-val="'+o.id+'">编辑</i>';
					optCol += '<i class="show_asset_repub" data-bman="'+business.createId+'" data-bid="'+business.id+'" data-val="'+o.id+'">重新发布</i>';
					optCol += '<i class="show_asset_remove" data-val="'+o.id+'">删除</i></em>';
					break;
			}				
			optCol += '</span>';

			var disposesStr = '';
			disposes.map(function(o){
				disposesStr += dtypeOptionArr[parseInt(o.disposeType)-1]+o.disposeType+ptypeOptionArr[parseInt(o.alg)-1]+"、";
			});

			disposesStr = disposesStr.substring(0, disposesStr.length-1)

			var assetType = '';
			if(o.bondType==11){
				assetType = '个人债权';
			}else if(o.bondType==12){
				assetType = '企业债权';
			}else if(o.bondType==13){
				assetType = '逾期贷款';
			}

			var disposeStatusStr = o.disposeStatus==0?'待处置':(o.disposeStatus==1?"处置中":"已处置");

			content += '<span class="lzlglbtopspa"><i>'+(i+1)+'</i><em class="curdis"></em></span>';
			content += '<span class="lzlglbtopspb">'+(!!o.no?o.no:'--')+'</span>';
			content += '<span class="lzlglbtopspd">'+(!!o.title?o.title:'--')+'</span>';
			content += '<span class="lzlglbtopsph">'+(!!o.assetRental?o.assetRental:'资产包总额')+'</span>';
			content += '<span class="lzlglbtopspd">'+('售价')+'</span>';
			content += '<span class="lzlglbtopspd">'+assetType+'</span>';
			content += '<span class="lzlglbtopspg">'+disposesStr+'</span>';
			content += '<span class="lzlglbtopsph">'+disposeStatusStr+'</span>';
			content += '<span class="lzlglbtopspj">'+o.operUserName+'</span>';
			content += optCol;
			content += '<span class="lzlglbtopspd">'+((!!o.isHomePage?"是":"否"))+'</span>';
			content += '<span class="lzlglbtopsph">'+((!!o.mark?o.mark:'--'))+'</span>';
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
  		,btn: ['是的', '取消']
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
					businessLevel = 1020;
					break;
				case 1:
					businessLevel = 1030;
					break;
				case 2:
					businessLevel = 1040;
					break;
				case 3:
					businessLevel = 1050;
					break;
				case 4:
					businessLevel = 1060;
					break;
				case 5:
					businessLevel = 1070;
					break;
			}

			data.businessLevel = businessLevel;

			qsExec('sale', '/business/release', 'post', data, true, false, function(res){
				console.log(res)
			});

			var listSelector, pageSelector;
			switch(curType){
				case 0:
					listSelector = '#table_toBeAudit';
					pageSelector = '#toBeAudit';
					break;
				case 1:
					listSelector = '#table_retorted';
					pageSelector = '#retorted';
					break;
				case 2:
					listSelector = '#table_toBePub';
					pageSelector = '#toBePub';
					break;
				case 3:
					listSelector = '#table_hasPubed';
					pageSelector = '#hasPubed';
					break;
				case 4:
					listSelector = '#table_hasOff';
					pageSelector = '#hasOff';
					break;
				case 5:
					listSelector = '#table_useless';
					pageSelector = '#useless';
					break;
			}

			getBondList({page: 1, pageCount:qs_pagination.pageCount, businessStatus: businessLevel}, listSelector, pageSelector);
	  	}
	});
}

$(function(){
	if(bondType==11){
		$("#bondIdStr").text('个债ID');
	}else if(bondType==12){
		$("#bondIdStr").text('企债ID');
	}else if(bondType==13){
		$("#bondIdStr").text('贷款ID');
	}

	$("#borrowTime").on('click', function(){
		laydate({istime: true, format: 'YYYY-MM-DD'})
	});

	$("#refundTime").on('click', function(){
		laydate({istime: true, format: 'YYYY-MM-DD'})
	});

	/** 星级评分部分 **/
	$('#qs_rate').raty({
		path: 'common/raty-2.5.2/lib/img',
		scoreName: 'userBond.grade',
		click: function(score){
			var realScore = score * 2;
			$("#score_str").text(realScore+"分");
		}
	});
	/** 评分部分结束 **/


	//资产列表转态切换
	$(".batabqh>span").click(function(){
		var banum=$(this).index();
		$(this).addClass("wzbtcur").siblings().removeClass("wzbtcur");
		$(".bazyglnrvv").eq(banum).show().siblings(".bazyglnrvv").hide()
		
	});

	var start = {
	  elem: '#entrust_start',
	  format: 'YYYY-MM-DD',
	  max: '2099-06-16', //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	     end.min = datas; //开始日选好后，重置结束日的最小日期
	     end.start = datas //将结束日的初始值设定为开始日
	  }
	};

	var end = {
	  elem: '#entrust_end',
	  format: 'YYYY-MM-DD',
	  max: '2099-06-16',
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	    start.max = datas; //结束日选好后，重置开始日的最大日期
	  }
	};

	laydate(start);
	laydate(end);

	/**凭证上传部分**/
	$('#filesUpload').fileupload({
		formData: {type: 6},
		headers: cookieData,
        dataType: 'json',
        /**add: function (e, data) {
        	console.log(e, data)
            data.context = $('<button/>').text('Upload').appendTo(document.body).click(function () {
				data.context = $('<p/>').text('Uploading...').replaceAll($(this));
				data.submit();
			});
        },**/
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);

	        if(progress<100){
	        	$("#progress .bar").css('display', 'inline-block');
	        	$("#extend-bar").css('display', 'inline-block');
	        }else{
	        	$("#progress .bar").css('display', 'none');
	        	$("#extend-bar").css('display', 'none');
	        }

	        $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );
	    },

        done: function (e, data) {
            var res = data.result;
            if(typeof res !='object'){
        		res = eval('('+res+')');
        	}

            if(res.code==2000){
            	var content = '<span style="margin-left:20px;">'+data.files[0].name+'</span>';
	            $('.attaches').append(content);

	            tokenToBeUpload.push(res.data);
            }else{
            	layer.alert(res.msg, {icon: 5});
            }
            
        }
    });

	/**主图上传部分**/
    $('#filesUploadMain').fileupload({
		formData: {type: 6},
		headers: cookieData,
        dataType: 'json',
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);

	        if(progress<100){
	        	$("#progress .bar").css('display', 'inline-block');
	        	$("#extend-bar").css('display', 'inline-block');
	        }else{
	        	$("#progress .bar").css('display', 'none');
	        	$("#extend-bar").css('display', 'none');
	        }

	        $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );
	    },
        done: function (e, data) {
        	var res = data.result;
        	if(typeof res !='object'){
        		res = eval('('+res+')');
        	}

            if(res.code==2000){
            	if (data.files && data.files[0]) {
			        var reader = new FileReader();
			        reader.onload = function(e) {
			            var content = '<div style="margin-right:20px;display:inline-block;position:relative"><img src="'+e.target.result+'" style="width:66px; height:53px;">';
			            content += '<a data-val="'+res.data+'" onclick="removeMainImg(this)" javascript:":;"><img style="position:absolute;top:0;right:0;width:23px" src="./img/u540.png" /></a></div>';
		            	$('.image-upload').prepend(content);
			        }
			        reader.readAsDataURL(data.files[0]);
		            
			    }

	            mimgToBeUpload.push(res.data);
            }else{
            	layer.alert(res.msg, {icon: 5});
            }
        }
    });

	//加载省份
    qsExec('res', '/area/list', 'post', {}, false, true, autoProvinc);

	//表单提交
    $("#submitBtn").bind('click', function(){
	    return false;
    	
    });

    //省份变化时触发
    $(document).delegate('.sewvbm>li', 'click', function(){
		var type = $(this).data('type');
		var aid = $(this).data('val');
		if(type=='province'){
			if(!!aid){
				//加载市
				qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoCity);
			}else{
				autoCity();
			}
		}
	});

	$(document).delegate("select[name='userBond.bondProvince']", 'change', function(){
		var aid = $(this).val();
		if(!!aid){
			//加载市
			qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoBondCity);
		}else{
			autoBondCity();
		}
	});

	$(document).delegate("select[name='userBond.debtProvince']", 'change', function(){
		var aid = $(this).val();
		if(!!aid){
			//加载市
			qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoDebtCity);
		}else{
			autoDebtCity();
		}
	});


    //删除一个处置方式
    $(document).delegate('.delDealWay', 'click', function(){
    	$(this).parent().remove();
    	reloadDealWay();
    });

    //处置方式
    $(document).delegate(".dtype", 'focus', function(){
    	curVal = parseInt($(this).find('option:selected').val());

		var dtypeSel = '';
		dtypeOptionArr.map(function(o, i){
			var index = i+1;
			var disabled = '';
			//已选的、并且不是当前选中的处置方式不可选
			for(var j=0;j<dealWay.length;j++){
				if(dealWay[j].dtype==index && index!==curVal){
					disabled = 'disabled';
					break;
				}
			}

			dtypeSel += '<option value='+index+' '+disabled+'>'+o+'</option>';
		});

		var content = dtypeSel;

		$(this).html(content);
    }).delegate(".dtype", 'change', function(){
    	reloadDealWay();
    	$(this).blur();
    }).delegate(".dtype", 'blur', function(){
    	$(this).val(curVal);
    })

    $(document).delegate(".ptype", 'change', function(){
    	switch($(this).val()){
    		case "1":
    			$(this).siblings('p').find('em').text('元');
    			break;
    		case "2":
    			$(this).siblings('p').find('em').text('%');
    			break;
    		case "3":
    			$(this).siblings('p').find('em').text('折');
    			break;
    	}
		reloadDealWay();
    });

    $(document).delegate(".pvalue", 'keyup', function(){
		reloadDealWay();
    });

    //添加一个处置方式
	$(document).delegate('.lbagqnadd', 'click', function(){
		var dtypeSel = '<select class="dtype">';
		var hasSet = false;
		dtypeOptionArr.map(function(o, i){
			var index = i+1;
			var disabled = '';
			//已选的处置方式不可选
			for(var j=0;j<dealWay.length;j++){
				if(dealWay[j].dtype==index){
					disabled = 'disabled';
					break;
				}
			}

			//将可用的第一项作为默认已选	
			if(disabled=='' && !hasSet){
				dealWay.push({dtype: index, ptype:1, pvalue:''});
				hasSet = true;
			}

			dtypeSel += '<option value='+index+' '+disabled+'>'+o+'</option>';
		});

		dtypeSel += '</select>';

		var content = '<div class="lbagfbxqnrev">';
		content += '<em>【一】：</em>';
		content += dtypeSel;
		content += '<select class="ptype"><option value=1>佣金</option><option value=2>百分比</option>';
		content += '<option value=3>转让折扣</option></select>';
		content += '<p><input class="pvalue" type="text"><em>元</em></p>';
		content += '<span><em></em><i>注释</i></span>';
		content += '<a class="delDealWay">删除</a>';
		content += '</div>';

		var sdw = '';
		dealWay.map(function(item){
			sdw += '<span>'+dtypeOptionArr[item.dtype-1]+'</span>';
		});

		$("#selectedDealWay").html(sdw);

		$(this).before(content);
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
				params.businessStatus = 1020;
				listSelector = '#table_toBeAudit';
				pageSelector = '#toBeAudit';
				break;
			case 1:
				params.businessStatus = 1030;
				listSelector = '#table_retorted';
				pageSelector = '#retorted';
				break;
			case 2:
				params.businessStatus = 1040;
				listSelector = '#table_toBePub';
				pageSelector = '#toBePub';
				break;
			case 3:
				params.businessStatus = 1050;
				listSelector = '#table_hasPubed';
				pageSelector = '#hasPubed';
				break;
			case 4:
				params.businessStatus = 1060;
				listSelector = '#table_hasOff';
				pageSelector = '#hasOff';
				break;
			case 5:
				params.businessStatus = 1070;
				listSelector = '#table_useless';
				pageSelector = '#useless';
				break;
		}

		getBondList({page: arr[1], pageCount:qs_pagination.pageCount}, listSelector, pageSelector);
	})

	getBondList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1020 }, '#table_toBeAudit', '#toBeAudit');
	getBondList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1030}, '#table_retorted', '#retorted');
	getBondList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1040}, '#table_toBePub', '#toBePub');
	getBondList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1050}, '#table_hasPubed', '#hasPubed');
	getBondList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1060}, '#table_hasOff', '#hasOff');
	getBondList({page:1, pageCount:qs_pagination.pageCount, businessStatus:1070}, '#table_useless', '#useless');

	/**标签部分**/
	function onAddTag(tag) {
        alert("Added a tag: " + tag);
    }

    function onRemoveTag(tag) {
        alert("Removed a tag: " + tag);
    }

    function onChangeTag(input, tag) {
        alert("Changed a tag: " + tag);
    }

    $('#self_tags').tagsInput({ width: 'auto' });

    //自选标签
    $("#default_tags_option").on('change', function(){
    	
    });
    /**标签部分结束**/
    $.validator.setDefaults({
		errorClass: "asset-invalid",
	});

	//重定义身份证校验
	$.validator.methods.creditcard = function( value, element ) {
		varlue = value.toUpperCase();
		return this.optional( element ) || (/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(value));
	}

    $('#bondForm').validate({
		rules: {
			'userBond.title': 'required',
			'userBond.totalMoney': {
				required: true,
				number: true,
			},
			'userBond.totalInterestMoney': {
				number: true,
			},
			'userBond.loanMoney': {
				number: true,
			},
			'userBond.assessTotalPrice': {
				number: true,
			},
			'userBond.collStage': {
				digits: true,
			},
			'userBond.realUrgeNum': {
				digits: true,
			},
			'userBond.phoneUrgeNum': {
				digits: true,
			},
			'userBond.entrustUrgeNum': {
				digits: true,
			},
			'userBond.bondName': 'required',
			'userBond.bondContactsName': 'required',
			'userBond.bondContactsPhone': 'required',
			'userBond.bondContactsIdcode': {
				required: true,
				creditcard: true,
			},
			'userBond.debtName': 'required',
		},
		messages:{
			'userBond.title': '请填写标题',
			'userBond.totalMoney': {
				required: '请填写资产总额',
				number: '数据格式有误',
			},
			'userBond.totalInterestMoney': {
				number: '数据格式有误',
			},
			'userBond.loanMoney': {
				number: '数据格式有误',
			},
			'userBond.assessTotalPrice': {
				number: '数据格式有误',
			},
			'userBond.collStage': {
				digits: '数据格式有误',
			},
			'userBond.realUrgeNum': {
				digits: '数据格式有误',
			},
			'userBond.phoneUrgeNum': {
				digits: '数据格式有误',
			},
			'userBond.entrustUrgeNum': {
				digits: '数据格式有误',
			},
			'userBond.bondName': '请输入债权方姓名',
			'userBond.bondContactsName': '债权方联系人姓名不能为空',
			'userBond.bondContactsPhone': '债权方联系人电话不能为空',
			'userBond.bondContactsIdcode': {
				required: '债权方联系人身份证号不能为空',
				creditcard: '请填写正确的身份证号码',
			},
			'userBond.debtName': '请填写债务方姓名',
		},
		submitHandler: function(form){
			/**if(tokenToBeUpload.length<1){
				layer.msg('请上传凭证');
				return false;
			}**/
			//资产类型
	    	var assetType = $("#bondType").find('.radiuscurb').data('val');
	    	//资产所在地-省份
	    	var province = $("#province").parent().siblings('.sewvbm').find('li.pca_cur').data('val')
	    	var city = $("#city").parent().siblings('.sewvbm').find('li.pca_cur').data('val');
	    	
	    	if(!city){
	    		city = '';
	    	}
	    	
	    	//债务方类型
	    	var debtType = $("#debtType").find('.radiuscurb').data('val');
	    	//债务方现住地址
	    	var debtIsAddress = $("#debtIsAddress").find('.radiuscurb').data('val');
	    	//债务方实际经营地址
	    	var debtIsOperate = $("#debtIsOperate").find('.radiuscurb').data('val');
	    	//债务方资产状况：
			var debtSituationAssetArr = [];
			var debtSituationAsset = '';
	    	$("#debtSituationAsset").find('input:checked').each(function(i, o){
	    		debtSituationAssetArr.push($(o).val());
	    	});
	    	debtSituationAsset = debtSituationAssetArr.join(',');
	    	//债务方实际经营状况
	    	var debtSituationOperate = $("#debtSituationOperate").find('.radiuscurb').data('val');
	    	//债务方所处行业
	    	var debtSituationIndustry = $("#debtSituationIndustry").find('.radiuscurb').data('val');
	    	//债务方婚姻状况
	    	var debtSituationMarriage = $("#debtSituationMarriage").find('.radiuscurb').data('val');
	    	//学历
	    	var debtSituationEducation = $("#debtSituationEducation").find('.radiuscurb').data('val');
	    	//身体状况
	    	var debtSituationBody = $("#debtSituationBody").find('.radiuscurb').data('val');
	    	//家庭成员
	    	var debtSituationMember = $("#debtSituationMember").find('.radiuscurb').data('val');
	    	//社会关系
	    	var debtSituationSocial = $("#debtSituationSocial").find('.radiuscurb').data('val');

	    	//处置方式
	    	var dispose = JSON.stringify(disposesArr);
	    	var formData = $("#bondForm").serialize();
	    	formData += '&userBond.bondType='+assetType+'&userBond.province='+province+'&userBond.city='+city
	    		+'&userBond.debtType='+debtType+'&userBond.debtIsAddress='+debtIsAddress
	    		+'&userBond.debtIsOperate='+debtIsOperate+'&userBond.debtSituationAsset='+debtSituationAsset
	    		+'&userBond.debtSituationOperate='+debtSituationOperate+'&userBond.debtSituationIndustry='+debtSituationIndustry
	    		+'&userBond.debtSituationMarriage='+debtSituationMarriage+'&userBond.debtSituationEducation='+debtSituationEducation
	    		+'&userBond.debtSituationBody='+debtSituationBody+'&userBond.debtSituationMember='+debtSituationMember
	    		+'&userBond.debtSituationSocial='+debtSituationSocial;

	    	var disposesArr = [];
	    	dealWay.map(function(o, i){
	    		formData += '&disposes['+i+'].disposeType='+ o.dtype;
	    		formData += '&disposes['+i+'].alg='+ o.ptype;
	    		formData += '&disposes['+i+'].value='+ o.pvalue;
	    	})
	    	
	    	var tags = $("#self_tags").val();
	    	var tagsArr = tags.split(',');
	    	var assetFiles = [];
	    	var labels = [];

	    	var fileCount = 0;
	    	mimgToBeUpload.map(function(o){
	    		formData += '&assetFiles['+fileCount+'].type='+ 0;
	    		formData += '&assetFiles['+fileCount+'].filename='+ o;
	    		fileCount++;
	    	})

	    	tokenToBeUpload.map(function(o){
	    		formData += '&assetFiles['+fileCount+'].type='+ 2;
	    		formData += '&assetFiles['+fileCount+'].filename='+ o;
	    		fileCount++;
	    	})

	    	tagsArr.map(function(o, i){
	    		formData += '&labels['+i+'].name='+ o;
	    	})

	    	var actionUrl = '/bond/addBond';

	    	if(recordId!==-1){
	    		formData += '&userBond.id='+ recordId;
	    		actionUrl = '/bond/updateBond';
	    	}

	    	$.ajax({
	    		crossDomain: true,
	    		type: 'post',
	    		url: qs_base_config.sale.uri+actionUrl,
		        data : formData,
		        async: true,
		        headers: cookieData,
		        success: function(res){
		        	if(res.code==2000){
		        		layer.alert('数据提交成功！', {icon: 6});
		        		resetForm();
		        		$(".ladtcv").hide();
		        	}else{
		        		layer.alert(res.msg, {icon: 5});
		        	}
		        },
		        error: function(e){
		        	console.log(e)
		        }
	    	});
		}
	});

	//列表操作项
	$(document).delegate('.show_asset_detail', 'click', function(){
		//查看详情
		console.log($(this).data('val'))
	})

	$(document).delegate('.show_asset_edit', 'click', function(){
		//编辑
		editAssets($(this).data('val'));
	})

	$(document).delegate('.show_asset_pass', 'click', function(){
		//审核通过
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 104;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_refund', 'click', function(){
		//驳回
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 105;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_invalid', 'click', function(){
		//无效
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 103;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_remove', 'click', function(){
		//删除
		console.log($(this).data('val'))
	})

	$(document).delegate('.show_asset_up', 'click', function(){
		//上架
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 107;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_down', 'click', function(){
		//下架
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 106;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_pub', 'click', function(){
		//发布
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 101;

		busiTrans(data);
	})

	$(document).delegate('.show_asset_repub', 'click', function(){
		//重新发布
		var data = {};
		data.businessId = $(this).data('bid');
		data.reqUserId = $(this).data('bman');
		data.operType = 102;

		busiTrans(data);
	})
});