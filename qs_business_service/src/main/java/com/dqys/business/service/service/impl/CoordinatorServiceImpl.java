package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.coordinator.CoordinatorEnum;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.utils.message.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private LenderService lenderService;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private TeammateReMapper teammateReMapper;

    @Override
    public void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType, Integer userid) {
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {//借款人
            LenderInfo lenderInfo = (LenderInfo) lenderService.get(objectId).getData();
            if (lenderInfo == null) {
                map.put("result", "no_lender");
                return;
            } else {
                map.put("accrual", lenderInfo.getAccrual() == null ? 0 : lenderInfo.getAccrual());//总利息
                map.put("loan", lenderInfo.getLoan() == null ? 0 : lenderInfo.getLoan());//总贷款
                map.put("appraisal", lenderInfo.getAppraisal() == null ? 0 : lenderInfo.getAppraisal());//抵押物总评估
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(lenderInfo.getEndAt());
                long nowTime = new Date().getTime();
                int day = (int) (calendar.getTimeInMillis() - nowTime) / (1000 * 3600 * 24);
                map.put("dayCount", day > 0 ? day : 0);//逾期天数
            }
        }
        if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {//资产包
            AssetInfo assetInfo = assetInfoMapper.get(objectId);
            if (assetInfo == null) {
                map.put("result", "no_asset");
                return;
            } else {
                map.put("name", assetInfo.getName());//资产包名称
                map.put("accrual", assetInfo.getAccrual() == null ? 0 : assetInfo.getAccrual());//总利息
                map.put("loan", assetInfo.getLoan() == null ? 0 : assetInfo.getLoan());//总贷款
                map.put("appraisal", assetInfo.getAppraisal() == null ? 0 : assetInfo.getAppraisal());//抵押物总评估
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(assetInfo.getEndAt());
                long nowTime = new Date().getTime();
                int day = (int) (calendar.getTimeInMillis() - nowTime) / (1000 * 3600 * 24);
                map.put("dayCount", day > 0 ? day : 0);//逾期天数
            }
        }
        UserTeam userTeam = new UserTeam();
        userTeam.setObjectType(objectType);
        userTeam.setObjectId(objectId);
        userTeam.setCompanyId(companyId);
        UserTeam team = userTeamMapper.selectByPrimaryKeySelective(userTeam);
        if (team == null) {//判断是否在t_user_team表中添加了记录，添加了返回信息，没添加的返回id
            Map<String, Object> adminUser = coordinatorMapper.getAdminUser(companyId);
            Integer mangerId = MessageUtils.transMapToInt(adminUser, "id");
            userTeam.setMangerId(mangerId == null ? userid : mangerId);
            userTeam.setCtreaterId(userid);
            userTeamMapper.insertSelective(userTeam);
            map.put("userTeamId", userTeam.getId());
            map.put("result", "yes_add");
        } else {
            List<TeamDTO> list = getLenderOrAsset(companyId, objectId, objectType);
            for (TeamDTO t : list) {
                Map<String, Object> task = getTaskCount(companyId, t.getUserId(), objectType);
                t.setFinishTask(MessageUtils.transMapToInt(task, "finish"));
                t.setOngoingTask(MessageUtils.transMapToInt(task, "ongoing"));
                t.setTotalTask(MessageUtils.transMapToInt(task, "total"));
                t.setLeaveWordTime("");//最后留言时间
            }
            map.put("companys", companyList(objectId, objectType));//对象类型相应的公司
            map.put("teams", list);//团队信息
            map.put("people", getPeopleNum(companyId, objectId, objectType));//团队人数
            map.put("result", "yes");
        }
    }

    @Override
    public Map<String, Object> getCompanyUserList(String realName, Integer userId, Integer companyId) {
        Map map = new HashMap<>();
        List<Map<String, Object>> list = coordinatorMapper.getCompanyUserList(realName, userId, companyId);
        map.put("users", list);
        return map;
    }

    @Override
    public Map addTeammate(Integer userTeamId, Integer userId, String remark, Integer[] userIds) {
        Map map = new HashMap<>();
        for (Integer uid : userIds) {
            Integer flag = 0;
            List<TeammateRe> users = new ArrayList<>();
            TeammateRe teammateRe = new TeammateRe();
            teammateRe.setUserTeamId(userTeamId);
            List<TeammateRe> list = teammateReMapper.selectSelective(teammateRe);//查询用于判断人员类别的定位
            teammateRe.setUserId(uid);
            if (list.size() > 0) {
               users = teammateReMapper.selectSelective(teammateRe);//查询用于判断人员加入过这个协作器没有
            }
            if (users.size() > 0) {
                TeammateRe teammateRe1 = users.get(0);
                if (teammateRe1.getStatus() == 2) {
                    teammateRe1.setStatus(0);
                    flag = teammateReMapper.updateByPrimaryKeySelective(teammateRe1);
                }
            } else {
                if (list.size() == 0) {
                    teammateRe.setType(0);
                } else if (list.size() == 1) {
                    teammateRe.setType(1);
                } else {
                    teammateRe.setType(2);
                }
                teammateRe.setBusinessType(0);
                teammateRe.setJoinType(0);
                teammateRe.setStatus(0);
                flag = teammateReMapper.insertSelective(teammateRe);
            }
            if (flag > 0) {
                Integer result = messageService.add("任务邀请", remark, userId, uid, CoordinatorEnum.taskMes.getName(), MessageEnum.TASK.getValue());//添加消息记录
                if (result > 0) {
                    //发送短信或是邮件
                }
            }
        }
        return map;
    }

    /**
     * 获取借款人或是资产包的团队信息
     *
     * @param companyId
     * @param objectId
     * @param objectType
     * @return
     */
    private List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType) {
        return coordinatorMapper.getLenderOrAsset(companyId, objectId, objectType);
    }

    /**
     * 获取任务数
     *
     * @param companyId
     * @param userId
     * @param objectType
     * @return(finish,total,ongoing)
     */
    private Map<String, Object> getTaskCount(Integer companyId, Integer userId, Integer objectType) {
        Map<String, Object> map = coordinatorMapper.getTaskRatio(companyId, userId, objectType);//业绩比例
        Map<String, Object> map2 = coordinatorMapper.getTaskOngoing(companyId, userId, objectType);//获取当前进行的任务数
        map.put("ongoing", map2.get("ongoing"));
        return map;
    }

    /**
     * 获取团队人数
     *
     * @param companyId
     * @param objectId
     * @param objectType
     * @return（type,peopleNum）
     */
    private List<Map<String, Object>> getPeopleNum(Integer companyId, Integer objectId, Integer objectType) {
        return coordinatorMapper.getPeopleNum(companyId, objectId, objectType);
    }

    /**
     * 获取相应的公司
     *
     * @param objectId
     * @param objectType
     * @return
     */
    private List<Map<String, Object>> companyList(Integer objectId, Integer objectType) {
        return coordinatorMapper.companyList(objectId, objectType);
    }

    @Override
    public Integer createDistribution(String type, Integer id) {
        return null;
    }

    @Override
    public Integer inviteDistribution(Integer companyId, Integer distributionId, String cooperationType, Integer userId) {
        return null;
    }
}
