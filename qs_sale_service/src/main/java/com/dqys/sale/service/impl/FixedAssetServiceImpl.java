package com.dqys.sale.service.impl;

import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.core.base.SysProperty;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RandomUtil;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetBusiness;
import com.dqys.flowbusiness.service.dto.BusinessDto;
import com.dqys.flowbusiness.service.service.BusinessService;
import com.dqys.sale.orm.mapper.*;
import com.dqys.sale.orm.mapper.business.AssetUserReMapper;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.pojo.*;
import com.dqys.sale.orm.query.FixedAssetQuery;
import com.dqys.sale.service.constant.ObjectTypeEnum;
import com.dqys.sale.service.dto.FADto;
import com.dqys.sale.service.dto.FixedAssetDTO;
import com.dqys.sale.service.facade.FixedAssetService;
import com.dqys.sale.service.facade.TSaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Service
public class FixedAssetServiceImpl implements FixedAssetService {
    @Autowired
    private FixedAssetMapper fixedAssetMapper;
    @Autowired
    private AssetFileMapper assetFileMapper;
    @Autowired
    private DisposeMapper disposeMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private BusinessORelationMapper businessORelationMapper;
    @Autowired
    private LabelReMapper labelReMapper;
    @Autowired
    @Qualifier("saleBusinessService")
    private BusinessService businessService;
    @Autowired
    private TSaleUserService tSaleUserService;
    @Autowired
    private AssetUserReMapper assetUserReMapper;

    @Override
    public JsonResponse list(FixedAssetQuery fixedAssetQuery) {
        List<FixedAssetDTO> dtos = getFixedAssetDTOs(fixedAssetQuery);
        Map map = new HashMap<>();
        map.put("fixedAssetList", dtos);
        map.put("query", fixedAssetQuery);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse getLable(String name) {
        List<Label> list = labelMapper.getLableList(name);
        return JsonResponseTool.success(list);
    }

    @Override
    public JsonResponse fixedList(FixedAssetQuery fixedAssetQuery) {
        fixedAssetQuery.setPageCount(1);
        List<FixedAssetDTO> dtos = getFixedAssetDTOs(fixedAssetQuery);
        for (FixedAssetDTO dto : dtos) {
            FADto faDto = new FADto();
            FixedAsset asset = dto.getFixedAsset();
            if (asset != null) {
                transformToFADto(faDto, asset);
            }
            dto.setFaDto(faDto);
            dto.setFixedAsset(null);
        }
        Map map = new HashMap<>();
        map.put("fixedAssetList", dtos);
        map.put("query", fixedAssetQuery);
        return JsonResponseTool.success(map);
    }

    private void transformToFADto(FADto faDto, FixedAsset asset) {
        faDto.setAssetType(asset.getAssetType());
        String address = "";
        if (asset.getProvince() != null && AreaTool.getAreaById(asset.getProvince()) != null) {
            address += AreaTool.getAreaById(asset.getProvince()).getLabel();
        }
        if (asset.getCity() != null && AreaTool.getAreaById(asset.getCity()) != null) {
            address += AreaTool.getAreaById(asset.getCity()).getLabel();
        }
        if (asset.getArea() != null && AreaTool.getAreaById(asset.getArea()) != null) {
            AreaTool.getAreaById(asset.getArea()).getLabel();
        }
        if (asset.getAddress() != null) {
            address += asset.getAddress();
        }
        faDto.setAddress(address);
        faDto.setCollectionNum(asset.getCollectionNum());
        faDto.setDisposeNum(asset.getDisposeNum());
        faDto.setDisposeStatus(asset.getDisposeStatus());
        faDto.setEntrustBegintime(asset.getEntrustBegintime());
        faDto.setEntrustEndtime(asset.getEntrustEndtime());
        faDto.setFloor(asset.getFloor());
        faDto.setId(asset.getId());
        faDto.setNo(asset.getNo());
        faDto.setIsSpecial(asset.getIsSpecial());
        faDto.setOrientation(asset.getOrientation());
        faDto.setRighterType(asset.getRighterType());
        faDto.setTitle(asset.getTitle());
        faDto.setYear(asset.getYear());
        faDto.setSource(asset.getSource());
    }

    private List<FixedAssetDTO> getFixedAssetDTOs(FixedAssetQuery query) {
        query.setStartPage(query.getStartPage());
        query.setObjectType(ObjectTypeEnum.fixed_asset.getValue());
        List<FixedAsset> fixedAssetList = fixedAssetMapper.fixedList(query);
        Integer count = fixedAssetList.size();
        query.setTotalCount(count);
        List<FixedAsset> fixedAssetListNew = new ArrayList<>();
        if (fixedAssetList != null && fixedAssetList.size() > 0) {
            int size = query.getStartPage() + query.getPageCount();
            if (size > query.getTotalCount()) {
                size = query.getTotalCount();
            }
            for (int i = query.getStartPage(); i < size; i++) {
                fixedAssetListNew.add(fixedAssetList.get(i));
            }
        }
        List<FixedAssetDTO> dtos = new ArrayList<>();
        for (FixedAsset entity : fixedAssetListNew) {
            FixedAssetDTO dto = new FixedAssetDTO();
            dto.setLabels(entity.getLabels());
            dto.setFixedAsset(entity);
            dto.setAssetFiles(entity.getAssetFiles());
            dto.setDisposes(entity.getDisposes());
            dto.setBusiness(entity.getBusiness());
            dto.setAssetUserRe(getAssetUserRe(entity.getId(), ObjectTypeEnum.fixed_asset.getValue()));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public AssetUserRe getAssetUserRe(Integer objectId, Integer objectType) {
        if (UserSession.getCurrent() == null || UserSession.getCurrent().getUserId() == 0) {
            return null;
        }
        List<AssetUserRe> list = getAssetUserRes(objectId, objectType);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public JsonResponse getDetail(Integer fixedAssetId) {
        FixedAsset fixedAsset = fixedAssetMapper.selectByPrimaryKey(fixedAssetId);
        List<AssetFile> assetFile = assetFileMapper.selectByAssetId(fixedAssetId, ObjectTypeEnum.fixed_asset.getValue());
        List<Dispose> disposes = disposeMapper.selectByAssetId(fixedAssetId, ObjectTypeEnum.fixed_asset.getValue());
        List<Label> labels = labelMapper.selectByAssetId(fixedAssetId, ObjectTypeEnum.fixed_asset.getValue());
        Map map = new HashMap<>();
        map.put("fixedAsset", fixedAsset);
        map.put("assetFile", assetFile);
        map.put("disposes", disposes);
        map.put("labels", labels);
        getDisposeAndCollect(fixedAssetId, ObjectTypeEnum.fixed_asset.getValue(), map);
        return JsonResponseTool.success(map);
    }

    @Override
    public void getDisposeAndCollect(Integer id, Integer type, Map map) {
        if (UserSession.getCurrent() != null && UserSession.getCurrent().getUserId() != 0) {
            List<AssetUserRe> list = getAssetUserRes(id, type);
            AssetUserRe re;
            if (list != null && list.size() > 0) {
                re = list.get(0);
                map.put("assetUserRe", re);
                map.put("result", "yes");
            } else {
                map.put("result", "no");
            }
        } else {
            map.put("result", "no_login");
        }
    }

    private List<AssetUserRe> getAssetUserRes(Integer id, Integer type) {
        AssetUserRe re = new AssetUserRe();
        re.setAssetId(id);
        re.setAssetType(type);
        re.setUserId(UserSession.getCurrent().getUserId());
        return assetUserReMapper.selectByUserRe(re);
    }

    @Override
    public JsonResponse addFixed_tx(FixedAssetDTO fixedAssetDTO) {
        if (CommonUtil.checkParam(fixedAssetDTO) || CommonUtil.checkParam(fixedAssetDTO.getFixedAsset())) {
            return JsonResponseTool.failure("请把信息填写完整");
        }
        FixedAsset fixedAsset = fixedAssetDTO.getFixedAsset();
        setEntity(fixedAsset);
        Integer id = fixedAssetMapper.insertSelective(fixedAsset);
        if (id == 0) {
            return JsonResponseTool.failure("添加失败");
        }
        createBusiness(fixedAsset.getId(), ObjectTypeEnum.fixed_asset.getValue());
        addOtherEntity_tx(fixedAssetDTO.getLabels(), fixedAssetDTO.getDisposes(), fixedAssetDTO.getAssetFiles(), fixedAsset.getId(), ObjectTypeEnum.fixed_asset.getValue());
        return JsonResponseTool.success(id);
    }

    private void setEntity(FixedAsset entity) {
        if (entity.getOperUser() == null) {
            entity.setOperUser(UserSession.getCurrent().getUserId());
        }
        if (entity.getNo() == null) {
            entity.setNo(RandomUtil.getCode(RandomUtil.FIXED_ASSET_CODE));//编号
        }

    }

    /**
     * 更具当前对象的id创建业务
     *
     * @param id 数据id
     * @return
     */
    @Override
    public Integer createBusiness(Integer id, Integer objectType) {
        BusinessDto businessDto = new BusinessDto();
        businessDto.setObjectId(id);
        businessDto.setObjcetType(objectType);
        Integer userId = UserSession.getCurrent().getUserId();
        SaleUser saleUser = tSaleUserService.getAdmin();
        if (userId == saleUser.getId().intValue()) {
            return businessService.createBusiness_tx(businessDto, userId, AssetBusiness.type, AssetBusiness.getBeAnnouncedAdmin().getLevel());//平台待发布
        }
        return businessService.createBusiness_tx(businessDto, userId, AssetBusiness.type, AssetBusiness.getBeAnnounced().getLevel());//用户待发布
    }

    @Override
    public void addOtherEntity_tx(List<Label> labels, List<Dispose> disposes, List<AssetFile> assetFiles, Integer id, Integer objectType) {
        //文件
        if (assetFiles != null && assetFiles.size() > 0) {
            assetFileMapper.deleteByPrimaryKeyObject(id, objectType);
            for (AssetFile file : assetFiles) {
                file.setAssetId(id);
                file.setAssetType(objectType);
                assetFileMapper.insertSelective(file);
            }
        }
        //处置方式
        if (disposes != null && disposes.size() > 0) {
            disposeMapper.deleteByPrimaryKeyObject(id, objectType);
            for (Dispose dis : disposes) {
                dis.setAssetType(objectType);
                dis.setAssetId(id);
                disposeMapper.insertSelective(dis);
            }
        }
        //标签
        if (labels != null && labels.size() > 0) {
            LabelRe labelRe = new LabelRe();
            labelReMapper.deleteByPrimaryKeyObject(id, objectType);
            for (Label label : labels) {
                if (label.getId() == null && label.getName() != null) {
                    //查询标签库是否存在。不存在就新增
                    List<Label> las = labelMapper.selectByLable(label);
                    if (las != null && las.size() > 0) {
                        label.setId(las.get(0).getId());
                    } else {
                        labelMapper.insertSelective(label);
                    }
                }
                if (label.getId() != null) {
                    labelRe.setAsssetId(id);
                    labelRe.setAssetType(objectType);
                    labelRe.setLabelId(label.getId());
                    labelReMapper.insertSelective(labelRe);
                }
            }
        }
    }

    @Override
    public JsonResponse updateFixed(FixedAssetDTO fixedAssetDTO) {
        if (CommonUtil.checkParam(fixedAssetDTO) || CommonUtil.checkParam(fixedAssetDTO.getFixedAsset())) {
            return JsonResponseTool.failure("请把信息填写完整");
        }
        FixedAsset fixedAsset = fixedAssetDTO.getFixedAsset();
        if (fixedAsset == null && fixedAsset.getId() == null) {
            return JsonResponseTool.failure("缺少必要数值");
        }
        Integer num = fixedAssetMapper.updateByPrimaryKeySelective(fixedAsset);
        if (num == 0) {
            return JsonResponseTool.failure("修改失败");
        }
        addOtherEntity_tx(fixedAssetDTO.getLabels(), fixedAssetDTO.getDisposes(), fixedAssetDTO.getAssetFiles(), fixedAsset.getId(), ObjectTypeEnum.fixed_asset.getValue());
        return JsonResponseTool.success(null);
    }


}
