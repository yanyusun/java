var cookieData = Cookies.get('_backend_userToken');

if(cookieData){
	cookieData = JSON.parse(cookieData);
}
var mimgToBeUpload = [];		//待上传的主图

function autoProvinc(res){
	$("select[name='news.province']").html('');
	$("select[name='news.province']").append('<option value="">全部地区</option>');

	res.map(function(o){
		$("select[name='news.province']").append("<option value='"+o.value+"'>"+o.label+"</option>");
	});
}

//自动填充市数据
function autoCity(res){
	$("select[name='news.city']").html('');
	$("select[name='news.city']").append('<option value="">请选择</option>');

	if(res){
		res.map(function(o){
			$("select[name='news.city']").append("<option value='"+o.value+"'>"+o.label+"</option>");
		});
	}else{
		autoArea();
	}
}

//自动填充区数据
function autoArea(res){
	$("select[name='news.area']").html('');
	$("select[name='news.area']").append('<option value="">请选择</option>');

	if(res){
		res.map(function(o){
			$("select[name='news.area']").append("<option value='"+o.value+"'>"+o.label+"</option>");
		});
	}
}

$(function(){
	var ue = UE.getEditor('editor');

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
    /**标签部分结束**/

	//封面图上传
	$('#filesUpload').fileupload({
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
            	var content = '<span style="margin-left:20px;">';
            	//data.files[0].name
            	content += '<img style="width:60px" src="'+
            		qs_base_config.res.uri+"/res/getSource?fileName="+res.data+"&isTmp=false"+'" />';
            		content += '</span>';
	            $('.attaches').html(content);

	            mimgToBeUpload = [];

	            mimgToBeUpload.push(res.data);
            }else{
            	layer.alert(res.msg, {icon: 5});
            }
            
        }
    });

    qsExec('res', '/area/list', 'post', {}, false, true, autoProvinc);

    $("select[name='news.province']").on('change', function(){
    	var aid = $(this).val();

    	if(!!aid){
			//加载市
			qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoCity);
		}else{
			autoCity();
		}
    });

    $("select[name='news.city']").on('change', function(){
    	var aid = $(this).val();

    	if(!!aid){
			//加载市
			qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoArea);
		}else{
			autoArea();
		}
    });

    $.validator.setDefaults({
		errorClass: "asset-invalid",
	});

	$("#saveBtn").on('click', function(){
		saveNews(1);
	});

	$("#save").on('click', function(){
		saveNews(0);
	});

	$("#saveAndPub").on('click', function(){
		saveNews(2);
	});

	$("#resetBtn").on('click', function(){
		resetForm();
	});

	function resetForm(){
		$("#article_form")[0].reset();
		mimgToBeUpload = [];
		$("#progress .bar").css('display', 'none');
	    $("#extend-bar").css('display', 'none');
	    $('.attaches').html('');
	}

	var saveNews = function(status){
		$('#article_form').validate({
			rules: {
				'title': 'required',
			},
			messages:{
				'title': '请填写标题',
			},
			submitHandler: function(form){
				var digestStr = $.trim($("#digest").val());
				var contentStr = $.trim(ue.getContentTxt());
				var formData = $(form).serialize();

				if(!!contentStr){
					formData += '&news.content='+ue.getContent();
				}else{
					layer.msg('请填写正文内容');
					return false;
				}

				if(!digestStr){
					digestStr = contentStr.substr(0, 45);
				}

				formData += '&news.digest='+digestStr;

				if(mimgToBeUpload.length)
					formData += '&news.cover='+mimgToBeUpload[0];
				formData += '&news.status='+status;

				qsExec('sale', '/news/addOrUpdateNews', 'post', formData, true, false, function(res){
					console.log(res)
					resetForm();
				}, '数据保存成功', '数据保存失败');
			}
		});
	}
});