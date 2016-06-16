package com.dqys.business.service.service.impl;


import com.dqys.business.orm.mapper.asset.ContactInfoMapper;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.query.asset.IOUQuery;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.business.orm.query.asset.PawnQuery;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.utils.AssetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yvan on 16/6/12.
 */
@Service
@Primary
public class LenderServiceImpl implements LenderService {

    @Autowired
    private ContactInfoMapper contactInfoMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper IOUInfoMapper;

    @Override
    public Integer deleteLenderInfo(Integer id) {
        return contactInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addLenderInfo(ContactInfo contactInfo) {
        Integer addResult = contactInfoMapper.insert(contactInfo);
        if (addResult.equals(1)) {
            return contactInfo.getId();
        }
        return addResult;
    }

    @Override
    public Integer updateLenderInfo(ContactInfo contactInfo) {
        return contactInfoMapper.update(contactInfo);
    }

    @Override
    public ContactInfo getLenderInfo(Integer id) {
        return contactInfoMapper.get(id);
    }

    @Override
    public List<ContactInfo> queryListLender(LenderQuery lenderQuery) {
        return contactInfoMapper.queryList(lenderQuery);
    }

    @Override
    public Integer deleteLenderRelation(Integer id) {
        return lenderInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addLenderRelation(LenderInfo lenderInfo) {
        Integer addResult = lenderInfoMapper.insert(lenderInfo);
        if (addResult.equals(1)) {
            return lenderInfo.getId();
        }
        return addResult;
    }

    @Override
    public Integer updateLenderRelation(LenderInfo lenderInfo) {
        return lenderInfoMapper.update(lenderInfo);
    }

    @Override
    public LenderInfo getLenderRelation(Integer id) {
        return lenderInfoMapper.get(id);
    }

    @Override
    public Integer deleteIOUInfo(Integer id) {
        return IOUInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addIOUInfo(IOUInfo IOUInfo, String name) {
        IOUInfo.setIouNo(AssetUtil.createIouCode(name));
        Integer addResult = IOUInfoMapper.insert(IOUInfo);
        if (addResult.equals(1)) {
            return IOUInfo.getId();
        }
        return addResult;
    }

    @Override
    public Integer updateIOUInfo(IOUInfo IOUInfo) {
        return IOUInfoMapper.update(IOUInfo);
    }

    @Override
    public IOUInfo getIOUInfo(Integer id) {
        return IOUInfoMapper.get(id);
    }

    @Override
    public Integer countIOU() {
        return IOUInfoMapper.count();
    }

    @Override
    public List<IOUInfo> listIOUByLenderId(Integer lenderId) {
        return IOUInfoMapper.listByLenderId(lenderId);
    }

    @Override
    public List<IOUInfo> queryListIOU(IOUQuery IOUQuery) {
        return IOUInfoMapper.queryList(IOUQuery);
    }

    @Override
    public Integer deletePawn(Integer id) {
        return pawnInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addPawn(PawnInfo pawnInfo) {
        pawnInfo.setPawnNo(AssetUtil.createPawnCode());
        Integer addResult = pawnInfoMapper.insert(pawnInfo);
        if (addResult.equals(1)) {
            return pawnInfo.getId();
        }
        return addResult;
    }

    @Override
    public Integer updatePawn(PawnInfo pawnInfo) {
        return pawnInfoMapper.update(pawnInfo);
    }

    @Override
    public PawnInfo getPawn(Integer id) {
        return pawnInfoMapper.get(id);
    }

    @Override
    public Integer countPawn() {
        return pawnInfoMapper.count();
    }

    @Override
    public List<PawnInfo> listPawnByLenderId(Integer lenderId) {
        return pawnInfoMapper.listByLenderId(lenderId);
    }

    @Override
    public List<PawnInfo> queryListPawn(PawnQuery pawnQuery) {
        return pawnInfoMapper.queryList(pawnQuery);
    }
}
