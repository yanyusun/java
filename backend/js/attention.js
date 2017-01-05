var dtypeOptionArr = ['常规催收', '司法化解', '市场处置', '民间转贷', '拍卖', '转让'];
var ptypeOptionArr = ['元', '%', '折'];
var tokenToBeUpload = [];		//待上传的凭证
var mimgToBeUpload = [];		//待上传的主图
//默认选中的处置方式
var dealWay = [{dtype: 1, ptype:1, pvalue:''}];
var curVal = 1;

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
	$("#assetForm")[0].reset();
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

$(function(){
	/** 星级评分部分 **/
	$('#qs_rate').raty({
		path: 'common/raty-2.5.2/lib/img',
		scoreName: 'assetPackage.grade',
		click: function(score){
			var realScore = score * 2;
			$("#score_str").text(realScore+"分");
		}
	});

	/** 评分部分结束 **/

	//分页查询列表
	function getAssetList(params, listSelector, pageSelector){
		//获取列表
		qsExec('sale', '/asset/list', 'get', params, true, true, function(res){
			$(listSelector).html('');
			var data = res.assetPackageList;
	 		var content = '<li>';
			data.map(function(item, i){
				var o = item.assetPackage;
				var disposes = item.disposes;
				
				var optCol = '<span class="lzlglbtopspk lzlglbtopspcls"><i>操作</i>';
				optCol += '<em><i>查看详情</i><i>编辑</i><i>审核通过</i><i>驳回</i><i>无效</i><i>删除</i></em></span>';

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
				content += '<span class="lzlglbtopspb">'+(!!o.assetNo?o.assetNo:'--')+'</span>';
				content += '<span class="lzlglbtopspd">'+(!!o.title?o.title:'--')+'</span>';
				content += '<span class="lzlglbtopsph">'+(!!o.assetRental?o.assetRental:'资产包总额')+'</span>';
				content += '<span class="lzlglbtopspd">'+('售价')+'</span>';
				content += '<span class="lzlglbtopspd">'+assetType+'</span>';
				content += '<span class="lzlglbtopspg">'+disposesStr+'</span>';
				content += '<span class="lzlglbtopsph">'+disposeStatusStr+'</span>';
				content += '<span class="lzlglbtopspj">'+(!!o.operUser?o.operUser:'创建者')+'</span>';
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

	//资产列表转态切换
	$(".batabqh>span").click(function(){
		var banum=$(this).index();
		$(this).addClass("wzbtcur").siblings().removeClass("wzbtcur");
		$(".bazyglnrvv").eq(banum).show().siblings(".bazyglnrvv").hide()
		
	});

	var start = {
	  elem: '#entrust_start',
	  format: 'YYYY/MM/DD',
	  min: laydate.now(), //设定最小日期为当前日期
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
	  format: 'YYYY/MM/DD',
	  min: laydate.now(),
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
    qsExec('res', '/area/list', 'post', {}, false, true, autoRightProvinc);

	//表单提交
    $("#submitBtn").bind('click', function(){
	    return false;
    	
    });

    //自动填充省份数据
    function autoProvinc(res){
    	$("#province").parent().siblings(".sewvbm").html('');
    	$("#province").parent().siblings(".sewvbm").append("<li data-type='province' data-val=''>请选择</li>");

    	res.map(function(o){
			$("#province").parent().siblings(".sewvbm").append("<li data-type='province' data-val='"+o.value+"'>"+o.label+"</li>");
		});
    }

    function autoRightProvinc(res){
    	$("#right_pca").find("select[name='assetPackage.entrustProvince']").html('');
    	$("#right_pca").find("select[name='assetPackage.entrustProvince']").append("<option value=''>请选择</option>");

    	res.map(function(o){
    		$("#right_pca").find("select[name='assetPackage.entrustProvince']").append("<option value='"+o.value+"'>"+o.label+"</option>");
		});
    }

    //自动填充市数据
    function autoCity(res){
    	$("#city").parent().siblings(".sewvbm").html('');
    	$("#city").parent().siblings(".sewvbm").append("<li data-type='city' data-val=''>请选择</li>");
    	if(res){
    		res.map(function(o){
				$("#city").parent().siblings(".sewvbm").append("<li data-type='city' data-val='"+o.value+"'>"+o.label+"</li>");
			});
    	}
    }

    function autoRightCity(res){
    	$("select[name='assetPackage.entrustCity']").html('');
    	$("select[name='assetPackage.entrustCity']").append("<option value=''>请选择</option>");
    	if(res){
    		res.map(function(o){
				$("select[name='assetPackage.entrustCity']").append("<option value='"+o.value+"'>"+o.label+"</option>");
			});
    	}
    }

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

	$(document).delegate("select[name='assetPackage.entrustProvince']", 'change', function(){
		var aid = $(this).val();
		if(!!aid){
			//加载市
			qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoRightCity);
		}else{
			autoRightCity();
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
	$(".lbagqnadd").bind('click', function(){
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
				params.checkStatus = 0;
				listSelector = '#table_toBeAudit';
				pageSelector = '#toBeAudit';
				break;
			case 1:
				params.checkStatus = 1;
				listSelector = '#table_retorted';
				pageSelector = '#retorted';
				break;
			case 2:
				params.checkStatus = 2;
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
				params.enable = 1;
				listSelector = '#table_useless';
				pageSelector = '#useless';
				break;
		}

		getAssetList({page: arr[1], pageCount:qs_pagination.pageCount}, listSelector, pageSelector);
	})

	getAssetList({page:1, pageCount:qs_pagination.pageCount, checkStatus:0 }, '#table_toBeAudit', '#toBeAudit');
	getAssetList({page:1, pageCount:qs_pagination.pageCount, checkStatus:1}, '#table_retorted', '#retorted');
	getAssetList({page:1, pageCount:qs_pagination.pageCount, checkStatus:2}, '#table_toBePub', '#toBePub');
	getAssetList({page:1, pageCount:qs_pagination.pageCount}, '#table_hasPubed', '#hasPubed');
	getAssetList({page:1, pageCount:qs_pagination.pageCount}, '#table_hasOff', '#hasOff');
	getAssetList({page:1, pageCount:qs_pagination.pageCount, enable:1}, '#table_useless', '#useless');

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

    $('#assetForm').validate({
		rules: {
			'assetPackage.title': 'required',
			'assetPackage.entrustBegintime': 'required',
			'assetPackage.entrustEndtime': 'required',
			'assetPackage.totalMoney': 'required',
		},
		messages:{
			'assetPackage.title': '请填写标题',
			'assetPackage.entrustBegintime': '请设置委托开始时间',
			'assetPackage.entrustEndtime': '请设置委托结束时间',
			'assetPackage.totalMoney': '资产总额不能为空',
		},
		submitHandler: function(form){
			/**if(tokenToBeUpload.length<1){
				layer.msg('请上传凭证');
				return false;
			}**/
			//资产类型
	    	var assetType = $("#assetType").find('.radiuscurb').data('val');
	    	//资产所在地-省份
	    	var province = $("#province").parent().siblings('.sewvbm').find('li').data('val')
	    	var city = $("#city").parent().siblings('.sewvbm').find('li').data('val');
	    	if(!city){
	    		city = '';
	    	}
	    	//处置方式
	    	
	    	//委托方类型
	    	var entrustType = $("#entrustType").find('.radiuscurb').data('val');
	    	var bankType = $("#bankType").find('.radiuscurb').data('val');

	    	var dispose = JSON.stringify(disposesArr);
	    	var formData = $("#assetForm").serialize();
	    	formData += '&assetPackage.assetType='+assetType+'&assetPackage.province='+province+'&assetPackage.city='+city
	    		+'&assetPackage.entrustType='+entrustType+'&assetPackage.isBank='+bankType;

	    	var disposesArr = [];
	    	dealWay.map(function(o, i){
	    		formData += '&disposes['+i+'].disposeType='+ o.dtype;
	    		formData += '&disposes['+i+'].alg='+ o.ptype;
	    		formData += '&disposes['+i+'].value='+ o.pvalue;
	    	})
	    	//mimgToBeUpload
	    	//tokenToBeUpload
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

	    	$.ajax({
	    		crossDomain: true,
	    		type: 'post',
	    		url: qs_base_config.sale.uri+"/asset/addAsset",
		        data : formData,
		        async: true,
		        headers: cookieData,
		        success: function(res){
		        	if(res.code==2000){
		        		layer.alert('数据提交成功！', {icon: 6});
		        		getAssetList({page:1, pageCount:qs_pagination.pageCount, checkStatus:0 }, '#table_toBeAudit', '#toBeAudit');
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
});