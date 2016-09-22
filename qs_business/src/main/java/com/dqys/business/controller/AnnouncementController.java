package com.dqys.business.controller;

import com.dqys.business.orm.pojo.common.Announcement;
import com.dqys.business.service.service.common.AnnouncementService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/9/22.
 */
@RestController
@RequestMapping(value = "/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService amService;

    /**
     * @api {get} http://{url}/announcement/get 根据ID获取公告
     * @apiName get
     * @apiGroup announcement
     * @apiParam {number} id id
     * @apiSuccess {Announcement} data 参考：http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/Announcement.java
     */
    @RequestMapping(value = "/get")
    public JsonResponse get(Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(amService.get(id));
    }

    /**
     * @api {post} http://{url}/announcement/add 添加公告
     * @apiName add
     * @apiGroup announcement
     * @apiParam {Announcement} data 参考：http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/Announcement.java
     * @apiSuccess {number} data id
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResponse add(Announcement announcement){
        if(CommonUtil.checkParam(announcement, announcement.getContent(), announcement.getIds(),
                announcement.getMark())){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(amService.insert(announcement));
    }

    /**
     * @api {get} http://{url}/announcement/delete 删除公告
     * @apiName delete
     * @apiGroup announcement
     * @apiParam {number} id id
     */
    @RequestMapping(value = "/delete")
    public JsonResponse delete(Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(amService.delete(id));
    }

    /**
     * @api {get} http://{url}/announcement/list 获取当前用户所有公告
     * @apiName list
     * @apiGroup announcement
     * @apiSuccess {Announcement} data 参考：http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/Announcement.java
     */
    @RequestMapping(value = "/list")
    public JsonResponse list(){
        return CommonUtil.responseBack(amService.listByUser(UserSession.getCurrent().getUserId()));
    }
}
