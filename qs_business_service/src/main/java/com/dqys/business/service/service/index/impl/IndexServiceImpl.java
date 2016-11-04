package com.dqys.business.service.service.index.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.index.IndexMapper;
import com.dqys.business.orm.pojo.index.UserMessage;
import com.dqys.business.service.service.index.IndexService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.utils.AreaTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/8/1.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;

    @Override
    public void getStatistic(Map map, Integer userId) {
        Integer userType = 0;//用户类型
        List<TUserTag> tUserTags = tUserTagMapper.selectByUserId(userId);
        if (tUserTags.size() > 0) {
            TUserTag tUserTag = tUserTags.get(0);
            userType = (int) tUserTag.getUserType();
        }
        map.put("await", MessageUtils.transMapToInt(indexMapper.receive(userId), "count"));//待完成任务-------------

        Integer objectType = 0;//对象类型
        if (userType == 0) {//普通用户

        } else if (userType == 1) {//平台管理员
            objectType = ObjectTypeEnum.LENDER.getValue();
        } else if (userType == 2) {//委托号

        } else if (userType == 31) {//催收
            objectType = ObjectTypeEnum.LENDER.getValue();
        } else if (userType == 32) {//律师
            objectType = ObjectTypeEnum.LENDER.getValue();
        } else if (userType == 33) {//中介
            objectType = ObjectTypeEnum.PAWN.getValue();
        }
        map.put("join", MessageUtils.transMapToInt(indexMapper.getJoinTask(objectType, userId), "count"));//参与的任务
        int unfinished = 0;
        int finish = 0;
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            Integer count1 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByOneSelfAndCompany(1, objectType, userId), "count");//已完成任务自己或公司分配
            Integer count2 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByTeam(1, objectType, userId), "count");//已完成任务团队分配
            finish = count1 + count2;
            Integer count3 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByOneSelfAndCompany(0, objectType, userId), "count");//正在进行任务自己或公司分配
            Integer count4 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByTeam(0, objectType, userId), "count");//正在进行任务团队分配
            unfinished = count3 + count4;
        } else if (objectType == ObjectTypeEnum.PAWN.getValue()) {
            Integer total = MessageUtils.transMapToInt(indexMapper.getPawnTotalTask(objectType, userId), "count");
            Integer count3 = MessageUtils.transMapToInt(indexMapper.getPawnAllotByOCIsUnfinished(objectType, userId), "count");//正在进行任务自己或公司分配
            Integer count4 = MessageUtils.transMapToInt(indexMapper.getPawnAllotByTeamIsUnfinished(objectType, userId), "count");//正在进行任务团队分配
            unfinished = (count3 == null ? 0 : count3) + (count4 == null ? 0 : count4);
            finish = total - count3 - count4;
        }
        map.put("finish", finish);//已完成------------
        map.put("unfinished", unfinished);//正在进行中------------
    }

    @Override
    public void getUserDetail(Map map, Integer userId) {
        TUserInfo info = tUserInfoMapper.selectByPrimaryKey(userId);
        UserMessage message = indexMapper.selectByUser(userId);
        message.setProvince(AreaTool.getAreaById(MessageUtils.transStringToInt(message.getProvince())).getLabel());
        message.setCity(AreaTool.getAreaById(MessageUtils.transStringToInt(message.getCity())).getLabel());
        message.setArea(AreaTool.getAreaById(MessageUtils.transStringToInt(message.getArea())).getLabel());
        Map taskMap = coordinatorMapper.getTaskRatio(info.getCompanyId(), userId, ObjectTypeEnum.LENDER.getValue());
        Integer finish = MessageUtils.transMapToInt(taskMap, "finish") == null ? 0 : MessageUtils.transMapToInt(taskMap, "finish");
        Integer total = MessageUtils.transMapToInt(taskMap, "total") == null ? 0 : MessageUtils.transMapToInt(taskMap, "total");
        if (total == 0) {
            message.setFinishRate(0 + "%");
        } else {
            DecimalFormat formatDec = new DecimalFormat("0.00");
            message.setFinishRate(formatDec.format(finish / total * 100) + "%");
        }
        map.put("detail", message);
    }
}
