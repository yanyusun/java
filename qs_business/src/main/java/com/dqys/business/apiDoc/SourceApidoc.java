package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SourceApidoc {

    /**
     * @api {get} http://{url}/source/listNavigation 分类列表
     * @apiName listNavigation
     * @apiGroup source
     * @apiParam {number} lenderId 借款人ID
     * @apiParam {number} type 实勘1|证件合同0(默认)
     * @apiSuccess {SelectonDTOList} data 分类详细信息
     * @apiUse SelectonDTOList
     */

    /**
     * @api {post} http://{url}/source/addNavigation 增加分类
     * @apiName addNavigation
     * @apiGroup source
     * @apiUse SourceNavigation
     * @apiParam {number} data 新增后的数据ID
     */

    /**
     * @api {get} http://{url}/source/deleteNavigation 删除分类
     * @apiName deleteNavigation
     * @apiGroup source
     * @apiParam {number} id 分类ID
     */

    /**
     * @api {post} http://{url}/source/add 增加资源
     * @apiName add
     * @apiGroup source
     * @apiParam {object} sourceInfoDTO
     * @apiSuccess {number} data 新增的ID
     */

    /**
     * @api {get} http://{url}/source/get 获取资源信息
     * @apiName get
     * @apiGroup source
     * @apiParam {number} lenderId 借款人Id
     * @apiParam {number} navId 分类ID
     * @apiSuccess {SourceInfoDTO} data 资源信息
     * @apiUse SourceInfoDTO
     * @apiUse SourceDTO
     */

    /**
     * @api {post} http://{url}/source/update 修改资源信息
     * @apiName update
     * @apiGroup source
     * @apiUse SourceInfo
     * @apiSuccess {number} data 修改后的数据id
     */

    /**
     * @apiDefine SourceNavigation
     * @apiParam {number} id 数据id
     * @apiParam {string} name 导航名称
     * @apiParam {number} pid 上层目录ID
     * @apiParam {number} lenderId 默认(0),传值时表示特殊分类,该借款人独有
     * @apiParam {number} type // 实勘1|证件合同0(默认)
     * 参考git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/SourceNavigation.java
     */

    /**
     * @apiDefine SourceInfo
     * @apiParam {number} [id] 数据ID
     * @apiParam {number} navId 分类ID
     * @apiParam {number} lenderId 借款人Id
     * @apiParam {string} code 文件编号
     * @apiParam {string} userIds 可视人员ID的集合，“,”隔开
     * @apiParam {number} isshow 是否展示外网(1是0否)
     * @apiParam {number} watermark 水印(1有0没有)
     * @apiParam {number} open 公开(1公开0不公开)
     * @apiParam {string} memo 详情信息
     * @apiParam {SourceDTO} sourceDTOList 保存的文件信息
     * git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/common/SourceInfoDTO.java
     */

    /**
     * @apiDefine SourceInfoDTO
     * @apiSuccessExample {json} SourceDTO:
     * {
     *     id:1,
     *     navId:1,
     *     lenderId:1,
     *     code:"asdasdasd",
     *     userIds:"1,2,3",
     *     isshow:1,
     *     watermark:1,
     *     open:1,
     *     memo:null,
     *     sourceDTOList:[]
     * }
     * git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/common/SourceInfoDTO.java
     */

    /**
     * @apiDefine SourceDTO
     * @apiSuccessExample {json} SourceDTO:
     * {
     *     id:1,
     *     sourceId:1,
     *     fileName:"/asd/asd/a.png",
     *     name:null,
     *     memo:null
     * }
     * git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/common/SourceDTO.java
     */



}
