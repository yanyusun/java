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
     * 根据ID获取公告
     * @param id 数据ID
     * @return
     */
    @RequestMapping(value = "/get")
    public JsonResponse get(Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(amService.get(id));
    }

    /**
     * 添加公告
     * @param announcement 公告对象
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResponse add(Announcement announcement){
        if(CommonUtil.checkParam(announcement, announcement.getContent(), announcement.getIds(),
                announcement.getCover(), announcement.getIsCover())){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(amService.insert(announcement));
    }

    /**
     * 删除公告
     * @param id 数据ID
     * @return
     */
    @RequestMapping(value = "/delete")
    public JsonResponse delete(Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(amService.delete(id));
    }

    /**
     * 获取当前用户所有公告
     * @return
     */
    @RequestMapping(value = "/list")
    public JsonResponse list(){
        return CommonUtil.responseBack(amService.listByUser(UserSession.getCurrent().getUserId()));
    }
}
