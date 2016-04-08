/**
 * Created by pan on 11/17/15.
 */

/* 判断空值 */
function isEmpty(obj) {
    if(null == obj || "null" == obj || "undefined" == obj || "undefined" == typeof(obj) || "" == obj) {
        return true;
    } else {
        return false;
    }
}

//时间戳转日期
function timerChanger(dateData) {
    var date = new Date(dateData);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    var timeChanged = Y+M+D+h+m+s ;
    return timeChanged;
}
function dateChanger(dateData) {
    if(dateData == "-") {
        return dateData;
    }
    var date = new Date(dateData);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    var dateChanger = Y+M+D;
    return dateChanger;
}

function validationInput(parentEl) {
    var canCountiue = true;
    $.each($(parentEl + " input"), function(index, el) {
        if(!isEmpty($(el).attr("min")) && $(el).val() < $(el).attr("min")) {
            alert("不小于"+$(el).attr("min"));
            $(el).focus();
            canCountiue = false;
            return false;
        }
    });

    return canCountiue;
}

/* 请求数据 */
function selectedPage(url, params, curPage, callback) {
    if(isEmpty(curPage)) {
        curPage = 1;
    }
    $(".loadding").removeClass("hidden");
    params.curPage = curPage;
    $.ajax({
        url: url,
        data: params,
        success: function(ret) {
            $(".loadding").addClass("hidden");
            if(ret.code == 2000) {
                $(".data_content").children().remove();

                callback(ret.data.data);

                initPaggingBar(url, params, curPage, callback, ret.data.pageNum);
            }
        }
    });
}
/* 初始化分页工具条 */
function initPaggingBar(url, params, curPage, callback, pageNum) {
    if(pageNum != $(".page_count").children().length) {
        $(".pagebar").children().remove();
        $(".pagebar").append(
            "<li class='pre_page'>" +
            "<a href='#' aria-label='Previous'>" +
            "<span aria-hidden='true'>&laquo;</span>" +
            "</a>" +
            "</li>"
        );
        for(var i=1; i<= pageNum; i++) {
            $(".pagebar").append("<li  class='page_num'><a href='#' >"+ i +"</a></li>");
        }
        $(".pagebar").append(
            "<li class='next_page'>" +
            "<a href='#' aria-label='Next'>" +
            "<span aria-hidden='true'>&raquo;</span>" +
            "</a>" +
            "</li>"
        );

        $(".page_num").on("click", function() {
            selectedPage(url, params, $(this).children().text(), callback);
        });
        $(".pre_page").on("click", function() {
            selectedPage(url, params, curPage - 1 > 0 ? curPage - 1 : 1, callback);
        });
        $(".next_page").on("click", function() {
            selectedPage(url, params, curPage + 1 > pageNum ? pageNum : curPage + 1, callback);
        });
    }

    $(".page_num").removeClass("active");
    $(".page_num:eq(" + (curPage-1) + ")").addClass("active");
}

/* 全选/反选 整个页面的全部checkbox */
function checkeAll() {
    if($(":checkbox:not(:checked)").length == 0) {
        $.each($(":checkbox"), function(index, item) {
            item.checked = false;
        });
    } else {
        $.each($(":checkbox"), function(index, item) {
            item.checked = true;
        });
    }
}

/* 批量更新 选中的 */
function batchUpdateFn() {
    if($(":checkbox:checked").length == 0) {
        return;
    }
    var datas = "";
    $.each($(":checkbox:checked"), function(index, item) {
        //id version ordernum
        datas = datas + $(item).val() + ":" + $(item).next().val() + ":" + $(item).parent().parent().find("input[type=number]").val() + ",";
    });

    $(".loadding").removeClass("hidden");
    $.post("/share/shared_fund_batch_update", {"datas": datas}, function(ret) {
        $(".loadding").addClass("hidden");
        if(ret.code == "2000") {
            alert("更新成功");
            //设置数据version
            $.each($(":checkbox:checked"), function(index, item) {
                $(item).next().val(parseInt($(item).next().val())+1);
            });
        } else {
            alert(ret.msg);
        }
    });
}