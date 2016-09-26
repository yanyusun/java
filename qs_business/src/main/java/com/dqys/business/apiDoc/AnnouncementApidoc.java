package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class AnnouncementApidoc {

    /**
     * @api {get} /announcement/get 根据ID获取公告
     * @apiName get
     * @apiGroup announcement
     * @apiParam {number} id 数据id
     * @apiSuccess {AnnouncementDTO} data 公告内容
     * @apiUse AnnouncementDTO
     */

    /**
     * @api {post} /announcement/add 添加公告
     * @apiName add
     * @apiGroup announcement
     * @apiUse Announcement
     * @apiSuccess {number} data 添加后的数据id
     */

    /**
     * @api {get} /announcement/delete 删除公告
     * @apiName delete
     * @apiGroup announcement
     * @apiParam {number} id 公告id
     * @apiSuccess {number} data 返回删除条数
     */

    /**
     * @api {get} /announcement/list 获取当前用户所有公告
     * @apiName list
     * @apiGroup announcement
     * @apiSuccess {AnnouncementDTO} data 公告集合
     * @apiUse AnnouncementDTO
     */


    /**
     * @apiDefine AnnouncementDTO
     * @apiSuccessExample {json} AnnouncementDTO
     * {
     *     "id": 1,
     *     "ids": "12,18,229",
     *     "content": "测试正文内容",
     *     "cover": "/asd/asd.img",
     *     "isCover": 0,
     *     "mark": "摘要",
     *     "createAt": "2016-09-26",
     *     "stateFlag": 0
     * }
     * git: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/Announcement.java
     */

    /**
     * @apiDefine Announcement
     * @apiParam {number} [id] 数据id
     * @apiParam {string} ids 被授权人Id集合，“,”分隔
     * @apiParam {string} content 富文本内容
     * @apiParam {string} cover 封面图片路径
     * @apiParam {number} isCover 封面图片是否显示在正文中(1显示，0不显示)
     * @apiParam {string} [mark] 摘要
     */

}
