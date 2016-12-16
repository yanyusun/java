package com.dqys.business.controller;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyQuery;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.service.organization.OrganizationCompanyService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.FileTool;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * 机构管理
 * Created by mkfeng on 2016/12/13.
 */
@Controller
@RequestMapping("/organiz")
public class OrganizationCompanyController {

    @Autowired
    private OrganizationCompanyService organizationService;
    @Autowired
    private UserService userService;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;

    /**
     * @api {post} organiz/organizList 获取机构管理列表
     * @apiName organiz/organizList
     * @apiSampleRequest organiz/organizList
     * @apiGroup organiz
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/organizList")
    @ResponseBody
    public JsonResponse organizList(OrganizationCompanyQuery query) {
        Map map = organizationService.organizList(query);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure("查询失败");
        }
    }

    /**
     * @api {post} organiz/updateCompany 修改或添加公司信息
     * @apiName organiz/updateCompany
     * @apiSampleRequest organiz/updateCompany
     * @apiParam {string} companyName 公司名称
     * @apiParam {string} credential 统一社会信用代码（营业执照号码）
     * @apiParam {string} licence 营业执照扫描件
     * @apiParam {int} type 类别
     * @apiParam {int} userType 用户类型
     * @apiParam {string} identity 身份证号
     * @apiParam {string} mobile 手机号
     * @apiParam {string} realName 姓名
     * @apiParam {string} identity 身份证号
     * @apiParam {string} legalPerson 企业法人扫描件
     * @apiParam {string} companyAccount 帐号名称>>公司简称
     * @apiParam {string} introduction 功能介绍
     * @apiParam {int} province 省份
     * @apiParam {int} city 市
     * @apiParam {int} area 区县
     * @apiParam {string} companyRemark 功能简介
     * @apiParam {string} remark 备注
     * @apiGroup organiz
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/updateCompany")
    @ResponseBody
    public JsonResponse updateCompany(String companyName, String credential, String licence,
                                      Integer type, Integer userType, String realName, String legalPerson,
                                      String identity, String mobile, String companyAccount, Integer userId,
                                      String introduction, Integer province, Integer city, Integer area, String companyRemark,
                                      String remark) throws ParseException {
        //用户存在了继续下面的格式判断

        TUserInfo userInfo = tUserInfoMapper.selectByPrimaryKey(userId);
        if (CompanyTypeEnum.getCompanyTypeEnum(type) == null) {
            return JsonResponseTool.paramErr("公司类型错误");
        }
        if ((userType == 1) || (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, null, String.valueOf(userType)).isEmpty())) {
            return JsonResponseTool.paramErr("用户类型无效");
        }
        if (StringUtils.isBlank(companyName)) {
            return JsonResponseTool.paramErr("公司名不能为空");
        }
        if (StringUtils.isBlank(credential)) {
            return JsonResponseTool.paramErr("营业执照注册号不能为空");
        }
        if (StringUtils.isBlank(licence)) {
            return JsonResponseTool.paramErr("营业执照未上传");
        }
        if (StringUtils.isBlank(realName)) {
            return JsonResponseTool.paramErr("姓名无效");
        }
        String identityMsg = FormatValidateTool.idCardValidate(identity);
        if (!StringUtils.isBlank(identityMsg)) {
            return JsonResponseTool.paramErr("身份证无效," + identityMsg);
        }
        if (!FormatValidateTool.checkMobile(mobile)) {
            return JsonResponseTool.paramErr("手机号无效");
        }
        //验证公司有效性
        ServiceResult<Integer> companyResult = companyService.validateCompany(credential);
        if (companyResult.getFlag()) {//防止重复存在
            //返回营业执照注册号已经注册
            if (userInfo.getCompanyId() == null || companyResult.getData().intValue() != userInfo.getCompanyId().intValue()) {
                return JsonResponseTool.failure("营业执照注册号已经注册");
            }
        }
        TCompanyInfo tCompanyInfo = new TCompanyInfo();
        //公司信息
        tCompanyInfo.setCompanyName(companyName);
        tCompanyInfo.setCredential(credential);
        tCompanyInfo.setLicence(licence);
        tCompanyInfo.setType(type);
        tCompanyInfo.setLegalPerson(legalPerson);
        tCompanyInfo.setBusinessType(userType);
        tCompanyInfo.setProvince(province);
        tCompanyInfo.setCity(city);
        tCompanyInfo.setArea(area);
        tCompanyInfo.setCompanyAccount(companyAccount);
        tCompanyInfo.setRemark(introduction);
        tCompanyInfo.setRemark(companyRemark);
        if (tCompanyInfo.getLicence() != null && !"".equals(tCompanyInfo.getLicence())) {
            try {
                if (!FileTool.saveFileSync(tCompanyInfo.getLicence())) {
                    return JsonResponseTool.failure("保存失败，请重新上传");
                }
            } catch (IOException e) {
                return JsonResponseTool.failure("保存失败，请重新上传");
            }
        }
        if (tCompanyInfo.getLegalPerson() != null && !"".equals(tCompanyInfo.getLegalPerson())) {
            try {
                if (!FileTool.saveFileSync(tCompanyInfo.getLegalPerson())) {
                    return JsonResponseTool.failure("保存失败，请重新上传");
                }
            } catch (IOException e) {
                return JsonResponseTool.failure("保存失败，请重新上传");
            }
        }
        if (userInfo.getCompanyId() != null) {
            TCompanyInfo companyInfo = tCompanyInfoMapper.selectByPrimaryKey(userInfo.getCompanyId());
            if (companyInfo != null) {
                //修改公司信息
                if (companyInfo.getIsAuth() == 2) {
                    tCompanyInfo.setIsAuth(0);
                }
                tCompanyInfo.setId(companyInfo.getId());
                Integer count = tCompanyInfoMapper.updateByPrimaryKeySelective(tCompanyInfo);
            } else {
                return JsonResponseTool.paramErr("公司信息修改失败");
            }
        } else {
            Integer res = tCompanyInfoMapper.insertSelective(tCompanyInfo);//添加公司信息
            if (res == 0) {
                return JsonResponseTool.failure(companyResult.getMessage());
            }
            userInfo.setCompanyId(companyResult.getData());
        }
        userInfo.setRealName(realName);
        userInfo.setUserName(realName);
        userInfo.setIdentity(identity);
        userInfo.setMobile(mobile);
        userInfo.setRemark(remark);
        tUserInfoMapper.updateByPrimaryKeySelective(userInfo);
        return JsonResponseTool.success("成功");
    }

    /**
     * @api {post} organiz/organizListToOutExcel 导出机构管理列表
     * @apiName organiz/organizListToOutExcel
     * @apiSampleRequest organiz/organizListToOutExcel
     * @apiGroup organiz
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/organizListToOutExcel")
    @ResponseBody
    public JsonResponse organizListToOutExcel(OrganizationCompanyQuery query) {
        Map map = organizationService.organizListToOutExcel(query);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure("查询失败");
        }
    }

}
