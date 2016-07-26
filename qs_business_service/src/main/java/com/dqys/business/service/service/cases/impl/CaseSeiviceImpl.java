package com.dqys.business.service.service.cases.impl;

import com.dqys.business.orm.mapper.cases.CaseInfoMapper;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.business.service.service.cases.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/7/26.
 */
@Repository
@Primary
public class CaseSeiviceImpl implements CaseService {

    @Autowired
    private CaseInfoMapper caseInfoMapper;

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return caseInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(CaseInfo caseInfo) {
        return caseInfoMapper.insert(caseInfo);
    }

    @Override
    public CaseInfo get(Integer id) {
        return caseInfoMapper.get(id);
    }

    @Override
    public Integer update(CaseInfo caseInfo) {
        return caseInfoMapper.update(caseInfo);
    }
}
