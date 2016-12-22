package com.dqys.auth.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.auth.service.facade.CompanyService;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.FileTool;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author by pan on 16-5-3.
 */
@Service
@Primary
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;


    @Override
    public ServiceResult<Integer> validateCompany(@Param("credential") String credential, @Param("businessType") Integer businessType) {
        CompanyQuery companyQuery = new CompanyQuery();
        companyQuery.setCredential(credential);
        companyQuery.setBusinessType(businessType);
        List<TCompanyInfo> tCompanyInfoList = this.queryCompany(companyQuery);
        if (null == tCompanyInfoList || tCompanyInfoList.isEmpty()) {
            return ServiceResult.failure("没有数据", ObjectUtils.NULL);
        }

        return ServiceResult.success(tCompanyInfoList.get(0).getId());
    }

    @Override
    public ServiceResult<Integer> validateCompany(@Param("credential") String credential) {
        return validateCompany(credential, null);
    }

    @Override
    public ServiceResult<Integer> addCompany_tx(TCompanyInfo tCompanyInfo) {
        //新增数据
        if (tCompanyInfo.getLicence() != null && !"".equals(tCompanyInfo.getLicence())) {
            try {
                if (!FileTool.saveFileSync(tCompanyInfo.getLicence())) {
                    return ServiceResult.failure("营业执照保存失败，请重新上传", null);
                }
            } catch (IOException e) {
                return ServiceResult.failure("营业执照保存失败，请重新上传", null);
            }
        }
        if (tCompanyInfo.getLegalPerson() != null && !"".equals(tCompanyInfo.getLegalPerson())) {
            try {
                if (!FileTool.saveFileSync(tCompanyInfo.getLegalPerson())) {
                    return ServiceResult.failure("手持身份证照保存失败，请重新上传", null);
                }
            } catch (IOException e) {
                return ServiceResult.failure("手持身份证照保存失败，请重新上传", null);
            }
        }
        if (tCompanyInfo.getId() != null) {
            this.tCompanyInfoMapper.updateByPrimaryKeySelective(tCompanyInfo);
        } else {
            Integer result = this.tCompanyInfoMapper.insertSelective(tCompanyInfo);
            if (result <= 0) {
                return ServiceResult.failure("新增失败", ObjectUtils.NULL);
            }
        }
        return ServiceResult.success(tCompanyInfo.getId());
    }


    private List<TCompanyInfo> queryCompany(CompanyQuery query) {
        return this.tCompanyInfoMapper.queryList(query);
    }

    @Override
    public TCompanyInfo get(Integer id) {
        return this.tCompanyInfoMapper.selectByPrimaryKey(id);
    }
}
