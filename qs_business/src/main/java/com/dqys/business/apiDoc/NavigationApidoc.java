package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class NavigationApidoc {

    /**
     * @api {POST} http://{url}/nav/add 增加导航栏
     * @apiName add
     * @apiGroup navigation
     * @apiUse Navigation
     * @apiSuccess {number} data 新增key
     */

    /**
     * @api {POST} http://{url}/nav/delete 删除导航栏
     * @apiName delete
     * @apiGroup navigation
     * @apiParam {number} id 导航栏key
     */

    /**
     * @api {POST} http://{url}/nav/update 修改导航栏
     * @apiName update
     * @apiGroup navigation
     * @apiUse Navigation
     * @apiSuccess {number} data 修改后的数据ID
     */

    /**
     * @api {get} http://{url}/nav/get 获取导航栏
     * @apiName get
     * @apiGroup navigation
     * @apiParam {number} id 导航栏KEY
     * @apiSuccess {NavigationDTO} data 导航栏数据
     * @apiUse NavigationDTO
     */

    /**
     * @api {GET} http://{url}/nav/getTop 获取初始导航栏
     * @apiName getTop
     * @apiGroup navigation
     * @apiSuccess {NavigationDTO} data 导航栏数据
     * @apiUse NavigationDTO
     */

    /**
     * @api {GET} http://{url}/nav/listById 根据导航栏key获取子导航栏
     * @apiName listById
     * @apiGroup navigation
     * @apiParam {number} id 导航栏key
     * @apiSuccess {NavigationDTO} data 导航栏数据
     * @apiUse NavigationDTO
     */

    /**
     * @apiDefine Navigation
     * @apiParam {number} id 主键key
     * @apiParam {string} value 地址值path
     * @apiParam {String} name 名称value
     * @apiParam {number} [pid] 父级key
     * @apiParam {number} [manager] 管理员(1为可视,下同)
     * @apiParam {number} [governor] 管理者
     * @apiParam {number} [employee] 普通员工
     * @apiParam {number} [platform] 平台
     * @apiParam {number} [personal] 个人
     * @apiParam {number} [entrust] 委托
     * @apiParam {number} [collection] 催收
     * @apiParam {number} [agent] 中介
     * @apiParam {number} [law] 律所
     * @apiParam {number} [sort] 排序(数值越大越优先)
     */

    /**
     * @apiDefine NavigationDTO
     * @apiSuccessExample {json} NavigationDTO:
     * {
     *     "key": 13,
     *     "value": "操作日志",
     *     "path": "/memo",
     *     "icon": "",
     *     "child": true,
     *     "group": null
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/NavigationDTO.java
     */



}
