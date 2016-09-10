package com.dqys.auth.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.auth.service.facade.CompanyService;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.FileTool;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

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
    public ServiceResult<Integer> validateCompany(String credential) {
        CompanyQuery companyQuery = new CompanyQuery();
        companyQuery.setCredential(credential);
        List<TCompanyInfo> tCompanyInfoList = this.queryCompany(companyQuery);
        if (null == tCompanyInfoList || tCompanyInfoList.isEmpty()) {
            return ServiceResult.failure("没有数据", ObjectUtils.NULL);
        }

        return ServiceResult.success(tCompanyInfoList.get(0).getId());
    }

    @Override
    public ServiceResult<Integer> addCompany_tx(TCompanyInfo tCompanyInfo, Integer userId) {
        //新增数据

        Integer result = this.tCompanyInfoMapper.insertSelective(tCompanyInfo);
        if (result <= 0) {
            return ServiceResult.failure("新增失败", ObjectUtils.NULL);
        }

        try {
            if (!FileTool.saveFileSync(tCompanyInfo.getLicence())) {
                throw new UnexpectedRollbackException("保存附件失败");
            }
        } catch (IOException e) {
            throw new UnexpectedRollbackException("保存附件异常");
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
