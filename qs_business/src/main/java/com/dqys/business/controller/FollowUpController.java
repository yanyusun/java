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

import java.io.IOException;
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
    {
    "code": 2000,
    "msg": "成功",
    "data": [
    {
    "id": 18,
    "version": null,
    "stateflag": null,
    "createAt": "2016-09-22",
    "updateAt": null,
    "remark": null,
    "objectId": null,
    "objectType": null,
    "userId": null,
    "teamId": null,
    "content": "11",
    "secondObjectId": null,
    "secondObjectType": null,
    "liquidateStage": 11,
    "secondLiquidateStage": null,
    "sendStatus": null,
    "userInfo": {
    "id": null,
    "version": null,
    "stateflag": null,
    "createAt": null,
    "updateAt": null,
    "remark": null,
    "userName": "zs",
    "realName": null,
    "account": null,
    "wechat": null,
    "avg": null,
    "sex": true,
    "mobile": null,
    "email": null,
    "password": null,
    "salt": null,
    "identity": null,
    "companyId": null,
    "status": null,
    "qq": null
    },
    "teammateRe": null,
    "companyInfo": {
    "id": null,
    "version": null,
    "stateflag": null,
    "createAt": null,
    "updateAt": null,
    "remark": null,
    "companyName": "test",
    "credential": null,
    "licence": null,
    "legalPerson": null,
    "province": null,
    "city": null,
    "area": null,
    "address": null,
    "isAuth": null,
    "type": null
    },
    "fileList": [
    {
    "id": null,
    "version": null,
    "stateflag": null,
    "createAt": null,
    "updateAt": null,
    "remark": null,
    "pathFilename": "aaa.jpg",
    "showFilename": "p.jpg",
    "followUpMessageId": null
    }
    ]
    }
    ]
    }
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(FollowUpMessageQuery followUpMessageQuery) {
        if(followUpMessageQuery.getObjectId()==null||followUpMessageQuery.getObjectType()==null||followUpMessageQuery.getLiquidateStage()==null){
            return JsonResponseTool.paramErr("参数错误");
        }
        List<FollowUpMessage> list = followUpMessageService.listAndCancelUnread(followUpMessageQuery);
        return JsonResponseTool.success(list);
    }

    /**
     * @api {POST} http://{url}/follow_up/add 增加跟进信息,状态为未发送
     * @apiName add
     * @apiGroup followUp
     * @apiUse FollowUpMessageDTO
     * @apiSuccessExample {json} Data-Response:
    {
    "code": 2000,
    "msg": "成功",
    "data": {
    "id": null,
    "objectId": 111,
    "objectType": 111,
    "secondObjectId": 11,
    "secondObjectType": 11,
    "content": "11",
    "liquidateStage": 11,
    "secondLiquidateStage": 11,
    "fileList": [
    {
    "id": null,
    "version": null,
    "stateflag": null,
    "createAt": null,
    "updateAt": null,
    "remark": null,
    "pathFilename": "aaa.jpg",
    "showFilename": "p.jpg",
    "followUpMessageId": 18
    }
    ]
    }
    }
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(FollowUpMessageDTO followUpMessageDTO) throws IOException{
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
