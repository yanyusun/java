$(document).ready(function() {

 /*资产包详情页收藏*/
		$(document).delegate('.bapebondsc', 'click', function(){

			var baasdetailid = getQueryString('id');
			 if($(".lbagcqgsc ").hasClass("perbondcur")){
			 	return false;
			 }else{
			 	qsExec('business', '/s/business/collect', 'get', {objectType:16,objectId :baasdetailid,status:1}, true, false, function(res){
					if(!!res.result&&res.result=='yes'){
						$(".bapebondsc").addClass("perbondcur");
					        $(".bapebondsc").text('已收藏');
						
					}
					
					
				});
			 }
			
		});




	/*资产包详情*/
	
	var baasdetailid = getQueryString('id');
	
	qsExec('sale', '/asset/noVerify/getDetail', 'get', {assetId:baasdetailid}, true, true, syzcblb);
	function syzcblb(data){
		   console.log('资产包',data);
   	        var bb =data.assetPackage?data.assetPackage:[];//判断是否存在，存在就给 data.fixedAssetList 如果不存在就给个空的[]
			
			
			        var assetType='';
			        if(bb.assetType!=null){
	                    if(bb.assetType=='0'){
							assetType="资产包";
						}
						if(bb.assetType=='1'){
							assetType="小额信贷包";
						}
						if(bb.assetType=='2'){
							assetType="信用卡包";
						}if(bb.assetType=='3'){
							assetType='混合包';
						}
						if(bb.assetType=='4'){
								assetType='其他包';
						}
					}
			        var assetTypew='';
			        if(bb.assetType!=null){
	                    if(bb.assetType=='0'){
							assetTypew="资产包类型：资产包";
						}
						if(bb.assetType=='1'){
							assetTypew="资产包类型：小额信贷包";
						}
						if(bb.assetType=='2'){
							assetTypew="资产包类型：信用卡包";
						}if(bb.assetType=='3'){
							assetTypew='资产包类型：混合包';
						}
						if(bb.assetType=='4'){
							assetTypew='资产包类型：其他包';
						}
					}
			        
					
					var entrustType='';
					if(bb.entrustType!=null){
						if(bb.entrustType=='0'){
							entrustType='企业';
						}if(bb.entrustType=='1'){
							entrustType='个人';
						}
					}
					var entrustTypely='';
					if(bb.entrustType!=null){
						if(bb.entrustType=='0'){
							entrustTypely='资产包来源：企业';
						}if(bb.entrustType=='1'){
							entrustTypely='资产包来源：个人';
						}
					}
					
					
					var assetNo='';
					if(!!bb.assetNo){
					   assetNo=bb.assetNo;
					}
					
					var title='';
					if(!!bb.title){
						title=bb.title;
					}
					
					var createTime='';
					if(!!bb.createTime){
						createTime='发布时间：'+bb.createTime;
					}
					var totalMoney='';
					if(!!bb.totalMoney){
						totalMoney=bb.totalMoney;
					}
					var totalMoneyw='';
					if(!!bb.entrustPhone){
						var enmody=''
						enmody=bb.entrustPhone/10000;
						totalMoneyw='资产包总额：'+enmody.toFixed(2)+'万元';
					}
					var collectionNum='';
					if(bb.collectionNum!=null){
						collectionNum=bb.collectionNum;
					}
					var disposeNum='';
					if(disposeNum!=null){
						disposeNum=bb.disposeNum;
					}
					var entrustAddress='';
					if(!!bb.entrustAddress){
						entrustAddress=bb.entrustAddress;
					}
					var province='';
					if(!!bb.province){
						province=bb.province;
					}
					var city='';
					if(!!bb.city){
						city=bb.city;
					}
					var area='';
					if(!!bb.area){
						area=bb.area;
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
					
					
					var describes='';
					if(!!bb.describes){
						describes=bb.describes;
					}
					var deremark='';
					if(!!bb.remark){
						deremark=bb.remark;
					}
					
					
				$(".bamsasst").append(
					'<p>'+
					  '<span>资产状况</span>'+
					  '<em>'+describes+'</em>'+
					'</p>'+
					'<p>'+
					  '<span>采光情况</span>'+
					  '<em>'+deremark+'</em>'+
					'</p>'	
				);
					

				$(".lbagdctitf").append(
					'<span>'+assetType+'</span>'+
					'<span>'+entrustType +'</span>'+
					'<em>'+ title+'</em>'
				);
				$(".bafadtopr").append(
					'<p class="bafadtoprp bafadtoprpa">资产总额：<em>'+totalMoney+'</em> 元</p>'+
					'<p class="bafadtoprp bafadtoprpb">委托剩余：<em>35 </em>天</p>'+
					'<p class="bafadtoprp bafadtoprpc">收藏：<em>'+collectionNum+'</em></p>'+
					'<p class="bafadtoprp bafadtoprpc">申请处置：<em>'+disposeNum+'</em></p>'+
					'<div class="lbasfxqfdz lbasfxqfdzba">'+
					   '<p><span>位置：</span><em>'+province+city+area +'</em><i>'+ entrustAddress +'</i></p>'+
					   '<span><em></em><i></i></span>'+
					'</div>'+
					'<div class="bafarms">'+
					  '<span>固定资产编号：<em>'+assetNo+'</em></span>'+
					  '<em>反馈</em>'+
					'</div>'
				);
				
				
				
				var ddStr = '收藏';
				var classStr = 'lbagcqgsc lbagcqgsc-bja bapebondsc';
				if(!!data.result&&data.result=='yes'){
					var assetUserRe=data.assetUserRe;
					if(!!assetUserRe&&assetUserRe.isCollection!=null){
						if(assetUserRe.isCollection==1){
							ddStr = '已收藏';
							classStr = "lbagcqgsc lbagcqgsc-bja bapebondsc perbondcur";
						}else{ 	
							 //alert(1);
							 $(".bapebondsc").removeClass("perbondcur");
						}
					}
				}
				
				
				
				
				
				$(".lbagcxqgnnn").append(
					'<span class="badaftime">'+createTime+'</span>'+
					'<span class="'+classStr+'">'+ddStr+'</span>'+
					'<span class="lbagcqgsc lbagcqgdl lbagcqgsc-bjb">申请代理</span>'
				);
				
				$(".baassebag-basemessage").append(
					'<li>'+assetTypew+'</li>'+
					'<li>'+entrustTypely+'</li>'+
					'<li>'+totalMoneyw+'</li>'+
					'<li>处置方式：转让、拍卖等 没字段</li>'
				);
				
				$(".lbaxqfqhuzbimg").append(
					'<div class="lbaxqfqhuimg"><img src="'+guimg+'" align="" title=""></div>'+
					'<span class="lbaxqfqhufp"></span>'+
					'<span class="lbaxqfqhufm"><em></em><i>'+num+'</i></span>'+
					'<span class="lbaxqfqhurm"><em>5</em>/<i>'+num+'</i></span>'
				);
				
								
								
								
				
						
				var righterName='';
				if(bb.hasOwnProperty('righterName')){
					righterName='产权方姓名：'+bb.righterName;
				}
				var righterAddress='';
				if(bb.hasOwnProperty('righterAddress')){
					righterAddress='产权方地址：'+bb.righterAddress;
				}
				var righterPhone='';
				if(bb.hasOwnProperty('righterPhone')){
					righterPhone='产权方联系电话：'+bb.righterPhone;
				}
				var righterContactName='';
				if(bb.hasOwnProperty('righterContactName')){
					righterContactName='产权方联系电话：'+bb.righterContactName;
				}
				var righterContactPhone='';
				if(bb.hasOwnProperty('righterContactPhone')){
					righterContactPhone='产权方联系电话：'+bb.righterContactPhone;
				}
		
				$(".fixassetba").append(
					'righterName'+
					'righterAddress'+
					'righterPhone'+
					'righterContactName'+
					'righterContactPhone'
				);		
						
						
						
						
						
						
				
						
						
				
							
							
							
							
								
								
							
							
								
								
							
				
			
   }
	
	


	

});

