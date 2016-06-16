package com.dqys.business.controller.util;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;

/**
 * Created by Yvan on 16/6/12.
 */
public class CommonUtil {

    /**
     * 检验参数是否为空
     * @param datas
     * @return true 存在空值数据
     */
    public static boolean checkParam(Object... datas){
        if(datas.length > 0){
            for(Object o : datas){
                if(o == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通用判断结果是否为空返回消息
     * @param data
     * @return
     */
    public static JsonResponse responseBack(Object data){
        if(data == null){
            return JsonResponseTool.failure("操作失败!");
        }else{
            return JsonResponseTool.success(data);
        }
    }




}
