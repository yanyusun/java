/**
 * 对服务器中返回的图片进行编码
 * @param str
 * @returns {string}
 */
function base64Encode(str) {
    var CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    var out = "", i = 0, len = str.length, c1, c2, c3;
    while (i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if (i == len) {
            out += CHARS.charAt(c1 >> 2);
            out += CHARS.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if (i == len) {
            out += CHARS.charAt(c1 >> 2);
            out += CHARS.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
            out += CHARS.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += CHARS.charAt(c1 >> 2);
        out += CHARS.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
        out += CHARS.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
        out += CHARS.charAt(c3 & 0x3F);
    }
    return out;
}

/**
 * 获取验证码时所需的Key
 */
var getCaptchaKey = function(){
    var captchaKey = Cookies.get('globalCaptchaKey');

    if(!!captchaKey){
        return captchaKey;
    }else{
        var d = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = (d + Math.random()*16)%16 | 0;
            d = Math.floor(d/16);
            return (c=='x' ? r : (r&0x3|0x8)).toString(16);
        });

        captchaKey = uuid;
        var inFifteenMinutes = new Date(new Date().getTime() + 15 * 60 * 1000);

        Cookies.set('globalCaptchaKey', captchaKey, {
            expires: inFifteenMinutes,
            path: '/'
        });

        return captchaKey;
    }
}

/**
 * 获取图片验证码
 */
var getCaptchaFromApi = function (imgId){
    var key = getCaptchaKey();
    $.ajax({
        mimeType: "text/plain; charset=x-user-defined",
        type : 'post',
        url : qs_base_config.auth.uri+'/auth/captcha',
        data : {key: key},
        success : function(data){
            $("#"+imgId).attr('src','data:image/jpeg;base64,'+base64Encode(data));
        }
    });
}

/**
 * 通用的函数调用
 * @param  {[type]}   prefix        [string 接口前缀：auth/business/res]
 * @param  {[type]}   url           [string 接口地址]
 * @param  {[type]}   m             [string 接口访问方法：get/post]
 * @param  {[type]}   data          [object 参数]
 * @param  {Boolean}  needCookie    [bool 是否需要带上cookie头]
 * @param  {Function} silence       [bool 是否做消息提示]
 * @param  {Function} cb            [function 回调函数]
 * @param  {[type]}   okMsg         [string 成功时返回的消息]
 * @param  {[type]}   errorMsg      [string 失败时返回的消息]
 * @return {[type]}                 [description]
 */
var qsExec = function (prefix, url, m, data, needCookie, silence, cb, okMsg, errorMsg){
    url = qs_base_config[prefix].uri + url;
    var cookieData = {};
    if(needCookie=='undefined' || needCookie==null || needCookie){
        cookieData = Cookies.get('_backend_userToken');
		
        if(!cookieData){
            layer.alert('您还没有登录！', {icon: 5});
            //return false;
            //location.href = '/qingsoupc';
        }else{
            cookieData = JSON.parse(cookieData);
        }
    }

    $.ajax({
        crossDomain: true,
        type : m,
        url : url,
        data : data,
        async: true,
        headers: cookieData,
        success : function(res){
            if(res.code==2000){
                if(!silence){
                   if(!!okMsg){
                        layer.alert(okMsg, {icon: 6});
                    }else{
                        layer.alert(res.msg, {icon: 6});
                    } 
                }

	            if(cb){
	                cb(res.data);
	            }
            }else{
                if(!silence){
                    if(!!errorMsg){
                        layer.alert(errorMsg, {icon: 5});
                    }else{
                        layer.alert(res.msg, {icon: 5});
                    }
                }
            }
        },
        error: function(e){
            console.log(e)
        }
    });
}

/**
 * 获取url参数
 * @param  {[type]} name [参数名]
 * @return {[type]}      [description]
 */
var getQueryString = function (name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return unescape(r[2]);return null;
}