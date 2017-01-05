var cookieData = Cookies.get('_backend_userToken');
$(function(){
	$('#self_tags').tagsInput({ width: 'auto' });

	layui.upload({
		crossDomain: true,
	    type: 'post',
	    async: true,
		headers: cookieData,
		url: qs_base_config.res.uri + '/res/upload',
		success: function(res){
		    console.log(res); //上传成功返回值，必须为json格式
		}
	});

	qsExec('res', '/area/list', 'post', {}, false, true, autoProvinc);  

	function autoProvinc(res){
    	$("select[name='province']").html('');
    	$("select[name='province']").append('<option value=" ">全部地区</option>');

    	res.map(function(o){
			$("select[name='province']").append("<option value='"+o.value+"'>"+o.label+"</option>");
		});

		layui.use('form', function(){
		  	var form = layui.form();
		  	form.render('select');
		});
    }

    //自动填充市数据
    function autoCity(res){
    	$("select[name='city']").html('');
    	$("select[name='city']").append('<option value=" ">请选择</option>');

    	if(res){
    		res.map(function(o){
				$("select[name='city']").append("<option value='"+o.value+"'>"+o.label+"</option>");
			});
    	}else{
    		autoArea();
    	}

    	layui.use('form', function(){
		  	var form = layui.form();
		  	form.render('select');
		});
    }

    //自动填充区数据
    function autoArea(res){
    	$("select[name='area']").html('');
    	$("select[name='area']").append('<option value=" ">请选择</option>');

    	if(res){
    		res.map(function(o){
				$("select[name='area']").append("<option value='"+o.value+"'>"+o.label+"</option>");
			});
    	}

    	layui.use('form', function(){
		  	var form = layui.form();
		  	form.render('select');
		});
    }

    layui.use(['form', 'layedit'], function(){
	  	var form = layui.form();

	  	var layedit = layui.layedit;

		var editIndex = layedit.build('content', {
			uploadImage: {url: '/upload/', type: 'post'}
	  	});
		
		form.on('select(province)', function (data) {
			var aid = $.trim(data.value);

			if(!!aid){
				//加载市
				qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoCity);
			}else{
				autoCity();
			}
		});

		form.on('select(city)', function (data) {
			var aid = $.trim(data.value);

			if(!!aid){
				//加载市
				qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoArea);
			}else{
				autoArea();
			}
		});

		form.verify({
		    title: function(value){
		      	if(value.length < 5){
		        	return '标题至少得5个字符啊';
		      	}
		    },
		    type: function(value){

		    },
		    content: function(value){
		    	layedit.sync(editIndex);

		    	var vt = $.trim(layedit.getText(editIndex));

		    	if(vt==''){
		    		return '请填写正文内容';
		    	}
		    }
		});

		form.on("submit(articleForm)", function(data){
            console.log(data);return false;
        });
	});
});