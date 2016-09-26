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
     * @api {GET} http://{url}/nav/getInit 获取初始导航栏
     * @apiName getInit
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



}
