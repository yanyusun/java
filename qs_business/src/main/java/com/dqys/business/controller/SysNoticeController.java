package com.dqys.business.controller;

import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.business.orm.query.sysNotice.SysNoticeQuery;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.dto.sysNotice.SysNoticeDTO;
import com.dqys.business.service.service.sysNotice.SysNoticeService;
import com.dqys.business.service.utils.sysNotice.SysNoticeUtil;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-9-7.
 */
@RestController
@RequestMapping(value = "/sys_notice")
public class SysNoticeController extends BaseApiContorller {
    @Autowired
    private SysNoticeService sysNoticeService;

    /**
     * @api {GET} http://{url}/sys_notice/c_pageList 分页查询系统消息
     * @apiName c_pageList
     * @apiGroup sys_notice
     * @apiUse SysNoticeQuery
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "total": 2,
     * "data": [
     * {
     * "id": 3,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "title": "111",
     * "content": "1111",
     * "type": 0,
     * "picname": "3333",
     * "userId": 1
     * },
     * {
     * "id": 4,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "title": "111222",
     * "content": "111122",
     * "type": 0,
     * "picname": "333322",
     * "userId": 1
     * }
     * ]
     * }
     * }
     */

    @RequestMapping(value = "/c_pageList", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(SysNoticeQuery sysNoticeQuery) {
        if (sysNoticeQuery.getIsPaging()) {
            int usertype = UserServiceUtils.headerStringToInt(UserSession.getCurrent().getUserType());
            if (UserInfoEnum.USER_TYPE_ADMIN.getValue() == usertype) {//如果是平台方有权利看到没有发布的内容
                sysNoticeQuery.setIntroduce(false);
            }
            List<SysNotice> list=null;
            int total=0;
            if (sysNoticeQuery.getBeginDate() != null || sysNoticeQuery.getEndDate() != null || sysNoticeQuery.getSearchText() != null) {//有这些查询条件就不查缓存
                total = sysNoticeService.queryCountWithoutCache(sysNoticeQuery);
                list = sysNoticeService.listWithoutCache(sysNoticeQuery);
            } else {//用缓存
                total = sysNoticeService.queryCount(sysNoticeQuery);
                list = sysNoticeService.list(sysNoticeQuery);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("total", total);
            map.put("data", list);
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.paramErr("参数错误");
        }
    }

    /**
     * @api {GET} http://{url}/sys_notice/add 查询更进信息,并去除对应阶段的未读数据
     * @apiName add
     * @apiGroup sys_notice
     * @apiUse SysNoticeDTO
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "id": null,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "title": "11122266",
     * "content": "11112266",
     * "type": 1,
     * "picname": "33332266",
     * "userId": 1
     * }
     * }
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(SysNoticeDTO sysNoticeDTO) throws Exception {
        SysNotice sysNotice = SysNoticeUtil.toSysNotice(sysNoticeDTO);
        int result = sysNoticeService.insert(sysNotice);
        if (result == sysNoticeService.failNO) {
            return JsonResponseTool.failure("添加时保存图片失败");
        }
        return JsonResponseTool.success(sysNotice);
    }

    /**
     * @api {GET} http://{url}/sys_notice/update 查询更进信息,并去除对应阶段的未读数据
     * @apiName add
     * @apiGroup sys_notice
     * @apiUse SysNoticeDTO
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "id": null,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "title": "11122266",
     * "content": "11112266",
     * "type": 1,
     * "picname": "33332266",
     * "userId": 1
     * }
     * }
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(SysNoticeDTO sysNoticeDTO) throws Exception {
        SysNotice sysNotice = SysNoticeUtil.toSysNotice(sysNoticeDTO);
        sysNoticeService.updateByPrimaryKeySelective(sysNotice);
        return JsonResponseTool.success(sysNotice);
    }

    /**
     * @api {DELETE} http://{url}/sys_notice/del 查询更进信息,并去除对应阶段的未读数据
     * @apiName del
     * @apiGroup sys_notice
     * @apiParam {number} [id] id
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "id": null,
     * "version": null,
     * "stateflag": null,
     * "createAt": null,
     * "updateAt": null,
     * "remark": null,
     * "title": "11122266",
     * "content": "11112266",
     * "type": 1,
     * "picname": "33332266",
     * "userId": 1
     * }
     * }
     */
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse update(int id) {
        sysNoticeService.deleteByPrimaryKey(id);
        return JsonResponseTool.success(null);
    }

}

