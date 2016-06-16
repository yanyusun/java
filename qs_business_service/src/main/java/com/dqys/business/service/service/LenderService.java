package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.query.asset.IOUQuery;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.business.orm.query.asset.PawnQuery;

import java.util.List;

/**
 * Created by Yvan on 16/6/12.
 */
public interface LenderService {

    /********************************************
     * 借款人基础信息
     ********************************************/

    /**
     * 删除借款人基础信息
     * @param id
     * @return
     */
    Integer deleteLenderInfo(Integer id);

    /**
     * 增加借款人基础信息
     * @param contactInfo
     * @return
     */
    Integer addLenderInfo(ContactInfo contactInfo);

    /**
     * 修改借款人基础信息
     * @param contactInfo
     * @return
     */
    Integer updateLenderInfo(ContactInfo contactInfo);

    /**
     * 根据ID获取借款联系人基础信息
     * @param id
     * @return
     */
    ContactInfo getLenderInfo(Integer id);

    /**
     * 条件获取借款联系人基础信息
     * @param lenderQuery
     * @return
     */
    List<ContactInfo> queryListLender(LenderQuery lenderQuery);

    /********************************************
     * 借款人关系
     ********************************************/

    /**
     * 删除借款人关系
     * @param id
     * @return
     */
    Integer deleteLenderRelation(Integer id);

    /**
     * 增加借款人关系
     * @param lenderInfo
     * @return
     */
    Integer addLenderRelation(LenderInfo lenderInfo);

    /**
     * 修改借款人关系
     * @param lenderInfo
     * @return
     */
    Integer updateLenderRelation(LenderInfo lenderInfo);

    /**
     * 根据ID获取借款人关系
     * @param id
     * @return
     */
    LenderInfo getLenderRelation(Integer id);

    /********************************************
     * 借据信息
     ********************************************/

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteIOUInfo(Integer id);

    /**
     * 增加
     * @param IOUInfo
     * @return
     */
    Integer addIOUInfo(IOUInfo IOUInfo, String name);

    /**
     * 修改
     * @param IOUInfo
     * @return
     */
    Integer updateIOUInfo(IOUInfo IOUInfo);

    /**
     * 根据ID获取借款人基础信息
     * @param id
     * @return
     */
    IOUInfo getIOUInfo(Integer id);

    /**
     * 统计所有的借据
     * @return
     */
    Integer countIOU();

    /**
     * 查询借款人下的所有借据
     * @param lenderId
     * @return
     */
    List<IOUInfo> listIOUByLenderId(Integer lenderId);

    /**
     * 多条件查询借据
     * @param IOUQuery
     * @return
     */
    List<IOUInfo> queryListIOU(IOUQuery IOUQuery);


    /********************************************
     * 抵押物信息
     ********************************************/

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deletePawn(Integer id);

    /**
     * 增加抵押物
     * @param pawnInfo
     * @return
     */
    Integer addPawn(PawnInfo pawnInfo);

    /**
     * 修改抵押物
     * @param pawnInfo
     * @return
     */
    Integer updatePawn(PawnInfo pawnInfo);

    /**
     * 根据ID获取借款人基础信息
     * @param id
     * @return
     */
    PawnInfo getPawn(Integer id);

    /**
     * 统计抵押物信息
     * @return
     */
    Integer countPawn();

    /**
     * 根据借款人查看抵押物
     * @param lenderId
     * @return
     */
    List<PawnInfo> listPawnByLenderId(Integer lenderId);

    /**
     * 多条件查询抵押物
     * @param pawnQuery
     * @return
     */
    List<PawnInfo> queryListPawn(PawnQuery pawnQuery);
}
