package com.dqys.business.service.utils.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.dto.followUp.FollowUpSourceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yan on 16-8-15.
 */
public class FollowUpUtil {
    public static FollowUpMessage toFollowUpMessage(FollowUpMessageDTO followUpMessageDTO){
        FollowUpMessage followUpMessage= new FollowUpMessage();
        followUpMessage.setObjectId(followUpMessageDTO.getObjectId());
        followUpMessage.setContent(followUpMessageDTO.getContent());
        followUpMessage.setObjectType(followUpMessageDTO.getObjectType());
        followUpMessage.setLiquidateStage(followUpMessageDTO.getLiquidateStage());
        if(null!=followUpMessageDTO.getSecondLiquidateStage()){
            followUpMessage.setSecondLiquidateStage(followUpMessageDTO.getSecondLiquidateStage());
        }
        //// TODO: 16-11-18 为了保证前端没有id传入也能显示展示修改成如下
        //原来
//        if(null!=followUpMessageDTO.getSecondObjectId()){
//            followUpMessage.setSecondObjectType(followUpMessageDTO.getSecondObjectType());
//            followUpMessage.setSecondObjectId(followUpMessageDTO.getSecondObjectId());
//        }
        //改后
        if(null!=followUpMessageDTO.getSecondObjectId()){
            followUpMessage.setSecondObjectId(followUpMessageDTO.getSecondObjectId());
        }
        if(null!=followUpMessageDTO.getSecondObjectType()){
            followUpMessage.setSecondObjectType(followUpMessageDTO.getSecondObjectType());
        }

        return followUpMessage;
    }
    public static FollowUpSource toFollowUpSource(FollowUpSourceDTO followUpSourceDTO){
        FollowUpSource followUpSource = new FollowUpSource();
        followUpSource.setPathFilename(followUpSourceDTO.getPathFilename());
        followUpSource.setShowFilename(followUpSourceDTO.getShowFilename());
        followUpSource.setFollowUpMessageId(followUpSourceDTO.getFollowUpMessageId());
        followUpSource.setType(followUpSourceDTO.getType());
        followUpSource.setObjectType(followUpSourceDTO.getObjectType());
        followUpSource.setObjectId(followUpSourceDTO.getObjectId());
        return followUpSource;
    }
    public static List<FollowUpSourceDTO> toFollowUpSourceDTOList(List<FollowUpSource> followUpSourceList){
        List<FollowUpSourceDTO> followUpSourceDTOList = new ArrayList<>();
        for(FollowUpSource followUpSource : followUpSourceList){
            FollowUpSourceDTO followUpSourceDTO = new FollowUpSourceDTO();
            followUpSourceDTO.setPathFilename(followUpSource.getPathFilename());
            followUpSourceDTO.setShowFilename(followUpSource.getShowFilename());
            followUpSourceDTO.setFollowUpMessageId(followUpSource.getFollowUpMessageId());
            followUpSourceDTO.setObjectType(followUpSource.getObjectType());
            followUpSourceDTO.setObjectId(followUpSource.getObjectId());
            followUpSourceDTO.setPid(followUpSource.getPid());
            followUpSourceDTOList.add(followUpSourceDTO);
        }
        return followUpSourceDTOList;
    }
}
