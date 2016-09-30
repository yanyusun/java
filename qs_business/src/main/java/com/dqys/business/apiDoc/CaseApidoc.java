package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class CaseApidoc {

    /**
     * @api {post} /case/add 创建案件信息
     * @apiName add
     * @apiGroup case
     * @apiUse Case
     * @apiUse CaseCourt
     * @apiSuccess {number} data 新增后的ID
     */

    /**
     * @api {post} /case/listAdd 批量创建案件信息
     * @apiName listAdd
     * @apiGroup case
     * @apiParam {caseDTOList} caseDTOList 参考创建案件信息
     * @apiUse Case
     * @apiUse CaseCourt
     */

    /**
     * @api {post} /case/update 修改案件信息
     * @apiName update
     * @apiGroup case
     * @apiUse Case
     * @apiUse CaseCourt
     * @apiSuccess {number} data 修改后的ID
     */

    /**
     * @api {post} /case/list 根据借款人查询案件信息
     * @apiName list
     * @apiGroup case
     * @apiParam {number} id 借款人ID
     * @apiParam {number} [index] 第N件案件
     * @apiSuccess {CaseDTO} data 案件信息
     * @apiUse CaseDTO
     * @apiUse CaseCourtDTO
     */

    /**
     * @api {post} /case/listCase 根据案件查询子案件（单条）
     * @apiName listCase
     * @apiGroup case
     * @apiParam {number} id 案件ID
     * @apiParam {number} [index] 第N件子案件
     * @apiSuccess {CaseDTO} data 案件信息
     * @apiUse CaseDTO
     * @apiUse CaseCourtDTO
     */

    /**
     * @api {post} /case/divide 拆分案件
     * @apiName divide
     * @apiGroup case
     * @apiParam {number} id 案件ID
     * @apiParam {string} ids 借据ID集(","隔开)
     * @apiSuccess {number} data 拆分后的ID
     */

    /**
     * @apiDefine Case
     * @apiParam {number} [id] 主键ID(修改时必传,增加时可不传)
     * @apiParam {number} pawnId 抵押物ID
     * @apiParam {number} iouIds 借据Ids(逗号隔开)
     * @apiParam {number} [pId] 被拆分的案件特有属性
     * @apiParam {string} plaintiff 原告
     * @apiParam {string} defendant 被告
     * @apiParam {string} spouse 配偶
     * @apiParam {string} mortgagor 抵押人名称
     * @apiParam {string} mortgageTime 抵押次数
     * @apiParam {string} guarantor 保证人名称
     * @apiParam {string} mortgageTime 抵押次数
     * @apiParam {string} guarantor 保证人
     * @apiParam {string} evaluateExcellent 评优
     * @apiParam {string} evaluateLevel 评级
     * @apiParam {number} lawsuitAmount 诉讼金额
     * @apiParam {number} lawsuitCorpus 诉讼本金
     * @apiParam {number} lawsuitAccrual 诉讼利息
     * @apiParam {number} attachmentStatus 查封(1为肯定,表示已查封,下同)
     * @apiParam {date} attachmentDate 查封时间
     * @apiParam {string} attachmentCourt 法院
     * @apiParam {number} attachmentTime 查封次数
     * @apiParam {number} isPreservation 保全(1保全)
     * @apiParam {date} preservationStart 保全开始时间
     * @apiParam {date} preservationStart 保全结束时间
     * @apiParam {string} preservationMemo 续保情况
     * @apiParam {number} isFirst 首封(1)
     * @apiParam {string} firstAttachmentCourt 首封法院
     * @apiParam {string} preservationCourt 执行保全法院
     * @apiParam {string} firstAttachmentCode 法院案号
     * @apiParam {date} firstAttachmentDate 查封时间
     * @apiParam {string} memo 备注
     * @apiParam {string} lawsuitMemo 诉讼备注
     * @apiParam {string} attachmentMemo 查封情况
     * @apiParam {CaseCourt} courtDTOList 相关联法院
     */

    /**
     * @apiDefine CaseDTO
     * @apiSuccessExample {json} CaseDTO:
     * {}
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java
     */

    /**
     * @apiDefine CaseCourt
     * @apiParam {number} [id] 主键
     * @apiParam {string} caseId 案件ID
     * @apiParam {string} court 法院名称
     * @apiParam {string} code 法院案号
     * @apiParam {string} lawyer 法官
     * @apiParam {number} gender 性别(1男性)
     * @apiParam {string} lawyerMemo 备注
     * @apiParam {string} mobile 手机
     * @apiParam {string} tel 座机
     * @apiParam {string} other 其他联系方式
     */

    /**
     * @apiDefine CaseCourtDTO
     * @apiSuccessExample {json} CaseCourtDTO:
     * {}
     * git : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java
     */

}
