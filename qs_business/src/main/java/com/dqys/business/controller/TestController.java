package com.dqys.business.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RabbitMQProducerTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pan on 16-8-5.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/r")
    @ResponseBody
    public JsonResponse uploadImg(){
        RabbitMQProducerTool.addToMailSendQueue("342088816@qq.com","232qqqqq");
            return JsonResponseTool.success("!!!");

    }
}
