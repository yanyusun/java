package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.query.TUserQuery;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.part.ParticipationMapper;
import com.dqys.business.service.dto.company.CompanyTeamReDTO;
import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.dto.part.PartDto;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.DistributionService;
import com.dqys.business.service.service.ParticipationService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.utils.DateFormatTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mkfeng on 2016/11/1.
 */
@Service
public class ParticipationServiceImpl implements ParticipationService {
    @Autowired
    private ParticipationMapper participationMapper;
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;

    /**
     * 获取参与的公司
     *
     * @return
     */
    @Override
    public Map findList(Integer objectId, Integer objectType) {
        Map map = new HashMap<>();
        map.put("result", "no");
        List<PartDto> partDtos = null;
        DistributionDTO dto = null;
        try {
            dto = distributionService.listDistribution_tx(objectType, objectId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "清收案组调取出错，可能清收案组还未创建");
            return map;
        }
        if (dto != null && dto.getCompanyTeamReDTOList() != null) {
            partDtos = setPartDto(dto.getCompanyTeamReDTOList(), objectId, objectType);
        }
        map.put("result", "yes");
        map.put("list", partDtos);

        return map;
    }

    @Override
    public Map getDetail(Integer objectId, Integer objectType, Integer companyId) {
        Map map = new HashMap<>();
        map.put("result", "yes");
        map.put("detail", null);
        Map mapDto = findList(objectId, objectType);
        if (mapDto.get("list") != null) {
            List<PartDto> list = (List<PartDto>) mapDto.get("list");
            for (PartDto dto : list) {
                if (dto.getCompanyId() == companyId.intValue()) {
                    map.put("detail", dto);
                }
            }
        }
        return map;
    }

    private List<PartDto> setPartDto(List<CompanyTeamReDTO> companyTeamReDTOList, Integer objectId, Integer objectType) {
        List<PartDto> partDtos = new ArrayList<>();
        List<String> companyIds = new ArrayList<>();
        for (CompanyTeamReDTO com : companyTeamReDTOList) {
            TUserInfo info = tUserInfoMapper.selectByPrimaryKey(com.getUserId());
            if (info != null && info.getCompanyId() != null && !companyIds.contains(info.getCompanyId())) {
                companyIds.add(info.getCompanyId().toString());
                TCompanyInfo infoC = tCompanyInfoMapper.selectByPrimaryKey(info.getCompanyId());
                PartDto dto = new PartDto();
                dto.setRate(com.getRate());
                TUserQuery query = new TUserQuery();
                query.setCompanyId(info.getCompanyId());
                dto.setComapanyTotal(tUserInfoMapper.queryCount(query));//获取公司总人数
                dto.setCompanyAddress(com.getAddress());
                dto.setCompanyContent(infoC.getRemark());
                dto.setCompanyId(info.getCompanyId());
                dto.setCompanyJoinTime(DateFormatTool.format(com.getTime() == null ? new Date() : com.getTime(), DateFormatTool.DATE_FORMAT_10_REG1));
                dto.setCompanyName(infoC.getCompanyName());
                //查询协作器数量
                List<Map<String, Object>> maps = coordinatorMapper.getPeopleNum(info.getCompanyId(), objectId, objectType);
                Integer peopleNum = 0;
                for (Map m : maps) {
                    if (MessageUtils.transMapToInt(m, "peopleNum") != null) {
                        peopleNum += MessageUtils.transMapToInt(m, "peopleNum");
                    }
                }
                dto.setJoinPeopleNum(peopleNum);//获取参与的人数
                partDtos.add(dto);
            }
        }
        return partDtos;
    }
}
