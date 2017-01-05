$(document).ready(function() {
	
	/*内页资产包列表*/
	qsExec('sale', '/asset/noVerify/assetList', 'post', {}, false, true, syzcblb);
	function syzcblb(data){
		    console.log('内页资产包',data);
   	        var bb =data.assetPackageList?data.assetPackageList:[];//判断是否存在，存在就给 data.fixedAssetList 如果不存在就给个空的[]
			for(var i = 0; i <= bb.length; i++) {
				//传一个id给详情页
				var asetid ='';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.id){
					asetid=bb[i].apDto.id;
				}
				
				var batime3='';
				if(!!bb[i]&&!!bb[i].oRelation&&!!bb[i].oRelation.entrustBegintime){
					
					//获取两次的值
					var timeb=bb[i].oRelation.updateAt;
					var timee=bb[i].oRelation.createAt;
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
				var cassetType = '';
				if(!!bb[i]&&!!bb[i].apDto&&bb[i].apDto.assetType!=null) {
					if(bb[i].apDto.assetType=='0'){
						cassetType="资产包";
					}
					if(bb[i].apDto.assetType=='1'){
						cassetType="小额信贷包";
					}
					if(bb[i].apDto.assetType=='2'){
						cassetType="信用卡包";
					}if(bb[i].apDto.assetType=='3'){
						cassetType='混合包';
					}
					if(bb[i].apDto.assetType=='4'){
							cassetType='其他包';
					}
				}
				var crighterType = '';
				if(!!bb[i]&&!!bb[i].apDto&&bb[i].apDto.entrustType!=null) {
					if(bb[i].apDto.entrustType=='0'){
						crighterType="企业";
					}
					if(bb[i].apDto.entrustType=='1'){
						crighterType="个人";
					}
				}
				
				var disposeStatus = '';
				if(!!bb[i]&&!!bb[i].apDto&&bb[i].apDto.disposeStatus!=null) {
					if(bb[i].apDto.disposeStatus=='0'){
						disposeStatus="待处置";
					}
					if(bb[i].apDto.disposeStatus=='1'){
						disposeStatus="处置中";
					}
					if(bb[i].apDto.disposeStatus=='2'){
						disposeStatus="已处置";
					}
				}

				//bb[i].apDto.entrustBegintime
				var entrustBegintime='';
				if(!!bb[i]&&!!bb[i].oRelation&&!!bb[i].oRelation.createAt){
					entrustBegintime=bb[i].oRelation.createAt;
				}
				//assettomody   bb[i].apDto.totalMoney
				var assettomody='';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.totalMoney){
					assettomody=bb[i].apDto.totalMoney/10000+'W';
				}
				
				//bb[i].apDto.address
				var address='';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.address){
					address=bb[i].apDto.address;
				}
				//bb[i].apDto.no
				var gno='';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.no){
					gno='【' + bb[i].apDto.no + '】'
				}
				//bb[i].apDto.title
				var gtitle='';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.title){
					gtitle=bb[i].apDto.title;
				}
				
				//bb[i].apDto.orientation
				var gorientation='南北 / ';
				if(!!bb[i]&&!!bb[i].apDto&&bb[i].apDto.orientation!=null){
					if(bb[i].apDto.orientation=='0'){
						gorientation='南北 / ';
					}
					if(bb[i].apDto.orientation=='1'){
						gorientation='东南 / ';
					}
					if(bb[i].apDto.orientation=='2'){
						gorientation='西南 / ';
					}
					if(bb[i].apDto.orientation=='3'){
						gorientation='东北 / ';
					}
					if(bb[i].apDto.orientation=='4'){
						gorientation='西北 / ';
					}
					if(bb[i].apDto.orientation=='5'){
						gorientation='东 / ';
					}
					if(bb[i].apDto.orientation=='6'){
						gorientation='西 / ';
					}
					if(bb[i].apDto.orientation=='7'){
						gorientation='南 / ';
					}
					if(bb[i].apDto.orientation=='8'){
						gorientation='北 / ';
					}
				}
				
				
				var floor='高楼层（共25层）/ ';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.floor){
					floor='高楼层（共' + bb[i].apDto.floor + '层）/ ';
				}
				var year='2008年建板楼 / ';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.year){
					year=bb[i].apDto.year+'年建板楼 /';
				}
				
				//bb[i].apDto.collectionNum
				var collectionNum='50';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.collectionNum){
					collectionNum=bb[i].apDto.collectionNum;
				}
				//bb[i].apDto.disposeNum
				var disposeNum='50';
				if(!!bb[i]&&!!bb[i].apDto&&!!bb[i].apDto.disposeNum){
					disposeNum=bb[i].apDto.disposeNum;
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

				$(".assetlistny").append(
					'<li>' +

					//列表左侧-s
					'<div class="horizontal-pic-box">' +
					'<a href="pcasset-bag-details.html?id='+asetid+'">' +
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
					'<span class="lbazczqra-spa">' + cassetType + '</span>' +
					'<span class="lbazczqra-spa">' + crighterType+ '</span>' +
					'<em class="lbazczqra-ema">'+assettomody+'</em>' +
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
					'<div class="lbazcbn">'+
						'<span>拖欠利息：<em>4027.94元</em></span>'+
						'<span>总贷款金额：<em>370.68万</em></span>'+
						'<span>抵押物评估总价：<em>563.99万</em></span>'+				
					'</div>'+					
					'</div>' +
					'<div class="lbazqnrbr">' +
					'<a href="" class="lbazqnrbrab">收藏</a>' +
					'<a href="" class="lbazqnrbrac">申请处置</a>' +
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

