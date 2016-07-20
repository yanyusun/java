package com.dqys.core.utils;

import com.dqys.core.base.BasePageDTO;
import com.dqys.core.base.BasePropertyDTO;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.apache.poi.ss.formula.functions.T;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.List;

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
     * 检验参数是否全为空
     * @param datas
     * @return true 存在不为空数据
     */
    public static boolean checkNullParam(Object... datas){
        if(datas.length > 0){
            for(Object o : datas){
                if(o != null){
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
        if(data == null || data.equals(0)){
            return JsonResponseTool.failure("操作失败!");
        }else{
            return JsonResponseTool.success(data);
        }
    }

    /**
     * 通用检查返回结果<非空|非空字符串|非0返回值>
     * @param id
     * @return
     */
    public static boolean checkResult(Integer id){
        if(id == null || id.equals("") || id.equals("0")){
            return true;
        }
        return false;
    }


    /**
     * 判断当前登录用户是否是总管理员
     *
     * @return true 是总管理员
     */
    public static boolean isManage() {
        String roleStr = UserSession.getCurrent().getRoleId();
        String typeStr = UserSession.getCurrent().getUserType();

        String reg = NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_PLATFORM);
        String reg1 = NoSQLWithRedisTool.getValueObject(KeyEnum.ROLE_ADMINISTRATOR_KEY);
        // 这里写死
//        String reg = "1";
//        String reg1 = "1";

        if (roleStr.indexOf(reg) > 0
                && typeStr.indexOf(reg1) > 0) {
            return true;
        }
        return false;
    }


}
