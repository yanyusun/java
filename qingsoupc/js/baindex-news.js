$(document).ready(function() {

	/*首页新闻12.24-简写*/
	qsExec('sale', '/news/noVerify/newsList', 'post', {}, false, true, newssy);

	function newssy(data) {
		//console.log(data);
		var newbb = data.newsList;
		for(var i = 0; i <= newbb.length; i++) {
			$(".lbasynrbzul-news,.lbasynrbzul-hy,.lbasynrbzul-yw").append(
				'<li>' +
				'<a>' +
				'<span>' +
				'<em>' + newbb[0].lables[i].name + '</em>' +
				'<i>' + newbb[0].news.title + '</i>' +
				'</span>' +
				'<i>' + newbb[0].news.openTime + '</i>' +
				'</a>' +
				'</li>'
			);
		}
	};

	/*首页新闻12.24-分开写*/
	/*$.ajax({
		type:"post",
		url:"http://192.168.1.51:8080/s/news/noVerify/newsList",
		async:true,
		//data:{id:123},
		dataType:"json",
		"headers": {
	  	"x-qs-certified": "false,",
	    "x-qs-role": "1,",
	    "x-qs-status": "1",
	    "x-qs-type": "1,",
	    "x-qs-user": "YWVlODkwNDBmZjcyZTBiN2ZiZTRiN2E2OTVkYWIyZGN8fHx8Mjk2",

	    "content-type": "application/x-www-form-urlencoded"   //使用get 时  就不需要这个
	 },
		success:function(newsaa){
			
			console.log('新闻',newsaa);
			//alert("首页新闻成功");

				//var newbb=newsaa.data.newsList.labels;
				//var newbb=JSON.stringify(newsaa.data.newsList[0].news);
				
				var newbb=newsaa.data.newsList;
				//console.log(newbb.id);
                for(var i=0;i<=newbb.length;i++){
					//alert(newbb.length);
					$(".lbasynrbzul-news,.lbasynrbzul-hy,.lbasynrbzul-yw").append("<li><a><span><em>"+newbb[0].lables[i].name+"</em><i>"+newbb[0].news.title+"</i></span><i>"+newbb[0].news.openTime+"</i></a></li>");
				}
		},
		error:function(aa){
			console.log(aa);
			//alert("首页新闻失败");
		}
	});*/

	

});

