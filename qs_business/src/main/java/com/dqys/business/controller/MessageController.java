package com.dqys.business.controller;

import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.service.service.MessageService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
     * @apiDescription 查询消息列表信息
     * @apiSampleRequest message/pageList
     * @apiGroup Message
     * @apiName message/pageList
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public JsonResponse messageList(Message message) {
        List<Message> list = messageService.selectByMessage(message);
        if (list == null) {
            return JsonResponseTool.noData();
        } else {
            return JsonResponseTool.success(list);
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
    public JsonResponse readMessage(Integer... id) {

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
