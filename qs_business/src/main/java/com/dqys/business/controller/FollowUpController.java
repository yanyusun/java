package com.dqys.business.controller;

import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.business.service.utils.followUp.FollowUpUtil;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * @api {GET} http://{url}/follow_up/list 查询更进信息,并去除对应阶段的未读数据
     * @apiName list
     * @apiGroup followUp
     * @apiUse FollowUpMessageQuery
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": [
     * {
     * "id": 1,
     * "objectId": null,
     * "objectType": null,
     * "userId": null,
     * "teamId": null,
     * "content": null,
     * "version": null,
     * "createAt": null,
     * "updateAt": null,
     * "stateflag": null,
     * "secondObjectId": null,
     * "secondObjectType": null,
     * "liquidateStage": 1,
     * "secondLiquidateStage": null,
     * "sendStatus": null,
     * "userInfo": null,
     * "teammateRe": null,
     * "companyInfo": null
     * }
     * ]
     * }
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(FollowUpMessageQuery followUpMessageQuery) {
        List<FollowUpMessage> list = followUpMessageService.listAndCancelUnread(followUpMessageQuery);
        return JsonResponseTool.success(list);
    }

    /**
     * @api {POST} http://{url}/follow_up/add 增加跟进信息,状态为未发送
     * @apiName add
     * @apiGroup followUp
     * @apiUse FollowUpMessageDTO
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "id": null,
     * "objectId": 111,
     * "objectType": 111,
     * "userId": 1,
     * "teamId": 0,
     * "content": "111",
     * "version": null,
     * "createAt": null,
     * "updateAt": null,
     * "stateflag": null,
     * "secondObjectId": null,
     * "secondObjectType": 111,
     * "liquidateStage": 111,
     * "secondLiquidateStage": 111,
     * "sendStatus": null,
     * "userInfo": null,
     * "teammateRe": null,
     * "companyInfo": null
     * }
     * }
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(FollowUpMessageDTO followUpMessageDTO) {
        if (followUpMessageDTO.getObjectId() == null || followUpMessageDTO.getObjectType() == null || followUpMessageDTO.getLiquidateStage() == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        followUpMessageService.insert(followUpMessageDTO);
        return JsonResponseTool.success(followUpMessageDTO);
    }

    /**
     * @api {GET} http://{url}/follow_up/add 增加跟进信息,状态为未发送
     * @apiName list
     * @apiGroup followUp
     * @apiParam {number} objectId 对象id
     * @apiParam {number} objectType 对象类型
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": [
     * {
     * "count": 2,
     * "moment": 111
     * },
     * {
     * "count": 1,
     * "moment": 222
     * }
     * ]
     * }
     */
    @RequestMapping(value = "/unread_count", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse unReadCount(int objectId, int objectType) {
        List<Map<String, String>> countMap = followUpReadStatusService.getCountMap(objectId, objectType);
        return JsonResponseTool.success(countMap);
    }

}
