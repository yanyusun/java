package com.dqys.sale.controller;

import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import com.dqys.sale.service.exception.bean.UserBusTotalException;
import com.dqys.sale.service.facade.UserBusTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pan on 16-12-21.
 */
@RestController
@RequestMapping(value = "/UserBusTotal")
public class UserBusTotalController extends BaseApiContorller {

    @Autowired
    private UserBusTotalService userBusTotalService;

    /**
     * 用户统计信息列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public JsonResponse list() {
        UserBusTotalQuery query = new UserBusTotalQuery();
        List<UserBusTotal> list=userBusTotalService.list(query);
        return JsonResponseTool.success(list);
    }

    /**
     * @api {GET} http://{url}/UserBusTotal/get 用户业务信息统计
     * @apiName get
     * @apiGroup UserBusTotal
     * @apiSuccessExample {json} Data-Response:
        {
        "code": 2000,
        "msg": "成功",
        "data": {
        "hasPublish": 1,//已发布数量
        "onCollection": 1,已收藏数量
        "onBusiness": 1,处置中数量
        "id": 1,
        "userId": 3 用户id
        }
        }
     */
    @RequestMapping(value = "/get")
    public JsonResponse getByUserId() throws UserBusTotalException {
        Integer userId = UserSession.getCurrent().getUserId();
        return JsonResponseTool.success(userBusTotalService.getByUserId(userId));
    }


}
