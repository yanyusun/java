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

    /**
     * @apiDefine Lender
     * @apiParam {number} id 主键
     * @apiParam {number} [assetId] 资产包ID,只有是资产包借款人时才有
     * @apiParam {date} startAt 委托开始时间
     * @apiParam {date} endAt 委托结束时间
     * @apiParam {number} accrual 总利息
     * @apiParam {number} loan 总贷款
     * @apiParam {number} appraisal 总评估
     * @apiParam {string} loanType 贷款类型
     * @apiParam {string} loanMode 贷款方式
     * @apiParam {string} loanName 贷款名称
     * @apiParam {string} evaluateExcellent 评优
     * @apiParam {string} evaluateLevel 评级
     * @apiParam {string} disposeMode 处置方式
     * @apiParam {string} tags 标签
     * @apiParam {string} urgeType 催收阶段
     * @apiParam {string} entrustBornType 委托来源类型
     * @apiParam {number} entrustBorn 委托来源公司ID
     * @apiParam {string} guaranteeType 担保类型
     * @apiParam {string} guaranteeMode 担保方式
     * @apiParam {string} guaranteeSource 担保资源
     * @apiParam {number} isGuaranteeConnection 担保方是否能联系
     * @apiParam {string} guaranteeEconomic 担保方经济情况
     * @apiParam {number} isLawsuit 是否诉讼
     * @apiParam {number} isDecision 是否判决
     * @apiParam {number} realUrgeTime 实地催收次数
     * @apiParam {number} phoneUrgeTime 电话催收次数
     * @apiParam {number} entrustUrgeTime 委托催收次数
     * @apiParam {number} canContact 债务方是否能联系
     * @apiParam {number} canPay 债务方是否能偿还
     * @apiParam {number} isWorth 抵押物是否覆盖债务
     * @apiParam {string} memo 备注
     * @apiParam {number} operatorId 操作人ID
     */

    /**
     * @apiDefine LenderDTO
     * @apiSuccessExample {json} lenderDTO:
     * {
     * "id": 1,
     * "assetId": 1,
     * "startAt": "2016-07-06",
     * "endAt": "2016-09-06",
     * "accrual": null,
     * "loan": null,
     * "appraisal": null,
     * "loanType": null,
     * "loanMode": null,
     * "loanName": null,
     * "evaluateExcellent": null,
     * "evaluateLevel": null,
     * "disposeMode": null,
     * "tags": null,
     * "urgeType": null,
     * "entrustBornType": null,
     * "entrustBorn": null,
     * "guaranteeType": null,
     * "guaranteeMode": null,
     * "guaranteeSource": null,
     * "isGuaranteeConnection": null,
     * "guarenteeEconomic": null,
     * "isLawsuit": null,
     * "isDecision": null,
     * "realUrgeTime": null,
     * "phoneUrgeTime": null,
     * "entrustUrgeTime": null,
     * "canContact": null,
     * "canPay": null,
     * "isWorth": null,
     * "memo": null,
     * "operatorId": 0,
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderDTO.java
     */

}
