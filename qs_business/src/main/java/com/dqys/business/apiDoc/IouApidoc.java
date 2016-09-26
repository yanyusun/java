package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class IouApidoc {

    /**
     * @api {get} http://{url}/iou/delete 删除借据
     * @apiName delete
     * @apiGroup iou
     * @apiParam {number} id 借据ID
     */

    /**
     * @api {post} http://{url}/iou/add 新增借据
     * @apiName add
     * @apiGroup iou
     * @apiUse Iou
     * @apiSuccess {number} data 新增后的数据ID
     */

    /**
     * @api {post} http://{url}/iou/listAdd 新增借据(多条)
     * @apiName listAdd
     * @apiGroup iou
     * @apiParam {IouDTOList} iouDTOList 参考新增借据
     * @apiUse Iou
     */

    /**
     * @api {post} http://{url}/iou/update 修改借据信息
     * @apiName update
     * @apiGroup iou
     * @apiUse Iou
     * @apiSuccess {number} data 修改后的ID
     */

    /**
     * @api {post} http://{url}/iou/get 获取借据信息
     * @apiName get
     * @apiGroup iou
     * @apiParam {number} id 借据ID
     * @apiSuccess {IouDTO} data 借据信息
     * @apiUse IouDTO
     */

    /**
     * @api {post} http://{url}/iou/listIou 获取借款人的借据信息
     * @apiName listIou
     * @apiGroup iou
     * @apiParam {number} id 借款人ID
     * @apiSuccess {IouDTO} data 借据信息
     * @apiUse IouDTO
     */

    /**
     * @apiDefine Iou
     * @apiParam {numbder} id 主键
     * @apiParam {numbder} iouNo 编号
     * @apiParam {string} iouName 借据名称
     * @apiParam {string} type 借据类型
     * @apiParam {string} agency 代理机构
     * @apiParam {date} loanTime 放款时间
     * @apiParam {date} loanAtTime 到款时间
     * @apiParam {numbder} amount 金额
     * @apiParam {numbder} pactRate 合同利率
     * @apiParam {numbder} outtimeMultiple 逾期倍数
     * @apiParam {string} appropriationMultiple 挪用倍数
     * @apiParam {numbder} accrualRepay 已还利息金额
     * @apiParam {numbder} loanRepay 已还贷款金额
     * @apiParam {string} levelType 5级分类
     * @apiParam {numbder} outDays 逾期天数
     * @apiParam {numbder} lessCorpus 剩余本金
     * @apiParam {numbder} accrualArrears 拖欠利息
     * @apiParam {numbder} penalty 罚息
     * @apiParam {numbder} arrears 欠款合计
     * @apiParam {date} endAt 欠款截止日期
     * @apiParam {numbder} worth 价值
     * @apiParam {numbder} advanceCorpus 提前偿还本金
     * @apiParam {string} evaluateExcellent 评优
     * @apiParam {string} evaluateLevel 评级
     * @apiParam {string} memo 备注
     * @apiParam {number} lenderId 借款基础信息
     * @apiParam {string} pawnIds 抵押物IDs
     * @apiParam {string} pawnNames 抵押物名称集合
     */

    /**
     * @apiDefine IouDTO
     * @apiSuccessExample {json} IouDTO:
     * {
     *     "id": 540,
     *     "version": 0,
     *     "stateflag": 0,
     *     "createAt": "2016-09-22",
     *     "updateAt": "2016-09-22",
     *     "remark": null,
     *     "iouNo": "IO16090002",
     *     "name": "借据01",
     *     "lenderId": 245,
     *     "type": "1",
     *     "agency": "2AADD",
     *     "iouCode": "S45543",
     *     "loanTime": "2016-09-22",
     *     "loanAttime": "2016-09-22",
     *     "amount": 0,
     *     "pactRate": 0,
     *     "outtimeMultiple": 0,
     *     "appropriationMultiple": "0",
     *     "accrualRepay": 0,
     *     "loanRepay": 0,
     *     "levelType": "1",
     *     "outDays": 0,
     *     "lessCorpus": 0,
     *     "accrualArrears": 0,
     *     "penalty": 0,
     *     "arrears": 0,
     *     "endAt": "2016-09-21",
     *     "worth": 0,
     *     "advanceCorpus": 0,
     *     "evaluateExcellent": "nice",
     *     "evaluateLevel": "A",
     *     "memo": null,
     *     "onCollection": 0,
     *     "onLawyer": 0,
     *     "onAgent": 0
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/IouDTO.java
     */


}
