package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.orm.query.TUserTagQuery;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.*;
import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.cases.CaseInfoMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.mapper.repay.RepayMapper;
import com.dqys.business.orm.mapper.zcy.ZcyEstatesMapper;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.business.orm.pojo.coordinator.*;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.orm.pojo.zcy.ZcyEstates;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.*;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.*;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.business.service.utils.operType.OperTypeUtile;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
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
    private BusinessMapper businessMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private ZcyService zcyService;
    @Autowired
    private ContactInfoMapper contactInfoMapper;

    @Override
    public void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType, Integer userid) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (companyId == null) {
            TUserInfo userInfo = tUserInfoMapper.selectByPrimaryKey(userId);
            companyId = userInfo.getCompanyId();
        }
        //类型为抵押物的就查询抵押物的借款人
        if (objectType == ObjectTypeEnum.PAWN.getValue().intValue()) {
            PawnInfo info = pawnInfoMapper.get(objectId);
            if (info != null) {
                objectId = info.getLenderId();
                objectType = ObjectTypeEnum.LENDER.getValue();
            }
        }
        //查询相应协作器是否存在，不存在的进行创建
        UserTeam userTeam = new UserTeam();
        userTeam.setObjectType(objectType);
        userTeam.setObjectId(objectId);
        userTeam.setCompanyId(companyId);
        UserTeam team = new UserTeam();
        team = userTeamMapper.selectByPrimaryKeySelective(userTeam);
        if (objectType == ObjectTypeEnum.LENDER.getValue().intValue()) {//借款人
            LenderInfo lenderInfo = getLenderInfo(map, objectId);//获取借款人信息
            if (lenderInfo == null) return;
//            if (team == null&&lenderInfo.getAssetId()!=null) {
//                //查询借款人上级资产包的协作器
//                userTeam.setObjectId(lenderInfo.getAssetId());
//                userTeam.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
//                UserTeam teamAsset = userTeamMapper.selectByPrimaryKeySelective(userTeam);
//                if (teamAsset != null) {
//                    team = teamAsset;
//                }
//            }
        }
        if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue().intValue()) {//资产包
            if (getAsset(map, objectId)) return;
        }
        if (objectType == ObjectTypeEnum.ASSETSOURCE.getValue().intValue()) {//资产源
            if (getSource(map, objectId)) return;
        }
        if (objectType == ObjectTypeEnum.CASE.getValue().intValue()) {//案件
            if (getCase(map, objectId)) return;
        }
        if (team == null) {//判断是否在t_user_team表中添加了记录，添加了返回信息，没添加的返回id
            //需要判断userId是否拥有创建的权限
            String role = UserSession.getCurrent() == null ? "0" : UserSession.getCurrent().getRoleId();
            boolean authority = false;
            String[] roleType = role.split(",");
            for (int i = 0; i < roleType.length; i++) {
                if (RoleTypeEnum.ADMIN.getValue().toString().equals(roleType[i]) || RoleTypeEnum.REGULATOR.getValue().toString().equals(roleType[i])) {//用户为管理员或管理者才拥有权限
                    authority = true;
                }
            }
            if (!authority) {
                map.put("result", "no");
                map.put("msg", "没有权限创建协作器");
                return;
            }

            Map<String, Object> adminUser = coordinatorMapper.getAdminUser(companyId);
            Integer mangerId = MessageUtils.transMapToInt(adminUser, "id");//获取管理员id
            userTeam.setMangerId(mangerId == null ? userid : mangerId);
            userTeam.setCtreaterId(userid);
            //查询操作人员被操作事物关系
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setUserId(userId);
            objectUserRelationQuery.setObjectId(objectId);
            objectUserRelationQuery.setObjectType(objectType);
            objectUserRelationQuery.setType(3);//业务流转类型
            List<ObjectUserRelation> list = objectUserRelationMapper.list(objectUserRelationQuery);
            for (ObjectUserRelation obj : list) {
                if (obj.getVisibleType() != null && obj.getVisibleType() == 1) {
                    userTeam.setObjectOperStatus(1);//设置对象可操作状态
                    break;
                }
            }
            Integer result = userTeamMapper.insertSelective(userTeam);//添加公司内成员协作器
            map.put("userTeamId", userTeam.getId());
            map.put("result", "yes");
        } else {
            List<TeamDTO> list = getTeamDTOs(companyId, team);//协作器团队信息
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
     * 获取案件信息
     *
     * @param map
     * @param objectId
     * @return
     */
    private boolean getCase(Map<String, Object> map, Integer objectId) {
        CaseInfo info = caseInfoMapper.get(objectId);
        if (info == null) {
            map.put("result", "no");//案件不存在
            map.put("msg", "查询案件信息失败");
            return true;
        } else {
            map.put("detail", info);
        }
        return false;
    }

    /**
     * 获取资产源的信息
     *
     * @param map
     * @param objectId
     * @return
     */
    private boolean getSource(Map<String, Object> map, Integer objectId) {
        ZcyEstates zcyEstates = zcyEstatesMapper.selectByPrimaryKey(objectId);
        if (zcyEstates == null) {
            map.put("result", "no_asset");//资产源不存在
            map.put("msg", "查询资产源信息失败");
            return true;
        } else {
            map.putAll(zcyService.zcyDetail(objectId));
        }
        return false;
    }

    //获取协作器团队信息
    private List<TeamDTO> getTeamDTOs(Integer companyId, UserTeam team) {
        List<TeamDTO> list = getLenderOrAsset(companyId, team.getObjectId(), team.getObjectType());//获取借款人或是资产包的团队信息
        TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(team.getMangerId());//管理员信息
        if (tUserInfo != null) {
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setUserId(tUserInfo.getId());
            teamDTO.setRealName(tUserInfo.getRealName());
            teamDTO.setRoleType(10);//管理员
            list.add(teamDTO);
        }
        return list;
    }

    /**
     * 删除重复的协作器
     */
    private void delRepetitionUserTeam(UserTeam team) {
        List<Integer> userTeamIds = userTeamMapper.selectByCompany(team.getCompanyId(), team.getObjectId(), team.getObjectType());
        if (userTeamIds.size() > 1) {//存在多条时
            for (Integer id : userTeamIds) {
                if (!id.equals(team.getId())) {
                    userTeamMapper.deleteByPrimaryKey(id);
                }
            }
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
            map.put("msg", "查询资产包信息失败");
            return true;
        } else {
            map.put("name", assetInfo.getName());//资产包名称
            map.put("numberNo", assetInfo.getAssetNo() == null ? 0 : assetInfo.getAssetNo());//编号
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
            map.put("msg", "查询借款人信息失败");
            return null;
        } else {
            ContactInfo con = contactInfoMapper.getByModel(ObjectTypeEnum.LENDER.getValue().toString(), ContactTypeEnum.LENDER.getValue(), objectId);
            map.put("name", con != null ? con.getName() : "");//借款人名称
            map.put("sex", con != null ? con.getGender() : "");//性别
            map.put("avg", con != null ? con.getAvg() : "");//头像
            map.put("numberNo", lenderInfo.getLenderNo() == null ? 0 : lenderInfo.getLenderNo());//编号
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
        Integer uId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        map.put("result", "no");
        //用户id和公司id只能选择其一，没有公司id就通过用户id查找公司id
        if ((companyId == null && userId != null) || (companyId != null && userId == null)) {
            List<Map<String, Object>> list = coordinatorMapper.getCompanyUserList(realName, userId, companyId);
            //集合中去除自己的
            for (Map m : list) {
                if (uId.toString().equals(m.get("userId").toString())) {
                    list.remove(m);
                    break;
                }
            }
            map.put("users", list);
            map.put("result", "yes");
        } else {
            map.put("msg", "参数传输有误");
        }
        return map;
    }

    @Override
    public Map addTeammate(Integer userTeamId, Integer userId, String remark, Integer[] userIds) throws BusinessLogException {
        Map map = new HashMap<>();
        int num = 0;
        UserTeam team = new UserTeam();
        team.setId(userTeamId);
        UserTeam userTeam = userTeamMapper.selectByPrimaryKeySelective(team);
        if (userTeam == null) {
            map.put("msg", "协作器不存在");
            map.put("result", "no");
            return map;
        }
//        delRepetitionUserTeam(userTeam);//重新查询下，避免重复多条相同协作器
        Map userAndCompany = coordinatorMapper.getUserAndCompanyByUserId(userId);
        for (Integer uid : userIds) {
            Integer flag = 0;
            TeammateRe teammateRe = new TeammateRe();
            teammateRe.setUserId(uid);
            teammateRe.setUserTeamId(userTeamId);
            teammateRe.setJoinType(TeammateReEnum.JOIN_TYPE_PASSIVITY.getValue());
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
            if (flag == -1) {//已经加入过案组
                map.put("msg", "可能已经在案组或已邀请");
            }
            if (flag > 0) {
                num++;
                //发送短信
                messageService.sendSmsByTeammate(userTeam, teammateRe, userAndCompany, uid, remark);
            }
        }
        if (num > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        businessLogService.add(userTeam.getObjectId(), userTeam.getObjectType(), UserInfoEnum.COORDINATOR_ADD_USER.getValue(), UserInfoEnum.COORDINATOR_ADD_USER.getName(), "", 0, userTeam.getId());
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
        if (size > 5 && teammateRe.getJoinType().equals(TeammateReEnum.JOIN_TYPE_PASSIVITY.getValue())) {
            return -3;//被邀请的人数已满
        }
        //判断是否存在管理者，不存在返回第一个邀请要管理者
        if (!judgeTeamReType(teammateRe, TeammateReEnum.TYPE_ADMIN.getValue())) {
            TUserTag tags = tUserTagMapper.selectByUserId(teammateRe.getUserId()).get(0);
            if (RoleTypeEnum.REGULATOR.getValue() != (int) tags.getRoleId()) {
                return -4;//第一个添加人员不是管理者
            }
            teammateRe.setType(TeammateReEnum.TYPE_ADMIN.getValue());
        } else if (!judgeTeamReType(teammateRe, TeammateReEnum.TYPE_AUXILIARY.getValue())) {
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
                teammateRe.setId(teammateRe1.getId());
            } else {
                flag = -1;//已经加入过案组或已邀请
            }
        } else {
            flag = teammateReMapper.insertSelective(teammateRe);//添加参与人
        }
        return flag;
    }

    /**
     * 判别该协作器中是否存在了管理者或所属人
     *
     * @param teammateRe
     * @return
     */
    private boolean judgeTeamReType(TeammateRe teammateRe, Integer type) {
        boolean flag = false;
        TeammateRe re = new TeammateRe();
        re.setType(type);
        re.setUserTeamId(teammateRe.getUserTeamId());
        re.setStatus(TeammateReEnum.STATUS_INIT.getValue());
        List<TeammateRe> list = teammateReMapper.selectSelective(re);//查看待接收中是否存在
        if (list.size() > 0) {
            flag = true;
        } else {
            re.setStatus(TeammateReEnum.STATUS_ACCEPT.getValue());
            list = teammateReMapper.selectSelective(re);//查看已经接收是否存在
            if (list.size() > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 设定业务id （重复的不设置业务号，没有业务号就不添加事物关系信息）
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
    public Map isAccept(Integer teammateId, Integer status, Integer userId, Integer operUserId) throws BusinessLogException {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "no");
        TeammateRe teammateRe = teammateReMapper.selectByPrimaryKey(teammateId);//团队
        if (teammateRe == null) {
            map.put("msg", "团队查询有误");
            return map;
        }
        if (teammateRe.getStatus() != 0) {
            map.put("msg", "重复操作");
            return map;
        }
        UserTeam userTeam = userTeamMapper.get(teammateRe.getUserTeamId());//协作器
        if (userTeam == null) {
            map.put("msg", "案组查询有误");//不存在
            return map;
        }
//            if (userT.getMangerId() != userId) {
//                map.put("result", "no_authority");//没有权限
//                return map;
//            }
        teammateRe.setStatus(status);
        Integer result = teammateReMapper.updateByPrimaryKeySelective(teammateRe);
        if (status == 1 && result > 0) {
            //是所属人的就进行修改对象的跟进时间
//            if (teammateRe.getType() == TeammateReEnum.TYPE_AUXILIARY.getValue().intValue()) {
//                setFollowUpTime(userTeam.getObjectId(), userTeam.getObjectType());
//            }
            OURelation ouRelation = new OURelation();
            Integer object_oper_status = userTeam.getObjectOperStatus();//对象可操作状态:0拥有对其全部的操作1来自业务流转只对其下部分对象有操作权限',
            if (1 == object_oper_status) {
                List<OURelation> list = ouRelationMapper.findByOURelation(userTeam.getMangerId(), userTeam.getObjectType());//查询业务流转对象
                if (list.size() > 0) {
                    ouRelation = list.get(0);
                }
            } else {
                ouRelation.setStatus(OURelationEnum.STATUS_ACCEPT.getValue());
                ouRelation.setObjectType(userTeam.getObjectType());
                ouRelation.setObjectId(userTeam.getObjectId());
            }
            ouRelation.setType(OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
            ouRelation.setUserId(teammateRe.getUserId());
            ouRelation.setEmployerId(teammateRe.getUserTeamId());
            if (checkExist(ouRelation)) {
                ouRelationMapper.insertSelective(ouRelation);//添加事物关系
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
            businessLogService.add(userTeam.getObjectId(), userTeam.getObjectType(), UserInfoEnum.COORDINATOR_ACCEPT_ADD_USER.getValue(), UserInfoEnum.COORDINATOR_ACCEPT_ADD_USER.getName(), "", 0, userTeam.getId());
            sendInviteCoordinator(teammateRe, userTeam, userId, operUserId, status);
            map.put("result", "yes");
        } else if (status == 2) {
            map.put("result", "yes");
            businessLogService.add(userTeam.getObjectId(), userTeam.getObjectType(), UserInfoEnum.COORDINATOR_REJECT_ADD_USER.getValue(), UserInfoEnum.COORDINATOR_REJECT_ADD_USER.getName(), "", 0, userTeam.getId());
            sendInviteCoordinator(teammateRe, userTeam, userId, operUserId, status);
        }
        return map;
    }

    /**
     * 修改对象的跟进时间
     *
     * @param objectId
     * @param objectType
     */
    private void setFollowUpTime(Integer objectId, Integer objectType) {
        if (objectType == ObjectTypeEnum.LENDER.getValue().intValue()) {
            LenderInfo info = lenderInfoMapper.get(objectId);
            if (info != null) {
                info.setBelongFollowTime(new Date());
                lenderInfoMapper.update(info);
            }
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue().intValue()) {
            AssetInfo info = assetInfoMapper.get(objectId);
            if (info != null) {
                info.setBelongUpTime(new Date());
                assetInfoMapper.update(info);
            }
        }
    }

    /**
     * 协作器的邀请或主动加入的同意或拒绝进行发送短信
     *
     * @param teammateRe
     * @param userTeam
     * @param userId     发送者（操作者）
     * @param operUserId （邀请发起人）
     * @param status     （1同意，2拒绝）
     */
    private void sendInviteCoordinator(TeammateRe teammateRe, UserTeam userTeam, Integer userId, Integer operUserId, Integer status) {
        SmsUtil smsUtil = new SmsUtil();
        String content = "";
        Integer sendUserId = userId;//发送
        Integer recUserId = operUserId;//接收
        if (teammateRe.getJoinType() == TeammateReEnum.JOIN_TYPE_PASSIVITY.getValue()) {//被邀请
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(recUserId);
            Map operC = coordinatorMapper.getUserAndCompanyByUserId(sendUserId);
            if (status == 1) {//同意
                content = smsUtil.sendSms(SmsEnum.INVITE_COORDINATOR_YES.getValue(), MessageUtils.transMapToString(userC, "mobile"),
                        MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(operC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()));
            } else if (status == 2) {//拒绝
                content = smsUtil.sendSms(SmsEnum.INVITE_COORDINATOR_NO.getValue(), MessageUtils.transMapToString(userC, "mobile"),
                        MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(operC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()));
            }
        } else if (teammateRe.getJoinType() == TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue()) {//主动加入，管理员操作
            recUserId = teammateRe.getUserId();//因为主动加入，所以审核同意发送的是管理员，接收的为协作器成员
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(recUserId);
            Map operC = coordinatorMapper.getUserAndCompanyByUserId(sendUserId);
            if (status == 1) {//同意
                content = smsUtil.sendSms(SmsEnum.INITIATIVE_JOIN_YES.getValue(), MessageUtils.transMapToString(userC, "mobile"),
                        MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(operC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()));
            } else if (status == 2) {//拒绝
                content = smsUtil.sendSms(SmsEnum.INITIATIVE_JOIN_NO.getValue(), MessageUtils.transMapToString(userC, "mobile"),
                        MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(operC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()));
            }
        }
        String title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.INSIDE_RESULT.getValue());
        messageService.add(title, content, sendUserId, recUserId, "", MessageEnum.SERVE.getValue(), MessageBTEnum.INSIDE_RESULT.getValue(), "");
    }

    @Override
    public Map addTeammate(Integer userTeammateId, Integer userId) throws BusinessLogException {
        Map map = new HashMap<>();
        Integer flag = 0;
        map.put("result", "no");
        UserTeam userTeam = new UserTeam();
        userTeam.setId(userTeammateId);
        UserTeam userT = userTeamMapper.selectByPrimaryKeySelective(userTeam);//团队信息
        if (userT == null) {
            map.put("msg", "该案组已不存在");//协作器不存在
            return map;
        }
        TeammateRe teammateRe = new TeammateRe();
        teammateRe.setUserId(userId);
        teammateRe.setUserTeamId(userTeammateId);
        teammateRe.setJoinType(TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue());
        teammateRe.setBusinessType(TeammateReEnum.BUSINESS_TYPE_TASK.getValue());
        flag = getTeammateFlag(teammateRe);
        if (flag > 0) {
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
            map.put("result", "yes");
        } else if (flag == -1) {
            map.put("msg", "已经加入过案组或申请加入已发出");//已经加入过案组
        } else if (flag == -2) {
            map.put("msg", "案组人数已满");//人数已满
        } else if (flag == -4) {
            map.put("msg", "您不是管理者");//第一个不是管理者
        } else {
            map.put("msg", "操作失败");
        }
        businessLogService.add(userT.getObjectId(), userT.getObjectType(), UserInfoEnum.COORDINATOR_ADD_USER.getValue(), "主动加入案组", "", 0, userT.getId());
        return map;
    }

    @Override
    public void auditBusiness(Map map, Integer userId, Integer objectId, Integer objectType, Integer status) throws BusinessLogException {
        map.put("result", "no");
        Integer operType = 0;
        String text = "";
        Integer receive_id = 0;//录入人
        List<Integer> ids = new ArrayList<>();
        ids.add(objectId);
        List<Integer> buinessIds = repayMapper.getBusinessId(objectType, ids);
        if (buinessIds.size() == 0) {
            map.put("msg", "业务号不存在");
            return;
        }
        Business business = businessMapper.get(buinessIds.get(0));
        if (business == null) {
            map.put("msg", "业务号不存在");
            return;
        }
        if (business.getStatus() == status) {
            map.put("msg", "重复操作");
            return;
        }
        //修改业务状态
        Integer result = repayMapper.updateBusinessStatus(buinessIds.get(0), status);
        if (result > 0) {
            SmsUtil smsUtil = new SmsUtil();//发送短信通知
            Integer code = 0;
            boolean audit = true;//是否为业务审核
            if (status == BusinessStatusEnum.platform_pass.getValue()) {
                code = SmsEnum.BUSINESS_AUDIT_YES.getValue();
            } else if (status == BusinessStatusEnum.platform_refuse.getValue()) {
                code = SmsEnum.BUSINESS_AUDIT_NO.getValue();
            } else if (status == BusinessStatusEnum.init.getValue()) {//重新申请审核
                audit = false;
                TUserTagQuery tUserTagQuery = new TUserTagQuery();
                tUserTagQuery.setUserType(UserInfoEnum.USER_TYPE_ADMIN.getValue());
                tUserTagQuery.setRole(RoleTypeEnum.ADMIN.getValue());
                List<TUserTag> list = tUserTagMapper.selectByQuery(tUserTagQuery);//重新申请审核，重新发送给平台管理员
                if (list.size() > 0) {
                    receive_id = list.get(0).getUserId();//平台管理员id
                }
                code = SmsEnum.APPLY_AGAIN.getValue();
                text = "重新业务审核申请";
            }
            if (objectType == ObjectTypeEnum.LENDER.getValue() && audit) {
                LenderInfo len = coordinatorMapper.getLenderInfo(objectId);
                if (len != null) {
                    receive_id = len.getOperator() == null ? 0 : len.getOperator();
                }
                operType = LenderEnum.UPDATE_EDIT.getValue();
                text = "借款人审核操作";
            } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue() && audit) {
                AssetInfo assetInfo = coordinatorMapper.getAssetInfo(objectId);
                if (assetInfo != null) {
                    receive_id = assetInfo.getOperator() == null ? 0 : assetInfo.getOperator();
                }
                operType = AssetPackageEnum.update.getValue();
                text = "资产包审核操作";
            }
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(receive_id);//接受者
            String content = "";
            if (audit) {
                content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), getObjectName(objectType, objectId));
            } else {
                Map operC = coordinatorMapper.getUserAndCompanyByUserId(userId);//发送者
                content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(operC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), getObjectName(objectType, objectId));
            }
            String title = getMessageTitle(objectId, objectType, MessageBTEnum.BUSINESS.getValue());
            messageService.add(title, content, userId, receive_id, "", MessageEnum.SERVE.getValue(), MessageBTEnum.BUSINESS.getValue(), "");//添加通知消息
            map.put("result", "yes");
        } else {
            map.put("msg", "审核业务号失败");
        }
        businessLogService.add(objectId, objectType, operType, text, "", 0, 0);//添加操作日志
    }

    @Override
    public void isPause(Map map, Integer objectId, Integer objectType, Integer status, Integer userId) throws
            BusinessLogException {
        Integer operType = 0;//操作类型
        List<Map<String, Object>> receive_ids = new ArrayList<>();
        LenderInfo lenderInfo = new LenderInfo();
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            //对借款人的操作
            lenderInfo.setId(objectId);
            lenderInfo.setIsStop(status);
            LenderInfo lender = lenderInfoMapper.get(objectId);
            if (lender == null) {
                map.put("result", "noExist");//不存在记录
                map.put("msg", "借款人记录不存在");
                return;
            }
            if (lender.getIsStop() == status) {
                map.put("result", "repetition");//重复操作
                map.put("msg", "重复操作");//
                return;
            }
            coordinatorMapper.updateLender(lenderInfo);//修改借款人isStop状态
            operType = LenderEnum.UPDATE_EDIT.getValue();
            receive_ids = coordinatorMapper.getUserIdByObjUserRelToLender(objectType, objectId);
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            //对资产包的操作
            AssetInfo assetInfo = new AssetInfo();
            assetInfo.setId(objectId);
            assetInfo.setIsStop(status);
            AssetInfo asset = assetInfoMapper.get(objectId);
            if (asset == null) {
                map.put("result", "noExist");//不存在记录
                map.put("msg", "资产包记录不存在");
                return;
            }
            if (asset.getIsStop() == status) {
                map.put("result", "repetition");//重复操作
                map.put("msg", "重复操作");//
                return;
            }
            if (assetInfoMapper.update(assetInfo) > 0) {//资产包暂停或无效后，下面的借款人全部暂停或无效了
                lenderInfo.setIsStop(status);
                lenderInfo.setAssetId(objectId);
                coordinatorMapper.updateLender(lenderInfo);//修改资产包isStop状态
            }
            operType = AssetPackageEnum.update.getValue();
            receive_ids = coordinatorMapper.getUserIdByObjUserRelToAsset(objectType, objectId);//被通知人员
        } else if (objectType == ObjectTypeEnum.ASSETSOURCE.getValue().intValue()) {
            //对资产源的操作
            ZcyEstates zcyEstates = new ZcyEstates();
            zcyEstates.setId(objectId);
            zcyEstates.setResultStatus(status);
            ZcyEstates estates = zcyEstatesMapper.selectByPrimaryKey(objectId);
            if (estates == null) {
                map.put("result", "noExist");//不存在记录
                map.put("msg", "资产源记录不存在");
                return;
            }
            if (estates.getResultStatus() == status) {
                map.put("result", "repetition");//重复操作
                map.put("msg", "重复操作");//
                return;
            }
            zcyEstatesMapper.updateByPrimaryKey(zcyEstates);
            operType = AssetSourceEnum.UPDATE_EDIT.getValue();
            receive_ids = coordinatorMapper.getUserIdByObjUserRelToLender(objectType, objectId);//被通知人员
        }
        businessLogService.add(objectId, objectType, operType, "暂停或无效操作", "", 0, 0);
        Integer code = 0;
        if (status == 1) {
            code = SmsEnum.BUSINESS_PAUSE.getValue();//设置业务暂停
        } else if (status == 0) {
            code = SmsEnum.BUSINESS_OPEN.getValue();//设置业务正常
        } else if (status == 2) {
            code = SmsEnum.BUSINESS_INVALID.getValue();//设置业务无效
        }
        SmsUtil smsUtil = new SmsUtil();//发送短信通知
        for (Map receive_id : receive_ids) {
            Integer rec = MessageUtils.transMapToInt(receive_id, "user_id");
            if (rec != null && !userId.equals(rec)) {
                Map userC = coordinatorMapper.getUserAndCompanyByUserId(rec);
                Map oper = coordinatorMapper.getUserAndCompanyByUserId(userId);
                String content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(oper, "companyName"),
                        CompanyTypeEnum.getCompanyTypeEnum(MessageUtils.transMapToInt(oper, "companyType")).getName(), MessageUtils.transMapToString(oper, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), getObjectName(objectType, objectId));
                String title = getMessageTitle(objectId, objectType, MessageBTEnum.BUSINESS_PAUSE.getValue());
                messageService.add(title, content, userId, rec, "", MessageEnum.SERVE.getValue(), MessageBTEnum.BUSINESS_PAUSE.getValue(), "");//添加通知消息
            }
        }
        map.put("result", "yes");
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
    public Map deleteTeammatUser(Integer userId, Integer teamUserId, Integer userTeamId, Integer status, Integer
            substitutionUid, Integer operUserId) throws Exception {
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
            code = teamVerdict(userId, teamUserId, userTeamId, status, substitutionUid);//删除联系人判定
            teamDel(code, teamUserId, userTeamId);
            businessLogService.add(userTeam.getObjectId(), userTeam.getObjectType(), UserInfoEnum.COORDINATOR_DEL_USER.getValue(), UserInfoEnum.COORDINATOR_DEL_USER.getName(), "", 0, 0);//添加操作日志
        } else if (status == null && substitutionUid != null) {//发送短信通知替补的人
            code = teamVerdict(userId, teamUserId, userTeamId, status, substitutionUid);//删除联系人判定
            if ("200".equals(MessageUtils.transMapToString(code, "code"))) {
                SmsUtil smsUtil = new SmsUtil();//发送短信通知
                Map userC = coordinatorMapper.getUserAndCompanyByUserId(substitutionUid);
                Map oper = coordinatorMapper.getUserAndCompanyByUserId(userId);
                String content = smsUtil.sendSms(SmsEnum.REPLACE_CONTACTS.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                        MessageUtils.transMapToString(oper, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()));
                String title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.REPLACE_CONTACTS.getValue());
                String url = MessageUtils.setOperUrl("/coordinator/delUser?status=0&teamUserId=" + teamUserId + "&userTeamId=" + userTeamId + "&substitutionUid=" + substitutionUid + "&operUserId=" + userId, null,
                        "/coordinator/delUser?status=1&teamUserId=" + teamUserId + "&userTeamId=" + userTeamId + "&substitutionUid=" + substitutionUid + "&operUserId=" + userId, null, null);
                messageService.add(title, content, userId, substitutionUid, "", MessageEnum.TASK.getValue(), MessageBTEnum.REPLACE_CONTACTS.getValue(), url);//添加通知消息
                map.put("result", "yes");
            }
        } else if (status != null && substitutionUid != null) {//替换协作器联系人
            businessLogService.add(userTeam.getObjectId(), userTeam.getObjectType(), UserInfoEnum.COORDINATOR_REPLACE_USER.getValue(), UserInfoEnum.COORDINATOR_REPLACE_USER.getName(), "", 0, 0);//添加操作日志
            if (userId.equals(substitutionUid)) {
                if (status == TeammateReEnum.STATUS_ACCEPT.getValue().intValue()) {
                    code = teamVerdict(userId, teamUserId, userTeamId, status, substitutionUid);//删除联系人判定
                    teamDel(code, teamUserId, userTeamId);//删除操作
                    if ("200".equals(code.get("code"))) {
                        teamVerdict(userId, substitutionUid, userTeamId, null, null);
                        teamDel(code, teamUserId, userTeamId);//如果替换人在该团队已经存在，那么先删除之后
                        TeammateRe teammateRe = (TeammateRe) code.get("teammateRe");
                        teammateRe.setId(null);
                        teammateRe.setUserId(userId);
                        teammateRe.setStatus(TeammateReEnum.STATUS_INIT.getValue());
                        teammateRe.setJoinType(TeammateReEnum.JOIN_TYPE_PASSIVITY.getValue());
                        teammateReMapper.insert(teammateRe);//添加到团队中
                        Map res = isAccept(teammateRe.getId(), status, userId, operUserId);//需要进行一个同意操作
                        if (MessageUtils.transMapToString(res, "result").equals("yes")) {
                            SmsUtil smsUtil = new SmsUtil();//发送短信通知
                            Map userC = coordinatorMapper.getUserAndCompanyByUserId(teamUserId);
                            Map oper = coordinatorMapper.getUserAndCompanyByUserId(userId);
                            String content = smsUtil.sendSms(SmsEnum.REPLACE.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                                    MessageUtils.transMapToString(oper, "realName"),
                                    ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), getObjectName(userTeam.getObjectType(), userTeam.getObjectId()), TeammateReEnum.get(teammateRe.getType()));
                            String title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.REPLACE.getValue());
                            messageService.add(title, content, userId, teamUserId, "", MessageEnum.TASK.getValue(), MessageBTEnum.REPLACE.getValue(), "");//添加通知消息
                            map.put("result", "yes");
                        } else {
                            throw new Exception();
                        }
                    }
                } else {
                    map.put("result", "yes");
                }
            } else {
                map.put("result", "no_self");
                map.put("msg", "不正确的访问(非本人帐号)");
            }
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
                map.put("msg", "查看您是否已经在团队中或被替补人员查询有误");
            } else if ("500".equals(code.get("code"))) {
                map.put("result", "no_tiBu");
                map.put("msg", "需要替补人");
            } else if ("502".equals(code.get("code"))) {
                map.put("result", "no_lowRank");
                map.put("msg", "没有权限做出删除");
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
                name = info.getAssetNo() == null ? "" : info.getAssetNo();
            }
        } else if (ObjectTypeEnum.PAWN.getValue().equals(objectType)) {//抵押物
            PawnInfo info = pawnInfoMapper.get(objectId);
            if (info != null) {
                name = info.getPawnNo() == null ? "" : info.getPawnNo();
            }
        } else if (ObjectTypeEnum.LENDER.getValue().equals(objectType)) {//借款人
            LenderInfo info = lenderInfoMapper.get(objectId);
            if (info != null) {
                name = info.getLenderNo() == null ? "" : info.getLenderNo();
            }
        } else if (ObjectTypeEnum.IOU.getValue().equals(objectType)) {//借据
            IOUInfo info = iouInfoMapper.get(objectId);
            if (info != null) {
                name = info.getIouNo() == null ? "" : info.getIouNo();
            }
        } else if (ObjectTypeEnum.ASSETSOURCE.getValue().equals(objectType)) {//资产源
            ZcyEstates info = zcyEstatesMapper.selectByPrimaryKey(objectId);
            if (info != null) {
                name = info.getHouseNo() == null ? "" : info.getHouseNo();
            }
        } else if (ObjectTypeEnum.CASE.getValue().equals(objectType)) {//案件
            CaseInfo info = caseInfoMapper.get(objectId);
            if (info != null) {
                name = info.getCaseNo() == null ? "" : info.getCaseNo();
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
    public Boolean verdictOrganization(Integer flowId, Integer flowType, Integer onStatus, Integer type, boolean modify) {
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
                    if (modify) {
                        pawnInfoMapper.update(info);//修改抵押物的机构处置状态
                        setOnByPawnAndIou(flowId, flowType, coll, age, lay);
                    }
                }
            } else if (flowType == ObjectTypeEnum.IOU.getValue()) {
                IOUInfo info = iouInfoMapper.get(flowId);
                if (info != null) {
                    info.setOnCollection(coll);
                    info.setOnAgent(age);
                    info.setOnLawyer(lay);
                    if (modify) {
                        iouInfoMapper.update(info);//修改借据的机构处置状态
                        setOnByPawnAndIou(flowId, flowType, coll, age, lay);
                    }
                }
            }
        }
        return flag;
    }

    private void setOnByPawnAndIou(Integer id, Integer type, Integer coll, Integer age, Integer lay) {
        if (type == ObjectTypeEnum.PAWN.getValue()) {
            //同步修改抵押物下的所有借据状态（商定之后的修改）
            List<IOUInfo> infos = iouInfoMapper.findByPawnId(id);
            for (IOUInfo inf : infos) {
                inf.setOnCollection(coll);
                inf.setOnAgent(age);
                inf.setOnLawyer(lay);
                iouInfoMapper.update(inf);
            }
        } else if (type == ObjectTypeEnum.IOU.getValue()) {
            //同步修改借据下的所有抵押物状态（商定之后的修改）
            List<PawnInfo> infos = pawnInfoMapper.findByIouId(id);
            for (PawnInfo inf : infos) {
                inf.setOnCollection(coll);
                inf.setOnAgent(age);
                inf.setOnLawyer(lay);
                pawnInfoMapper.update(inf);
            }
        }
    }

    @Override
    public Map businessFlow(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer operType, Integer companyTeamId) {
        Map map = new HashMap<>();
        map.put("result", "no");
        if (companyTeamId == null) {
            CompanyTeam team = companyTeamMapper.getByTypeId(objectType, objectId);//分配器id参数为null，自己查询分配器id
            //借款人分配器不存在，不去查询资产包的分配器（资产包里不需要看到业务流转的处置机构）
//            if (team == null && objectType == ObjectTypeEnum.LENDER.getValue()) {
//                LenderInfo info = lenderInfoMapper.get(objectId);
//                if (info != null && info.getAssetId() != null) {
//                    team = companyTeamMapper.getByTypeId(ObjectTypeEnum.ASSETPACKAGE.getValue(), info.getAssetId());
//                }
//            }
            if (team != null) {
                companyTeamId = team.getId();
            } else {
                //根据用户类型为处置机构时创建分配器
                String typeStr = UserSession.getCurrent().getUserType();
                if (CommonUtil.isDispose(typeStr.substring(0, typeStr.indexOf(",")))) {
                    // 调用分配器接口
                    try {
                        DistributionDTO distributionDTO = distributionService.listDistribution_tx(objectType, objectId);
                        companyTeamId = distributionDTO.getId();
                    } catch (BusinessLogException e) {
                        map.put("msg", "创建分配器异常");//该对象类型不存在分配器
                        return map;
                    }
                } else {
                    map.put("msg", "该对象类型不存在分配器");//该对象类型不存在分配器
                    return map;
                }
            }
        }
        if (ObjectTypeEnum.PAWN.getValue() == flowType) {//抵押物
            if (PawnEnum.MAINTAIN_REGULAR.getValue().equals(operType)) {//维持常规催收
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 1, 1);
            } else if (PawnEnum.MARKET_DISPOSITION.getValue().equals(operType)) {//市场处置
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 1, 1, 0);
            } else if (PawnEnum.CM_SIMULTANEOUS.getValue().equals(operType)) {//催收/市场同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 1, 0);
            } else if (PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue().equals(operType)) {//执行司法化解
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 1, 0, 1);
            } else if (PawnEnum.CJ_SIMULTANEOUS.getValue().equals(operType)) {//催收/司法化解同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 0, 1);
            } else if (PawnEnum.CMJ_SIMULTANEOUS.getValue().equals(operType)) {//催收、市场、司法同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, PawnEnum.getPawnEnum(operType).getName(), companyTeamId, 0, 0, 0);
            } else {
                map.put("msg", "业务操作类型有误");//业务操作类型有误
            }
        } else if (ObjectTypeEnum.IOU.getValue() == flowType) {//借据
            if (IouEnum.MAINTAIN_REGULAR.getValue().equals(operType)) {//维持常规催收
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 1, 1);
            } else if (IouEnum.MARKET_DISPOSITION.getValue().equals(operType)) {//市场处置
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 1, 1, 0);
            } else if (IouEnum.CM_SIMULTANEOUS.getValue().equals(operType)) {//催收/市场同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 1, 0);
            } else if (IouEnum.EXECUTE_JUSTICE_RESOLVE.getValue().equals(operType)) {//执行司法化解
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 1, 0, 1);
            } else if (IouEnum.CJ_SIMULTANEOUS.getValue().equals(operType)) {//催收/司法化解同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 0, 1);
            } else if (IouEnum.CMJ_SIMULTANEOUS.getValue().equals(operType)) {//催收、市场、司法同时进行
                setFlow(map, objectId, objectType, flowId, flowType, operType, IouEnum.getIouEnum(operType).getName(), companyTeamId, 0, 0, 0);
            } else {
                map.put("msg", "业务操作类型有误");//业务操作类型有误
            }
        } else {
            map.put("msg", "流转对象类型有误");//流转对象类型有误
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
        if ("no".equals(result)) {
            map.put("msg", "用户信息不存在");
        }
        return map;
    }

    @Override
    public Map setDeadline(Integer objectId, Integer objectType, String dateTime) {
        Map map = new HashMap<>();
        map.put("result", "no");
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        //判断用户是否有权限操作
        if (!jurisdictionByDeadline(objectId, objectType, userId)) {
            map.put("msg", "您没有权限操作");
            return map;
        }
        //修改借款人或资产包的委托结束时间
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            LenderInfo info = lenderInfoMapper.get(objectId);
            if (info == null) {
                map.put("msg", "不存在信息记录");
            } else {
                info.setEndAt(DateFormatTool.parse(dateTime, DateFormatTool.DATE_FORMAT_10_REG1));
                lenderInfoMapper.update(info);
                map.put("result", "yes");
            }
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo info = assetInfoMapper.get(objectId);
            if (info == null) {
                map.put("msg", "不存在信息记录");
            } else {
                info.setEndAt(DateFormatTool.parse(dateTime, DateFormatTool.DATE_FORMAT_10_REG1));
                assetInfoMapper.update(info);
                map.put("result", "yes");
            }
        }
        return map;
    }

    /**
     * 判断用户是否有权限设置委托期限
     *
     * @param userId
     * @return （true有权限false无权）
     */
    private boolean jurisdictionByDeadline(Integer objectId, Integer objectType, Integer userId) {
        TUserTag tag = getUserTagByObject(objectId, objectType);
        if (tag != null && tag.getUserId() == userId) {
            return true;//当前用户为录入人才有权限
        } else {
            List<TeamDTO> teamDTOs = getTeamList(userId, objectId, objectType);
            if (teamDTOs != null) {
                for (TeamDTO t : teamDTOs) {
                    if ((t.getRoleType() == TeammateReEnum.TYPE_ADMIN.getValue() || t.getRoleType() == 10) && t.getUserId() == userId) {
                        return true;//当前用户为协作器的管理员或是管理者才有权限
                    }
                }
            }
        }
        return false;
    }


    @Override
    public TUserTag getUserTagByObject(Integer objectId, Integer objectType) {
        Integer userId = 0;
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            LenderInfo info = lenderInfoMapper.get(objectId);
            if (info != null) {
                userId = info.getOperator();
            }
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo info = assetInfoMapper.get(objectId);
            if (info != null) {
                userId = info.getOperator();
            }
        }
        if (userId != 0) {
            List<TUserTag> tags = tUserTagMapper.selectByUserId(userId);
            if (tags.size() > 0) {
                return tags.get(0);
            }
        }
        return null;
    }

    @Override
    public List<TeamDTO> getTeamList(Integer userId, Integer objectId, Integer objectType) {
        TUserInfo userInfo = tUserInfoMapper.selectByPrimaryKey(userId);
        if (userInfo != null) {
            Integer companyId = userInfo.getCompanyId();
            UserTeam userTeam = userTeamMapper.getByObject(objectId, objectType, companyId);
            if (userTeam == null && objectType == ObjectTypeEnum.LENDER.getValue()) {
                LenderInfo lenderInfo = lenderInfoMapper.get(objectId);
                if (lenderInfo != null && lenderInfo.getAssetId() != null) {
                    userTeam = userTeamMapper.getByObject(lenderInfo.getAssetId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), companyId);
                }
            }
            if (userTeam != null) {
                return getTeamDTOs(companyId, userTeam);
            }
        }
        return null;
    }

    @Override
    public Map getUserDetail(Integer userId) {
        Map map = new HashMap<>();
        UserDetail detail = coordinatorMapper.getUserDetail(userId);
        map.put("detail", detail);
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
                "type=3&flowId=" + flowId + "&flowType=" + flowType + "&companyTeamId=" + companyTeamId + "&operType=" + operType + "&userId=" + userId);
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
        boolean modify = true;
        if ((coll == 0 && !c) || (lawy == 0 && !l) || (agen == 0 && !a)) {//判断是否符合，发送给平台管理员短信
            messageService.businessFlow(objectId, objectType, flowId, flowType, operation, userId, operUrl);
            modify = modifyOnstatus(flowId, flowType, coll, lawy, agen);//判断是否进行修改处置状态，默认可以
        }
        //进行处置机构发送短信
        messageService.judicature(objectId, objectType, flowId, flowType, userId, operation, lawy, modify);//司法机构
        messageService.collectiones(objectId, objectType, flowId, flowType, userId, operation, coll, modify);//催收机构
        messageService.intermediary(objectId, objectType, flowId, flowType, userId, operation, agen, modify);//市场处置
        map.put("result", "yes");
    }

    /**
     * 用于判别对处置状态是否进行修改
     *
     * @param flowId
     * @param flowType
     * @param coll     常规催收（0可以1不能）
     * @param lawy     司法化解（0可以1不能）
     * @param agen     市场处置（0可以1不能）
     * @return
     */
    private boolean modifyOnstatus(Integer flowId, Integer flowType, Integer coll, Integer lawy, Integer agen) {
        Integer coll_original = 0;
        Integer lawy_original = 0;
        Integer agen_original = 0;
        if (flowType == ObjectTypeEnum.PAWN.getValue()) {
            PawnInfo info = pawnInfoMapper.get(flowId);
            coll_original = info.getOnCollection();
            lawy_original = info.getOnLawyer();
            agen_original = info.getOnAgent();
        } else if (flowType == ObjectTypeEnum.IOU.getValue()) {
            IOUInfo info = iouInfoMapper.get(flowId);
            coll_original = info.getOnCollection();
            lawy_original = info.getOnLawyer();
            agen_original = info.getOnAgent();
        }
        if (coll == 1) {
            coll_original = coll;
        }
        if (lawy == 1) {
            lawy_original = lawy;
        }
        if (agen == 1) {
            agen_original = agen;
        }
        //如果三个状态都是为“不能”就不进行修改
        if (coll_original == 1 && lawy_original == 1 && agen_original == 1) {
            return false;
        } else {
            return true;
        }
    }

    private Map teamVerdict(Integer userId, Integer teamUserId, Integer userTeamId, Integer status, Integer substitutionUid) {
        Map map = new HashMap<>();
        TeammateRe teammateRe = new TeammateRe();
        teammateRe.setUserId(teamUserId);
        teammateRe.setUserTeamId(userTeamId);
        List<TeammateRe> list = teammateReMapper.selectSelective(teammateRe);//团队中用户
        teammateRe.setUserId(userId);
        List<TeammateRe> list1 = teammateReMapper.selectSelective(teammateRe);//操作用户
        if (list.size() == 0) {
            map.put("code", "501");//团队中不存在这人
            return map;
        }
        TeammateRe team = list.get(0);
        if (list1.size() > 0) {
            TeammateRe oper = list1.get(0);
            if (status == null && oper.getType() > team.getType()) {
                map.put("code", "502");//下级不能删除上级
                return map;
            }
        } else {
            UserTeam userTeam = userTeamMapper.get(team.getUserTeamId());
            if (status == null && !userTeam.getMangerId().equals(userId)) {
                map.put("code", "502");//不是管理员，没有权限
                return map;
            }
        }
        if (substitutionUid == null && (team.getType().equals(TeammateReEnum.TYPE_ADMIN.getValue())
                || team.getType().equals(TeammateReEnum.TYPE_AUXILIARY.getValue()))) {//管理者0|所属人1|参与者2
            map.put("code", "500");//需要有人替补
            return map;
        }
        if (team.getStatus().equals(TeammateReEnum.STATUS_DELETE.getValue())) {
            map.put("code", "503");//不能重复删除
            return map;
        }
        map.put("teammateRe", team);//参与处置的对象
        map.put("code", "200");//成功
        return map;
    }

    private void teamDel(Map code, Integer teamUserId, Integer userTeamId) {
        if (code == null) {
            return;
        }
        TeammateRe team = (TeammateRe) code.get("teammateRe");
        if (team == null) {
            return;
        }
        team.setStatus(TeammateReEnum.STATUS_DELETE.getValue());
        Integer result = teammateReMapper.updateByPrimaryKey(team);
        teammateReMapper.deleteByPrimaryKey(team.getId());
        OURelation ouRelation = new OURelation();
        ouRelation.setUserId(teamUserId);
        ouRelation.setEmployerId(userTeamId);
        ouRelation.setType(OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
        Integer result1 = ouRelationMapper.deleteByPrimaryKey(ouRelation);
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

    public UserTeam getTeam(Integer objectId, Integer objectType, int userId) {
        ObjectTypeEnum objectTypeEnum = ObjectTypeEnum.getObjectTypeEnum(objectType);
        UserTeam lenderTeam;
        UserTeam assetTeam;
        UserTeam sourceTeam;
        LenderInfo lenderInfo;
        switch (objectTypeEnum) {
            case PAWN:
                PawnInfo pawnInfo = pawnInfoMapper.get(objectId);
                lenderTeam = userTeamMapper.getTeam(pawnInfo.getLenderId(), ObjectTypeEnum.LENDER.getValue(), userId);
                if (lenderTeam != null) {//返回借款人的team
                    return lenderTeam;
                }
                //查询上级资产包的team
                lenderInfo = lenderInfoMapper.get(pawnInfo.getLenderId());
                assetTeam = userTeamMapper.getTeam(lenderInfo.getAssetId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), userId);
                if (assetTeam != null) {
                    return assetTeam;
                }
                //// TODO: 16-10-8  查找资产源
                break;
            case IOU:
                IOUInfo iouInfo = iouInfoMapper.get(objectId);
                lenderTeam = userTeamMapper.getTeam(iouInfo.getLenderId(), ObjectTypeEnum.LENDER.getValue(), userId);
                if (lenderTeam != null) {//返回借款人的team
                    return lenderTeam;
                }
                //查询上级资产包的team
                lenderInfo = lenderInfoMapper.get(iouInfo.getLenderId());
                assetTeam = userTeamMapper.getTeam(lenderInfo.getAssetId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), userId);
                if (assetTeam != null) {
                    return assetTeam;
                }
                break;
            case LENDER:
                lenderTeam = userTeamMapper.getTeam(objectId, objectType, userId);
                if (lenderTeam != null) {//返回借款人的team
                    return lenderTeam;
                }
                //查询上级资产包的team
                lenderInfo = lenderInfoMapper.get(objectId);
                assetTeam = userTeamMapper.getTeam(lenderInfo.getAssetId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), userId);
                if (assetTeam != null) {
                    return assetTeam;
                }
                break;
            case ASSETPACKAGE:
                assetTeam = userTeamMapper.getTeam(objectId, ObjectTypeEnum.ASSETPACKAGE.getValue(), userId);
                if (assetTeam != null) {
                    return assetTeam;
                }
                break;
            case ASSETSOURCE:
                sourceTeam = userTeamMapper.getTeam(objectId, ObjectTypeEnum.ASSETSOURCE.getValue(), userId);
                if (sourceTeam != null) {
                    return sourceTeam;
                }
                break;
        }
        return null;
    }


}
