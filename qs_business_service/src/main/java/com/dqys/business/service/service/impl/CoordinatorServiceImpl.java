package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.CoordinatorEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.mapper.repay.RepayMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.AssetPackageEnum;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
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
    @Autowired
    private OURelationMapper ouRelationMapper;
    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private RepayMapper repayMapper;

    @Override
    public void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType, Integer userid) {
        UserTeam userTeam = new UserTeam();
        userTeam.setObjectType(objectType);
        userTeam.setObjectId(objectId);
        userTeam.setCompanyId(companyId);
        UserTeam team = new UserTeam();
        team = userTeamMapper.selectByPrimaryKeySelective(userTeam);
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
            if (team == null) {
                userTeam.setObjectId(lenderInfo.getAssetId());
                userTeam.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
                UserTeam teamAsset = userTeamMapper.selectByPrimaryKeySelective(userTeam);
                if (teamAsset != null) {
                    team = teamAsset;
                }
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

        if (team == null) {//判断是否在t_user_team表中添加了记录，添加了返回信息，没添加的返回id
            //需要判断userId是否拥有创建的权限？
            Map<String, Object> adminUser = coordinatorMapper.getAdminUser(companyId);
            Integer mangerId = MessageUtils.transMapToInt(adminUser, "id");
            userTeam.setMangerId(mangerId == null ? userid : mangerId);
            userTeam.setCtreaterId(userid);
            userTeamMapper.insertSelective(userTeam);//添加公司内成员协作器
            map.put("userTeamId", userTeam.getId());
            map.put("result", "yes_add");
        } else {
            List<TeamDTO> list = getLenderOrAsset(companyId, userTeam.getId(), userTeam.getObjectType());//获取借款人或是资产包的团队信息
            for (TeamDTO t : list) {//查询每个人员的任务数
                Map<String, Object> task = getTaskCount(companyId, t.getUserId(), objectType);
                t.setFinishTask(MessageUtils.transMapToInt(task, "finish"));
                t.setOngoingTask(MessageUtils.transMapToInt(task, "ongoing"));
                t.setTotalTask(MessageUtils.transMapToInt(task, "total"));
                t.setLeaveWordTime(MessageUtils.transMapToString(coordinatorMapper.getLastLeaveWord(t.getUserId()), "time"));//最后留言时间
            }
            map.put("companys", companyList(objectId, objectType));//对象类型相应的公司
            map.put("teams", list);//团队信息
            map.put("people", getPeopleNum(companyId, objectId, objectType));//团队人数
            map.put("userTeamId", team.getId());
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
    public Map addTeammate(Integer userTeamId, Integer userId, String remark, Integer[] userIds) throws BusinessLogException {
        businessLogService.add(userId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.ADD_COMMON_USER.getValue(), "添加参与人", "", 0, 0);
        Map map = new HashMap<>();
        for (Integer uid : userIds) {
            Integer flag = 0;
            Integer businessType = TeammateReEnum.BUSINESS_TYPE_TASK.getValue();
            Integer joinType = TeammateReEnum.JOIN_TYPE_PASSIVITY.getValue();
            flag = getTeammateFlag(userTeamId, uid, flag, businessType, joinType);//添加参与人
            if (flag > 0) {
                Integer result = messageService.add("任务邀请", remark, userId, uid, CoordinatorEnum.taskMes.getName(), MessageEnum.TASK.getValue());//添加消息记录
                if (result > 0) {
                    //发送短信或是邮件
                    messageService.sendSMS(uid,null,remark);
                }
            }
        }
        return map;
    }

    private Integer getTeammateFlag(Integer userTeamId, Integer uid, Integer flag, Integer businessType, Integer joinType) {
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
            if (teammateRe1.getStatus() == TeammateReEnum.STATUS_REFUSE.getValue()) {//邀请过的并且以前拒绝过的就修改为待接收状态
                teammateRe1.setStatus(TeammateReEnum.STATUS_INIT.getValue());
                flag = teammateReMapper.updateByPrimaryKeySelective(teammateRe1);
            }
        } else {
            if (list.size() == 0) {
                teammateRe.setType(TeammateReEnum.TYPE_ADMIN.getValue());
            } else if (list.size() == 1) {
                teammateRe.setType(TeammateReEnum.TYPE_AUXILIARY.getValue());
            } else {
                teammateRe.setType(TeammateReEnum.TYPE_PARTICIPATION.getValue());
            }
            teammateRe.setBusinessType(businessType);
            teammateRe.setJoinType(joinType);
            teammateRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
            flag = teammateReMapper.insertSelective(teammateRe);//添加参与人
        }
        return flag;
    }

    @Override
    public Map isAccept(Integer teammateId, Integer status) throws BusinessLogException {
        businessLogService.add(teammateId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.UPDATE_COMMON_USER.getValue(), "被邀请人同意或拒绝", "", 0, 0);
        Map<String, Object> map = new HashMap<>();
        TeammateRe teammateRe = new TeammateRe();
        teammateRe.setId(teammateId);
        teammateRe.setStatus(status);
        Integer result = teammateReMapper.updateByPrimaryKeySelective(teammateRe);
        if (status == 1) {
            if (result > 0) {
                Map userTeammate = coordinatorMapper.selectByUserTeamAndMateRe(teammateId);
                OURelation ouRelation = new OURelation();
                ouRelation.setStatus(OURelationEnum.STATUS_ACCEPT.getValue());
                ouRelation.setType(OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
                ouRelation.setObjectType(MessageUtils.transMapToInt(userTeammate, "object_type"));
                ouRelation.setObjectId(MessageUtils.transMapToInt(userTeammate, "object_id"));
                ouRelation.setUserId(MessageUtils.transMapToInt(userTeammate, "user_id"));
                ouRelation.setEmployerId(MessageUtils.transMapToInt(userTeammate, "user_team_id"));
                if (checkExist(ouRelation)) {
                    ouRelationMapper.insertSelective(ouRelation);
                }
                //如果是资产包，还需要加入资产包下的借款人
                if (ouRelation.getObjectType() == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
                    LenderInfo lenderInfo = new LenderInfo();
                    lenderInfo.setAssetId(ouRelation.getObjectId());
                    List<LenderInfo> lends = coordinatorMapper.selectByLender(lenderInfo);
                    for (LenderInfo len : lends) {
                        ouRelation.setObjectId(len.getId());
                        ouRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
                        if (checkExist(ouRelation)) {
                            ouRelationMapper.insertSelective(ouRelation);
                        }
                    }
                }
            }
            map.put("result", "yes");
        }
        return map;
    }

    private boolean checkExist(OURelation ouRelation) {
        OURelation our = new OURelation();
        our.setObjectId(ouRelation.getObjectId());
        our.setObjectType(ouRelation.getObjectType());
        our.setUserId(ouRelation.getUserId());
        List<OURelation> list = ouRelationMapper.selectBySelective(our);
        if (list.size() == 0) {
            Map map = coordinatorMapper.selectByBusinessId(ouRelation.getObjectType(), ouRelation.getObjectId());
            ouRelation.setBusinessId(MessageUtils.transMapToInt(map, "business_id"));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map addTeammate(Integer userTeammateId, Integer userId) throws BusinessLogException {
        businessLogService.add(userId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.ADD_COMMON_USER.getValue(), "主动加入案组", "", 0, 0);
        Map map = new HashMap<>();
        Integer flag = 0;
        getTeammateFlag(userTeammateId, userId, flag, TeammateReEnum.BUSINESS_TYPE_TASK.getValue(), TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue());
        return map;
    }

    @Override
    public void auditBusiness(Map map,Integer userId, Integer objectId, Integer objectType, Integer status) throws BusinessLogException {
        Integer operType=0;
        String text="";
        Integer receive_id=0;//录入人
        if(objectType==ObjectTypeEnum.LENDER.getValue()){
           LenderInfo len= coordinatorMapper.getLenderInfo(objectId);
            receive_id=len.getOperator();
            operType= LenderEnum.UPDATE_EDIT.getValue();
            text="借款人审核操作";
        }else if(objectType==ObjectTypeEnum.ASSETPACKAGE.getValue()){
            AssetInfo assetInfo=coordinatorMapper.getAssetInfo(objectId);
            receive_id=assetInfo.getOperator();
            operType= AssetPackageEnum.update.getValue();
            text="资产包审核操作";
        }
        businessLogService.add(objectId, objectType,operType,text , "", 0, 0);//添加操作日志
        List<Integer> ids = new ArrayList<>();
        ids.add(objectId);
        List<Integer> buinessIds = repayMapper.getBusinessId(objectType, ids);
        if (buinessIds.size() > 0) {
            Integer result = repayMapper.updateBusinessStatus(buinessIds.get(0), status);
            if (result > 0) {
                String content="您的审核"+(status==1?"通过":"不通过");
                messageService.add("审核结果",content,userId,receive_id,"",MessageEnum.SERVE.getValue());//添加通知消息
                //发送短信或邮件
                messageService.sendSMS(receive_id,null,content);
                map.put("result", "yes");
                return;
            }
        }
        map.put("result", "no");
    }

    @Override
    public void isPause(Map map, Integer objectId, Integer objectType, Integer status,Integer userId) throws BusinessLogException {
       Integer operType=0;
        String text="";
        List<Integer> receive_ids=new ArrayList<>();
        LenderInfo lenderInfo=new LenderInfo();
        if(objectType==ObjectTypeEnum.LENDER.getValue()){
            lenderInfo.setId(objectId);
            lenderInfo.setIsStop(status);
            coordinatorMapper.updateLender(lenderInfo);//借款人暂停后开启
            operType= LenderEnum.UPDATE_EDIT.getValue();
            text="借款人暂停操作";
            receive_ids=coordinatorMapper.getUserIdByObjUserRelToLender(objectType,objectId);
        }else if(objectType==ObjectTypeEnum.ASSETPACKAGE.getValue()){
            AssetInfo assetInfo=new AssetInfo();
            assetInfo.setId(objectId);
            assetInfo.setIsStop(status);
            if(assetInfoMapper.update(assetInfo)>0){//资产包暂停后，下面的借款人全部暂停了
                lenderInfo.setIsStop(status);
                lenderInfo.setAssetId(objectId);
                coordinatorMapper.updateLender(lenderInfo);
            };
            operType= AssetPackageEnum.update.getValue();
            text="资产包暂停操作";
            receive_ids=coordinatorMapper.getUserIdByObjUserRelToAsset(objectType,objectId);
        }
        String content="对暂停操作进行了"+(status==0?"关闭":"开启");
        businessLogService.add(objectId, objectType,operType,text , "", 0, 0);//添加操作日志
        for(Integer receive_id :receive_ids){
            messageService.add(text,content,userId,receive_id,"",MessageEnum.SERVE.getValue());//添加通知消息
            //发送短信或邮件
            messageService.sendSMS(receive_id, null, content);
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
    @Override
    public List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType) {
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

}
