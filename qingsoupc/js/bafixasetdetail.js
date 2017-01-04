$(document).ready(function() {
	


/*qsExec('sale', '/bond/noVerify/getDetail', 'get', {fixedAssetId :baasdetailid}, false, true, syzcblb);
console.log('证券详情',data);*/

		/*固定资产详情*/
        var baasdetailid = getQueryString('id');
	
		qsExec('sale', '/fixed/noVerify/getDetail', 'get', {fixedAssetId :baasdetailid}, false, true, syzcblb);
		function syzcblb(data){
			   console.log('固定资产详情',data);
	   	       var bb =data.fixedAsset?data.fixedAsset:[];//判断是否存在，存在就给 data.fixedAssetList 如果不存在就给个空的[]
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
        
        
        var righterType='';
		if(bb.righterType!=null){
			if(bb.righterType=='0'){
				righterType='企业';
			}if(bb.righterType=='1'){
				righterType='个人';
			}
		}
		
		 
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
		
		var assetRental='';
		if(!!bb.assetRental){
			assetRental=bb.assetRental;
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
		if(!!bb.no){
			no=bb.no;
		}
		var entrustBegintime='';
		if(!!bb.entrustBegintime){
			entrustBegintime=bb.entrustBegintime;
		}
		
		
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
		}
		
						
						
		
		
		$(".fixasbaxw").append(
			'<li>'+assetType2+'</li>'+
			'<li>'+isPledge+'</li>'+
			'<li>'+wwassetRental+'</li>'+
			'<li>'+houseNature+'</li>'+
			'<li>'+usedYear+'</li>'+
			'<li>转让价格：434.1万元</li>'+
			'<li>'+houseSize+'</li>'+
			'<li>处置方式：转让、拍卖等</li>'+
			'<li>'+hasHouseCertificate+'</li>'
		);
		
		$(".lbagcxqgnnn").append(
			'<span class="badaftime">'+entrustBegintime+'</span>'+
			'<a href="" class="lbagcqgsc lbagcqgsc-bja">收藏</a>'+
			'<a href="" class="lbagcqgsc lbagcqgsc-bjb">申请代理</a>'
		);

		$(".bafadtopr").append(
			'<p class="bafadtoprp bafadtoprpa">资产总额：<em>'+assetRental+'</em> 元</p>'+
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
			'<span>'+assetType+'</span>'+
			'<span>'+righterType +'</span>'+
			'<em>'+ title+'</em>'
		);
				
				
				
				
				
				
	   }



	

});

