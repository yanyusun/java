package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.CoordinatorEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.cases.CaseInfoMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.mapper.repay.RepayMapper;
import com.dqys.business.orm.mapper.zcy.ZcyEstatesMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.orm.pojo.zcy.ZcyEstates;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.*;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.SmsUtil;
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
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private CaseInfoMapper caseInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private ZcyEstatesMapper zcyEstatesMapper;
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
    @Autowired
    private CompanyTeamMapper companyTeamMapper;

    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;


    @Override
    public void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType, Integer userid) {
        UserTeam userTeam = new UserTeam();
        userTeam.setObjectType(objectType);
        userTeam.setObjectId(objectId);
        userTeam.setCompanyId(companyId);
        UserTeam team = new UserTeam();
        team = userTeamMapper.selectByPrimaryKeySelective(userTeam);
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {//借款人
            LenderInfo lenderInfo = getLenderInfo(map, objectId);//获取借款人信息
            if (lenderInfo == null) return;
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
            if (getAsset(map, objectId)) return;
        }

        if (team == null) {//判断是否在t_user_team表中添加了记录，添加了返回信息，没添加的返回id
            //需要判断userId是否拥有创建的权限？
            Map<String, Object> adminUser = coordinatorMapper.getAdminUser(companyId);
            Integer mangerId = MessageUtils.transMapToInt(adminUser, "id");
            userTeam.setMangerId(mangerId == null ? userid : mangerId);
            userTeam.setCtreaterId(userid);
            //查询操作人员被操作事物关系
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setUserId(userid);
            objectUserRelationQuery.setObjectType(objectType);
            objectUserRelationQuery.setType(3);//业务流转类型
            List<ObjectUserRelation> list = objectUserRelationMapper.list(objectUserRelationQuery);
            for (ObjectUserRelation obj : list) {
                if (obj.getVisibleType() != null && obj.getVisibleType() == 1) {
                    userTeam.setObjectOperStatus(1);//设置对象可操作状态
                    break;
                }
            }
            userTeamMapper.insertSelective(userTeam);//添加公司内成员协作器
            map.put("userTeamId", userTeam.getId());
            map.put("result", "yes");
        } else {
            List<TeamDTO> list = getLenderOrAsset(companyId, userTeam.getObjectId(), userTeam.getObjectType());//获取借款人或是资产包的团队信息
            TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(team.getMangerId());//管理员信息
            if (tUserInfo != null) {
                TeamDTO teamDTO = new TeamDTO();
                teamDTO.setUserId(tUserInfo.getId());
                teamDTO.setRealName(tUserInfo.getRealName());
                teamDTO.setRoleType(1);
                list.add(teamDTO);
            }
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

    /**
     * 获取资产包信息
     *
     * @param map
     * @param objectId
     * @return
     */
    private boolean getAsset(Map<String, Object> map, Integer objectId) {
        AssetInfo assetInfo = assetInfoMapper.get(objectId);
        if (assetInfo == null) {
            map.put("result", "no_asset");//资产包不存在
            return true;
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
        return false;
    }

    /**
     * 获取借款人信息
     *
     * @param map
     * @param objectId
     * @return
     */
    private LenderInfo getLenderInfo(Map<String, Object> map, Integer objectId) {
        LenderInfo lenderInfo = lenderInfoMapper.get(objectId);
        if (lenderInfo == null) {
            map.put("result", "no_lender");//借款人不存在
            return null;
        } else {
            map.put("accrual", lenderInfo.getAccrual() == null ? 0 : lenderInfo.getAccrual());//总利息
            map.put("loan", lenderInfo.getLoan() == null ? 0 : lenderInfo.getLoan());//总贷款
            map.put("appraisal", lenderInfo.getAppraisal() == null ? 0 : lenderInfo.getAppraisal());//抵押物总评估
            Calendar calendar = Calendar.getInstance();
            if (lenderInfo.getEndAt() != null) {
                calendar.setTime(lenderInfo.getEndAt());
            }
            long nowTime = new Date().getTime();
            int day = (int) (calendar.getTimeInMillis() - nowTime) / (1000 * 3600 * 24);
            map.put("dayCount", day > 0 ? day : 0);//逾期天数
        }

        return lenderInfo;
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
        Map map = new HashMap<>();
        int num = 0;
        UserTeam team = new UserTeam();
        team.setId(userTeamId);
        UserTeam userTeam = userTeamMapper.selectByPrimaryKeySelective(team);
        String title = "未知";
        if (userTeam != null) {
            title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.INSIDE.getValue());
        }
        Map userAndCompany = coordinatorMapper.getUserAndCompanyByUserId(userId);
        for (Integer uid : userIds) {
            Integer flag = 0;
            TeammateRe teammateRe = new TeammateRe();
            teammateRe.setUserId(uid);
            teammateRe.setUserTeamId(userTeamId);
            teammateRe.setJoinType(TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue());
            teammateRe.setBusinessType(TeammateReEnum.BUSINESS_TYPE_TASK.getValue());
            flag = getTeammateFlag(teammateRe);//添加参与人
            if (flag == -3) {//人数已满
                map.put("msg", "人数已到极限，此次成功邀请" + num + "人");
                break;
            }
            if (flag == -4) {//第一个添加人员不是管理者
                map.put("msg", "第一个不是管理者");
                break;
            }
            if (flag > 0) {
                num++;
                Integer result = messageService.add(title, remark, userId, uid, CoordinatorEnum.taskMes.getName(), MessageEnum.TASK.getValue(), MessageBTEnum.INSIDE.getValue(),
                        MessageUtils.setOperUrl("/coordinator/isAccept?status=1&teammateId=" + teammateRe.getId(), null, "/coordinator/isAccept?status=2&teammateId=" + teammateRe.getId(), null, null));//添加消息记录
                if (result > 0) {
                    //发送短信
                    messageService.sendSmsByTeammate(userTeam, userAndCompany, uid, remark);
                }
            }
        }
        if (num > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
//        businessLogService.add(userId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.ADD_COMMON_USER.getValue(), "添加参与人", "", 0, 0);
        return map;
    }

    /**
     * 添加参与人信息
     *
     * @return
     */
    private Integer getTeammateFlag(TeammateRe teammateRe) {
        Integer flag = 0;
        List<TeammateRe> users = new ArrayList<>();
        TeammateRe re = new TeammateRe();
        re.setUserTeamId(teammateRe.getUserTeamId());
        //邀请的参与协作器的人员数
        re.setStatus(TeammateReEnum.STATUS_INIT.getValue());//待接收
        Integer size = teammateReMapper.selectSelective(re).size();
        re.setStatus(TeammateReEnum.STATUS_ACCEPT.getValue());//接收
        size += teammateReMapper.selectSelective(re).size();
        if (size > 5 && teammateRe.getJoinType().equals(TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue())) {
            return -2;//主动加入人数已满
        }
        if (size > 23 && teammateRe.getJoinType().equals(TeammateReEnum.JOIN_TYPE_PASSIVITY.getValue())) {
            return -3;//被邀请的人数已满
        }
        if (size == 0) {
            TUserTag tags = tUserTagMapper.selectByUserId(teammateRe.getUserId()).get(0);
            if (RoleTypeEnum.REGULATOR.getValue() != (int) tags.getRoleId()) {
                return -4;//第一个添加人员不是管理者
            }
            teammateRe.setType(TeammateReEnum.TYPE_ADMIN.getValue());
        } else if (size == 1) {
            teammateRe.setType(TeammateReEnum.TYPE_AUXILIARY.getValue());
        } else {
            teammateRe.setType(TeammateReEnum.TYPE_PARTICIPATION.getValue());
        }
        teammateRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
        re.setUserId(teammateRe.getUserId());
        re.setStatus(null);
        users = teammateReMapper.selectSelective(re);//查询用于判断人员加入过这个协作器没有
        if (users.size() > 0) {
            TeammateRe teammateRe1 = users.get(0);
            if (teammateRe1.getStatus() == TeammateReEnum.STATUS_REFUSE.getValue()) {//邀请过的并且以前拒绝过的就修改为待接收状态
                teammateRe1.setStatus(TeammateReEnum.STATUS_INIT.getValue());
                teammateRe1.setType(teammateRe.getType());
                flag = teammateReMapper.updateByPrimaryKeySelective(teammateRe1);
            } else {
                flag = -1;//已经加入过案组
            }
        } else {
            flag = teammateReMapper.insertSelective(teammateRe);//添加参与人
        }
        return flag;
    }

    /**
     * 设定业务id
     *
     * @param ouRelation
     * @return
     */
    private boolean checkExist(OURelation ouRelation) {
        OURelation our = new OURelation();
        our.setObjectId(ouRelation.getObjectId());
        our.setObjectType(ouRelation.getObjectType());
        our.setUserId(ouRelation.getUserId());
//        our.setType(ouRelation.getType());
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
    public Map isAccept(Integer teammateId, Integer status, Integer userId) throws BusinessLogException {
//        businessLogService.add(teammateId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.UPDATE_COMMON_USER.getValue(), "被邀请人同意或拒绝", "", 0, 0);
        Map<String, Object> map = new HashMap<>();
        TeammateRe teammateRe = teammateReMapper.selectByPrimaryKey(teammateId);
        if (teammateRe == null) {
            map.put("result", "no_exist");
            return map;
        }
        UserTeam userT = null;
        if (teammateRe.getUserId() != userId) {
            UserTeam userTeam = new UserTeam();
            userTeam.setId(teammateRe.getUserTeamId());
            userT = userTeamMapper.selectByPrimaryKeySelective(userTeam);
            if (userT == null) {
                map.put("result", "no_exist");//不存在
                return map;
            }
//            if (userT.getMangerId() != userId) {
//                map.put("result", "no_authority");//没有权限
//                return map;
//            }
        }
        teammateRe.setStatus(status);
        Integer result = teammateReMapper.updateByPrimaryKeySelective(teammateRe);
        if (status == 1 && result > 0) {
            Map userTeammate = coordinatorMapper.selectByUserTeamAndMateRe(teammateId);//查询团队中的协作器信息
            OURelation ouRelation = new OURelation();
            String object_oper_status = MessageUtils.transMapToString(userTeammate, "object_oper_status");//对象可操作状态:0拥有对其全部的操作1来自业务流转只对其下部分对象有操作权限',
            if ("1".equals(object_oper_status)) {
                List<OURelation> list = ouRelationMapper.findByOURelation(userT.getMangerId(), userT.getObjectType());//查询业务流转对象
                if (list.size() > 0) {
                    ouRelation = list.get(0);
                }
            } else {
                ouRelation.setStatus(OURelationEnum.STATUS_ACCEPT.getValue());
                ouRelation.setObjectType(MessageUtils.transMapToInt(userTeammate, "object_type"));
                ouRelation.setObjectId(MessageUtils.transMapToInt(userTeammate, "object_id"));
            }
            ouRelation.setType(OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
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
                    ouRelation.setId(null);
                    ouRelation.setObjectId(len.getId());
                    ouRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
                    if (checkExist(ouRelation)) {
                        ouRelationMapper.insertSelective(ouRelation);
                    }
                }
            }
            //发送消息
            map.put("result", "yes");
        } else if (status == 2 && result > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addTeammate(Integer userTeammateId, Integer userId) throws BusinessLogException {
        Map map = new HashMap<>();
        Integer flag = 0;
        TeammateRe teammateRe = new TeammateRe();
        teammateRe.setUserId(userId);
        teammateRe.setUserTeamId(userTeammateId);
        teammateRe.setJoinType(TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue());
        teammateRe.setBusinessType(TeammateReEnum.BUSINESS_TYPE_TASK.getValue());
        flag = getTeammateFlag(teammateRe);
        if (flag > 0) {
            UserTeam userTeam = new UserTeam();
            userTeam.setId(userTeammateId);
            UserTeam userT = userTeamMapper.selectByPrimaryKeySelective(userTeam);//团队信息
            if (userT != null) {
                TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(userT.getMangerId());//接受者
                TUserInfo sendUser = tUserInfoMapper.selectByPrimaryKey(userId);//请求者
                String content = "";//消息内容
                if (tUserInfo != null && sendUser != null) {
                    SmsUtil smsUtil = new SmsUtil();
                    content = smsUtil.sendSms(SmsEnum.INITIATIVE_JOIN.getValue(), tUserInfo.getMobile(), tUserInfo.getRealName(), sendUser.getRealName(),
                            ObjectTypeEnum.getObjectTypeEnum(userT.getObjectType()).getName(), getObjectName(userT.getObjectType(), userT.getObjectId()));//发送短信
                    String title = getMessageTitle(userT.getObjectId(), userT.getObjectType(), MessageBTEnum.INITIATIVE.getValue());
                    messageService.add(title, content, userId, userT.getMangerId(), "", MessageEnum.SERVE.getValue(), MessageBTEnum.INITIATIVE.getValue(),
                            MessageUtils.setOperUrl("/coordinator/isAccept?status=1&teammateId=" + teammateRe.getId(), null, "/coordinator/isAccept?status=2&teammateId=" + teammateRe.getId(), null, null));
                }
            }
            map.put("result", "yes");
        } else if (flag == -1) {
            map.put("result", "exist");//已经纯在
        } else if (flag == -2) {
            map.put("result", "limitation");//人数已满
        } else if (flag == -4) {
            map.put("result", "no_admin");//第一个不是管理者
        } else {
            map.put("result", "no");
        }
//        businessLogService.add(userId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.ADD_COMMON_USER.getValue(), "主动加入案组", "", 0, 0);
        return map;
    }

    @Override
    public void auditBusiness(Map map, Integer userId, Integer objectId, Integer objectType, Integer status) throws BusinessLogException {
        Integer operType = 0;
        String text = "";
        Integer receive_id = 0;//录入人
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            LenderInfo len = coordinatorMapper.getLenderInfo(objectId);
            if (len != null) {
                receive_id = len.getOperator() == null ? 0 : len.getOperator();
            }
            operType = LenderEnum.UPDATE_EDIT.getValue();
            text = "借款人审核操作";
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo assetInfo = coordinatorMapper.getAssetInfo(objectId);
            if (assetInfo != null) {
                receive_id = assetInfo.getOperator() == null ? 0 : assetInfo.getOperator();
            }
            operType = AssetPackageEnum.update.getValue();
            text = "资产包审核操作";
        }
        List<Integer> ids = new ArrayList<>();
        ids.add(objectId);
        List<Integer> buinessIds = repayMapper.getBusinessId(objectType, ids);
//        businessLogService.add(objectId, objectType, operType, text, "", 0, 0);//添加操作日志
        if (buinessIds.size() > 0) {
            Integer result = repayMapper.updateBusinessStatus(buinessIds.get(0), status);
            if (result > 0) {
                SmsUtil smsUtil = new SmsUtil();//发送短信通知
                Integer code = 0;
                if (status == BusinessStatusEnum.platform_pass.getValue()) {
                    code = SmsEnum.BUSINESS_AUDIT_YES.getValue();
                } else {
                    code = SmsEnum.BUSINESS_AUDIT_NO.getValue();
                }
                Map userC = coordinatorMapper.getUserAndCompanyByUserId(receive_id);
                String content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), getObjectName(objectType, objectId));
                String title = getMessageTitle(objectId, objectType, MessageBTEnum.BUSINESS.getValue());
                messageService.add(title, content, userId, receive_id, "", MessageEnum.SERVE.getValue(), MessageBTEnum.BUSINESS.getValue(), "");//添加通知消息
                map.put("result", "yes");
                return;
            }
        }
        map.put("result", "no");
    }

    @Override
    public void isPause(Map map, Integer objectId, Integer objectType, Integer status, Integer userId) throws BusinessLogException {
        Integer operType = 0;//操作类型
        List<Map<String, Object>> receive_ids = new ArrayList<>();
        LenderInfo lenderInfo = new LenderInfo();
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            lenderInfo.setId(objectId);
            lenderInfo.setIsStop(status);
            LenderInfo lender = lenderInfoMapper.get(objectId);
            if (lender == null) {
                map.put("result", "noExist");//不存在记录
                return;
            }
            if (lender.getIsStop() == status) {
                map.put("result", "repetition");//重复操作
                return;
            }
            coordinatorMapper.updateLender(lenderInfo);//借款人暂停后开启
            operType = LenderEnum.UPDATE_EDIT.getValue();
            receive_ids = coordinatorMapper.getUserIdByObjUserRelToLender(objectType, objectId);
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo assetInfo = new AssetInfo();
            assetInfo.setId(objectId);
            assetInfo.setIsStop(status);
            AssetInfo asset = assetInfoMapper.get(objectId);
            if (asset == null) {
                map.put("result", "noExist");//不存在记录
                return;
            }
            if (asset.getIsStop() == status) {
                map.put("result", "repetition");//重复操作
                return;
            }
            if (assetInfoMapper.update(assetInfo) > 0) {//资产包暂停后，下面的借款人全部暂停了
                lenderInfo.setIsStop(status);
                lenderInfo.setAssetId(objectId);
                coordinatorMapper.updateLender(lenderInfo);
            }
            ;
            operType = AssetPackageEnum.update.getValue();
            receive_ids = coordinatorMapper.getUserIdByObjUserRelToAsset(objectType, objectId);//被通知人员
        }
        Integer code = 0;
        if (status == 1) {
            code = SmsEnum.BUSINESS_PAUSE.getValue();//开启暂停
        } else {
            code = SmsEnum.BUSINESS_OPEN.getValue();//关闭暂停
        }
        SmsUtil smsUtil = new SmsUtil();//发送短信通知
        for (Map receive_id : receive_ids) {
            Integer rec = MessageUtils.transMapToInt(receive_id, "user_id");
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(rec);
            Map oper = coordinatorMapper.getUserAndCompanyByUserId(userId);
            String content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(oper, "companyName"),
                    MessageUtils.transMapToString(oper, "companyType"), MessageUtils.transMapToString(oper, "realName"),
                    ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), getObjectName(objectType, objectId));
            String title = getMessageTitle(objectId, objectType, MessageBTEnum.BUSINESS_PAUSE.getValue());
            messageService.add(title, content, userId, rec, "", MessageEnum.SERVE.getValue(), MessageBTEnum.BUSINESS_PAUSE.getValue(), "");//添加通知消息
        }
        map.put("result", "yes");
//        businessLogService.add(objectId, objectType, operType, text, "", 0, 0);//添加操作日志
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
    @Override
    public Map<String, Object> getTaskCount(Integer companyId, Integer userId, Integer objectType) {
        Map<String, Object> map = coordinatorMapper.getTaskRatio(companyId, userId, objectType);//业绩比例
        Map<String, Object> map2 = coordinatorMapper.getTaskOngoing(companyId, userId, objectType);//获取当前进行的任务数
        map.put("ongoing", map2.get("ongoing"));
        return map;
    }

    @Override
    public Map<String, Object> getObjectProperty(Integer objectId, Integer objectType) {
        Map<String, Object> map = new HashMap<>();
        String objectNo = "";//对象编号
        if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo assetInfo = assetInfoMapper.get(objectId);
            objectNo = assetInfo.getAssetNo();
        }
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            LenderInfo lenderInfo = lenderInfoMapper.get(objectId);
            objectNo = lenderInfo.getLenderNo();
        }
        map.put("objectNo", objectNo);
        return map;
    }

    @Override
    public String getMessageTitle(Integer objectId, Integer objectType, Integer businessType) {
        String title = ObjectTypeEnum.getObjectTypeEnum(objectType).getName();//标题：对象类型+对象名称+业务类型
        title += getObjectName(objectType, objectId);//对象名称编号
        title += MessageBTEnum.get(businessType);
        return title;
    }

    @Override
    public Map deleteTeammatUser(Integer userId, Integer teamUserId, Integer userTeamId, Integer status, Integer substitutionUid) throws Exception {
        Map map = new HashMap<>();
        Map code = new HashMap<>();
        UserTeam ut = new UserTeam();
        ut.setId(userTeamId);
        UserTeam userTeam = userTeamMapper.selectByPrimaryKeySelective(ut);
        if (userTeam == null) {
            map.put("result", "no");
            map.put("msg", "协作器不存在");
            return map;
        }
        if (status == null && substitutionUid == null) {//删除协作器联系人
            code = teamDel(userId, teamUserId, userTeamId, status, substitutionUid);
        } else if (status != null && substitutionUid != null) {//替换协作器联系人
            if (userId.equals(substitutionUid)) {
                code = teamDel(userId, teamUserId, userTeamId, status, substitutionUid);//删除联系人
                if ("200".equals(code.get("code")) && status == 1) {
                    teamDel(userId, substitutionUid, userTeamId, null, null);//如果替换人在该团队已经存在，那么先删除之后
                    TeammateRe teammateRe = (TeammateRe) code.get("teammateRe");
                    teammateRe.setId(null);
                    teammateRe.setUserId(substitutionUid);
                    teammateReMapper.insert(teammateRe);
                    Map res = isAccept(teammateRe.getId(), status, substitutionUid);
                    if (MessageUtils.transMapToString(res, "result").equals("yes")) {
                        SmsUtil smsUtil = new SmsUtil();//发送短信通知
                        Map userC = coordinatorMapper.getUserAndCompanyByUserId(teamUserId);
                        Map oper = coordinatorMapper.getUserAndCompanyByUserId(substitutionUid);
                        String content = smsUtil.sendSms(SmsEnum.REPLACE.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                                MessageUtils.transMapToString(oper, "realName"),
                                ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()), TeammateReEnum.get(teammateRe.getType()));
                        String title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.REPLACE.getValue());
                        messageService.add(title, content, userId, substitutionUid, "", MessageEnum.TASK.getValue(), MessageBTEnum.REPLACE.getValue(), "");//添加通知消息
                        map.put("result", "yes");
                    } else {
                        throw new Exception();
                    }
                }
            } else {
                map.put("result", "no_self");
                map.put("msg", "不正确的访问");
            }
        } else if (status == null && substitutionUid != null) {//发送短信通知替补的人
            SmsUtil smsUtil = new SmsUtil();//发送短信通知
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(substitutionUid);
            Map oper = coordinatorMapper.getUserAndCompanyByUserId(userId);
            String content = smsUtil.sendSms(SmsEnum.REPLACE_CONTACTS.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                    MessageUtils.transMapToString(oper, "realName"),
                    ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()));
            String title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.REPLACE_CONTACTS.getValue());
            String url = MessageUtils.setOperUrl("/coordinator/delUser?status=0&teamUserId=" + teamUserId + "&userTeamId=" + userTeamId + "&substitutionUid=" + substitutionUid, null,
                    "/coordinator/delUser?status=1&teamUserId=" + teamUserId + "&userTeamId=" + userTeamId + "&substitutionUid=" + substitutionUid, null, null);
            messageService.add(title, content, userId, substitutionUid, "", MessageEnum.TASK.getValue(), MessageBTEnum.REPLACE_CONTACTS.getValue(), url);//添加通知消息
            map.put("result", "yes");
        } else {
            map.put("result", "no_par");
            map.put("msg", "参数错误");
        }
        if (code != null) {
            if ("200".equals(code.get("code"))) {
                map.put("result", "yes");
            } else if ("505".equals(code.get("code"))) {
                throw new Exception();
            } else if ("501".equals(code.get("code"))) {
                map.put("result", "no");
                map.put("msg", "删除失败");
            } else if ("500".equals(code.get("code"))) {
                map.put("result", "no_tiBu");
                map.put("msg", "需要替补人");
            } else if ("502".equals(code.get("code"))) {
                map.put("result", "no_lowRank");
                map.put("msg", "下级不能删除上级");
            } else if ("503".equals(code.get("code"))) {
                map.put("result", "no_delRepeat");
                map.put("msg", "不能重复删除");
            }
        }
        return map;
    }

    @Override
    public String delCoordinator(Integer companyId, Integer objectId, Integer objectType) {
        boolean flag = false;
        if (ObjectTypeEnum.PAWN.getValue().equals(objectType)) {
            PawnInfo pawnInfo = pawnInfoMapper.get(objectId);
            if (pawnInfo != null) {
                Integer lenderId = pawnInfo.getLenderId();
                Integer count = getUserTeamByObjectTypeCount(companyId, objectId, objectType, lenderId);
                if (count != null && count == 0) {
                    objectType = ObjectTypeEnum.LENDER.getValue();
                    objectId = lenderId;
                    flag = true;
                }
            }
        } else if (ObjectTypeEnum.IOU.getValue().equals(objectType)) {
            IOUInfo iouInfo = iouInfoMapper.get(objectId);
            if (iouInfo != null) {
                Integer lenderId = iouInfo.getLenderId();
                Integer count = getUserTeamByObjectTypeCount(companyId, objectId, objectType, lenderId);
                if (count != null && count == 0) {
                    objectType = ObjectTypeEnum.LENDER.getValue();
                    objectId = lenderId;
                    flag = true;
                }
            }
        } else {
            flag = true;
        }
        if (flag) {
            List<Integer> userTeamIds = userTeamMapper.selectByCompany(companyId, objectId, objectType);//协作器id集合
            if (userTeamIds.size() > 0) {
                teammateReMapper.deleteByUserTeamId(userTeamIds);//删除团队
                ouRelationMapper.deleteByUserTeamId(userTeamIds);//删除业务表
                userTeamMapper.deleteByCompany(companyId);//删除协作器
                return "yes";
            }
        }
        return "no";
    }

    private Integer getUserTeamByObjectTypeCount(Integer companyId, Integer objectId, Integer objectType, Integer lenderId) {
        List<Integer> userTeams = userTeamMapper.selectByCompany(companyId, lenderId, ObjectTypeEnum.LENDER.getValue());
        if (userTeams.size() > 0) {
            Integer userTeamId = userTeams.get(0);
            OURelation ouRelation = new OURelation();
            ouRelation.setType(OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
            ouRelation.setEmployerId(userTeamId);
            ouRelation.setObjectType(objectType);
            List<OURelation> list = ouRelationMapper.selectBySelective(ouRelation);
            Integer count = 0;
            if (list.size() > 0) {
                ouRelation.setObjectId(objectId);
                count = ouRelationMapper.deleteByPrimaryKey(ouRelation);
            }
            return list.size() - count;
        }
        return null;
    }

    @Override
    public String getObjectName(Integer objectType, Integer objectId) {
        String name = "";
        if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(objectType)) {//资产包
            AssetInfo info = assetInfoMapper.get(objectId);
            if (info != null) {
                name = info.getAssetNo();
            }
        } else if (ObjectTypeEnum.PAWN.getValue().equals(objectType)) {//抵押物
            PawnInfo info = pawnInfoMapper.get(objectId);
            if (info != null) {
                name = info.getPawnNo();
            }
        } else if (ObjectTypeEnum.LENDER.getValue().equals(objectType)) {//借款人
            LenderInfo info = lenderInfoMapper.get(objectId);
            if (info != null) {
                name = info.getLenderNo();
            }
        } else if (ObjectTypeEnum.IOU.getValue().equals(objectType)) {//借据
            IOUInfo info = iouInfoMapper.get(objectId);
            if (info != null) {
                name = info.getIouNo();
            }
        } else if (ObjectTypeEnum.ASSETSOURCE.getValue().equals(objectType)) {//资产源
            ZcyEstates info = zcyEstatesMapper.selectByPrimaryKey(objectId);
            if (info != null) {
                name = info.getHouseNo();
            }
        } else if (ObjectTypeEnum.CASE.getValue().equals(objectType)) {//案件
            CaseInfo info = caseInfoMapper.get(objectId);
            if (info != null) {
                name = info.getCaseNo();
            }
        }
        return name;
    }

    @Override
    public Integer insetOUReationByFlowWork(Integer userId, Integer objectId, Integer objectType) {
        Integer lenderId = 0;
        if (ObjectTypeEnum.PAWN.getValue().equals(objectType)) {
            PawnInfo info = pawnInfoMapper.get(objectId);
            if (info != null) {
                lenderId = info.getLenderId();
            }
        } else if (ObjectTypeEnum.IOU.getValue().equals(objectType)) {
            IOUInfo info = iouInfoMapper.get(objectId);
            if (info != null) {
                lenderId = info.getLenderId();
            }
        }
        Integer count = 0;
        List<OURelation> list = ouRelationMapper.selectOURelationByUserTeam(userId, lenderId, ObjectTypeEnum.LENDER.getValue());//借款人协作器下的事物关系表中的所有操作人员
        for (OURelation our : list) {
            our.setId(null);
            our.setObjectId(objectId);
            our.setObjectType(objectType);
            OURelation ouRelation = new OURelation();
            ouRelation.setObjectId(our.getObjectId());
            ouRelation.setObjectType(our.getObjectType());
            ouRelation.setUserId(our.getUserId());
            List<OURelation> relations = ouRelationMapper.selectBySelective(ouRelation);//判断对象和操作人员是否已经存在
            if (relations.size() == 0) {
                count += ouRelationMapper.insertSelective(our);
            }
        }
        return count;
    }

    @Override
    public Boolean verdictOrganization(Integer flowId, Integer flowType, Integer onStatus, Integer type) {
        boolean flag = false;
        Integer coll = 0;
        Integer lay = 0;
        Integer age = 0;
        if (flowType == ObjectTypeEnum.PAWN.getValue()) {
            PawnInfo info = pawnInfoMapper.get(flowId);
            if (info != null) {
                coll = info.getOnCollection();
                lay = info.getOnLawyer();
                age = info.getOnAgent();
            } else {
                return flag;
            }
        } else if (flowType == ObjectTypeEnum.IOU.getValue()) {
            IOUInfo info = iouInfoMapper.get(flowId);
            if (info != null) {
                coll = info.getOnCollection();
                lay = info.getOnLawyer();
                age = info.getOnAgent();
            } else {
                return flag;
            }
        }
        if (type == UserInfoEnum.USER_TYPE_COLLECTION.getValue() && coll != onStatus) {
            coll = onStatus;
            flag = true;
        } else if (type == UserInfoEnum.USER_TYPE_JUDICIARY.getValue() && lay != onStatus) {
            lay = onStatus;
            flag = true;
        } else if (type == UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue() && age != onStatus) {
            age = onStatus;
            flag = true;
        }
        if (flag) {
            if (flowType == ObjectTypeEnum.PAWN.getValue()) {
                PawnInfo info = pawnInfoMapper.get(flowId);
                if (info != null) {
                    info.setOnCollection(coll);
                    info.setOnAgent(age);
                    info.setOnLawyer(lay);
                    pawnInfoMapper.update(info);//修改抵押物的机构处置状态
                }
            } else if (flowType == ObjectTypeEnum.IOU.getValue()) {
                IOUInfo info = iouInfoMapper.get(flowId);
                if (info != null) {
                    info.setOnCollection(coll);
                    info.setOnAgent(age);
                    info.setOnLawyer(lay);
                    iouInfoMapper.update(info);//修改借据的机构处置状态
                }
            }
        }
        return flag;
    }

    @Override
    public Map businessFlow(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer operType, Integer companyTeamId) {
        Map map = new HashMap<>();
        if (companyTeamId == null) {
            CompanyTeam team = companyTeamMapper.getByTypeId(objectType, objectId);//协作器id参数为null，自己查询协作器id
            if (team != null) {
                companyTeamId = team.getId();
            }
        }
        if (ObjectTypeEnum.PAWN.getValue() == flowType) {//抵押物
            if (PawnEnum.MAINTAIN_REGULAR.getValue() == operType) {//维持常规催收
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 1, 1);
            } else if (PawnEnum.MARKET_DISPOSITION.getValue() == operType) {//市场处置
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 1, 1, 0);
            } else if (PawnEnum.CM_SIMULTANEOUS.getValue() == operType) {//催收/市场同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 1, 0);
            } else if (PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue() == operType) {//执行司法化解
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 1, 0, 1);
            } else if (PawnEnum.CJ_SIMULTANEOUS.getValue() == operType) {//催收/司法化解同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 0, 1);
            } else if (PawnEnum.CMJ_SIMULTANEOUS.getValue() == operType) {//催收、市场、司法同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 0, 0);
            } else {
                map.put("result", "no_operType");//业务操作类型有误
            }
        } else if (ObjectTypeEnum.IOU.getValue() == flowType) {//借据
            if (IouEnum.MAINTAIN_REGULAR.getValue() == operType) {//维持常规催收
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 1, 1);
            } else if (IouEnum.MARKET_DISPOSITION.getValue() == operType) {//市场处置
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 1, 1, 0);
            } else if (IouEnum.CM_SIMULTANEOUS.getValue() == operType) {//催收/市场同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 1, 0);
            } else if (IouEnum.EXECUTE_JUSTICE_RESOLVE.getValue() == operType) {//执行司法化解
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 1, 0, 1);
            } else if (IouEnum.CJ_SIMULTANEOUS.getValue() == operType) {//催收/司法化解同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 0, 1);
            } else if (IouEnum.CMJ_SIMULTANEOUS.getValue() == operType) {//催收、市场、司法同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 0, 0);
            } else {
                map.put("result", "no_operType");//业务操作类型有误
            }
        } else {
            map.put("result", "no_flowType");//流转对象类型有误
        }

        return map;
    }

    @Override
    public Map sendBusinessFlowResult(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer operType, Integer receiveUserId, Integer status) {
        Map map = new HashMap<>();
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String operation = "";
        if (ObjectTypeEnum.PAWN.getValue() == flowType) {
            operation = PawnEnum.getPawnEnum(operType).getName();
        } else if (ObjectTypeEnum.IOU.getValue() == flowType) {
            operation = IouEnum.getIouEnum(operType).getName();
        }
        String result = messageService.businessFlowResult(objectId, objectType, flowId, flowType, operation, userId, receiveUserId, status);
        map.put("result", result);
        return map;
    }

    /**
     * @param map
     * @param objectId
     * @param objectType
     * @param flowId
     * @param flowType
     * @param operation
     * @param coll       常规催收（0可以1不能）
     * @param lawy       司法化解（0可以1不能）
     * @param agen       市场处置（0可以1不能）
     */
    private void setFlow(Map map, Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer operType, String operation, Integer companyTeamId, Integer coll, Integer lawy, Integer agen) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String operUrl = MessageUtils.setOperUrl("/coordinator/businessFlowResult?status=1&objectId=" + objectId + "&objectType=" + objectType +
                        "&flowId=" + flowId + "&flowType=" + flowType + "&operType=" + operType + "&receiveUserId=" + userId, null,
                "/coordinator/businessFlowResult?status=0&objectId=" + objectId + "&objectType=" + objectType +
                        "&flowId=" + flowId + "&flowType=" + flowType + "&operType=" + operType + "&receiveUserId=" + userId, null,
                "?type=3&flowId=" + flowId + "&flowType=" + flowType + "&companyTeamId=" + companyTeamId + "&operType=" + operType + "&userId=" + userId);
        //消息列表使用的访问参数拼接
        boolean c = false;
        boolean l = false;
        boolean a = false;
        //获取分配器中参与的所有公司类型（31催收32律所33中介）
        List<Map> list = coordinatorMapper.findUserTypeByCompanyTeam(objectId, objectType);
        for (Map m : list) {
            if (UserInfoEnum.USER_TYPE_COLLECTION.getValue() == MessageUtils.transMapToInt(m, "userType")) {//催收
                c = true;
            } else if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue() == MessageUtils.transMapToInt(m, "userType")) {//律所
                l = true;
            } else if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue() == MessageUtils.transMapToInt(m, "userType")) {//中介
                a = true;
            }
        }
        if ((coll == 0 && !c) || (lawy == 0 && !l) || (agen == 0 && !a)) {//判断是否符合，发送给平台管理员短信
            messageService.businessFlow(objectId, objectType, flowId, flowType, operation, userId, operUrl);
        }
        //进行处置机构发送短信
        messageService.judicature(objectId, objectType, flowId, flowType, userId, operation, lawy);//司法机构
        messageService.collectiones(objectId, objectType, flowId, flowType, userId, operation, coll);//催收机构
        messageService.intermediary(objectId, objectType, flowId, flowType, userId, operation, agen);//市场处置
        map.put("result", "yes");
    }

    private Map teamDel(Integer userId, Integer teamUserId, Integer userTeamId, Integer status, Integer substitutionUid) {
        Map map = new HashMap<>();
        TeammateRe teammateRe = new TeammateRe();
        teammateRe.setUserId(teamUserId);
        teammateRe.setUserTeamId(userTeamId);
        List<TeammateRe> list = teammateReMapper.selectSelective(teammateRe);//团队中用户
        teammateRe.setUserId(userId);
        List<TeammateRe> list1 = teammateReMapper.selectSelective(teammateRe);//操作用户
        if (list.size() > 0 && list1.size() > 0) {
            TeammateRe team = list.get(0);
            TeammateRe oper = list1.get(0);
            if (status == null && substitutionUid == null && oper.getType() > team.getType()) {
                map.put("code", "502");//下级不能删除上级
                return map;
            }
            map.put("teammateRe", team);//参与处置的对象
            if (substitutionUid == null && (team.getType().equals(TeammateReEnum.TYPE_ADMIN.getValue()) || team.getType().equals(TeammateReEnum.TYPE_AUXILIARY.getValue()))) {//管理者0|所属人1|参与者2
                map.put("code", "500");//需要有人替补
                return map;
            }
            if (team.getStatus().equals(TeammateReEnum.STATUS_DELETE.getValue())) {
                map.put("code", "503");//不能重复删除
                return map;
            }
            team.setStatus(TeammateReEnum.STATUS_DELETE.getValue());
            Integer result = teammateReMapper.updateByPrimaryKey(team);
            teammateReMapper.deleteByPrimaryKey(team.getId());
            OURelation ouRelation = new OURelation();
            ouRelation.setUserId(teamUserId);
            ouRelation.setEmployerId(userTeamId);
            ouRelation.setType(OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
            Integer result1 = ouRelationMapper.deleteByPrimaryKey(ouRelation);
            if (result > 0 && result1 > 0) {
                map.put("code", "200");//成功
            } else {
                map.put("code", "505");//数据操作有误
            }
        } else {
            map.put("code", "501");//失败
        }
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
