package com.dqys.business.controller;

import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.core.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yan on 16-8-12.
 */
@RestController
@RequestMapping(value = "/follow_up")
public class FollowUpController {
    @Autowired
    private FollowUpMessageService followUpMessageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list() {
        return null;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse add() {
        followUpMessageService.insert()
        return null;
    }

    @RequestMapping(value = "/unread_count", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse unReadCount() {
        return null;
    }
}
