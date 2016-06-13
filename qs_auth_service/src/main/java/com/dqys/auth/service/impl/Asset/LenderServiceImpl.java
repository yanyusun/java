package com.dqys.auth.service.impl.Asset;

import com.dqys.auth.orm.dao.facade.asset.IOUInfoMapper;
import com.dqys.auth.orm.dao.facade.asset.LenderInfoMapper;
import com.dqys.auth.orm.dao.facade.asset.LenderRelationMapper;
import com.dqys.auth.orm.dao.facade.asset.PawnInfoMapper;
import com.dqys.auth.orm.pojo.asset.IOUInfo;
import com.dqys.auth.orm.pojo.asset.LenderInfo;
import com.dqys.auth.orm.pojo.asset.LenderRelation;
import com.dqys.auth.orm.pojo.asset.PawnInfo;
import com.dqys.auth.orm.query.asset.IOUQuery;
import com.dqys.auth.orm.query.asset.LenderQuery;
import com.dqys.auth.orm.query.asset.PawnQuery;
import com.dqys.auth.service.facade.asset.LenderService;
import com.dqys.auth.service.utils.AssetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yvan on 16/6/12.
 */
@Service
@Primary
public class LenderServiceImpl implements LenderService{

    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private LenderRelationMapper lenderRelationMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;

    @Override
    public Integer deleteLenderInfo(Integer id) {
        return lenderInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addLenderInfo(LenderInfo lenderInfo) {
        Integer addResult = lenderInfoMapper.insert(lenderInfo);
        if(addResult.equals(1)){
            return lenderInfo.getId();
        }
        return addResult;
    }

    @Override
    public Integer updateLenderInfo(LenderInfo lenderInfo) {
        return lenderInfoMapper.update(lenderInfo);
    }

    @Override
    public LenderInfo getLenderInfo(Integer id) {
        return lenderInfoMapper.get(id);
    }

    @Override
    public List<LenderInfo> queryListLender(LenderQuery lenderQuery) {
        return lenderInfoMapper.queryList(lenderQuery);
    }

    @Override
    public Integer deleteLenderRelation(Integer id) {
        return lenderRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addLenderRelation(LenderRelation lenderRelation) {
        Integer addResult = lenderRelationMapper.insert(lenderRelation);
        if(addResult.equals(1)){
            return lenderRelation.getId();
        }
        return addResult;
    }

    @Override
    public Integer updateLenderRelation(LenderRelation lenderRelation) {
        return lenderRelationMapper.update(lenderRelation);
    }

    @Override
    public LenderRelation getLenderRelation(Integer id) {
        return lenderRelationMapper.get(id);
    }

    @Override
    public List<LenderRelation> listLenderRelationByLenderId(Integer id) {
        return lenderRelationMapper.listByLenderId(id);
    }

    @Override
    public Integer deleteIOUInfo(Integer id) {
        return iouInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addIOUInfo(IOUInfo iouInfo, String name) {
        iouInfo.setCode(AssetUtil.createIouCode(name));
        Integer addResult = iouInfoMapper.insert(iouInfo);
        if(addResult.equals(1)){
            return iouInfo.getId();
        }
        return addResult;
    }

    @Override
    public Integer updateIOUInfo(IOUInfo iouInfo) {
        return iouInfoMapper.update(iouInfo);
    }

    @Override
    public IOUInfo getIOUInfo(Integer id) {
        return iouInfoMapper.get(id);
    }

    @Override
    public Integer countIOU() {
        return iouInfoMapper.count();
    }

    @Override
    public List<IOUInfo> listIOUByLenderId(Integer lenderId) {
        return iouInfoMapper.listByLenderId(lenderId);
    }

    @Override
    public List<IOUInfo> queryListIOU(IOUQuery iouQuery) {
        return iouInfoMapper.queryList(iouQuery);
    }

    @Override
    public Integer deletePawn(Integer id) {
        return pawnInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addPawn(PawnInfo pawnInfo) {
        pawnInfo.setCode(AssetUtil.createPawnCode());
        Integer addResult = pawnInfoMapper.insert(pawnInfo);
        if(addResult.equals(1)){
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
