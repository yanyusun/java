package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
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
public class UserBusTotalController {

    @Autowired
    private UserBusTotalService userBusTotalService;

    /**
     * 查看特定类型的公司
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public JsonResponse t() {
        UserBusTotalQuery query = new UserBusTotalQuery();
        List<UserBusTotal> list=userBusTotalService.list(query);
        return JsonResponseTool.success(list);
    }
}
