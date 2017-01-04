$(document).ready(function() {

	
	 /*个人中心首页上方和主体部分取值-简写*/
	qsExec('sale', '/saleUser/personal', 'post', {}, true, true, pensycen);
    function pensycen(data){
    	console.log('个人中心上方和主体',data);
    	var bb = data.detail;
		$(".bapcfnt>em").text(bb.name);
		//alert(bb.name);
		$(".bapcfnt>i").text(bb.mobile);
		$(".bapcfemal>em,.lbagxtoprmc > em").text(bb.email);
		$(".bapcfzh>i").text(bb.account);
		$(".bapcfadress>i").text(bb.provinceName+bb.cityName+bb.areaName);
		$(".badltime>i").text(bb.loginTime);
		$(".lbagxtopm>p>span>i").text(bb.account);
		$(".lbagxtopm>p>span>em").text(bb.mobile);
    }

   
    
    
    /*个人中心首页左侧部分取值-简写*/
    qsExec('sale', '/UserBusTotal/get', 'post', {}, true, true, pensycenf);
    function pensycenf(data){
    	console.log('个人中心左侧取值',data);
    	$(".bapinxyfb>a>i").text(' ( '+data.hasPublish+' )');
    	$(".bapinxsc>a>i").text(' ( '+data.onCollection+' )');
    	$(".bapinxczz>a>i").text(' ( '+data.onBusiness+' )');

    }
    
    
    

	

});

