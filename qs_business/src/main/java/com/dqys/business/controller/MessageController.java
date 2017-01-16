package com.dqys.business.controller;

import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageQuery;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息管理
 * Created by mkfeng on 2016/7/8.
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * @api {post} message/pageList 消息列表
     * @apiParam {int} page 分页数
     * @apiParam {int} pageCount 显示数目
     * @apiParam {int} type 类型 (0, "任务消息"),(1, "产品消息"), (2, "安全消息"),(3, "服务消息"),
     * @apiParam {int} status 状态（0未读，1已读）
     * @apiDescription 查询消息列表信息
     * @apiSampleRequest message/pageList
     * @apiGroup Message
     * @apiName message/pageList
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "totalMes": 28,//全部未读消息数
     * "productMes": 0,//产品未读消息数
     * "safetyMes": 0,//安全未读消息数"
     * "taskMes": 15,//任务未读消息数"
     * "list": [
     * {
     * "id": 64,
     * "title": "借款人null公司内部邀请",//标题
     * "contant": "协作器邀请验证消息",//内容
     * "sendTime": "2016-09-23",//发送时间
     * "typeName": "任务消息",// 消息名称
     * "status": 1,//状态（0未读1已读）
     * "label": "任务消息",//标签
     * "businessType": 0,// 业务类型
     * "operUrl": "{\"distribution\":\"\",\"accept\":\"/coordinator/isAccept?status=1&teammateId=23\",\"acceptRequestType\":\"get\",\"reject\":\"/coordinator/isAccept?status=2&teammateId=23\",\"rejectRequestType\":\"get\"}"//json格式操作地址
     * }
     * ],
     * "listCount": 50,//条件查询消息数量（分页使用）
     * "serveMes": 13 //服务未读消息数"
     * }
     * }
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public JsonResponse messageList(@ModelAttribute MessageQuery messageQuery) {
        Message message = MessageUtils.transToMessage(messageQuery);
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String userType = UserSession.getCurrent() == null ? "0" : UserSession.getCurrent().getUserType();
//        if (!userType.equals(UserInfoEnum.USER_TYPE_ADMIN.getValue() + ",")) {//不是平台管理员，只能看自己的信息
        message.setReceiveId(userId);
//        }
        List<Message> list = messageService.selectByMessage(message);
        if (list == null) {
            return JsonResponseTool.noData();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("list", MessageUtils.transToMessageDTO(list));
            Message sage = new Message();
//            map.put("total", messageService.selectCount(sage));//总共的记录数目
            sage.setReceiveId(message.getReceiveId());
            sage.setStatus(0);//标记的未读消息
            map.put("totalMes", messageService.selectCount(sage));//全部未读消息数
            sage.setType(MessageEnum.PRODUCT.getValue());
            map.put("productMes", messageService.selectCount(sage));//"产品未读消息数
            sage.setType(MessageEnum.SAFETY.getValue());
            map.put("safetyMes", messageService.selectCount(sage));//"安全未读消息数"
            sage.setType(MessageEnum.SERVE.getValue());
            map.put("serveMes", messageService.selectCount(sage));//"服务未读消息数"
            sage.setType(MessageEnum.TASK.getValue());
            map.put("taskMes", messageService.selectCount(sage));//"任务未读消息数"
            map.put("listCount", messageService.selectCount(message));//条件查询消息数量
            return JsonResponseTool.success(map);
        }
    }

    /**
     * 消息列表（C端）
     *
     * @param messageQuery
     * @return
     */
    @RequestMapping("/c/pageList")
    @ResponseBody
    public JsonResponse messageListC(@ModelAttribute MessageQuery messageQuery) {
        Message message = MessageUtils.transToMessage(messageQuery);
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        message.setReceiveId(userId);
        Map<String, Object> map = new HashMap<>();
        List<Message> list = messageService.selectByMessage(message);
        List<Map> messList = new ArrayList<>();
        for (Message mess : list) {
            Map messMap = new HashMap<>();
            messMap.put("type", mess.getType());
            messMap.put("typeName", MessageEnum.getEnumByValue(mess.getType()));
            messMap.put("title", mess.getType());
            messMap.put("time", mess.getSendTime());
            messMap.put("status", mess.getStatus());
            messList.add(messMap);
        }
        map.put("list", messList);
        map.put("count", messageService.selectCount(message));//条件查询消息数量
        return JsonResponseTool.success(map);
    }

    /**
     * 消息详情（C端）
     *
     * @param id
     * @return
     */
    @RequestMapping("/c/get")
    @ResponseBody
    public JsonResponse get(@RequestParam Integer id) {
        Message message = messageService.get(id);
        if (message == null) {
            return JsonResponseTool.failure("查不到详情信息");
        }
        return JsonResponseTool.success(message);
    }

    /**
     * @api {post} message/read 标记为已读(单个或批量)
     * @apiParam {int[]} id  消息id
     * @apiDescription 标记为已读(单个或批量)
     * @apiSampleRequest message/read
     * @apiGroup Message
     * @apiName message/read
     */
    @RequestMapping({"/read", "/c/read"})
    @ResponseBody
    public JsonResponse readMessage(@RequestParam("id") Integer... id) {
        Integer result = messageService.readMessage(id);
        if (result == 0) {
            return JsonResponseTool.failure("修改失败");
        } else {
            return JsonResponseTool.success(result);
        }
    }

    /**
     * @api {post} message/del 批量删除
     * @apiParam {int[]} ids 消息id
     * @apiDescription 批量删除
     * @apiSampleRequest message/del
     * @apiGroup Message
     * @apiName message/del
     */
    @RequestMapping("/del")
    @ResponseBody
    public JsonResponse del(@RequestParam("ids") Integer[] ids) {
        Integer result = messageService.del(ids);
        if (result == 0) {
            return JsonResponseTool.failure("删除失败");
        } else {
            return JsonResponseTool.success(result);
        }
    }

    /**
     * @api {post} message/setOper 修改操作状态
     * @apiParam {int} id 消息id
     * @apiParam {int} status 操作状态（0默认未操作1同意2拒绝）
     * @apiSampleRequest message/setOper
     * @apiGroup Message
     * @apiName message/setOper
     */
    @RequestMapping("/setOper")
    @ResponseBody
    public JsonResponse setOper(@RequestParam("id") Integer id, @RequestParam Integer status) {
        if (status != 0 && status != 1 && status != 2) {
            return JsonResponseTool.paramErr("操作状态有误");
        }
        Map map = messageService.setOper(id, status);
        if ("yes".equals(MessageUtils.transMapToString(map, "result"))) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} message/getCount 消息数量
     * @apiParam {int} [type]  消息类型(0, "任务消息"),(1, "产品消息"), (2, "安全消息"),(3, "服务消息"),
     * @apiParam {int} [status]  消息状态（0未读1已读）
     * @apiSampleRequest message/getCount
     * @apiGroup Message
     * @apiName message/getCount
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public JsonResponse getCount(Integer type, Integer status) {
        Message message = new Message();
        message.setStatus(status);
        message.setType(type);
        message.setReceiveId(UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId());
        return JsonResponseTool.success(messageService.selectCount(message));
    }


}
