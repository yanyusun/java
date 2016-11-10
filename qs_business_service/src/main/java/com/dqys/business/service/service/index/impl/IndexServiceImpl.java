package com.dqys.business.service.service.index.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.orm.query.TUserQuery;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.repay.RepayEnum;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.index.IndexMapper;
import com.dqys.business.orm.pojo.index.UserMessage;
import com.dqys.business.service.service.index.IndexService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.utils.AreaTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            finish = MessageUtils.transMapToInt(indexMapper.getLenderAllotByOneSelfAndCompany(RepayEnum.REPAY_STATUS_YES.getValue(), objectType, userId), "count");//已完成任务自己或公司分配
            unfinished = MessageUtils.transMapToInt(indexMapper.getLenderAllotByOneSelfAndCompany(RepayEnum.REPAY_STATUS_NO.getValue(), objectType, userId), "count");//正在进行任务自己或公司分配
        } else if (objectType == ObjectTypeEnum.PAWN.getValue()) {
            finish = MessageUtils.transMapToInt(indexMapper.getPawnAllotByOCIsUnfinished(RepayEnum.REPAY_STATUS_YES.getValue(),objectType, userId), "count");//已完成任务自己或公司分配
            unfinished =  MessageUtils.transMapToInt(indexMapper.getPawnAllotByOCIsUnfinished(RepayEnum.REPAY_STATUS_NO.getValue(),objectType, userId), "count");//正在进行任务自己或公司分配
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
        map.put("detail", message);//员工基础信息
        TUserQuery query = new TUserQuery();
        query.setCompanyId(info.getCompanyId());
        query.setStatus(0);//未激活
        List<TUserInfo> userInfos = tUserInfoMapper.queryList(query);
        map.put("notActivated", userInfos.size());//统计员工未激活人数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("enterToday", indexMapper.getLoginByTime(sdf.format(new Date()), info.getCompanyId()));//当天登入人数
        map.put("totalPeople", indexMapper.getAbsent(info.getCompanyId()));//公司总人数

    }
}
