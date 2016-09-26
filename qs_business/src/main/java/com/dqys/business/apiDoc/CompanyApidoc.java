package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/26.
 */
public class CompanyApidoc {

    /**
     * @api {GET} /api/company/listCompany 查看特定类型的公司
     * @apiName listCompany
     * @apiGroup organization
     * @apiParam {number} [type] 公司类型(1平台2委托31催收32律所33中介)
     * @apiSuccess {CompanyDTO} data 公司信息
     */

    /**
     * @api {get} /api/company/getRelation 获取公司间合作关系
     * @apiName getRelation
     * @apiGroup companyRelation
     * @apiParam {number} id 公司ID
     * @apiSuccess {CompanyRelationDTO} data 公司关系表
     * @apiUse CompanyRelationDTO
     */

    /**
     * @api {GET} /api/company/listOrganization 查看组织列表(部门|团队)
     * @apiName listOrganization
     * @apiGroup organization
     * @apiParam {number} companyId 公司Id
     * @apiParam {type} type 组织类型(apartment部门|team团队)
     * @apiSuccess {OrganizationDTO} data 组织架构
     * @apiUse OrganizationDTO
     */

    /**
     * @api {POST} /api/company/addOrganization 新增组织(部门|团队)
     * @apiName addOrganization
     * @apiGroup organization
     * @apiUse Organization
     * @apiSuccess {number} data 新增的ID
     */

    /**
     * @api {POST} /api/company/updateOrganization 修改组织(部门|团队)
     * @apiName updateOrganization
     * @apiGroup organization
     * @apiUse Organization
     * @apiSuccess {number} data 修改后的ID
     */

    /**
     * @api {get} /api/company/deleteOrganization 删除组织(部门|团队)
     * @apiName deleteOrganization
     * @apiGroup organization
     * @apiParam {number} id 组织ID
     */

    /**
     * @api {get} /api/company/getOrganization 获取组织(部门|团队)
     * @apiName getOrganization
     * @apiGroup organization
     * @apiParam {string} type 组织类型
     * @apiParam {number} id 组织ID
     * @apiSuccess {OrganizationDTO} data 返回组织架构
     * @apiUse OrganizationDTO
     */

    /**
     * @api {get} /api/company/getDistribution 获取该对象的分配器列表
     * @apiName getDistribution
     * @apiGroup distribution
     * @apiParam {number} type 分配对象类型(如:资产包asset)
     * @apiParam {number} id 分配对象ID
     * @apiSuccess {DistributionDTO} data 分配器列表
     * @apiUse CompanyTeamReDTO
     * @apiUse BusinessServiceDTO
     */

    /**
     * @api {get} /api/company/joinDistribution 申请加入分配器
     * @apiName joinDistribution
     * @apiGroup distribution
     * @apiParam {number} id 分配器ID
     * @apiSuccess {number} data 分配后的成员id
     */

    /**
     * @api {get} /api/company/inviteDistribution 邀请加入分配器
     * @apiName inviteDistribution
     * @apiGroup distribution
     * @apiParam {number} id 分配器ID
     * @apiParam {number} companyId 公司ID
     * @apiSuccess {number} data 分配后的成员id
     */

    /**
     * @api {get} /api/company/designDistribution 决定加入分配器(同意或者拒绝)
     * @apiName designDistribution
     * @apiGroup distribution
     * @apiParam {number} id 被邀请ID
     * @apiParam {number} status 操作类型(接收1|拒绝0)
     * @apiSuccess {number} data 操作后的成员id
     */

    /**
     * @api {get} /api/company/exitDistribution 退出分配器
     * @apiName exitDistribution
     * @apiGroup distribution
     * @apiParam {number} id 分配器成员ID
     * @apiSuccess {number} data
     */

    /**
     * @api {get} /api/company/listByService 根据业务类型获取公司
     * @apiName listByService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     * @apiSuccess {CompanyDTO} data 公司信息
     * @apiUse CompanyDTO
     */

    /**
     * @api {get} /api/company/listRelationByService 根据业务类型获取公司联系关系
     * @apiName listRelationByService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     * @apiParam {number} id 公司ID
     * @apiSuccess {CompanyDTO} data 公司信息
     * @apiUse CompanyDTO
     */

    /**
     * @api {get} /api/company/addBusinessService 平台为申请业务流转的公司添加业务流转伙伴接口
     * @apiName addBusinessService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型
     * @apiParam {number} id 公司ID
     * @apiParam {number} distributionId 分配器ID
     * @apiParam {number} businessType 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     * @apiParam {number} companyId 被邀请公司ID
     * @apiSuccess {number} 分配后的业务流转成员ID
     */

    /**
     * @api {get} /api/company/designBusinessService 被添加公司接受或者拒绝业务流转邀请
     * @apiName designBusinessService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     * @apiParam {number} id 公司ID
     * @apiParam {number} distributionId 分配器ID
     * @apiParam {number} businessType 业务流转类型
     * @apiParam {number} status 接收1拒绝2
     * @apiSuccess {number} 业务流转操作后的成员ID
     */

    /**
     * @apiDefine CompanyDTO
     * @apiSuccessExample {json} CompanyDTO-Response:
     * HTTP/1.1 2000 ok
     * {
     *     id:1,
     *     name:'name',
     *     province:'浙江省',
     *     city:'杭州市',
     *     district:'江干区'
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyDTO.java
     */

    /**
     * @apiDefine CompanyRelationDTO
     * @apiSuccessExample {json} CompanyRelationDTO:
     * HTTP/1.1 2000 ok
     * {
     *
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyRelationDTO.java
     */

    /**
     * @apiDefine Organization
     * @apiParam {string} type 类型
     * @apiParam {string} name 名称
     * @apiParam {number} userId 负责人ID
     * @apiParam {number} companyId 公司ID
     * @apiParam {number} pid 上级ID
     * @apiParam {string} remark 备注
     */

    /**
     * @apiDefine OrganizationDTO
     * @apiSuccessExample {json} Organization:
     * {
     * "id": 4,
     * "name": "管理层",
     * "user": null,
     * "userId": null
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/OrganizationDTO.java
     */

    /**
     * @apiDefine DistributionDTO
     * @apiSuccessExample {json} DistributionDTO:
     * {
     *      id:1,
     *     platformNum:0,
     *     mechanismNum:0,
     *     disposeNum:0,
     *     companyTeamReDTOList:[{CompanyTeamReDTO}...],
     * }
     * platformNum:平台数量 mechanismNum:机构数量 disposeNum:处置方数量
     */

    /**
     * @apiDefine CompanyTeamReDTO
     * @apiSuccessExample {json} CompanyTeamReDTO:
     * {
     *     "id": 42,
     *     "avg": null,
     *     "type": "委托方",
     *     "address": "浙江省温州市乐清市",
     *     "rate": "0",
     *     "task": 0,
     *     "contact": "ls",
     *     "status": 1,
     *     "time": "2016-09-20",
     *     "stateflag": 0
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyTeamReDTO.java
     */

    /**
     * @apiDefine BusinessServiceDTO
     * @apiSuccessExample {json} BusinessServiceDTO:
     * {
     *     "id": 50,
     *     "avg": null,
     *     "type": "32",
     *     "name": "abc中介",
     *     "address": "吉林省长春市长春市辖区",
     *     "rate": "0/0",
     *     "task": 0,
     *     "target": "pawnname",
     *     "time": "2016-09-21",
     *     "stateflag": null,
     *     "status": 0
     * }
     * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/BusinessServiceDTO.java
     */

}
