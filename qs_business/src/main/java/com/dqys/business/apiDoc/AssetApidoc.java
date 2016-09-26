package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class AssetApidoc {

    /**
     * @api {get} /asset/getInit 获取初始化数据
     * @apiName getInit
     * @apiGroup asset
     * @apiSuccess {SelectonDTO} assetType 资产包类型
     * @apiSuccess {SelectonDTO} excellent 评优
     * @apiSuccess {string} level 评级
     * @apiUse SelectonDTO
     */

    /**
     * @api {POST} /asset/add 添加资产包
     * @apiName add
     * @apiGroup asset
     * @apiUse Asset
     * @apiSuccess {number} data 新增的数据ID
     */

    /**
     * @api {get} /asset/delete 删除资产包
     * @apiName delete
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     */

    /**
     * @api {POST} /asset/update 修改资产包
     * @apiName update
     * @apiGroup asset
     * @apiUse Asset
     * @apiSuccess {number} data 修改后的数据ID
     */

    /**
     * @api {get} /asset/get 获取资产包
     * @apiName get
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     * @apiSuccess {AssetDTO} data 资产包信息
     * @apiUse AssetDTO
     */

    /**
     * @api {get} /asset/listLenderSelect 获取资产包下联系人下拉
     * @apiName listLenderSelect
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     * @apiSuccess {SelectonDTO} data 借款人信息
     * @apiUse SelectonDTO
     */

    /**
     * @api {get} /asset/excelIn excel导入资产包的借款人
     * @apiName excelIn
     * @apiGroup asset
     * @apiParam {number} id 公司ID
     * @apiParam {string} file excel文件
     */

    /**
     * @api {get} /asset/listLender 查询资产包借款人
     * @apiName listLender
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     * @apiSuccess {LenderListDTO} data 借款人相关信息
     * @apiUse LenderListDTO
     */

    /**
     * @api {get} /asset/list 获取资产包列表
     * @apiName list
     * @apiGroup asset
     * @apiParam {number} nav 子导航栏项目
     * @apiUse AssetListQuery
     * @apiUse tabEnum
     * @apiSuccess {AssetDTO} data 资产包信息
     * @apiUse AssetDTO
     */

    /**
     * @api {get} /asset/addLender 添加资产包借款人(整合版)
     * @apiName addLender
     * @apiGroup asset
     * @apiParam {number} id  资产包ID
     * @apiParam {LenderDTO} lenderDTO 借款人基础信息
     * @apiParam {ContactDTO} contactDTOList 联系人集合
     * @apiParam {PawnDTO} pawnDTOList 抵押物集合
     * @apiParam {IouDTO} iouDTOList 借据集合
     * @apiUse Iou
     * @apiUse Pawn
     * @apiUse LenderDTO
     * @apiUse ContactDTO
     */

    /**
     * @apiDefine Asset
     * @apiParam {number} id 主键
     * @apiParam {number} type 资产包类型
     * @apiParam {date} startAt 委托开始时间
     * @apiParam {date} endAt 委托结束时间
     * @apiParam {string} assetNo 资产包编号
     * @apiParam {number} accrual 总利息
     * @apiParam {number} loan 总贷款
     * @apiParam {number} appraisal 总评估
     * @apiParam {string} name 名臣
     * @apiParam {string} evaluateExcellent 评优
     * @apiParam {string} evaluateLevel 评级
     * @apiParam {number} province 省份
     * @apiParam {number} city 城市
     * @apiParam {number} district 区域
     * @apiParam {string} address 详细地址
     * @apiParam {string} loanOrganization 贷款机构
     * @apiParam {string} loanOrganizationDistrict 贷款机构行政区域
     * @apiParam {string} disposeMode 处置方式
     * @apiParam {string} tags 标签
     * @apiParam {number} isshow 是否展示
     * @apiParam {string} memo 备注
     * @apiParam {number} operatorId 操作人ID
     */

    /**
     * @apiDefine AssetDTO
     * @apiSuccessExample {json} AssetDTO:
     * {
     * "id": 54,
     * "type": null,
     * "startAt": null,
     * "endAt": null,
     * "assetNo": "QS1607072449",
     * "accrual": null,
     * "loan": null,
     * "appraisal": null,
     * "name": null,
     * "evaluateExcellent": null,
     * "evaluateLevel": null,
     * "province": null,
     * "city": null,
     * "district": null,
     * "address": null,
     * "loanOrganization": null,
     * "loanOrganizationDistrict": null,
     * "disposeMode": null,
     * "tags": null,
     * "isshow": 0,
     * "memo": null,
     * "operatorId": 0,
     * "operator": null
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/AssetDTO.java
     */

    /**
     * @apiDefine LenderListDTO
     * @apiSuccessExample {json} lenderListDTO:
     * {
     *     "id": 235,
     *     "name": "借款人",
     *     "type": "借款人",
     *     "born": "类型-1",
     *     "assetName": "委托方资产包",
     *     "excellent": "非常好",
     *     "level": "B",
     *     "dispose": "处置方式",
     *     "tag": null,
     *     "isLawsuit": "是",
     *     "guaranteeType": null,
     *     "guaranteeMode": null,
     *     "guaranteeSource": null,
     *     "guaranteeEconomic": null,
     *     "guaranteeContact": "是",
     *     "isWorth": "是",
     *     "isLawsuit": "是",
     *     "isDecision": "是",
     *     "realUrgeTime": 1,
     *     "phoneUrgeTime": null,
     *     "entrustTime": null,
     *     "canContact": "是",
     *     "canPay": "是",
     *     "memo": null
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderListDTO.java
     */

    /**
     * @apiDefine AssetListQuery
     * @apiParam {number} [type] 资产包类型
     * @apiParam {number} [areaId] 行政区域ID
     * @apiParam {number} [operator] 操作人Id
     * @apiParam {boolean} [isOwn] 是否选择自己录入选项
     * @apiParam {number} [companyId] 贷款公司Id
     * @apiParam {string} [code] 编号
     * @apiParam {date} [startAt] 开始时间
     * @apiParam {date} [endAt] 结束时间
     */
}
