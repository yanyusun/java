package com.dqys.business.apiDoc;

/**
 * Created by Administrator on 2016/9/27.
 */
public class UserApidoc {

    /**
     * @api {GET} http://{url}/api/user/listData 二级导航统计
     * @apiName listData
     * @apiGroup User
     * @apiDescription 二级导航数据统计
     * @apiSuccessExample {json} Data-Response:
     * {
     * plateform:1,
     * plateformCompany:1,
     * entrustTotal:1,
     * entrustAgency:1,
     * entrustSingle:1,
     * disposeTotal:1,
     * urge:1,
     * judicial:1,
     * dispose:1,
     * }
     */

    /**
     * @api {GET} http://{url}/api/user/listUser 获取公司
     * @apiName listUser
     * @apiGroup User
     * @apiDescription 暂未补充
     * @apiSuccess {UserDTO} data 用户信息
     * @apiUse UserDTO
     */

    /**
     * @api {GET} http://{url}/api/user/getInit 获取初始化列表
     * @apiName getInit
     * @apiGroup User
     * @apiSuccess {CompanyDTO} companyInfo 公司信息
     * @apiUse CompanyDTO
     * @apiSuccess {SelectonDTO} userStatus 用户状态集
     * @apiUse SelectonDTO
     * @apiSuccess {Property} userType 用户账号类型
     * @apiSuccess {Property} roleType 用户角色类型
     * @apiUse PropertyDTO
     */

    /**
     * @api {GET} http://{url}/api/user/list 用户列表
     * @apiName list
     * @apiGroup User
     * @apiUse UserListQuery
     * @apiSuccess {UserList} data
     * @apiUse UserList
     */

    /**
     * @api {POST} http://{url}/api/user/add 增加用户
     * @apiName add
     * @apiGroup User
     * @apiUse UserInsert
     * @apiSuccess {number} data 添加成功后的ID
     */

    /**
     * @api {POST} http://{url}/api/user/get 查看用户信息
     * @apiName get
     * @apiGroup User
     * @apiParam {number} id 用户ID
     * @apiSuccess {UserInsertDTO} data 用户信息
     * @apiUse UserInsertDTO
     */

    /**
     * @api {POST} http://{url}/api/user/update 修改用户信息
     * @apiName update
     * @apiGroup User
     * @apiUse UserInsert
     * @apiSuccess {number} data 添加后的Id
     */

    /**
     * @param id
     * @return
     * @api {get} http://{url}/api/user/delete 删除用户信息
     * @apiName delete
     * @apiGroup User
     */

    /**
     * @apiIgnore {GET} http://{url}/api/user/assignedBatch 批量分配<暂时不处理>
     * @apiName assignedBatch
     * @apiGroup User
     * @apiParam {string} ids 操作成员ID的集合,","分隔
     * @apiParam {number} id 交付给某人
     */

    /**
     * @api {GET} http://{url}/api/user/statusBatch 批量设置状态
     * @apiName statusBatch
     * @apiGroup User
     * @apiParam {string} ids 操作成员ID的集合,","分隔
     * @apiParam {number} status 状态
     */

    /**
     * @api {GET} http://{url}/api/user/userExcel 成员信息excel导入
     * @apiName userExcel
     * @apiGroup User
     * @apiParam {String} file 上传的excel文件
     */

    /**
     * @api {GET} http://{url}/api/user/sendMsg 消息提醒(暂时废除)
     * @apiName sendMsg
     * @apiGroup User
     * @apiParam {string} ids 操作用户ID集合,","分隔
     */

    /**
     * @api {GET} http://{url}/api/user/setPwdBatch 批量重置密码
     * @apiName setPwdBatch
     * @apiGroup User
     * @apiParam {string} ids 操作用户ID集合,","分隔
     */

    /**
     * @return
     * @api {POST} http://{url}/api/user/setPwd 重置密码
     * @apiName setPwd
     * @apiGroup User
     * @apiParam {number} id 被操作用户
     * @apiParam {string} pwd 新密码
     */

}
