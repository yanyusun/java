$(document).ready(function() {
	
			

		/*首页固定资产12.24*/
   qsExec('sale', '/fixed/noVerify/fixedList', 'post', {isHomePage: 1}, false, true, sygzclb);
   function sygzclb(data){
   	        var bb =data.fixedAssetList?data.fixedAssetList:[];
   	       console.log('首页固定资产',data);
			for(var i = 0; i <= bb.length; i++) {
				var batime3='';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.entrustBegintime){
					//获取两次的值
					var timeb=bb[i].faDto.entrustBegintime;
					var timee=bb[i].faDto.entrustEndtime;
					//转化一下格式
					var batime1=Date.parse(new Date(timeb));
					var batime2=Date.parse(new Date(timee));
					//计算并赋值
					batime3=Math.abs(parseInt((batime2 - batime1)/1000/3600/24));
				}
				//------------------------------------------->todo begin
				if(!!bb[i]&&bb[i].disposes){
					var disposes = bb[i].disposes;
					var disStr = '';
					for(var k=0;k<disposes.length;k++){
						var disposeTypeName="";
						var disposesType=disposes[k].disposeType;
						if(disposesType==1){
							disposeTypeName="常规催收";
						}else if(disposesType==2){
							disposeTypeName="司法化解";
						}else if(disposesType==3){
							disposeTypeName="市场处置";
						}else if(disposesType==4){
							disposeTypeName="民间货代";
						}else if(disposesType==5){
							disposeTypeName="拍卖";
						}else{
							disposeTypeName="转让";
						}
						var algUnit="";
						var alg =disposes[k].alg;
						if(alg==1){
							algUnit="元";
						}else if(alg==2){
							algUnit="%";
						}else{
							algUnit="折";
						}
						disStr =disStr+'  '+disposeTypeName+ '(' + disposes[k].value +algUnit+')';
					}
				
			    }	
				//-------------------------->todo end
				var assetType = '';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.assetType) {
					if(bb[i].faDto.assetType=='0'){
						assetType="房产";
					}
					if(bb[i].faDto.assetType=='1'){
						assetType="设备";
					}
					if(bb[i].faDto.assetType=='2'){
						assetType="土地";
					}
				}
				var righterType = '';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.righterType) {
					if(bb[i].faDto.righterType=='0'){
						righterType="个人";
					}
					if(bb[i].faDto.righterType=='1'){
						righterType="企业";
					}
				}
				
				var disposeStatus = '';
				if(!!bb[i]&&!!bb[i].faDto&&bb[i].faDto.disposeStatus!=null) {
					if(bb[i].faDto.disposeStatus=='0'){
						disposeStatus="待处置";
					}
					if(bb[i].faDto.disposeStatus=='1'){
						disposeStatus="处置中";
					}
					if(bb[i].faDto.disposeStatus=='2'){
						disposeStatus="已处置";
					}
				}

				//bb[i].faDto.entrustBegintime
				var entrustBegintime='';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.entrustBegintime){
					entrustBegintime=bb[i].faDto.entrustBegintime;
				}
				//bb[i].faDto.address
				var address='';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.address){
					address=bb[i].faDto.address;
				}
				//bb[i].faDto.no
				var gno='';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.no){
					gno='【' + bb[i].faDto.no + '】'
				}
				//bb[i].faDto.title
				var gtitle='';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.title){
					gtitle=bb[i].faDto.title;
				}


				//bb[i].faDto.orientation
				var gorientation='';
				if(bb[i].faDto.orientation!=null){
					if(bb[i].faDto.orientation=='0'){
						gorientation='南北 / ';
					}
					if(bb[i].faDto.orientation=='1'){
						gorientation='东南 / ';
					}
					if(bb[i].faDto.orientation=='2'){
						gorientation='西南 / ';
					}
					if(bb[i].faDto.orientation=='3'){
						gorientation='东北 / ';
					}
					if(bb[i].faDto.orientation=='4'){
						gorientation='西北 / ';
					}
					if(bb[i].faDto.orientation=='5'){
						gorientation='东 / ';
					}
					if(bb[i].faDto.orientation=='6'){
						gorientation='西 / ';
					}
					if(bb[i].faDto.orientation=='7'){
						gorientation='南 / ';
					}
					if(bb[i].faDto.orientation=='8'){
						gorientation='北 / ';
					}
				}
				
				
				var floor='';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.floor){
					floor='高楼层（共' + bb[i].faDto.floor + '层）/ ';
				}
				var year='';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.year){
					year=bb[i].faDto.year+'年建板楼 /';
				}
				
				//bb[i].faDto.collectionNum
				var collectionNum='0';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.collectionNum){
					collectionNum=bb[i].faDto.collectionNum;
				}
				//bb[i].faDto.disposeNum
				var disposeNum='0';
				if(!!bb[i]&&!!bb[i].faDto&&!!bb[i].faDto.disposeNum){
					disposeNum=bb[i].faDto.disposeNum;
				}
				//左侧图片和数量
				var num=0;
				var guimg='img/tu1_1.png';//默认图片
				if(!!bb[i]&&!!bb[i].assetFiles&&bb[i].assetFiles.length>0){
					var assetFiles=bb[i].assetFiles;
					for(var j=0;j<assetFiles.length;j++){
						if(assetFiles[j].type=='0'||assetFiles[j].type=='1'){
							num++;
						}
						if(assetFiles[j].type=='0'){
							guimg=assetFiles[j].filename;
						}
					}
				}
				
				
				
				
				
				
				
				
				
				//-------------------------------------->todo  begin
				
				var asetid = bb[i].faDto.id;
				var html = '<li>' +
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
					'<span>' + gorientation + floor + year + '21680元/m2</span>' +
					'</div>' +
					'<div class="lbazqnrbr">' +
					'<a href="" class="lbazqnrbrab">收藏</a>' +
					'<a href="" class="lbazqnrbrac">申请处置</a>' +
					'</div>' +
					'</div>' +
					'<div class="lbazczqnrc"><p>';
				var labels=bb[i].labels;
				for(var jj=0;jj<labels.length;jj++){
					if(jj%2){
						html=html+"<em>"+labels.name+"</em>";
					}else if(jj%3){
						html=html+"<i>"+labels.name+"</i>";
					}else{
						html=html+"<span>"+labels.name+"</span>";
					}
				}
				html=html+'</p><span><em>收藏：</em><i>' + collectionNum + '</i><em>申请处置：</em><i>' + disposeNum + '</i><em>剩余时间：'+ batime3 +'天</em></span></div> </div> </li>';


				//-------------------------------------->todo end

				//bb[i].labels.name

				// var gname='满五唯一';
				// if(!!bb[i]&&!!bb[i].labels&&!!bb[i].labels.name){
				// 	gname=bb[i].labels.name;
				// }
				//
				// var gname2='独家';
				// if(!!bb[i]&&!!bb[i].labels&&!!bb[i].labels.name){
				// 	gname2=bb[i].labels.name;
				// }
				// var gname3='有钥匙';
				// if(!!bb[i]&&!!bb[i].labels&&!!bb[i].labels.name){
				// 	gname3=bb[i].labels.name;
				// }
				$(".bagzcsul").append(html);
			}
   }



	

});

