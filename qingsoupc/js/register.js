$(function(){
	function autoProvinc(res){
		$(".lbaregprov").html('');
		$(".lbaregprov").append("<option value=''>请选择</option>");

		res.map(function(o){
			$(".lbaregprov").append("<option value='"+o.value+"'>"+o.label+"</option>");
		});
	}

	function autoCity(res){
		$(".lbaregcity").html('');
		$(".lbaregcity").append("<option value=''>请选择</option>");

		if(res && res.length){
			res.map(function(o){
				$(".lbaregcity").append("<option value='"+o.value+"'>"+o.label+"</option>");
			});
		}
	}

	function autoArea(res){
		$(".lbaregqu").html('');
		$(".lbaregqu").append("<option value=''>请选择</option>");

		if(res && res.length){
			res.map(function(o){
				$(".lbaregqu").append("<option value='"+o.value+"'>"+o.label+"</option>");
			});
		}
	}

	//加载省份数据
	qsExec('res', '/area/list', 'post', {}, false, true, autoProvinc);

	$(".lbaregprov").bind('change', function(){
		var aid = $(this).val();
		if(!!aid){
			//加载市数据
			qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoCity);
		}else{
			autoCity();
			autoArea();
		}
	})

	$(".lbaregcity").bind('change', function(){
		var aid = $(this).val();
		if(!!aid){
			//加载区数据
			qsExec('res', '/area/list', 'post', {aid: aid}, false, true, autoArea);
		}else{
			autoArea();
		}
	})
});