package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.LenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private LenderService lenderService;

    @Override
    public void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType) {
        LenderInfo lenderInfo = (LenderInfo) lenderService.get(objectId).getData();
        if (lenderInfo == null) {
            map.put("result", "no_lender");
        } else {
            map.put("accrual", lenderInfo.getAccrual() == null ? 0 : lenderInfo.getAccrual());//总利息
            map.put("loan", lenderInfo.getLoan() == null ? 0 : lenderInfo.getLoan());//总贷款
            map.put("appraisal", lenderInfo.getAppraisal() == null ? 0 : lenderInfo.getAppraisal());//抵押物总评估
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lenderInfo.getEndAt());
            long nowTime = new Date().getTime();
            int day = (int) (calendar.getTimeInMillis() - nowTime) / (1000 * 3600 * 24);
            map.put("dayCount", day > 0 ? day : 0);//逾期天数
            List<TeamDTO> list = getLenderOrAsset(companyId, objectId, objectType);
            for (TeamDTO t : list) {
                Map<String, Object> task = getTaskCount(companyId, t.getUserId(), objectType);
                t.setFinishTask(Integer.parseInt(task.get("finish").toString()));
                t.setOngoingTask(Integer.parseInt(task.get("ongoing").toString()));
                t.setTotalTask(Integer.parseInt(task.get("total").toString()));
                t.setLeaveWordTime("");//最后留言时间
            }
            map.put("teams", list);//团队信息
            map.put("people", getPeopleNum(companyId, objectId, objectType));//团队人数
            map.put("result", "yes");
        }
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

    @Override
    public Integer createDistribution(String type, Integer id) {
        return null;
    }

    @Override
    public Integer inviteDistribution(Integer companyId, Integer distributionId, String cooperationType, Integer userId) {
        return null;
    }
}
