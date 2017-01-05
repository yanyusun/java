$(document).ready(function() {
	
	
	   /*公司债券列表页收藏*/
		$(document).delegate('.lbazqnrbrab', 'click', function(){
             var pebondid=$(this).attr('id');
			 //var baasdetailid = getQueryString('id');
			 if($(".lbazqnrbrab ").hasClass("perbondcur")){
			 	return false;
			 }else{
			 	qsExec('business', '/s/business/collect', 'get', {objectType:11,objectId :pebondid,status:1}, true, false, function(res){
					if(!!res.result&&res.result=='yes'){
						$('#'+pebondid).addClass("perbondcur");
					        $('#'+pebondid).text('已收藏');
					}
				});
			 }
			
		});
		
		/*公司债券列表页申请处置*/
		$(document).delegate('.lbazqnrbrac', 'click', function(){
             var sqpebondid=$(this).attr('id');
			 if($(".lbazqnrbrac ").hasClass("perbondcur")){
			 	return false;
			 }else{
			 	qsExec('business', '/s/business/applyDispose', 'get', {objectType:11,objectId :sqpebondid,status:1}, true, false, function(res){
					if(!!res.result&&res.result=='yes'){
						$('#'+sqpebondid).addClass("perbondcur");
					        $('#'+sqpebondid).text('已申请过');
					}
				});
			 }
			
		});
		//
	
	

	/*公司债券列表*/
	qsExec('sale', '/bond/noVerify/bondList', 'post', {bondType:11}, true, true, sygzclb);
   function sygzclb(data){
   	        var bb =data.userBondList?data.userBondList:[];
   	        console.log('公司债券列表',data);
			for(var i = 0; i < bb.length; i++) {
				//传一个id给详情页
				var asetid ='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.id){
					asetid=bb[i].ubDto.id;
				}

				var batime3='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.entrustBegintime){
					
					//获取两次的值
					var timeb=bb[i].ubDto.entrustBegintime;
					var timee=bb[i].ubDto.entrustEndtime;
					//转化一下格式
					var batime1=Date.parse(new Date(timeb));
					var batime2=Date.parse(new Date(timee));
					//计算并赋值
					batime3=Math.abs(parseInt((batime2 - batime1)/1000/3600/24));
				}

				var disStr = '非诉（35%）';
				if(!!bb[i]&&!!bb[i].disposes&&bb[i].disposes.length > 0) {
					disStr = (bb[i].disposes[0].disposeType + '(' + bb[i].disposes[0].value + '%)');
				}
				var assetType = '';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.assetType) {
					if(bb[i].ubDto.assetType=='0'){
						assetType="房产";
					}
					if(bb[i].ubDto.assetType=='1'){
						assetType="设备";
					}
					if(bb[i].ubDto.assetType=='2'){
						assetType="土地";
					}
				}
				var righterType = '';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.righterType) {
					if(bb[i].ubDto.righterType=='0'){
						righterType="个人";
					}
					if(bb[i].ubDto.righterType=='1'){
						righterType="企业";
					}
				}
				
				var disposeStatus = '';
				if(!!bb[i]&&!!bb[i].ubDto&&bb[i].ubDto.disposeStatus!=null) {
					if(bb[i].ubDto.disposeStatus=='0'){
						disposeStatus="待处置";
					}
					if(bb[i].ubDto.disposeStatus=='1'){
						disposeStatus="处置中";
					}
					if(bb[i].ubDto.disposeStatus=='2'){
						disposeStatus="已处置";
					}
				}

				//bb[i].ubDto.entrustBegintime
				var entrustBegintime='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.entrustBegintime){
					entrustBegintime=bb[i].ubDto.entrustBegintime;
				}
				//bb[i].ubDto.address
				var address='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.address){
					address=bb[i].ubDto.address;
				}
				//bb[i].ubDto.no
				var gno='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.no){
					gno='【' + bb[i].ubDto.no + '】'
				}
				//bb[i].ubDto.title
				var gtitle='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.title){
					gtitle=bb[i].ubDto.title;
				}
				
				
				var totalInterestMoney=' ';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.totalInterestMoney){
					totalInterestMoney='拖欠利息：'+bb[i].ubDto.totalInterestMoney+'元';
				}
				
				var totalMoneyw='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.loanMoney){
					var enmody=''
					enmody=bb[i].ubDto.loanMoney/10000;
					totalMoneyw='总贷款金额：'+enmody.toFixed(2)+'万元';
				}
				

				//assessTotalPrice
				
				var assessTotalPrice='';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.assessTotalPrice){
					var enmody=''
					enmody=bb[i].ubDto.assessTotalPrice/10000;
					totalMoneyw='抵押物评估总价：'+enmody.toFixed(2)+'万元';
				}
				
				
				
				var floor='高楼层（共25层）/ ';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.floor){
					floor='高楼层（共' + bb[i].ubDto.floor + '层）/ ';
				}
				var year='2008年建板楼 / ';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.year){
					year=bb[i].ubDto.year+'年建板楼 /';
				}
				
				//bb[i].ubDto.collectionNum
				var collectionNum='50';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.collectionNum){
					collectionNum=bb[i].ubDto.collectionNum;
				}
				//bb[i].ubDto.disposeNum
				var disposeNum='50';
				if(!!bb[i]&&!!bb[i].ubDto&&!!bb[i].ubDto.disposeNum){
					disposeNum=bb[i].ubDto.disposeNum;
				}
				//bb[i].labels.name
				var gname='满五唯一';
				if(!!bb[i]&&!!bb[i].labels&&!!bb[i].labels.name){
					gname=bb[i].labels.name;
				}
				
				var gname2='独家';
				if(!!bb[i]&&!!bb[i].labels&&!!bb[i].labels.name){
					gname2=bb[i].labels.name;
				}
				var gname3='有钥匙';
				if(!!bb[i]&&!!bb[i].labels&&!!bb[i].labels.name){
					gname3=bb[i].labels.name;
				}


				//左侧图片和数量
				var num=0;
				var guimg='img/tu1_1.png';//默认图片
				if(!!bb[i]&&!!bb[i].assetFiles&&bb[i].assetFiles.length>0){
					var assetFiles=bb[i].assetFiles;
					for(var i=0;i<assetFiles.length;i++){
						if(assetFiles[i].type=='0'||assetFiles[i].type=='1'){
							num++;
						}
						if(assetFiles[i].type=='0'){
							guimg=assetFiles[i].filename;
						}
					}	
				}
				
				
				var ddStr = '收藏';
				var sqddStr = '申请处置';
				var classStr = 'lbazqnrbrab';
				var sqclassStr = 'lbazqnrbrac';
				if(!!bb[i].assetUserRe){
					var assetUserRe=bb[i].assetUserRe;
					if(!!assetUserRe&&assetUserRe.isCollection!=null){
						if(assetUserRe.isCollection==1){
							ddStr = '已收藏';
							classStr = "lbazqnrbrab perbondcur";
						}else{ 	
							 $(".lbazqnrbrab").removeClass("perbondcur");
						}
					}
					if(!!assetUserRe&&assetUserRe.isDispose!=null){
						if(assetUserRe.isDispose==1){
							sqddStr = '已申请';
							sqclassStr = "lbazqnrbrab perbondcur";
						}else{ 	
							 $(".lbazqnrbrab").removeClass("perbondcur");
						}
					}
				}
			
				
				$(".fixassetlistny").append(
					'<li>' +

					//列表左侧-s
					'<div class="horizontal-pic-box">' +
					'<a href="pccompany-bond-details.html?id='+asetid+'">' +
					'<p class="special"><img src="img/pic00.png"/></p>' +
					'<div class="horizontal-pic"><img src="'+guimg+'"/></div>' +
					'<div class="horizontal-evaluation">' +
					'<i class="pic-num left0">'+num+'</i>' +
					'<p class="star-box"><span class="star" style="width:30%;"></span></p>' +
					'</div>' +
					'</a>' +
					'</div>' +
					//列表右侧
					'<div class="lbazczqnrrv">' +

					'<div class="lbazczqra">' +
					'<span class="lbazczqra-spa">' + assetType + '</span>' +
					'<span class="lbazczqra-spa">' + righterType+ '</span>' +
					'<em class="lbazczqra-ema">' + gtitle + '</em>' +
					'<i class="lbazczqra-ia">' + gno + '</i>' +
					'<span class="lbazczqra-spb">状态：</span>' +
					'<em class="lbazczqra-emb">' + disposeStatus + '...</em>' +
					'<span class="lbazczqra-spc">' + entrustBegintime + '</span>' +
					'</div>' +

					'<div class="lbazczqnrb">' +
					'<div class="lbazqnrbf">' +
					'<p>' +
					'<span><em></em><i>' + address + '</i></span>' +
					'<em>处置方式：' + disStr + '</em>' +
					'</p>' +
					'<span>' + totalInterestMoney + totalMoneyw + assessTotalPrice + 
					'</div>' +
					'<div class="lbazqnrbr">' +
					'<span class="'+classStr+'" id='+asetid+'>'+ddStr+'</span>' +
					'<span class="'+sqclassStr+'" id="sq'+asetid+'">'+sqddStr+'</span>' +
					'</div>' +
					'</div>' +

					'<div class="lbazczqnrc">' +
					'<p><span>'+gname+'</span><em>'+gname2+'</em><i>有钥匙</i></p>' +
					'<span><em>收藏：</em><i>' + collectionNum + '</i><em>申请处置：</em><i>' + disposeNum + '</i><em>剩余时间：'+ batime3 +'天</em></span>' +
					'</div>' +

					'</div>' +

					'</li>'
				);
			}
   }
	
	


	

});

