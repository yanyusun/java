$(document).ready(function() {
	
	
	      /*个人债券列表页详情收藏(通过的)*/
		$(document).delegate('.bapebondsc', 'click', function(){

			 var baasdetailid = getQueryString('id');
			 if($(".lbagcqgsc ").hasClass("perbondcur")){
			 	return false;
			 }else{
			 	qsExec('business', '/s/business/collect', 'get', {objectType  :11,objectId :baasdetailid,status:1}, true, false, function(res){
					if(!!res.result&&res.result=='yes'){
						$(".bapebondsc").addClass("perbondcur");
					        $(".bapebondsc").text('已收藏');
					}
				});
			 }
			
		});
	


		/*个人债券详情*/
        var baasdetailid = getQueryString('id');
	
		qsExec('sale', '/bond/noVerify/getDetail', 'get', {bondId :baasdetailid}, true, true, syzcblb);
		function syzcblb(data){
		   console.log('个人债券，企业债券，逾期贷款详情',data);
   	       var bb =data.userBond?data.userBond:[];//判断是否存在，存在就给 data.fixedAssetList 如果不存在就给个空的[]
   	       
   	       
			 
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
			if(bb.hasOwnProperty('collectionNum')){
				//alert(bb.collectionNum);
				collectionNum=bb.collectionNum;
			}
			
			var disposeNum='';
			if(bb.hasOwnProperty('disposeNum')){
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

			
			
			
			var realUrgeNum='';
			if(bb.realUrgeNum!=null){
				realUrgeNum='实地催收次数：'+ bb.realUrgeNum+'次';
			}
			
			var phoneUrgeNum='';
			if(bb.phoneUrgeNum!=null){
				phoneUrgeNum='电话催收次数：'+ bb.phoneUrgeNum+'次';
			}
			
			
			//逾期时间
			var batime3='';
			if(bb.hasOwnProperty('borrowTime')&&bb.hasOwnProperty('refundTime')){
				//获取两次的值
				var timeb=bb.borrowTime;
				var timee=bb.refundTime;
				//转化一下格式
				var batime1=Date.parse(new Date(timeb));
				var batime2=Date.parse(new Date(timee));
				//计算并赋值
				batime3='逾期时间：'+Math.abs(parseInt((batime2 - batime1)/1000/3600/24))+'天';
			}
			//'<li>'+bondsmanEconomic+'</li>'+
			var bondsmanEconomic='';
			if(bb.hasOwnProperty('bondsmanEconomic')){
			  if(bb.bondsmanEconomic=='0'){
			  	bondsmanEconomic='担保人的经济状况：优';
			  }
			  if(bb.bondsmanEconomic=='1'){
			  	bondsmanEconomic='担保人的经济状况：良';
			  }
			  if(bb.bondsmanEconomic=='2'){
			  	bondsmanEconomic='担保人的经济状况：一般';
			  }
			  if(bb.bondsmanEconomic=='3'){
			  	bondsmanEconomic='担保人的经济状况：差';
			  }
			}
			
			var isWorth='';
			if(bb.hasOwnProperty('isWorth')){
				if(bb.isWorth=='1'){
					isWorth='抵/质押物价值能否覆盖债务：能';
				}
				if(bb.isWorth=='0'){
					isWorth='抵/质押物价值能否覆盖债务：否';
				}
			}
			 var entrustUrgeNum='';
			 if(bb.hasOwnProperty('entrustUrgeNum')){
			 	entrustUrgeNum=bb.entrustUrgeNum;
			 }
			 var isLawsuit='';
			 if(bb.hasOwnProperty('isLawsuit')){
			 	if(bb.isLawsuit=='1'){
			 		isLawsuit='诉讼与否：未诉讼';
			 	}
			 	if(bb.isLawsuit=='0'){
			 		isLawsuit='诉讼与否：已诉讼';
			 	}
			 }
			 var canContact='';
			 if(bb.hasOwnProperty('canContact')){
			 	if(bb.canContact=='1'){
			 		canContact='债务方是否能正常联系：是';
			 	}
			 	if(bb.canContact=='0'){
			 		canContact='债务方是否能正常联系：否';
			 	}
			 }
			 var canPay='';
			 if(bb.hasOwnProperty('canPay')){
			 	if(bb.canPay=='1'){
			 		canPay='债务方是否有还款能力：是';
			 	}
			 	if(bb.canPay=='0'){
			 		canPay='债务方是否有还款能力：否';
			 	}
			 }
			
			$(".fixasbaxwa").append(
				'<li>'+batime3+'</li>'+
				'<li>担保人是否能联系：是</li>'+
				'<li>'+realUrgeNum+'</li>'+
				'<li>是否有凭证：有</li>'+
				//'<li>'+bondsmanEconomic+'</li>'+
				'<li>'+phoneUrgeNum+'</li>'+
				'<li>是否有担保：有</li>'+
				'<li>'+isWorth+'</li>'+
				'<li>委托催收次数：'+entrustUrgeNum+'次</li>'+
				'<li>担保方式：有/无</li>'+
				'<li>'+isLawsuit+'</li>'+
				'<li>'+canContact+'</li>'+
				'<li>担保方式：担保公司-XXXXX</li>'+
				'<li>担保方式：有/无</li>'+
				'<li>判决与否：未判决</li>'+
				'<li>'+canPay+'</li>'
			);
			
			
			var debtName='';
			if(bb.hasOwnProperty('debtName')){
			  debtName=bb.debtName;
			}
			var bondContactsName='';
			if(bb.hasOwnProperty('bondContactsName')){
			  bondContactsName=bb.bondContactsName;
			}
			//
			var bondProvinceName='';
			if(bb.hasOwnProperty('bondProvinceName')){
			  bondProvinceName=bb.bondProvinceName;
			}
			var bondCityName='';
			if(bb.hasOwnProperty('bondCityName')){
			  bondCityName=bb.bondCityName;
			}
			var bondAreaName='';
			if(bb.hasOwnProperty('bondAreaName')){
			  bondAreaName=bb.bondAreaName;
			}
			var bondAddress='';
			if(bb.hasOwnProperty('bondAddress')){
			  bondAddress=bb.bondAddress;
			}
			var bondContactsPhone='';
			if(bb.hasOwnProperty('bondContactsPhone')){
			  bondContactsPhone=bb.bondContactsPhone;
			}
			var bondContactsIdcode='';
			if(bb.hasOwnProperty('bondContactsIdcode')){
			  bondContactsIdcode=bb.bondContactsIdcode;
			}

			$(".fixasbaxwb").append(
				'<li>债权方名称：'+debtName+'</li>'+
				'<li>债权方联系姓名：'+bondContactsName+'</li>'+
				'<li>债权方地址：'+bondProvinceName+''+bondCityName+''+bondAreaName+''+bondAddress+'</li>'+
				'<li>债权方联系电话：'+bondContactsPhone+'</li>'+
				'<li>债权方联系身份证号：'+bondContactsIdcode+'</li>'
			);
			
			var debtType='';
			if(bb.hasOwnProperty('debtType')){
				if(bb.debtType=='0'){
					debtType='债务方类型：企业';
				}
				if(bb.debtType=='1'){
					debtType='债务方类型：个人';
				}
			}
			var debtIsOperate='';
			if(bb.hasOwnProperty('debtIsOperate')){
				if(bb.debtIsOperate=='0'){
					debtIsOperate='债务方实际经营地址：清楚';
				}
				if(bb.debtIsOperate=='1'){
					debtIsOperate='债务方实际经营地址：不清楚';
				}
			}
			var debtSituationMarriage='';
			if(bb.hasOwnProperty('debtSituationMarriage')){
				if(bb.debtSituationMarriage=='0'){
					debtSituationMarriage='债务方婚姻状况：已婚有子';
				}
				if(bb.debtSituationMarriage=='1'){
					debtSituationMarriage='债务方婚姻状况：已婚无子';
				}
				if(bb.debtSituationMarriage=='2'){
					debtSituationMarriage='债务方婚姻状况：未婚';
				}
				if(bb.debtSituationMarriage=='3'){
					debtSituationMarriage='债务方婚姻状况：离异';
				}
				if(bb.debtSituationMarriage=='4'){
					debtSituationMarriage='债务方婚姻状况：不清楚';
				}
			}
			var debtName='';
			if(bb.hasOwnProperty('debtName')){
			  debtName='债务方名称：'+bb.debtName;
			}
			var debtIdcode='';
			if(bb.hasOwnProperty('debtIdcode')){
			  debtIdcode='债务方证件号：'+bb.debtIdcode;
			}
			
			var debtSituationEducation='';
			if(bb.hasOwnProperty('debtSituationEducation')){
				if(bb.debtSituationEducation=='0'){
					debtSituationEducation='债务学历：硕士及以上';
				}
				if(bb.debtSituationEducation=='1'){
					debtSituationEducation='债务学历：本科';
				}
				if(bb.debtSituationEducation=='2'){
					debtSituationEducation='债务学历：大专';
				}
				if(bb.debtSituationEducation=='3'){
					debtSituationEducation='债务学历：其他';
				}
				if(bb.debtSituationEducation=='4'){
					debtSituationEducation='债务学历：不清楚';
				}
			}
			
			var debtProvinceName='';
			if(bb.hasOwnProperty('debtProvinceName')){
			  debtProvinceName=bb.debtProvinceName;
			}
			var debtCityName='';
			if(bb.hasOwnProperty('debtCityName')){
			  debtCityName=bb.debtCityName;
			}
			var debtAreaName='';
			if(bb.hasOwnProperty('debtAreaName')){
			  debtAreaName=bb.debtAreaName;
			}
			var debtAddress='';
			if(bb.hasOwnProperty('debtAddress')){
			  debtAddress=bb.debtAddress;
			}
			
			var debtSituationAsset='';
			if(bb.hasOwnProperty('debtSituationAsset')){
				if(bb.debtSituationAsset=='1'){
					debtSituationAsset='有房产';
				}
				if(bb.debtSituationAsset=='2'){
					debtSituationAsset='有车辆';
				}
				if(bb.debtSituationAsset=='3'){
					debtSituationAsset='无资产';
				}
				if(bb.debtSituationAsset=='4'){
					debtSituationAsset='在亲友名下';
				}
				if(bb.debtSituationAsset=='5'){
					debtSituationAsset='有固定收入来源';
				}
				if(bb.debtSituationAsset=='6'){
					debtSituationAsset='债务方欠其他人钱';
				}
				if(bb.debtSituationAsset=='7'){
					debtSituationAsset='其他人欠债务方钱';
				}
				if(bb.debtSituationAsset=='8'){
					debtSituationAsset='不清楚';
				}
				
			}
			var debtSituationBody='';
			if(bb.hasOwnProperty('debtSituationBody')){
				if(bb.debtSituationBody=='0'){
					debtSituationBody='健康';
				}
				if(bb.debtSituationBody=='1'){
					debtSituationBody='不健康';
				}
				if(bb.debtSituationBody=='2'){
					debtSituationBody='不清楚';
				}

			}
			var debtPhone='';
			if(bb.hasOwnProperty('debtPhone')){
			  debtPhone=bb.debtPhone;
			}
			var debtSituationMember='';
			if(bb.hasOwnProperty('debtSituationMember')){
				if(bb.debtSituationMember=='0'){
					debtSituationMember='能提供';
				}
				if(bb.debtSituationMember=='1'){
					debtSituationMember='不能提供';
				}
			}
			var debtPhone='';
			if(bb.hasOwnProperty('debtPhone')){
			  debtPhone=bb.debtPhone;
			}
			var debtSituationIndustry='';
			if(bb.hasOwnProperty('debtSituationIndustry')){
				if(bb.debtSituationIndustry=='0'){
					debtSituationIndustry='农林牧副渔';
				}
				if(bb.debtSituationIndustry=='1'){
					debtSituationIndustry='生产制造业';
				}
				if(bb.debtSituationIndustry=='0'){
					debtSituationIndustry='服务行业';
				}
				if(bb.debtSituationIndustry=='1'){
					debtSituationIndustry='不清楚';
				}
			}
			var debtSituationSocial='';
			if(bb.hasOwnProperty('debtSituationSocial')){
				if(bb.debtSituationSocial=='0'){
					debtSituationSocial='好';
				}
				if(bb.debtSituationSocial=='1'){
					debtSituationSocial='不好';
				}
			}
			var describes='';
			if(bb.hasOwnProperty('describes')){
				describes=bb.describes;
			}
			
			$(".lbagzxqnrc").append(
				'<p>'+
				   '<em>'+describes+'</em>'+
				'</p>'
			);
			
							
						
			
			$(".fixasbaxwc").append(
				'<li>'+debtType+'</li>'+
				'<li>'+debtIsOperate+'</li>'+
				'<li>'+debtSituationMarriage+'</li>'+
				'<li>'+debtName+'</li>'+
				'<li>'+debtIdcode+'</li>'+
				'<li>'+debtSituationEducation+'</li>'+
				'<li>债务方地址：'+debtProvinceName+''+debtCityName+''+debtAreaName+''+debtAddress+'</li>'+
				'<li>债务方资产状况：'+debtSituationAsset+'</li>'+
				'<li>债务方负责人身体状况：'+debtSituationBody+'</li>'+
				'<li>债务方联系电话：'+debtPhone+'</li>'+
				'<li>债务方家庭成员状况：'+debtSituationMember+'</li>'+
				'<li>债务方所处行业：'+debtSituationIndustry+'</li>'+
				'<li>债务方社会关系状况：'+debtSituationSocial+'</li>'
			);
			
			debtSituationSocial
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
			var debtType2='';
			if(bb.hasOwnProperty('debtType')){
				if(bb.debtType=='0'){
					debtType2='企业';
				}
				if(bb.debtType=='1'){
					debtType2='个人';
				}
			}
			
		    $(".lbagdctitf").append(
				'<span>'+debtType2+'</span>'+
				/*'<span>'+righterType +'</span>'+*/
				'<em>'+title+'</em>'
			);
   	       
	   
        
        
        
				
				
				
				
				
				
	   }



	

});

