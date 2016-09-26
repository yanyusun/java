package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class LenderApidoc {

    /**
     * @api {get} http://{url}/lender/getInit 获取初始化数据
     * @apiName getInit
     * @apiGroup lender
     * @apiSuccess {SelectonDTO} lenderType 借款人联系人类型
     * @apiSuccess {SelectonDTO} companyType 公司类型集合
     * @apiUse SelectonDTO
     */

    /**
     * @api {get} http://{url}/lender/list 获取借款人列表
     * @apiName list
     * @apiGroup lender
     * @apiParam {number} nav 具体的导航栏项目
     * @apiUse LenderListQuery
     * @apiSuccess {LenderListDTO} data 借款人列表信息
     * @apiUse LenderListDTO
     * @apiUse ContactDTO
     */

    /**
     * @api {post} http://{url}/lender/add 新增借款人
     * @apiName add
     * @apiGroup lender
     * @apiParam {LenderDTO} lenderDTO 借款人基础信息
     * @apiParam {ContactDTO} contactDTOList 联系人集合
     * @apiUse LenderDTO
     * @apiUse ContactDTO
     * @apiSuccess {number} data 增加后的数据ID
     */

    /**
     * @api {get} http://{url}/lender/delete 删除借款人
     * @apiName delete
     * @apiGroup lender
     * @apiParam {number} id 借款人Id
     */

    /**
     * @api {get} http://{url}/lender/update 修改借款人
     * @apiName update
     * @apiGroup lender
     * @apiParam {LenderDTO} lenderDTO 借款人基础信息
     * @apiParam {ContactDTO} contactDTOs 联系人集合
     * @apiUse LenderDTO
     * @apiUse ContactDTO
     * @apiSuccess {number} data 修改后的数据ID
     */

    /**
     * @api {get} http://{url}/lender/get 获取借款人信息
     * @apiName get
     * @apiGroup lender
     * @apiParam {number} id 借款人ID
     * @apiSuccess {LenderDTO} data 联系人信息
     * @apiUse LenderDTO
     */

    /**
     * @api {get} http://{url}/lender/getLenderAll 获取联系人所有相关信息
     * @apiName getLenderAll
     * @apiGroup lender
     * @apiParam {number} id 借款人ID
     * @apiSuccess {ContactDTO} contactDTOs 相关联系人信息
     * @apiSuccess {LenderDTO} lenderDTO 借款人基础信息
     * @apiSuccess {IouDTO} iouDTOs 借据信息
     * @apiSuccess {PawnDTO} pawnDTOs 抵押物信息
     * @apiUse ContactDTO
     * @apiUse LenderDTO
     * @apiUse IouDTO
     * @apiUse PawnDTO
     */

    /**
     * @apiDefine LenderListQuery
     * @apiParam {number} id 主键
     * @apiParam {number} entrustId 委托方
     * @apiParam {string} search 搜索(客户编号|姓名|电话|注释?)
     * @apiParam {string} urgeType 催收阶段
     * @apiParam {string} assetNo 资产包编号
     * @apiParam {number} belong 所属人
     * @apiParam {number} outDays N天以上未催收
     * @apiParam {number} isOutTime 逾期维护
     * @apiParam {number} canContact 可联系
     * @apiParam {number} isAssigned 已分配
     * @apiParam {number} isOwn 自己录入
     * @apiParam {number} isAsset 非资产包
     * @apiParam {number} passOut 转出
     * @apiParam {number} passIn 转入
     * @apiParam {number} isWorth 资不抵债
     * @apiParam {number} waitAssist 转协助的
     * @apiParam {number} assist 正在协助
     */

    /**
     * @apiDefine ContactDTO
     * @apiSuccessExample {json} ContactDTO:
     * {
     *     "id": 917,
     *     "name": "张飞",
     *     "type": 1,
     *     "idcard": "452722197507190776",
     *     "mobile": "13855552225",
     *     "mode": "11",
     *     "modeId": 245,
     *     "avg": null,
     *     "gender": 1,
     *     "company": "尴尬啊啊",
     *     "homeTel": "05714444222",
     *     "officeTel": "05713333222",
     *     "email": null,
     *     "province": "12",
     *     "city": "1202",
     *     "district": "120221",
     *     "address": "嘎嘎嘎",
     *     "memo": null,
     *     "code": null,
     *     "otherAddress": "[]"
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/ContactDTO.java
     */

}
