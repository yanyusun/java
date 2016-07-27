package com.dqys.business.controller;

import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mkfeng on 2016/7/26.
 */
@Controller
@RequestMapping("/operType")
public class OperTypeController {

    @Autowired
    private OperTypeService operTypeService;

    @Autowired
    private TUserTagMapper tUserTagMapper;

    /**
     * @api {post} operType/list 操作接口
     * @apiName operType/list
     * @apiSampleRequest operType/list
     * @apiParam {int} objectType 对象类型
     * @apiGroup　 OperType
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(@RequestParam("objectType") Integer objectType, HttpServletRequest httpServletRequest) {
        try {
            Integer userId = ProtocolTool.validateUser(
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
            );
            List<TUserTag> tags = tUserTagMapper.selectByUserId(userId);
            Integer roleType=3;//1-管理员;2-管理者;3-普通员工;
            Integer userType=0;//0-普通用户;1-平台管理员;2-委托号;31-催收;32-律所;33-中介
            if(tags.size()>0){
                TUserTag tag=tags.get(0);
                roleType=(int)tag.getRoleId();
                userType=(int)tag.getUserType();
            }
            List<OperType> operTypes = operTypeService.getOperType(roleType, userType, objectType);
            return JsonResponseTool.success(operTypes);
        } catch (Exception e) {
            return JsonResponseTool.serverErr();
        }
    }
}
