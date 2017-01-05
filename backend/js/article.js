var updateArticle = function(data){
	layer.msg('确定要执行当前操作吗', {
	  	time: 0 //不自动关闭
	  	,btn: ['确定', '取消']
	  	,yes: function(index){
	    	layer.close(index);
	    	
	    	/**var selector;
			var curType = 0;
			$(".lzlgutit>i").each(function(i, item){
				if($(this).hasClass('wzbtcur')){
					curType = i;
				}
			});

			switch(curType){
				case 0:
					selector = 'pub';
					break;
				case 1:
					selector = 'toBePub';
					break;
				case 2:
					selector = 'outline';
					break;
				case 3:
					selector = 'invalid';
					break;
			}**/

			qsExec('sale', '/news/addOrUpdateNews', 'post', data, function(res){
				window.location.reload()
			});
	  	}
	});
}

$(function(){
	$(".lzladdbtn").on('click', function(){
		location.href = 'article_form.html';
	});

	var getArticleList = function(params, listSelector, pageSelector){
		qsExec('sale', '/news/list', 'get', params, true, true, function(res){
			var list = res.newsList;
			$(listSelector).html('');
			var tmpContent = '';
			list.map(function(item ,i){
				var statusStr = '';
				var o = item.news;
				if(item.news.status==0){
					statusStr = '草稿';
				}else if(item.news.status==1){
					statusStr = '待发布';
				}else if(item.news.status==2){
					statusStr = '已发布';
				}else if(item.news.status==-1){
					statusStr = '无效';
				}

				var imgStr = '';
				if(!!item.news.cover){
					imgStr = qs_base_config.res.uri+"/res/getSource?fileName="+item.news.cover+"&isTmp=false";
				}

				var optCol = '<span class="lzlglbtopspk lzlglbtopspcls"><i>操作</i>';
				switch(pageSelector){
					case '#pub':
						optCol += '<em><i class="show_artical_edit" data-val="'+o.id+'">编辑</i>';
						optCol += '<i class="show_artical_invalid" data-val="'+o.id+'">无效</i>';
						optCol += '<i class="show_artical_outline" data-val="'+o.id+'">草稿</i>';
						optCol += '<i class="show_artical_remove" data-val="'+o.id+'">删除</i></em>';
						break;
					case '#toBePub':
						optCol += '<em><i class="show_artical_edit" data-val="'+o.id+'">编辑</i>';
						optCol += '<i class="show_artical_pub" data-val="'+o.id+'">发布</i>';
						optCol += '<i class="show_artical_outline" data-val="'+o.id+'">草稿</i>';
						optCol += '<i class="show_artical_remove" data-val="'+o.id+'">删除</i></em>';
						break;
					case '#outline':
						optCol += '<em><i class="show_artical_edit" data-val="'+o.id+'">编辑</i>';
						optCol += '<i class="show_artical_pub" data-val="'+o.id+'">发布</i>';
						optCol += '<i class="show_artical_invalid" data-val="'+o.id+'">无效</i>';
						optCol += '<i class="show_artical_remove" data-val="'+o.id+'">删除</i></em>';
						break;
					case '#invalid':
						optCol += '<em><i class="show_artical_edit" data-val="'+o.id+'">编辑</i>';
						optCol += '<i class="show_artical_pub" data-val="'+o.id+'">发布</i>';
						optCol += '<i class="show_artical_outline" data-val="'+o.id+'">草稿</i>';
						optCol += '<i class="show_artical_remove" data-val="'+o.id+'">删除</i></em>';
						break;
				}				
				optCol += '</span>';

				tmpContent += '<li>';
				tmpContent += '<span class="lzlglbtopspa lzlglbtopspacen"><i>'+(i+1)+'</i><em class="curdis"></em></span>';
				tmpContent += '<span class="lzlglbtopwzb"><img src="'+imgStr+'" class="lwzlog"></span>';
				tmpContent += '<span class="lzlglbtopwzc lzlglbtopspcls">'+item.news.title+'</span>';
				tmpContent += '<span class="lzlglbtopwzd">'+(item.news.province+'-'+item.news.city+'-'+item.news.area)+'</span>';
				tmpContent += '<span class="lzlglbtopwze">'+statusStr+'</span>';
				tmpContent += '<span class="lzlglbtopwzf lzlglbtopspcls">'+item.news.operUser+'</span>';
				tmpContent += optCol;
				tmpContent += '<span class="lzlglbtopwzh lzlglbtopspcls">'+(!item.news.mark)?'':item.news.mark+'</span>';
				tmpContent += '<span class="lzlglbtopwzj">'+(!item.news.openTime)?'':item.news.openTime+'</span>';
				tmpContent += '</li>';
			});

			$(listSelector).html(tmpContent);

			$(pageSelector).pagination({
				currentPage: params.page,
		        items: res.query.totalCount,
		        itemsOnPage: res.query.pageCount,
		        cssStyle: 'light-theme'
		    });
		});
	}

	getArticleList({page:1, pageCount:qs_pagination.pageCount, status: 2}, '#article_pub', '#pub');
	getArticleList({page:1, pageCount:qs_pagination.pageCount, status: 1}, '#article_toBePub', '#toBePub');
	getArticleList({page:1, pageCount:qs_pagination.pageCount, status: 0}, '#article_outline', '#outline');
	getArticleList({page:1, pageCount:qs_pagination.pageCount, status: -1}, '#article_invalid', '#invalid');

	//分页：跳转
	$(document).delegate(".page-link", 'click', function(){
		var curType = 0;
		$(".lzlgutit>i").each(function(i, item){
			if($(this).hasClass('wzbtcur')){
				curType = i;
			}
		});

		var listSelector, pageSelector;
		var pageHref = $(this).attr('href');
		var arr = pageHref.split('-');
		var params = {page: arr[1], pageCount:qs_pagination.pageCount};

		switch(curType){
			case 0:
				params.status = 2;
				listSelector = '#article_pub';
				pageSelector = '#pub';
				break;
			case 1:
				params.status = 1;
				listSelector = '#article_toBePub';
				pageSelector = '#toBePub';
				break;
			case 2:
				params.status = 0;
				listSelector = '#article_outline';
				pageSelector = '#aoutline';
				break;
			case 3:
				params.status = -1;
				listSelector = '#article_invalid';
				pageSelector = '#invalid';
				break;
		}

		getArticleList(params, listSelector, pageSelector);
	})
	
	$(".lzlgutit>i").click(function(){
		var wznum=$(this).index();
		$(this).addClass("wzbtcur").siblings().removeClass("wzbtcur");
		$(".lwzglqhztv").eq(wznum).removeClass('curdis');
		$(".lwzglqhztv").eq(wznum).siblings(".lwzglqhztv").addClass('curdis');
	});

	$(document).delegate('.show_artical_edit', 'click', function(){
		//编辑文章
		var id = $(this).data('val');
		qsExec('sale', '/news/noVerify/getDetail', 'get', {newsId: id}, false, true, function(res){
			console.log(res)
		});
	})

	$(document).delegate('.show_artical_invalid', 'click', function(){
		//设为无效
		var id = $(this).data('val');
		updateArticle({'news.id': id, 'news.status':-1});
	})

	$(document).delegate('.show_artical_pub', 'click', function(){
		//发布
		var id = $(this).data('val');
		updateArticle({'news.id': id, 'news.status':2});
	})

	$(document).delegate('.show_artical_outline', 'click', function(){
		//草稿
		var id = $(this).data('val');
		updateArticle({'news.id': id, 'news.status':0});
	})

	$(document).delegate('.show_artical_remove', 'click', function(){
		//删除
		var id = $(this).data('val');
	})
});