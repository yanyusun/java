package com.dqys.business.service.utils.followUp;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.pojo.followUp.FollowUpObject;
import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.service.dto.followUp.CFollowUpMessageDTO;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.dto.followUp.FollowUpSourceDTO;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.DateFormatTool;
import com.dqys.core.utils.FileTool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yan on 16-8-15.
 */
public class FollowUpUtil {
    public static FollowUpMessage toFollowUpMessage(FollowUpMessageDTO followUpMessageDTO) {
        FollowUpMessage followUpMessage = new FollowUpMessage();
        followUpMessage.setObjectId(followUpMessageDTO.getObjectId());
        followUpMessage.setContent(followUpMessageDTO.getContent());
        followUpMessage.setObjectType(followUpMessageDTO.getObjectType());
        followUpMessage.setLiquidateStage(followUpMessageDTO.getLiquidateStage());
        if (null != followUpMessageDTO.getSecondLiquidateStage()) {
            followUpMessage.setSecondLiquidateStage(followUpMessageDTO.getSecondLiquidateStage());
        }
        //// TODO: 16-11-18 为了保证前端没有id传入也能显示展示修改成如下
        //原来
//        if(null!=followUpMessageDTO.getSecondObjectId()){
//            followUpMessage.setSecondObjectType(followUpMessageDTO.getSecondObjectType());
//            followUpMessage.setSecondObjectId(followUpMessageDTO.getSecondObjectId());
//        }
        //改后
        if (null != followUpMessageDTO.getSecondObjectId()) {
            followUpMessage.setSecondObjectId(followUpMessageDTO.getSecondObjectId());
        }
        if (null != followUpMessageDTO.getSecondObjectType()) {
            followUpMessage.setSecondObjectType(followUpMessageDTO.getSecondObjectType());
        }

        return followUpMessage;
    }

    public static FollowUpSource toFollowUpSource(FollowUpSourceDTO followUpSourceDTO) {
        FollowUpSource followUpSource = new FollowUpSource();
        followUpSource.setPathFilename(followUpSourceDTO.getFilePathName());
        followUpSource.setShowFilename(followUpSourceDTO.getName());
        followUpSource.setFollowUpMessageId(followUpSourceDTO.getFollowUpMessageId());
        followUpSource.setType(followUpSourceDTO.getType());
        followUpSource.setObjectType(followUpSourceDTO.getObjectType());
        followUpSource.setObjectId(followUpSourceDTO.getObjectId());
        return followUpSource;
    }

    public static FollowUpSourceDTO toFollowUpSourceCDetial(FollowUpSource followUpSource) {
        FollowUpSourceDTO followUpSourceDTO = new FollowUpSourceDTO();
        String pathFileName = followUpSource.getPathFilename();
        followUpSourceDTO.setFilePathName(pathFileName);
        followUpSourceDTO.setName(followUpSource.getShowFilename());
        followUpSourceDTO.setDate(DateFormatTool.format(followUpSource.getCreateAt(), DateFormatTool.DATE_FORMAT_19));
        File file = FileTool.getFile(pathFileName, false);
        String size = FileTool.convertFileSize(file.length());
        followUpSourceDTO.setSize(size);
        //file
        return followUpSourceDTO;
    }


    public static List<FollowUpSourceDTO> toFollowUpSourceDTOList(List<FollowUpSource> followUpSourceList) {
        List<FollowUpSourceDTO> followUpSourceDTOList = new ArrayList<>();
        for (FollowUpSource followUpSource : followUpSourceList) {
            FollowUpSourceDTO followUpSourceDTO = new FollowUpSourceDTO();
            followUpSourceDTO.setId(followUpSource.getId());
            followUpSourceDTO.setFilePathName(followUpSource.getPathFilename());
            followUpSourceDTO.setName(followUpSource.getShowFilename());
            followUpSourceDTO.setFollowUpMessageId(followUpSource.getFollowUpMessageId());
            followUpSourceDTO.setObjectType(followUpSource.getObjectType());
            followUpSourceDTO.setObjectId(followUpSource.getObjectId());
            followUpSourceDTO.setPid(followUpSource.getPid());
            followUpSourceDTO.setDate(DateFormatTool.format(followUpSource.getCreateAt(), DateFormatTool.DATE_FORMAT_19));
            String pathFileName = followUpSource.getPathFilename();
            if (pathFileName != null) {
                File file = FileTool.getFile(pathFileName, false);
                String size = FileTool.convertFileSize(file.length());
                followUpSourceDTO.setSize(size);
            }
            followUpSourceDTOList.add(followUpSourceDTO);
        }
        return followUpSourceDTOList;
    }

    public static CFollowUpMessageDTO toCFollowUpMessageDTO(FollowUpMessage followUpMessage, int userId) {
        CFollowUpMessageDTO cFollowUpMessageDTO = new CFollowUpMessageDTO();
        cFollowUpMessageDTO.setId(followUpMessage.getId());
        cFollowUpMessageDTO.setCreateAt(followUpMessage.getCreateAt());
        cFollowUpMessageDTO.setObjectId(followUpMessage.getObjectId());
        cFollowUpMessageDTO.setObjectType(followUpMessage.getObjectType());
        cFollowUpMessageDTO.setSecondObjectId(followUpMessage.getSecondObjectId());
        cFollowUpMessageDTO.setSecondObjectType(followUpMessage.getSecondObjectType());
        cFollowUpMessageDTO.setLiquidateStage(followUpMessage.getLiquidateStage());
        cFollowUpMessageDTO.setSecondLiquidateStage(followUpMessage.getSecondLiquidateStage());
        TUserInfo userInfo = followUpMessage.getUserInfo();
        if (userInfo != null) {
            cFollowUpMessageDTO.setUserId(userInfo.getId());
            cFollowUpMessageDTO.setUsername(userInfo.getUserName());
            cFollowUpMessageDTO.setUserType(userInfo.getUserType());
            cFollowUpMessageDTO.setRoleId(userInfo.getRoleId());
            cFollowUpMessageDTO.setAvg(userInfo.getAvg());
            if (followUpMessage.getUserId() == userId) {
                cFollowUpMessageDTO.setMyself(true);
            }
        }
        TCompanyInfo companyInfo = followUpMessage.getCompanyInfo();
        if (companyInfo != null) {
            cFollowUpMessageDTO.setCompanyId(companyInfo.getId());
            cFollowUpMessageDTO.setCompanyName(companyInfo.getCompanyName());
        }
        if (followUpMessage.getFileList() != null) {
            cFollowUpMessageDTO.setFileList(followUpMessage.getFileList());
        }
        cFollowUpMessageDTO.setContent(followUpMessage.getContent());

        return cFollowUpMessageDTO;
    }

    public static List<CFollowUpMessageDTO> toCFollowUpMessageDTOList(List<FollowUpMessage> followUpMessageList) {
        Integer userId = UserSession.getCurrent().getUserId();
        List<CFollowUpMessageDTO> cFollowUpMessageDTOList = new ArrayList<>();
        for (FollowUpMessage followUpMessage : followUpMessageList) {
            CFollowUpMessageDTO cFollowUpMessageDTO = toCFollowUpMessageDTO(followUpMessage, userId);
            cFollowUpMessageDTOList.add(cFollowUpMessageDTO);
        }
        return cFollowUpMessageDTOList;
    }

    public static CFollowUpMessageDTO toIndexFollowUpMessageDTO(FollowUpObject followUpObject, int userId) {
        List<FollowUpMessage> list = followUpObject.getFollowUpMessages();
        FollowUpMessage followUpMessage = list.get(0);
        CFollowUpMessageDTO cFollowUpMessageDTO = toCFollowUpMessageDTO(followUpMessage, userId);
        cFollowUpMessageDTO.setUnreadNum(list.size());
        return cFollowUpMessageDTO;
    }

    public static List<CFollowUpMessageDTO> toIndexCFollowUpMessageDTOList(List<FollowUpObject> followUpObjectList) {
        Integer userId = UserSession.getCurrent().getUserId();
        List<CFollowUpMessageDTO> cFollowUpMessageDTOList = new ArrayList<>();
        for (FollowUpObject followUpMessage : followUpObjectList) {
            CFollowUpMessageDTO cFollowUpMessageDTO = toIndexFollowUpMessageDTO(followUpMessage, userId);
            cFollowUpMessageDTOList.add(cFollowUpMessageDTO);
        }
        return cFollowUpMessageDTOList;
    }
}
