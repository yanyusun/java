package com.dqys.business.service.service.index.impl;

import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.index.IndexMapper;
import com.dqys.business.service.service.index.IndexService;
import com.dqys.business.service.utils.message.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void getStatistic(Map map, Integer userId) {
        Integer userType = 0;//用户类型
        List<TUserTag> tUserTags = tUserTagMapper.selectByUserId(userId);
        if (tUserTags.size() > 10) {
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
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            Integer count1 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByOneSelfAndCompany(1, objectType, userId), "count");//已完成任务自己或公司分配
            Integer count2 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByTeam(1, objectType, userId), "count");//已完成任务团队分配
            map.put("finish", count1 + count2);//已完成------------
            Integer count3 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByOneSelfAndCompany(0, objectType, userId), "count");//正在进行任务自己或公司分配
            Integer count4 = MessageUtils.transMapToInt(indexMapper.getLenderAllotByTeam(0, objectType, userId), "count");//正在进行任务团队分配
            map.put("unfinished", count3 + count4);//正在进行中------------

        } else if (objectType == ObjectTypeEnum.PAWN.getValue()) {
            map.put("",getClass());
        }


    }
}
