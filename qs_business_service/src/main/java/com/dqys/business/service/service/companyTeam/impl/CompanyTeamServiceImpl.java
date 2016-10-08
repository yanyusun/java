package com.dqys.business.service.service.companyTeam.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.service.companyTeam.CompanyTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import static com.dqys.business.orm.constant.company.ObjectTypeEnum.LENDER;

/**
 * Created by yan on 16-10-8.
 */
@Service
@Primary
public class CompanyTeamServiceImpl implements CompanyTeamService{
    @Autowired
    private CompanyTeamMapper companyTeamMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    /**
     * 查询对象所在团队
     * @param objectType
     * @param objectId
     * @return
     */
    @Override
    public CompanyTeam getCompanyTeam(Integer objectType, Integer objectId) {
        ObjectTypeEnum objectTypeEnum = ObjectTypeEnum.getObjectTypeEnum(objectType);
        CompanyTeam lenderTeam;
        CompanyTeam assetTeam;
        CompanyTeam sourceTeam;
        LenderInfo lenderInfo;
        switch (objectTypeEnum){
            case PAWN:
               PawnInfo pawnInfo= pawnInfoMapper.get(objectId);
               lenderTeam=companyTeamMapper.getByTypeId(ObjectTypeEnum.LENDER.getValue(),pawnInfo.getLenderId());
                if(lenderTeam!=null){//返回借款人的team
                    return lenderTeam;
                }
                //查询上级资产包的team
                lenderInfo=lenderInfoMapper.get(pawnInfo.getLenderId());
                assetTeam=companyTeamMapper.getByTypeId(ObjectTypeEnum.ASSETPACKAGE.getValue(),lenderInfo.getAssetId());
                if(assetTeam!=null){
                    return assetTeam;
                }
                //// TODO: 16-10-8  查找资产源
             break;
            case IOU:
                IOUInfo iouInfo= iouInfoMapper.get(objectId);
                lenderTeam=companyTeamMapper.getByTypeId(ObjectTypeEnum.LENDER.getValue(),iouInfo.getLenderId());
                if(lenderTeam!=null){//返回借款人的team
                    return lenderTeam;
                }
                //查询上级资产包的team
                lenderInfo=lenderInfoMapper.get(iouInfo.getLenderId());
                 assetTeam=companyTeamMapper.getByTypeId(ObjectTypeEnum.ASSETPACKAGE.getValue(),lenderInfo.getAssetId());
                if(assetTeam!=null){
                    return assetTeam;
                }
              break;
            case LENDER:
                lenderTeam=companyTeamMapper.getByTypeId(objectType,objectId);
                if(lenderTeam!=null){//返回借款人的team
                    return lenderTeam;
                }
                //查询上级资产包的team
                lenderInfo=lenderInfoMapper.get(objectId);
                assetTeam=companyTeamMapper.getByTypeId(ObjectTypeEnum.ASSETPACKAGE.getValue(),lenderInfo.getAssetId());
                if(assetTeam!=null){
                    return assetTeam;
                }
                break;
            case ASSETPACKAGE:
                assetTeam=companyTeamMapper.getByTypeId(ObjectTypeEnum.ASSETPACKAGE.getValue(),objectId);
                if(assetTeam!=null){
                    return assetTeam;
                }
                break;
            case ASSETSOURCE:
                sourceTeam=companyTeamMapper.getByTypeId(ObjectTypeEnum.ASSETSOURCE.getValue(),objectId);
                if(sourceTeam!=null){
                    return sourceTeam;
                }
                break;
        }
        return null;
    }

}
