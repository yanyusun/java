package com.dqys.auth.service.facade;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.core.model.ServiceResult;
import org.apache.ibatis.annotations.Param;

/**
 * @author by pan on 16-5-3.
 */
public interface CompanyService {

    /**
     * 验证公司是否已存在
     *
     * @param credential
     * @return
     */
    ServiceResult<Integer> validateCompany(@Param("credential") String credential, @Param("businessType") Integer businessType);
    ServiceResult<Integer> validateCompany(@Param("credential") String credential);
    /**
     * 增加公司信息
     *
     * @param tCompanyInfo
     * @return
     */
    ServiceResult<Integer> addCompany_tx(TCompanyInfo tCompanyInfo);

    /**
     * 根据Id获取公司信息
     *
     * @param id
     * @return
     */
    TCompanyInfo get(Integer id);

}
