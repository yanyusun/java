package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class PawnApidoc {

    /**
     * @api {get} http://{url}/pawn/delete 删除抵押物
     * @apiName delete
     * @apiGroup pawn
     * @apiParam {number} id 抵押物ID
     */

    /**
     * @api {post} http://{url}/pawn/add 增加抵押物信息
     * @apiName add
     * @apiGroup pawn
     * @apiUse Pawn
     * @apiSuccess {number} data 增加后的数据ID
     */

    /**
     * @api {post} http://{url}/pawn/listAdd 增加抵押物信息(多条)
     * @apiName listAdd
     * @apiGroup pawn
     * @apiParam {objectList} pawnDTOList 参考增加抵押物信息
     */

    /**
     * @api {post} http://{url}/pawn/update 修改抵押物信息
     * @apiName update
     * @apiGroup pawn
     * @apiUse Pawn
     * @apiSuccess {number} data 修改后的数据ID
     */

    /**
     * @api {post} http://{url}/pawn/get 获取抵押物信息
     * @apiName get
     * @apiGroup pawn
     * @apiParam {number} id 抵押物ID
     * @apiSuccess {PawnDTO} data 抵押物信息
     * @apiUse PawnDTO
     */

    /**
     * @api {post} http://{url}/iou/listPawn 获取借款人的抵押物信息
     * @apiName listPawn
     * @apiGroup iou
     * @apiParam {number} id 借款人ID
     * @apiSuccess {PawnDTO} data 抵押物信息
     * @apiUse PawnDTO
     */

    /**
     * @apiDefine Pawn
     * @apiParam {number} id 主键
     * @apiParam {number} lenderId 借款人ID
     * @apiParam {string} pawnNo 抵押物编号
     * @apiParam {string} pawnName 抵押物名称
     * @apiParam {number} amount 贷款金额
     * @apiParam {string} type 抵押物类型
     * @apiParam {string} evaluateExcellent 评优
     * @apiParam {string} evaluateLevel 评级
     * @apiParam {string} size 规模大小
     * @apiParam {number} province 省份
     * @apiParam {number} city 城市
     * @apiParam {number} district 区域
     * @apiParam {string} address 详细地址
     * @apiParam {number} pawnRate 抵押率
     * @apiParam {string} disposeStatus 处置状态
     * @apiParam {number} worth 价值
     * @apiParam {string} memo 备注
     * @apiParam {string} iouIds 借据Id集合
     */

    /**
     * @apiDefine PawnDTO
     * @apiSuccessExample {json} PawnDTO:
     * {
     *     "id": 843,
     *     "pawnNo": "CO16090004",
     *     "pawnName": "抵押物A",
     *     "amount": 22,
     *     "type": "1",
     *     "evaluateExcellent": "1",
     *     "evaluateLevel": "A",
     *     "size": "0",
     *     "province": 14,
     *     "city": 1403,
     *     "district": 140302,
     *     "address": "嘎嘎嘎嘎嘎",
     *     "pawnRate": 2,
     *     "disposeStatus": "y",
     *     "worth": 23,
     *     "memo": null,
     *     "lenderId": 245,
     *     "iouIds": null,
     *     "iouNames": null,
     *     "agent":0
     *     "urge":0
     *     "lawyer":0
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/PawnDTO.java
     */


}
