package com.dqys.business.service.service.common;

import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.common.*;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.exception.bean.SourceEditException;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by Yvan on 16/8/19.
 * 资源 业务接口
 */
public interface SourceService {


    /**
     * 获取借款人的分类列表
     *
     * @param lenderId  借款人iD
     * @param estatesId 资产源iD
     * @param type      实勘1|证件合同0(默认)
     * @return
     */
    List<SelectDTOList> listNavigation(Integer lenderId, Integer estatesId, Integer type);

    /**
     * 获取借款人的分类列表(私有部分)
     * @param lenderId
     * @param estatesId
     * @param type
     * @return
     */
    List<SelectDTOList> listNavigationPerson(Integer lenderId, Integer estatesId, Integer type);

    /**
     * 获取借款人的分类列表(公共部分)
     * @param lenderId
     * @param estatesId
     * @param type
     * @return
     */
    List<SelectDTOList> listNavigationCommon(Integer lenderId, Integer estatesId, Integer type);


    /**
     * 获取借款人的分类列表(私有部分)
     * @param lenderId
     * @param estatesId
     * @param type
     * @param pid 父id
     * @return
     */
    List<CSourceNavDTO> listNavigation(Integer lenderId, Integer estatesId, Integer type,Integer pid);

    /**
     * 获取借款人的分类列表(公共部分)
     * @param lenderId
     * @param estatesId
     * @param type
     * @param pid 父id
     * @return
     */
    List<CSourceNavDTO> listNavigationCommon(Integer lenderId, Integer estatesId, Integer type,Integer pid);

    /**
     * 获取借款人的分类列表(公共部分)
     * @param lenderId
     * @param estatesId
     * @param type
     * @param pid 父id
     * @return
     */
    List<CSourceNavDTO> listNavigationPerson(Integer lenderId, Integer estatesId, Integer type,Integer pid);








    /**
     * 新增一个借款人的分类列表
     *
     * @param sourceNavigation
     * @return
     */
    JsonResponse addNavigation(SourceNavigation sourceNavigation);

    /**
     * 删除一个分类列表
     *
     * @param navId
     * @return
     */
    JsonResponse deleteNavigation(Integer navId);

    /**
     * 新增一条资源数据
     *
     * @param sourceInfoDTO
     * @return
     */
    JsonResponse addSource(SourceInfoDTO sourceInfoDTO);

    /**
     * 新增一条资源数据
     * @param sourceInfoDTO
     * @return
     */
     JsonResponse c_addSource(CSourceInfoDTO sourceInfoDTO);

    /**
     * 根据分类列表获取数据
     *
     * @param navId
     * @param lenderId
     * @return
     */
    SourceInfoDTO getSource(Integer navId, Integer lenderId, Integer estatesId);

    /**
     * 根据资源信息修改
     *
     * @param sourceInfoDTO
     * @return
     */
    JsonResponse updateSource(SourceInfoDTO sourceInfoDTO);

    /**
     * 得到重新设置后的ｎａｖｉｄ关联的所有可选内容
     * @param dto
     * @return
     */
    SelectDtoMap resetAndGetNewALL(NavUnviewDTO dto);

    /**
     * 是否具有资料实勘的权限
     * @param navId
     * @param lenderId
     * @param estatesId
     * @param userId
     * @return
     */
    boolean hasSourceAuth(Integer navId,Integer lenderId,Integer estatesId,Integer userId);

    //    /**
//     * 过滤掉当前用户不可见的分类
//     * @param list
//     * @param userSession
//     */
//    public void sourceNavigationFilter(List<SourceNavigation>  list, UserSession userSession){
//
//    }
    JsonResponse getSourceType(Integer navId, Integer objectId, Integer objectType);

    void renameSource (SourceEditDto sourceEditDto)throws SourceEditException;

    void delSource(SourceDelDTO sourceDelDTO) throws SourceEditException;
}
