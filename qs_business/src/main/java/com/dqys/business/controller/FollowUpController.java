package com.dqys.business.controller;

import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.business.service.utils.followUp.FollowUpUtil;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-8-12.
 */
@RestController
@RequestMapping(value = "/follow_up")
public class FollowUpController extends BaseApiContorller {
    @Autowired
    private FollowUpMessageService followUpMessageService;

    @Autowired
    private FollowUpReadStatusService followUpReadStatusService;
    /***
     * 查询更进信息,并去除对应阶段的未读数据
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(@ModelAttribute FollowUpMessageQuery followUpMessageQuery) {
        List<FollowUpMessage> list=followUpMessageService.listAndCancelUnread(followUpMessageQuery);
        return JsonResponseTool.success(list);
    }

    /**
     * 增加跟进信息,状态为未发送
     * @param dto
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute  FollowUpMessageDTO dto) {
        if (CommonUtil.checkParam(
                dto.getObjectId(),dto.getObjectType(),dto.getLiquidateStage()
        )) {
            return JsonResponseTool.paramErr("参数错误");
        }
        FollowUpMessage followUpMessage = FollowUpUtil.toFollowUpMessage(dto);
        if(followUpMessage!=null){
            followUpMessageService.insert(followUpMessage);
        }else{
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(followUpMessage);
    }

    /**
     * 查询对象所有阶段的未读留言数量
     * @param对象id
     * @return
     */
    @RequestMapping(value = "/unread_count", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse unReadCount(int objectId,int objectType)
    {
        Map<String,String> countMap=followUpReadStatusService.getCountMap(objectId,objectType);
        return JsonResponseTool.success(countMap);
    }

}
