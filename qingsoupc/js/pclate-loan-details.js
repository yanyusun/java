$(document).ready(function() {
	
	
	      /*逾期贷款详情页收藏*/
		$(document).delegate('.bapebondsc', 'click', function(){

			 var baasdetailid = getQueryString('id');
			 if($(".lbagcqgsc ").hasClass("perbondcur")){
			 	return false;
			 }else{
			 	qsExec('business', '/s/business/collect', 'get', {objectType  :11,objectId :baasdetailid,status:1}, true, false, function(res){
					if(!!res.result&&res.result=='yes'){
						$(this).addClass("perbondcur");
					        $(".bapebondsc").text('已收藏');
					}
				});
			 }
		});
	


		/*逾期贷款详情页*/
        var baasdetailid = getQueryString('id');
	
		qsExec('sale', '/bond/noVerify/getDetail', 'get', {bondId :baasdetailid}, true, true, syzcblb);
		function syzcblb(data){
		   console.log('个人债券，企业债券，逾期贷款详情',data);
   	       var bb =data.userBond?data.userBond:[];//判断是否存在，存在就给 data.fixedAssetList 如果不存在就给个空的[]
   	       
   	        /*var assetType='';
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
			}*/
	        
	        
	        /*var righterType='';
			if(bb.righterType!=null){
				if(bb.righterType=='0'){
					righterType='企业';
				}if(bb.righterType=='1'){
					righterType='个人';
				}
			}*/
			
			 
			var title='';
			if(!!bb.title){
				title=bb.title;
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
			
			var totalMoney='';
			if(!!bb.totalMoney){
				totalMoney=bb.totalMoney;
			}
			
			var collectionNum='';
			if(!!bb.collectionNum){
				collectionNum=bb.collectionNum;
			}
			
			var disposeNum='';
			if(!!bb.disposeNum){
				disposeNum=bb.disposeNum;
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
			var address='';
			if(!!bb.address){
				address=bb.address;
			}
			
			var no='';
			if(!!bb.bondNo){
				no=bb.bondNo;
			}
			var startTime='';
			if(!!bb.startTime){
				startTime=bb.startTime;
			}
			
			/*
			var assetType2='';
			if(bb.assetType!=null){
				if(bb.assetType=='0'){
					assetType2='资产类型：房产';
				}if(bb.assetType=='1'){
					assetType2='资产类型：设备';
				}if(bb.assetType=='2'){
					assetType2='资产类型：土地';
				}
			}
			
			var isPledge='';
			if(bb.isPledge!=null){
				if(bb.isPledge=='0'){
					isPledge='是否抵押：是';
				}if(bb.isPledge=='1'){
					isPledge='是否抵押：否';
				}
			}
			
			var wwassetRental='';
			if(!!bb.assetRental){
				var enmody=''
				enmody=bb.assetRental/10000;
				wwassetRental='资产包总额：'+enmody.toFixed(2)+'万元';
			}				
							
			var houseNature='';
			if(bb.houseNature!=null){
				if(bb.houseNature=='0'){
					houseNature='房产性质：不限';
				}if(bb.houseNature=='1'){
					houseNature='房产性质：居住';
				}if(bb.houseNature=='2'){
					houseNature='房产性质：商业';
				}if(bb.houseNature=='3'){
					houseNature='房产性质：工业';
				}
			}				
							
			var usedYear='';
			if(!!bb.usedYear){
				usedYear='已使用年限：'+bb.usedYear;
			}
			var houseSize='';
			if(!!bb.houseSize){
				houseSize='房产面积：'+bb.houseSize +' m<sup>2</sup>';
			}
			
			var hasHouseCertificate='';
			if(bb.hasHouseCertificate!=null){
				if(bb.hasHouseCertificate=='0'){
					hasHouseCertificate='房产证类型：有';
				}
				if(bb.hasHouseCertificate=='1'){
					hasHouseCertificate='房产证类型：无';
				}
			}*/
			
			var realUrgeNum='';
			if(bb.realUrgeNum!=null){
				realUrgeNum='实地催收次数：'+ bb.realUrgeNum+'次';
			}
			
			var phoneUrgeNum='';
			if(bb.phoneUrgeNum!=null){
				phoneUrgeNum='电话催收次数：'+ bb.phoneUrgeNum+'次';
			}
			var bondsmanContact='';
			if(bb.bondsmanContact!=null){
				if(bb.bondsmanContact=='0'){
					bondsmanContact='担保人是否能联系：是';
				}
				if(bb.bondsmanContact=='1'){
					bondsmanContact='担保人是否能联系：否';
				}
			}
			var entrustUrgeNum='';
			if(bb.entrustUrgeNum!=null){
				entrustUrgeNum='委托催收次数：'+entrustUrgeNum+'次'
			}
			
			$(".fixasbaxwa").append(
				'<li>逾期时间:5个月</li>'+
				'<li>担保人是否能联系：是</li>'+
				'<li>'+realUrgeNum+'</li>'+
				'<li>'+bondsmanContact+'</li>'+
				'<li>担保人的经济状况：不清楚</li>'+
				'<li>'+phoneUrgeNum+'</li>'+
				'<li>是否有担保：有</li>'+
				'<li>抵/质押物价值能否覆盖债务：能</li>'+
				'<li>'+entrustUrgeNum+'</li>'+
				'<li>担保方式：有/无</li>'+
				'<li>诉讼与否：未诉讼</li>'+
				'<li>债务方是否能正常联系：是</li>'+
				'<li>担保方式：担保公司-XXXXX</li>'+
				'<li>担保方式：有/无</li>'+
				'<li>判决与否：未判决</li>'+
				'<li>债务方是否有还款能力：不确定</li>'
			);

			$(".fixasbaxwb").append(
				'<li>债权方名称：XXX</li>'+
				'<li>债权方联系姓名：XXX</li>'+
				'<li>债权方地址：XXX</li>'+
				'<li>债权方联系电话：XXX</li>'+
				'<li>债权方联系方式：XXX</li>'+
				'<li>债权方联系身份证号：无</li>'
			);
			$(".fixasbaxwc").append(
				'<li>债务方类型：企业</li>'+
				'<li>债务方实际经营地址：XXX</li>'+
				'<li>债务方婚姻状况：XXX</li>'+
				'<li>债务方名称：XXX</li>'+
				'<li>债务方证件号：XXX</li>'+
				'<li>债务方学历状况：无</li>'+
				'<li>债务方地址：XXX</li>'+
				'<li>债务方资产状况：XXX</li>'+
				'<li>债务方负责人身体状况：XXX</li>'+
				'<li>债务方联系电话：XXX</li>'+
				'<li>企业类债务方经营状况：XXX</li>'+
				'<li>债务方家庭成员状况：XXX</li>'+
				'<li>债务方现住地址：XXX</li>'+
				'<li>债务方所处行业：XXX</li>'+
				'<li>债务方社会关系状况：XXX</li>'
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
				var ddStrsq = '申请代理';
				var classStrsq = 'lbagcqgsc lbagcqgsc-bjb';
				if(!!data.result&&data.result=='yes'){
					var assetUserRe=data.assetUserRe;
					if(!!assetUserRe&&assetUserRe.isCollection!=null){
						if(assetUserRe.isCollection==1){
							ddStrsq = '已申请代理';
							classStrsq = "lbagcqgsc lbagcqgsc-bjb perbondsqcur";
						}else{ 	
							 //alert(1);
							 $(".bapebondsc").removeClass("perbondsqcur");
						}
					}
				}
				
				
				
			$(".lbagcxqgnnn").append(
				'<span class="badaftime">'+startTime+'</span>'+
				'<span class="'+classStr+'" id="tesx">'+ddStr+'</span>'+
				'<span href="" class="'+classStrsq+'">'+ddStrsq+'</span>'
			);
	
			$(".bafadtopr").append(
				'<p class="bafadtoprp bafadtoprpa">债权总额：<em>'+totalMoney+'</em> 元</p>'+
				'<p class="bafadtoprp bafadtoprpb">委托剩余：<em>35 </em>天</p>'+
				'<p class="bafadtoprp bafadtoprpc">收藏：<em>'+collectionNum+'</em></p>'+
				'<p class="bafadtoprp bafadtoprpc">申请处置：<em>'+disposeNum+'</em></p>'+
				'<div class="lbasfxqfdz lbasfxqfdzba">'+
				   '<p><span>位置：</span><em>'+province+city+area +'</em><i>'+ address +'</i></p>'+
				   '<span><em></em><i></i></span>'+
				'</div>'+
				'<div class="bafarms">'+
				  '<span>固定资产编号：<em>'+no+'</em></span>'+
				  '<em>反馈</em>'+
				'</div>'
			);
			
			
			$(".lbaxqfqhuzbimg").append(
				'<div class="lbaxqfqhuimg"><img src="'+guimg+'" align="" title=""></div>'+
				'<span class="lbaxqfqhufp"></span>'+
				'<span class="lbaxqfqhufm"><em></em><i>'+num+'</i></span>'+
				'<span class="lbaxqfqhurm"><em>5</em>/<i>'+num+'</i></span>'
			);
			
		    $(".lbagdctitf").append(
				/*'<span>'+assetType+'</span>'+
				'<span>'+righterType +'</span>'*/+
				'<em>'+ title+'</em>'
			);
   	       
	   
        
        
        
				
				
				
				
				
				
	   }



	

});

