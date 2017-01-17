package com.dqys.business.controller;

import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.dto.followUp.CFollowUpMessageDTO;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.dto.followUp.FollowUpSourceDTO;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.business.service.service.followUp.FollowUpSourceService;
import com.dqys.business.service.service.objectUserRelation.ObjectUserRelationService;
import com.dqys.business.service.utils.followUp.FollowUpUtil;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private ObjectUserRelationService objectUserRelationService;

    @Autowired
    private FollowUpSourceService followUpSourceService;



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
     * "id": 18,
     * "version": null,
     * "stateflag": null,
     * "createAt": "2016-09-22",
     * "updateAt": null,
     * "remark": null,
     * "objectId": null,
     * "objectType": null,
     * "userId": null,
     * "teamId": null,
     * "content": "11",
     * "secondObjectId": null,
     * "secondObjectType": null,
     * "liquidateStage": 11,
     * "secondLiquidateStage": null,
     * "sendStatus": null,
     * "userInfo": {
     * "id": null,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "userName": "zs",
     * "realName": null,
     * "account": null,
     * "wechat": null,
     * "avg": null,
     * "sex": true,
     * "mobile": null,
     * "email": null,
     * "password": null,
     * "salt": null,
     * "identity": null,
     * "companyId": null,
     * "status": null,
     * "qq": null
     * },
     * "teammateRe": null,
     * "companyInfo": {
     * "id": null,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "companyName": "test",
     * "credential": null,
     * "licence": null,
     * "legalPerson": null,
     * "province": null,
     * "city": null,
     * "area": null,
     * "address": null,
     * "isAuth": null,
     * "type": null
     * },
     * "fileList": [
     * {
     * "id": null,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "pathFilename": "aaa.jpg",
     * "showFilename": "p.jpg",
     * "followUpMessageId": null
     * }
     * ]
     * }
     * ]
     * }
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResponse list(FollowUpMessageQuery followUpMessageQuery) throws Exception {
        if (followUpMessageQuery.getObjectId() == null || followUpMessageQuery.getObjectType() == null || followUpMessageQuery.getLiquidateStage() == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        followUpMessageService.objectList(followUpMessageQuery);
        List<FollowUpMessage> list = followUpMessageService.listAndCancelUnread(followUpMessageQuery);
        return JsonResponseTool.success(list);
    }
    @RequestMapping(value = "c/list", method = RequestMethod.GET)
    public JsonResponse c_list(FollowUpMessageQuery followUpMessageQuery) throws Exception {
        if (followUpMessageQuery.getObjectId() == null || followUpMessageQuery.getObjectType() == null || followUpMessageQuery.getLiquidateStage() == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        List<FollowUpMessage> list = followUpMessageService.listAndCancelUnread(followUpMessageQuery);
        return JsonResponseTool.success(FollowUpUtil.toCFollowUpMessageDTOList(list));
    }

    @RequestMapping(value = "c/index", method = RequestMethod.GET)
    public JsonResponse c_Index() throws Exception {
       // List<FollowUpMessage> list = followUpMessageService.listAndCancelUnread(followUpMessageQuery);
        List<CFollowUpMessageDTO> cFollowUpMessageDTOs = new ArrayList<>();
        CFollowUpMessageDTO cFollowUpMessageDTO = new CFollowUpMessageDTO();
        cFollowUpMessageDTO.setId(1);
        cFollowUpMessageDTO.setCreateAt(new Date());
        cFollowUpMessageDTO.setObjectId(32);
        cFollowUpMessageDTO.setObjectType(11);
        cFollowUpMessageDTO.setSecondObjectId(3443);
        cFollowUpMessageDTO.setSecondObjectType(16);
        cFollowUpMessageDTO.setLiquidateStage(323);
        cFollowUpMessageDTO.setSecondLiquidateStage(323);

            cFollowUpMessageDTO.setUsername("esdsdsd");


            cFollowUpMessageDTO.setCompanyName("fdfdfd");

        cFollowUpMessageDTO.setContent("dsds");
        cFollowUpMessageDTOs.add(cFollowUpMessageDTO);
        return JsonResponseTool.success(cFollowUpMessageDTOs);
    }



//    @RequestMapping(value = "c/list", method = RequestMethod.GET)
//    @ResponseBody
//    public JsonResponse list(FollowUpMessageQuery followUpMessageQuery) throws Exception {
//        if (followUpMessageQuery.getObjectId() == null || followUpMessageQuery.getObjectType() == null || followUpMessageQuery.getLiquidateStage() == null) {
//            return JsonResponseTool.paramErr("参数错误");
//        }
//        List<FollowUpMessage> list = followUpMessageService.listAndCancelUnread(followUpMessageQuery);
//        return JsonResponseTool.success(list);
//    }


    /**
     * @api {GET} http://{url}/follow_up/unread_count 读取未读的数量
     * @apiName unread_count
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
    public JsonResponse unReadCount(int objectId, int objectType) {
        List<Map<String, String>> countMap = followUpReadStatusService.getCountMap(objectId, objectType);
        return JsonResponseTool.success(countMap);
    }


    /**
     * @api {POST} http://{url}/follow_up/add,http://{url}/follow_up/c/add "增加跟进信息,状态为未发送
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
     * "secondObjectId": 11,
     * "secondObjectType": 11,
     * "content": "11",
     * "liquidateStage": 11,
     * "secondLiquidateStage": 11,
     * "fileList": [
     * {
     * "id": null,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "pathFilename": "aaa.jpg",
     * "showFilename": "p.jpg",
     * "followUpMessageId": 18
     * }
     * ]
     * }
     * }
     */
    @RequestMapping(value = {"add", "c/add"}, method = RequestMethod.POST)
    public JsonResponse add(FollowUpMessageDTO followUpMessageDTO) throws IOException {
        if (followUpMessageDTO.getObjectId() == null || followUpMessageDTO.getObjectType() == null || followUpMessageDTO.getLiquidateStage() == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        UserSession userSession = UserSession.getCurrent();
        int userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        int userRole = UserServiceUtils.headerStringToInt(userSession.getRoleId());
        if (!UserServiceUtils.isPlatBoolean(userType, userRole)
                && !objectUserRelationService.hasOnlyOneRelation(followUpMessageDTO.getObjectType(), followUpMessageDTO.getObjectId(), userSession.getUserId())) {
            return JsonResponseTool.failure("对不起,您暂无该权限");
        }
        followUpMessageService.insert(followUpMessageDTO);
        return JsonResponseTool.success(followUpMessageDTO);
    }


    /**
     * @api {POST} http://{url}/follow_up/c/addSource 增加跟进资源
     * @apiName c_addSource
     * @apiGroup followUp
     * @apiUse FollowUpSourceDTO
     */
    @RequestMapping(value = "c/addSource", method = RequestMethod.POST)
    public JsonResponse addSource(FollowUpSourceDTO followUpSourceDTO) throws IOException {
        //// TODO: 17-1-12 权限验证
        UserSession userSession = UserSession.getCurrent();
        int userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        int userRole = UserServiceUtils.headerStringToInt(userSession.getRoleId());
        if (!UserServiceUtils.isPlatBoolean(userType, userRole)
                && !objectUserRelationService.hasOnlyOneRelation(followUpSourceDTO.getObjectType(), followUpSourceDTO.getObjectId(), userSession.getUserId())) {
            return JsonResponseTool.failure("对不起,您暂无该权限");
        }
        followUpSourceService.add(followUpSourceDTO);
        return JsonResponseTool.success(followUpSourceDTO);
    }

    /**
     * @api {GET} http://{url}/follow_up/c/sourceList 信息列表
     * @apiName c_addSource
     * @apiGroup followUp
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": [
     * {
     * "id": null,
     * "pathFilename": null,
     * "showFilename": "ttt",
     * "followUpMessageId": null,
     * "type": 10,
     * "objectType": null,
     * "objectId": null,
     *  "size":454mb,
     * "date":2015-12-15 12:30
     * },
     * {
     * "id": null,
     * "pathFilename": "xxxx.jpg",
     * "showFilename": "ttt",
     * "followUpMessageId": null,
     * "type": 20,
     * "objectType": null,
     * "objectId": null,
     * "size":454mb,
     * "date":2015-12-15 12:30
     * }
     * ]
     * }
     */
    @RequestMapping(value = "c/sourceList", method = RequestMethod.GET)
    public JsonResponse sourceList(@RequestParam(defaultValue = "0") Integer pid, Integer objectType, Integer objectId) throws IOException {
        UserSession userSession = UserSession.getCurrent();
        int userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        int userRole = UserServiceUtils.headerStringToInt(userSession.getRoleId());
        if (!UserServiceUtils.isPlatBoolean(userType, userRole)
                && !objectUserRelationService.hasOnlyOneRelation(objectType, objectId, userSession.getUserId())) {
            return JsonResponseTool.failure("对不起,您暂无该权限");
        }
        List<FollowUpSourceDTO> list = followUpSourceService.listByPid(pid, objectType, objectId);
        return JsonResponseTool.success(list);
    }


}
