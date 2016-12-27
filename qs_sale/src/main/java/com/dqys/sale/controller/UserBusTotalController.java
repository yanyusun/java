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
     * @api {GET} http://{url}/UserBusTotal/get 读取未读的数量
     * @apiName get
     * @apiGroup UserBusTotal
     * @apiSuccessExample {json} Data-Response:
        {
        "code": 2000,
        "msg": "成功",
        "data": {
        "hasPublish": 1,
        "onCollection": 1,
        "onBusiness": 1,
        "id": 1,
        "userId": 3
        }
        }
     */
    @RequestMapping(value = "/get")
    public JsonResponse getByUserId() throws UserBusTotalException {
        Integer userId = UserSession.getCurrent().getUserId();
        return JsonResponseTool.success(userBusTotalService.getByUserId(userId));
    }


}
