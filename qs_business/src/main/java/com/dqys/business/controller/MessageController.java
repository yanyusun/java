package com.dqys.business.controller;

import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageQuery;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.constant.UserStatusTypeEnum;
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
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public JsonResponse messageList(@ModelAttribute MessageQuery messageQuery) {
        Message message = MessageUtils.transToMessage(messageQuery);
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String roleId = UserSession.getCurrent() == null ? "0" : UserSession.getCurrent().getRoleId();
        if (roleId.indexOf(UserInfoEnum.USER_TYPE_ADMIN.getValue().toString()) != -1) {
            message.setReceiveId(userId);
        }
        List<Message> list = messageService.selectByMessage(message);
        if (list == null) {
            return JsonResponseTool.noData();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("list", MessageUtils.transToMessageDTO(list));
            Message sage = new Message();
//            map.put("total", messageService.selectCount(sage));//总共的记录数目
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
            sage.setType(message.getType());
            sage.setStatus(message.getStatus());
            map.put("listCount", messageService.selectCount(sage));//条件查询消息数量
            return JsonResponseTool.success(map);
        }
    }

    /**
     * @api {post} message/read 标记为已读(单个或批量)
     * @apiParam {int[]} id  消息id
     * @apiDescription 标记为已读(单个或批量)
     * @apiSampleRequest message/read
     * @apiGroup Message
     * @apiName message/read
     */
    @RequestMapping("/read")
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


}
